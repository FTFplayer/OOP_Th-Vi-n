package danhsach;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import model.ChiTietPhieuMuon;
import model.ChiTietPhieuPhat;

public class DSChiTietPhieuPhat implements DanhSach {
    private ChiTietPhieuPhat[] dsChiTiet;
    private int n;
    Scanner sc = new Scanner(System.in);

    public DSChiTietPhieuPhat() {
        this.dsChiTiet = new ChiTietPhieuPhat[100];
        this.n = 0;
    }

    public DSChiTietPhieuPhat(int length) {
        this.n = 0;
        this.dsChiTiet = new ChiTietPhieuPhat[Math.max(length, 1)];
    }

    public DSChiTietPhieuPhat(ChiTietPhieuPhat[] dsChiTiet, int soLuong) {
        this.dsChiTiet = dsChiTiet;
        this.n = soLuong;
    }

    public ChiTietPhieuPhat getChiTiet(int index) {
        if (index < 0 || index >= n) return null;
        return dsChiTiet[index];
    }

    public int getSoLuong() { return n; }

    public String taoMaCTPPMoi() {
        int max = 0;
        for (int i = 0; i < n; i++) {
            if (dsChiTiet[i] != null && dsChiTiet[i].getMaCTPP() != null) {
                String digits = dsChiTiet[i].getMaCTPP().replaceAll("[^0-9]", "");
                if (!digits.isEmpty()) {
                    try {
                        int val = Integer.parseInt(digits);
                        if (val > max) max = val;
                    } catch (NumberFormatException ignored) {}
                }
            }
        }
        return String.format("CTPP%03d", max + 1);
    }

    public void PrintHeader() {
        System.out.println("+-----------+----------+-------------+------------+------------------------+-------------------------------------+");
        System.out.printf("| %-9s | %-8s | %-11s | %-10s | %-22s | %-35s |%n",
                "MA CTPP", "MA PP", "MA QUY DINH", "MA SACH", "TIEN PHAT", "LY DO CHI TIET");
        System.out.println("+-----------+----------+-------------+------------+------------------------+-------------------------------------+");
    }

    public void PrintFooter() {
        System.out.println("+-----------+----------+-------------+------------+------------------------+-------------------------------------+");
    }

    public void Input(String maPPCurrent, DSQuyDinhPhat listQD, DSBook dsBook) {
        System.out.print("Nhap so luong sach vi pham: ");
        int sl = Integer.parseInt(sc.nextLine().trim());
        dsChiTiet = new ChiTietPhieuPhat[Math.max(sl, 1)];
        this.n = 0;
        for (int i = 0; i < sl; i++) {
            System.out.println("Sach vi pham thu " + (i + 1) + ":");
            ChiTietPhieuPhat ct = new ChiTietPhieuPhat();
            ct.setMaCTPP(taoMaCTPPMoi());
            ct.Input(maPPCurrent, listQD, dsBook);
            Them(ct);
        }
    }

    @Override
    public void Output() {
        System.out.println("DANH SACH CHI TIET PHIEU PHAT:");
        PrintHeader();
        for (int i = 0; i < n; i++) {
            if (dsChiTiet[i] != null) dsChiTiet[i].Output();
        }
        PrintFooter();
    }

    public void Output(String maPP) {
        boolean found = false;
        System.out.println("DANH SACH CHI TIET THEO MA PP: " + maPP);
        System.out.println("+-----------+-------------+------------+------------------------+-------------------------------------+");
        System.out.printf("| %-9s | %-11s | %-10s | %-22s | %-35s |%n",
                "MA CTPP", "MA QUY DINH", "MA SACH", "TIEN PHAT", "LY DO CHI TIET");
        System.out.println("+-----------+-------------+------------+------------------------+-------------------------------------+");
        for (int i = 0; i < n; i++) {
            if (dsChiTiet[i] != null && dsChiTiet[i].getMaPP().equalsIgnoreCase(maPP)) {
                dsChiTiet[i].OutputTrongPhieu();
                found = true;
            }
        }
        System.out.println("+-----------+-------------+------------+------------------------+-------------------------------------+");
        if (!found) {
            System.out.println("Khong tim thay chi tiet nao cho ma PP: " + maPP);
        }
    }

