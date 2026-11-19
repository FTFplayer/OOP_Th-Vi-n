package danhsach;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
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

    public void Input(String maPPCurrent, DSQuyDinhPhat listQD, DSBook dsBook) {
        System.out.print("Nhap so luong sach vi pham: ");
        int sl = Integer.parseInt(sc.nextLine().trim());
        dsChiTiet = new ChiTietPhieuPhat[Math.max(sl, 1)];
        this.n = sl;
        for (int i = 0; i < sl; i++) {
            System.out.println("Sach vi pham thu " + (i + 1) + ":");
            dsChiTiet[i] = new ChiTietPhieuPhat();
            dsChiTiet[i].Input(maPPCurrent, listQD, dsBook);
        }
    }

    @Override
    public void Output() {
        System.out.println("DANH SACH CHI TIET PHIEU PHAT:");
        System.out.println("+----------+------------+-----------------+----------+");
        System.out.printf("| %-8s | %-10s | %-15s | %-8s |%n", "MA PP", "MA SACH", "TIEN PHAT", "MA LYDO");
        System.out.println("+----------+------------+-----------------+----------+");
        for (int i = 0; i < n; i++) {
            if (dsChiTiet[i] != null) {
                dsChiTiet[i].Output();
            }
        }
        System.out.println("+----------+------------+-----------------+----------+");
    }

    public void Output(String maPP) {
        boolean found = false;
        System.out.println("DANH SACH CHI TIET THEO MA PP: " + maPP);
        System.out.println("+----------+------------+-----------------+----------+");
        System.out.printf("| %-8s | %-10s | %-15s | %-8s |%n", "MA PP", "MA SACH", "TIEN PHAT", "MA LYDO");
        System.out.println("+----------+------------+-----------------+----------+");
        for (int i = 0; i < n; i++) {
            if (dsChiTiet[i] != null && dsChiTiet[i].getMaPP().equalsIgnoreCase(maPP)) {
                dsChiTiet[i].Output();
                found = true;
            }
        }
        System.out.println("+----------+------------+-----------------+----------+");
        if (!found) {
            System.out.println("Khong tim thay chi tiet nao cho ma PP: " + maPP);
        }
    }

    public void Them(DSQuyDinhPhat listQD, DSBook dsBook, String maPP) {
        ChiTietPhieuPhat ct = new ChiTietPhieuPhat();
        ct.Input(maPP, listQD, dsBook);
        Them(ct);
        System.out.println("Them thanh cong!");
    }

    public void Them(ChiTietPhieuPhat ct) {
        if (n >= dsChiTiet.length) {
            dsChiTiet = Arrays.copyOf(dsChiTiet, dsChiTiet.length + 1);
        }
        dsChiTiet[n++] = ct;
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
        for (int i = idx; i < n - 1; i++) {
            dsChiTiet[i] = dsChiTiet[i + 1];
        }
        dsChiTiet[--n] = null;
        return true;
    }

    public void Xoa() {
        System.out.print("Nhap ma phieu phat can xoa: ");
        String maPP = sc.nextLine().trim();
        System.out.print("Nhap ma sach can xoa: ");
        String maSach = sc.nextLine().trim();
        if (Xoa(maPP, maSach)) {
            System.out.println("Xoa thanh cong!");
        } else {
            System.out.println("Khong tim thay chi tiet de xoa!");
        }
    }

    public void Sua(DSQuyDinhPhat listQD, DSBook dsBook) {
        System.out.print("Nhap ma phieu phat can sua: ");
        String maPP = sc.nextLine().trim();
        System.out.print("Nhap ma sach can sua: ");
        String maSach = sc.nextLine().trim();

        int idx = -1;
        for (int i = 0; i < n; i++) {
            if (dsChiTiet[i] != null
                    && dsChiTiet[i].getMaPP().equalsIgnoreCase(maPP)
                    && dsChiTiet[i].getMaSach().equalsIgnoreCase(maSach)) {
                idx = i;
                break;
            }
        }
        if (idx == -1) {
            System.out.println("Khong tim thay chi tiet de sua!");
            return;
        }
        System.out.println("Nhap thong tin moi:");
        ChiTietPhieuPhat ctMoi = new ChiTietPhieuPhat();
        ctMoi.Input(maPP, listQD, dsBook);
        dsChiTiet[idx] = ctMoi;
        System.out.println("Sua thanh cong!");
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
                String[] data = line.split(",");
                if (data.length >= 4) {
                    dsChiTiet = Arrays.copyOf(dsChiTiet, count + 1);
                    dsChiTiet[count] = new ChiTietPhieuPhat(
                            data[0].trim(),
                            data[1].trim(),
                            Double.parseDouble(data[2].trim()),
                            data[3].trim());
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
                    writer.println(
                            dsChiTiet[i].getMaPP() + ","
                            + dsChiTiet[i].getMaSach() + ","
                            + (long) dsChiTiet[i].getTienPhat() + ","
                            + dsChiTiet[i].getMaLD());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Khong tim thay file " + filename);
        }
    }
}
