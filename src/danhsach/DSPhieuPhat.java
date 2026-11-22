package danhsach;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Scanner;
import model.ChiTietPhieuPhat;
import model.PhieuPhat;

public class DSPhieuPhat implements DanhSach {
    private int n;
    private PhieuPhat[] dsPhieuPhat;
    Scanner sc = new Scanner(System.in);

    public DSPhieuPhat(PhieuPhat[] dsPhieuPhat, int n) {
        this.dsPhieuPhat = dsPhieuPhat;
        this.n = n;
    }

    public DSPhieuPhat() {
        dsPhieuPhat = new PhieuPhat[100];
        this.n = 0;
    }

    public int getN() { return n; }
    public void setN(int n) { this.n = n; }
    public PhieuPhat getPhieuPhat(int vt) {
        if (vt > -1 && vt < n) return this.dsPhieuPhat[vt];
        return null;
    }
    public PhieuPhat[] getDanhSachPP() { return this.dsPhieuPhat; }

    public void napChiTiet(DSChiTietPhieuPhat dsCTPP) {
        for (int i = 0; i < n; i++) {
            if (dsPhieuPhat[i] != null) dsPhieuPhat[i].clearChiTiet();
        }
        if (dsCTPP == null) return;
        for (int i = 0; i < dsCTPP.getSoLuong(); i++) {
            ChiTietPhieuPhat ct = dsCTPP.getChiTiet(i);
            if (ct != null) {
                PhieuPhat pp = TimtheoMaPP(ct.getMaPP());
                if (pp != null) pp.themChiTiet(ct);
            }
        }
    }

    public void PrintHeader() {
        System.out.println("+------------+------------+------------+--------------+------------------------------+");
        System.out.printf("| %-10s | %-10s | %-10s | %-12s | %-28s |%n",
                "MA PP", "MA PM", "NGAY PHAT", "SO TIEN PHAT", "CAC CHI TIET");
        System.out.println("+------------+------------+------------+--------------+------------------------------+");
    }

    public void PrintFooter() {
        System.out.println("+------------+------------+------------+--------------+------------------------------+");
    }

    public void Input(DSPhieuMuon dspm) {
        System.out.print("Nhap so luong phieu phat: ");
        int sl = Integer.parseInt(sc.nextLine().trim());
        dsPhieuPhat = new PhieuPhat[Math.max(sl, 1)];
        this.n = 0;
        for (int i = 0; i < sl; i++) {
            System.out.println("Nhap phieu phat thu " + (i + 1) + ":");
            Them(dspm);
        }
    }

    public void Them(DSPhieuMuon dspm) {
        System.out.println("Tien hanh nhap thong tin phieu phat");
        PhieuPhat pp = new PhieuPhat();
        pp.Input(this, dspm);
        Them(pp);
    }

    public void Them(PhieuPhat pp) {
        if (n >= dsPhieuPhat.length) {
            dsPhieuPhat = Arrays.copyOf(dsPhieuPhat, dsPhieuPhat.length + 1);
        }
        dsPhieuPhat[n++] = pp;
    }

    @Override
    public void Output() {
        System.out.println("DANH SACH PHIEU PHAT");
        this.PrintHeader();
        for (int i = 0; i < n; i++) {
            if (dsPhieuPhat[i] != null) dsPhieuPhat[i].Output();
        }
        if (n == 0) System.out.println("DANH SACH RONG");
        this.PrintFooter();
    }

    public int Timvitri(String maPP) {
        for (int i = 0; i < n; i++) {
            if (dsPhieuPhat[i] != null && dsPhieuPhat[i].getMaPP().equalsIgnoreCase(maPP)) return i;
        }
        return -1;
    }

    public PhieuPhat TimtheoMaPP(String maPP) {
        int vt = Timvitri(maPP);
        return (vt != -1) ? dsPhieuPhat[vt] : null;
    }

    public void TimtheoMaPP() {
        System.out.print("Nhap ma phieu phat can tim: ");
        String mapp = sc.nextLine().trim();
        int vt = Timvitri(mapp);
        if (vt == -1) {
            System.out.println("Khong tim thay phieu phat ma: " + mapp);
            return;
        }
        this.PrintHeader();
        dsPhieuPhat[vt].Output();
        this.PrintFooter();
        dsPhieuPhat[vt].xuatChiTiet();
    }