    public void Them(DSQuyDinhPhat listQD, DSBook dsBook, String maPP) {
        ChiTietPhieuPhat ct = new ChiTietPhieuPhat();
        ct.setMaCTPP(taoMaCTPPMoi());
        ct.Input(maPP, listQD, dsBook);
        Them(ct);
        System.out.println("Them thanh cong!");
    }

    public void Them(ChiTietPhieuPhat ct) {
        if (ct == null) return;
        if (ct.getMaCTPP().isEmpty()) {
            ct.setMaCTPP(taoMaCTPPMoi());
        }
        if (n >= dsChiTiet.length) {
            dsChiTiet = Arrays.copyOf(dsChiTiet, dsChiTiet.length + 1);
        }
        dsChiTiet[n++] = ct;
    }

    public ChiTietPhieuPhat TimTheoMaCTPP(String maCTPP) {
        for (int i = 0; i < n; i++) {
            if (dsChiTiet[i] != null && dsChiTiet[i].getMaCTPP().equalsIgnoreCase(maCTPP)) {
                return dsChiTiet[i];
            }
        }
        return null;
    }

    public ChiTietPhieuPhat[] Tim(String maPP) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (dsChiTiet[i] != null && dsChiTiet[i].getMaPP().equalsIgnoreCase(maPP)) count++;
        }
        if (count == 0) return null;
        ChiTietPhieuPhat[] ketQua = new ChiTietPhieuPhat[count];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            if (dsChiTiet[i] != null && dsChiTiet[i].getMaPP().equalsIgnoreCase(maPP)) {
                ketQua[idx++] = dsChiTiet[i];
            }
        }
        return ketQua;
    }

    public void Tim() {
        System.out.print("Nhap ma phieu phat can tim: ");
        Output(sc.nextLine().trim());
    }

    public boolean XoaTheoMaCTPP(String maCTPP) {
        int idx = -1;
        for (int i = 0; i < n; i++) {
            if (dsChiTiet[i] != null && dsChiTiet[i].getMaCTPP().equalsIgnoreCase(maCTPP)) {
                idx = i;
                break;
            }
        }
        if (idx == -1) return false;
        for (int i = idx; i < n - 1; i++) dsChiTiet[i] = dsChiTiet[i + 1];
        dsChiTiet[--n] = null;
        return true;
    }

    public void XoaTheoMaPP(String maPP) {
        int i = 0;
        while (i < n) {
            if (dsChiTiet[i] != null && dsChiTiet[i].getMaPP().equalsIgnoreCase(maPP)) {
                for (int j = i; j < n - 1; j++) dsChiTiet[j] = dsChiTiet[j + 1];
                dsChiTiet[--n] = null;
            } else {
                i++;
            }
        }
    }

    public boolean Xoa(String maPP, String maSach) {
        int idx = -1;
        for (int i = 0; i < n; i++) {
            if (dsChiTiet[i] != null
                    && dsChiTiet[i].getMaPP().equalsIgnoreCase(maPP)
                    && dsChiTiet[i].getMaSach().equalsIgnoreCase(maSach)) {
                idx = i;
                break;
            }
        }
        if (idx == -1) return false;
        for (int i = idx; i < n - 1; i++) dsChiTiet[i] = dsChiTiet[i + 1];
        dsChiTiet[--n] = null;
        return true;
    }

    public void Xoa() {
        System.out.print("Nhap ma chi tiet phieu phat can xoa (VD: CTPP001): ");
        String maCTPP = sc.nextLine().trim();
        if (XoaTheoMaCTPP(maCTPP)) System.out.println("Xoa thanh cong!");
        else System.out.println("Khong tim thay chi tiet de xoa!");
    }

    private int laySoLuongMuonCuaSach(ChiTietPhieuMuon[] chiTietMuon, String maSach) {
        if (chiTietMuon == null) return -1;
        for (ChiTietPhieuMuon ct : chiTietMuon) {
            if (ct != null && ct.getMaSach().equalsIgnoreCase(maSach)) {
                return ct.getSoLuong();
            }
        }
        return -1;
    }

    private String chuoiSachTrongPhieuMuon(ChiTietPhieuMuon[] chiTietMuon) {
        if (chiTietMuon == null || chiTietMuon.length == 0) return "(Khong co du lieu phieu muon)";
        StringBuilder sb = new StringBuilder();
        for (ChiTietPhieuMuon ct : chiTietMuon) {
            if (ct != null) {
                if (sb.length() > 0) sb.append(", ");
                sb.append(ct.getMaSach()).append(" (").append(ct.getSoLuong()).append(" cuon)");
            }
        }
        return sb.toString();
    }

    private boolean biTrungSachVaQuyDinh(String maPP, String maLD, String maSach, String boQuaMaCTPP) {
        for (int i = 0; i < n; i++) {
            if (dsChiTiet[i] != null
                    && dsChiTiet[i].getMaPP().equalsIgnoreCase(maPP)
                    && dsChiTiet[i].getMaLD().equalsIgnoreCase(maLD)
                    && dsChiTiet[i].getMaSach().equalsIgnoreCase(maSach)
                    && !dsChiTiet[i].getMaCTPP().equalsIgnoreCase(boQuaMaCTPP)) {
                return true;
            }
        }
        return false;
    }

    private double tinhLaiTienPhat(ChiTietPhieuPhat ct, ChiTietPhieuMuon[] chiTietMuon,
            DSQuyDinhPhat listQD, DSBook dsBook) {
        if (ct == null || listQD == null || dsBook == null) return 0;
        if ("QD01".equalsIgnoreCase(ct.getMaLD())) {
            int slMuon = laySoLuongMuonCuaSach(chiTietMuon, ct.getMaSach());
            if (slMuon <= 0) slMuon = 1;
            double muc1Cuon = listQD.TinhTienPhat("QD01", ct.getSoLuong(), ct.getMaSach(), dsBook);
            return Math.max(0, muc1Cuon) * slMuon;
        }
        double muc1Cuon = listQD.TinhTienPhat(ct.getMaLD(), 0, ct.getMaSach(), dsBook);
        return Math.max(0, muc1Cuon) * ct.getSoLuong();
    }

    // Sua chi tiet trong 1 phieu phat: Nhap MA CTPP roi sua 1 luot dong bo voi PM/PNH (co rang buoc chat)
    public boolean SuaTrongPhieu(String maPP, ChiTietPhieuMuon[] chiTietMuon, long soNgayTre,
            DSQuyDinhPhat listQD, DSBook dsBook) {
        System.out.print("Nhap ma chi tiet phieu phat can sua (VD: CTPP001): ");
        String maCTPP = sc.nextLine().trim();

        ChiTietPhieuPhat ctCanSua = null;
        for (int i = 0; i < n; i++) {
            if (dsChiTiet[i] != null
                    && dsChiTiet[i].getMaPP().equalsIgnoreCase(maPP)
                    && dsChiTiet[i].getMaCTPP().equalsIgnoreCase(maCTPP)) {
                ctCanSua = dsChiTiet[i];
                break;
            }
        }
        if (ctCanSua == null) {
            System.out.println("Khong tim thay chi tiet " + maCTPP + " trong phieu " + maPP + "!");
            return false;
        }

        boolean coDuLieuPM = (chiTietMuon != null && chiTietMuon.length > 0);
        System.out.println("-> Dang sua [" + ctCanSua.getMaCTPP() + "] | Hien tai: QD=" + ctCanSua.getMaLD()
                + " | Sach=" + ctCanSua.getMaSach()
                + " | Tien=" + ctCanSua.getTienPhatKemSoLuong()
                + " | Ly do=" + ctCanSua.getLD());
        if (coDuLieuPM) {
            System.out.println("-> Sach thuoc phieu muon: " + chuoiSachTrongPhieuMuon(chiTietMuon));
        }

        // 1. Nhap ma sach moi (Enter de giu nguyen)
        String maSachMoi;
        while (true) {
            System.out.print("Nhap ma sach moi (Enter de giu nguyen [" + ctCanSua.getMaSach() + "]): ");
            String inp = sc.nextLine().trim();
            if (inp.isEmpty()) {
                maSachMoi = ctCanSua.getMaSach();
                break;
            }
            if (dsBook.timkiem(inp) == -1) {
                System.out.println("Ma sach khong ton tai trong danh sach sach!");
                continue;
            }
            if (coDuLieuPM && laySoLuongMuonCuaSach(chiTietMuon, inp) == -1) {
                System.out.println("Ma sach " + inp + " khong co trong phieu muon nay! ("
                        + chuoiSachTrongPhieuMuon(chiTietMuon) + ")");
                continue;
            }
            maSachMoi = inp;
            break;
        }

        // 2. Nhap ma quy dinh phat moi (QD01 / QD02 / QD03, Enter de giu nguyen)
        String maLDMoi;
        while (true) {
            System.out.print("Nhap ma quy dinh phat moi (QD01/QD02/QD03, Enter de giu nguyen [" + ctCanSua.getMaLD() + "]): ");
            String inp = sc.nextLine().trim().toUpperCase();
            if (inp.isEmpty()) {
                maLDMoi = ctCanSua.getMaLD();
            } else if (!inp.equals("QD01") && !inp.equals("QD02") && !inp.equals("QD03")) {
                System.out.println("Ma quy dinh chi nhan QD01, QD02 hoac QD03!");
                continue;
            } else {
                maLDMoi = inp;
            }

            if ("QD01".equalsIgnoreCase(maLDMoi) && soNgayTre <= 0 && !maLDMoi.equalsIgnoreCase(ctCanSua.getMaLD())) {
                System.out.println("Phieu muon nay khong bi tra tre, khong the chuyen sang QD01!");
                continue;
            }
            if (biTrungSachVaQuyDinh(maPP, maLDMoi, maSachMoi, ctCanSua.getMaCTPP())) {
                System.out.println("Sach " + maSachMoi + " da ton tai o quy dinh " + maLDMoi + " trong phieu nay!");
                continue;
            }
            break;
        }

        // 3. Nhap so luong moi (QD01 lay theo so ngay tre thuc te; QD02/QD03 <= so luong da muon)
        int slMoi;
        if ("QD01".equalsIgnoreCase(maLDMoi) && soNgayTre > 0) {
            slMoi = (int) soNgayTre;
            System.out.println("-> So ngay tre tu dong theo phieu muon: " + slMoi + " ngay");
        } else {
            int slToiDa = (!"QD01".equalsIgnoreCase(maLDMoi) && coDuLieuPM)
                    ? laySoLuongMuonCuaSach(chiTietMuon, maSachMoi)
                    : -1;
            int slMacDinh = ctCanSua.getSoLuong();
            if (slToiDa > 0 && slMacDinh > slToiDa) slMacDinh = slToiDa;
            String prompt = "QD01".equalsIgnoreCase(maLDMoi)
                    ? "Nhap so ngay tre moi (Enter de giu nguyen [" + slMacDinh + "]): "
                    : (slToiDa > 0
                            ? "Nhap so cuon vi pham moi (1 - " + slToiDa + ", Enter de giu nguyen [" + slMacDinh + "]): "
                            : "Nhap so cuon vi pham moi (Enter de giu nguyen [" + slMacDinh + "]): ");
            while (true) {
                System.out.print(prompt);
                String inp = sc.nextLine().trim();
                if (inp.isEmpty()) {
                    slMoi = slMacDinh;
                    break;
                }
                try {
                    int val = Integer.parseInt(inp);
                    if (val <= 0) {
                        System.out.println("So luong phai lon hon 0!");
                        continue;
                    }
                    if (slToiDa > 0 && val > slToiDa) {
                        System.out.println("So luong vi pham khong duoc vuot qua so luong da muon (" + slToiDa + ")!");
                        continue;
                    }
                    slMoi = val;
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Vui long nhap so nguyen hop le!");
                }
            }
        }

        // 4. Nhap ly do / ghi chu cu the moi (Enter de giu nguyen)
        System.out.print("Nhap ly do / ghi chu cu the moi (Enter de giu nguyen [" + ctCanSua.getLD() + "]): ");
        String lyDoInp = sc.nextLine().trim();
        String lyDoMoi = lyDoInp.isEmpty() ? ctCanSua.getLD() : lyDoInp;

        // Cap nhat va tinh lai tien phat
        ctCanSua.setMaSach(maSachMoi);
        ctCanSua.setMaLD(maLDMoi);
        ctCanSua.setSoLuong(slMoi);
        ctCanSua.setLD(lyDoMoi);
        ctCanSua.setTienPhat(tinhLaiTienPhat(ctCanSua, chiTietMuon, listQD, dsBook));
        return true;
    }

    public void Sua(DSQuyDinhPhat listQD, DSBook dsBook) {
        System.out.print("Nhap ma phieu phat can sua chi tiet: ");
        String maPP = sc.nextLine().trim();
        SuaTrongPhieu(maPP, null, 0, listQD, dsBook);
    }

    public double Thongke_tienPhat_theo_MaPhieuPhat(String maPP) {
        double tongTien = 0;
        ChiTietPhieuPhat[] ds = Tim(maPP);
        if (ds != null) {
            for (ChiTietPhieuPhat item : ds) {
                if (item != null) tongTien += item.getTienPhat();
            }
        }
        return tongTien;
    }

    public double UpdateTongTienPhat(String maPP) {
        double tong = 0;
        for (int i = 0; i < n; i++) {
            if (dsChiTiet[i] != null && dsChiTiet[i].getMaPP().equalsIgnoreCase(maPP)) {
                tong += dsChiTiet[i].getTienPhat();
            }
        }
        return tong;
    }

    @Override
    public void ReadFile() {
        ReadFile("data/ChiTietPhieuPhat.txt");
    }

    public void ReadFile(String filename) {
        int count = 0;
        try (Scanner fileScanner = new Scanner(new FileInputStream(filename))) {
            this.dsChiTiet = new ChiTietPhieuPhat[0];
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                if (line.isEmpty()) continue;
                String[] data = line.split(",", 7);
                if (data.length >= 5 && data[0].trim().toUpperCase().startsWith("CTPP")) {
                    // Dinh dang moi 7 cot: maCTPP,maPP,maSach,tienPhat,maLD,LD,soLuong
                    String lyDo = (data.length >= 6) ? data[5].trim() : "";
                    int soLuong = 1;
                    if (data.length >= 7 && !data[6].trim().isEmpty()) {
                        try {
                            soLuong = Math.max(1, Integer.parseInt(data[6].trim()));
                        } catch (NumberFormatException ignored) {}
                    }
                    dsChiTiet = Arrays.copyOf(dsChiTiet, count + 1);
                    dsChiTiet[count] = new ChiTietPhieuPhat(
                            data[0].trim(),
                            data[1].trim(),
                            data[2].trim(),
                            Double.parseDouble(data[3].trim()),
                            data[4].trim(),
                            lyDo,
                            soLuong);
                    count++;
                } else if (data.length >= 4) {
                    // Tuong thich nguoc dinh dang cu 6 cot: maPP,maSach,tienPhat,maLD,LD,soLuong
                    String lyDo = (data.length >= 5) ? data[4].trim() : "";
                    int soLuong = 1;
                    if (data.length >= 6 && !data[5].trim().isEmpty()) {
                        try {
                            soLuong = Math.max(1, Integer.parseInt(data[5].trim()));
                        } catch (NumberFormatException ignored) {}
                    }
                    String maCTPPAuto = String.format("CTPP%03d", count + 1);
                    dsChiTiet = Arrays.copyOf(dsChiTiet, count + 1);
                    dsChiTiet[count] = new ChiTietPhieuPhat(
                            maCTPPAuto,
                            data[0].trim(),
                            data[1].trim(),
                            Double.parseDouble(data[2].trim()),
                            data[3].trim(),
                            lyDo,
                            soLuong);
                    count++;
                }
            }
            this.n = count;
        } catch (FileNotFoundException e) {
            System.out.println("Khong tim thay file " + filename);
        } catch (NumberFormatException e) {
            System.out.println("Loi dinh dang so trong " + filename);
        }
    }

    @Override
    public void WriteFile() {
        WriteFile("data/ChiTietPhieuPhat.txt");
    }

    public void WriteFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(filename))) {
            for (int i = 0; i < n; i++) {
                if (dsChiTiet[i] != null) {
                    String maCTPP = dsChiTiet[i].getMaCTPP().isEmpty()
                            ? String.format("CTPP%03d", i + 1)
                            : dsChiTiet[i].getMaCTPP();
                    String lyDoSach = dsChiTiet[i].getLD().replace(",", " -");
                    writer.println(
                            maCTPP + ","
                            + dsChiTiet[i].getMaPP() + ","
                            + dsChiTiet[i].getMaSach() + ","
                            + (long) dsChiTiet[i].getTienPhat() + ","
                            + dsChiTiet[i].getMaLD() + ","
                            + lyDoSach + ","
                            + dsChiTiet[i].getSoLuong());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Khong tim thay file " + filename);
        }
    }
}