    public PhieuPhat[] TimtheoMaPM(String maPM) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (dsPhieuPhat[i] != null && dsPhieuPhat[i].getMaPM().equalsIgnoreCase(maPM)) count++;
        }
        PhieuPhat[] ds = new PhieuPhat[count];
        int index = 0;
        for (int i = 0; i < n; i++) {
            if (dsPhieuPhat[i] != null && dsPhieuPhat[i].getMaPM().equalsIgnoreCase(maPM)) {
                ds[index++] = dsPhieuPhat[i];
            }
        }
        return ds;
    }

    public void TimtheoMaPM() {
        System.out.print("Nhap ma phieu muon: ");
        String maPM = sc.nextLine().trim();
        PhieuPhat[] ds = TimtheoMaPM(maPM);
        if (ds.length == 0) {
            System.out.println("Ma PM " + maPM + " khong co phieu phat.");
            return;
        }
        System.out.println("DANH SACH PHIEU PHAT (MA PHIEU MUON: " + maPM + ")");
        this.PrintHeader();
        for (PhieuPhat pp : ds) pp.Output();
        this.PrintFooter();
    }

    public void TimTheoNgayPhat(LocalDate ngay) {
        boolean found = false;
        for (int i = 0; i < this.n; i++) {
            if (dsPhieuPhat[i] != null && dsPhieuPhat[i].getNgayPhat() != null
                    && dsPhieuPhat[i].getNgayPhat().equals(ngay)) {
                if (!found) {
                    this.PrintHeader();
                    found = true;
                }
                dsPhieuPhat[i].Output();
            }
        }
        if (found) this.PrintFooter();
        else System.out.println("Khong tim thay!");
    }

    public void TimTheoNgayPhat() {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            System.out.print("Nhap ngay phat (dd/MM/yyyy): ");
            TimTheoNgayPhat(LocalDate.parse(sc.nextLine().trim(), f));
        } catch (DateTimeParseException e) {
            System.out.println("Ngay khong hop le!");
        }
    }

    public boolean Xoa(String maPP) {
        int viTri = Timvitri(maPP);
        if (viTri == -1) return false;
        for (int i = viTri; i < n - 1; i++) dsPhieuPhat[i] = dsPhieuPhat[i + 1];
        dsPhieuPhat[--n] = null;
        return true;
    }

    public void Xoa() {
        System.out.print("Nhap ma phieu phat can xoa: ");
        String maPP = sc.nextLine().trim();
        if (Xoa(maPP)) System.out.println("Xoa phieu phat thanh cong!");
        else System.out.println("Khong tim thay phieu phat de xoa!");
    }

    public void Sua() {
        System.out.print("Nhap ma phieu phat can sua: ");
        String maPP = sc.nextLine().trim();
        int viTri = Timvitri(maPP);
        if (viTri == -1) {
            System.out.println("Khong tim thay phieu phat de sua!");
            return;
        }
        int chon;
        do {
            PhieuPhat p = dsPhieuPhat[viTri];
            System.out.println();
            System.out.println("+-------------------------------------------+");
            System.out.println("|        MENU SUA THONG TIN PHIEU PHAT      |");
            System.out.println("+-------------------------------------------+");
            System.out.println("| 1) Sua ma phieu muon.                     |");
            System.out.println("| 2) Sua ngay lap phieu phat.               |");
            System.out.println("| 3) Sua tong tien phat.                    |");
            System.out.println("| 0) Thoat.                                 |");
            System.out.println("+-------------------------------------------+");
            try {
                System.out.print("Chon: ");
                chon = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                chon = -1;
            }
            switch (chon) {
                case 1 -> {
                    System.out.print("Nhap ma phieu muon moi: ");
                    p.setMaPM(sc.nextLine().trim());
                    System.out.println("Da sua ma phieu muon.");
                }
                case 2 -> {
                    DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    try {
                        System.out.print("Nhap ngay phat moi (dd/MM/yyyy): ");
                        p.setNgayPhat(LocalDate.parse(sc.nextLine().trim(), f));
                        System.out.println("Da sua ngay lap phieu phat.");
                    } catch (DateTimeParseException e) {
                        System.out.println("Ngay khong dung dinh dang dd/MM/yyyy!");
                    }
                }
                case 3 -> {
                    try {
                        System.out.print("Nhap tong tien phat moi: ");
                        p.setTongSoTienPhat(Double.parseDouble(sc.nextLine().trim()));
                        System.out.println("Da sua tong tien phat.");
                    } catch (NumberFormatException e) {
                        System.out.println("So tien khong hop le!");
                    }
                }
                case 0 -> System.out.println("Hoan tat sua phieu phat.");
                default -> System.out.println("Lua chon khong hop le!");
            }
        } while (chon != 0);
    }

    public void ThongKePPtheoMaPM(String maPM) {
        PhieuPhat[] ds = TimtheoMaPM(maPM);
        if (ds.length == 0) {
            System.out.println("Tong so phieu phat theo ma phieu muon " + maPM + ": 0");
            return;
        }
        System.out.println("THONG KE PHIEU PHAT CO MA PM: " + maPM + " (" + ds.length + " phieu)");
        this.PrintHeader();
        for (PhieuPhat pp : ds) pp.Output();
        this.PrintFooter();
    }

    public void ThongKePPtheoMaPM() {
        System.out.print("Nhap ma phieu muon: ");
        ThongKePPtheoMaPM(sc.nextLine().trim());
    }

    public void ThongKeTongSoPP() { System.out.println("Tong so phieu phat: " + n); }

    public double TongTienPhat() {
        double tong = 0;
        for (int i = 0; i < n; i++) {
            if (dsPhieuPhat[i] != null) tong += dsPhieuPhat[i].getTongSoTienPhat();
        }
        return tong;
    }

    public void ThongKeTongTienPhat() { System.out.printf("Tong tien phat: %.0f VND%n", TongTienPhat()); }

    public PhieuPhat TimPhieuPhatMax() {
        if (n == 0) return null;
        PhieuPhat max = dsPhieuPhat[0];
        for (int i = 1; i < n; i++) {
            if (dsPhieuPhat[i].getTongSoTienPhat() > max.getTongSoTienPhat()) max = dsPhieuPhat[i];
        }
        return max;
    }

    public void ThongKePhieuPhatMax() {
        PhieuPhat max = TimPhieuPhatMax();
        if (max == null) {
            System.out.println("Danh sach phieu phat rong!!");
            return;
        }
        System.out.println("PHIEU PHAT CO TIEN PHAT LON NHAT");
        this.PrintHeader();
        max.Output();
        this.PrintFooter();
        max.xuatChiTiet();
    }

    public void ThongKeTheoKhoangNgay(LocalDate tuNgay, LocalDate denNgay) {
        boolean found = false;
        System.out.println("THONG KE PHIEU PHAT TU " + tuNgay + " DEN " + denNgay);
        this.PrintHeader();
        for (int i = 0; i < n; i++) {
            LocalDate ngay = dsPhieuPhat[i].getNgayPhat();
            if (ngay != null && !ngay.isBefore(tuNgay) && !ngay.isAfter(denNgay)) {
                dsPhieuPhat[i].Output();
                found = true;
            }
        }
        this.PrintFooter();
        if (!found) System.out.println("Khong co phieu phat nao trong khoang nay!");
    }

    public void ThongKeTheoKhoangNgay() {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            System.out.print("Nhap tu ngay (dd/MM/yyyy): ");
            LocalDate tuNgay = LocalDate.parse(sc.nextLine().trim(), f);
            System.out.print("Nhap den ngay (dd/MM/yyyy): ");
            LocalDate denNgay = LocalDate.parse(sc.nextLine().trim(), f);
            ThongKeTheoKhoangNgay(tuNgay, denNgay);
        } catch (DateTimeParseException e) {
            System.out.println("Ngay khong hop le!");
        }
    }

    public void ThongKeTongHop() {
        System.out.println("+-----------------------------------------+");
        System.out.println("|           THONG KE PHIEU PHAT           |");
        System.out.println("+-----------------------------------------+");
        System.out.printf("| Tong so phieu phat : %-18d |%n", n);
        System.out.printf("| Tong tien phat     : %-18.0f |%n", TongTienPhat());
        System.out.println("+-----------------------------------------+");
    }

    @Override
    public void ReadFile() {
        int count = 0;
        try (Scanner fileScanner = new Scanner(new FileInputStream("data/PhieuPhat.txt"))) {
            DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            this.dsPhieuPhat = new PhieuPhat[0];
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                if (line.isEmpty()) continue;
                String[] data = line.split(",");
                if (data.length >= 4) {
                    dsPhieuPhat = Arrays.copyOf(dsPhieuPhat, count + 1);
                    dsPhieuPhat[count] = new PhieuPhat();
                    dsPhieuPhat[count].setMaPP(data[0].trim());
                    dsPhieuPhat[count].setMaPM(data[1].trim());
                    try {
                        dsPhieuPhat[count].setNgayPhat(LocalDate.parse(data[2].trim(), f));
                    } catch (DateTimeParseException e) {
                        dsPhieuPhat[count].setNgayPhat(null);
                    }
                    dsPhieuPhat[count].setTongSoTienPhat(Double.parseDouble(data[3].trim()));
                    count++;
                }
            }
            this.n = count;
        } catch (FileNotFoundException e) {
            System.out.println("Khong tim thay file data/PhieuPhat.txt!");
        }
    }

    @Override
    public void WriteFile() {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try (PrintWriter writer = new PrintWriter(new FileOutputStream("data/PhieuPhat.txt"))) {
            for (int i = 0; i < n; i++) {
                if (dsPhieuPhat[i] != null) {
                    String ngayPhat = dsPhieuPhat[i].getNgayPhat() != null
                            ? dsPhieuPhat[i].getNgayPhat().format(f) : "";
                    writer.println(
                            dsPhieuPhat[i].getMaPP() + ","
                            + dsPhieuPhat[i].getMaPM() + ","
                            + ngayPhat + ","
                            + (long) dsPhieuPhat[i].getTongSoTienPhat());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Khong tim thay file data/PhieuPhat.txt!");
        }
    }
}
