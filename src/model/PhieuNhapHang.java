package model;

import java.util.Arrays;
import java.util.Scanner;

public class PhieuNhapHang {
    private String maPNH;
    private String maNCC;
    private String maNV;
    private String ngayNhap;
    private double tongTien;

    private ChiTietPhieuNhapHang[] dsChiTiet = new ChiTietPhieuNhapHang[0];
    private int soChiTiet = 0;

    public PhieuNhapHang() {
        this("", "", "", "", 0);
    }

    public PhieuNhapHang(String maPNH, String maNCC, String maNV,
            String ngayNhap, double tongTien) {
        this.maPNH = maPNH;
        this.maNCC = maNCC;
        this.maNV = maNV;
        this.ngayNhap = ngayNhap;
        setTongTien(tongTien);
    }

    public PhieuNhapHang(PhieuNhapHang copy) {
        if (copy != null) {
            this.maPNH = copy.maPNH;
            this.maNCC = copy.maNCC;
            this.maNV = copy.maNV;
            this.ngayNhap = copy.ngayNhap;
            this.tongTien = copy.tongTien;
            this.soChiTiet = copy.soChiTiet;
            this.dsChiTiet = Arrays.copyOf(copy.dsChiTiet, copy.soChiTiet);
        }
    }

    public String getMaPNH() { return maPNH; }
    public void setMaPNH(String maPNH) { this.maPNH = maPNH; }
    public String getMaPhieuNhapHang() { return maPNH; }
    public String getMaNCC() { return maNCC; }
    public void setMaNCC(String maNCC) { this.maNCC = maNCC; }
    public String getMaNV() { return maNV; }
    public void setMaNV(String maNV) { this.maNV = maNV; }
    public String getNgayNhap() { return ngayNhap; }
    public void setNgayNhap(String ngayNhap) { this.ngayNhap = ngayNhap; }
    public double getTongTien() { return tongTien; }

    public void setTongTien(double tongTien) {
        if (tongTien < 0) throw new IllegalArgumentException("Tong tien khong duoc am.");
        this.tongTien = tongTien;
    }

    public ChiTietPhieuNhapHang[] getDsChiTiet() { return dsChiTiet; }
    public int getSoChiTiet() { return soChiTiet; }

    public void clearChiTiet() {
        this.dsChiTiet = new ChiTietPhieuNhapHang[0];
        this.soChiTiet = 0;
    }

    public ChiTietPhieuNhapHang timChiTiet(String maCT) {
        for (int i = 0; i < soChiTiet; i++) {
            if (dsChiTiet[i] != null && dsChiTiet[i].getMaCT().equalsIgnoreCase(maCT)) {
                return dsChiTiet[i];
            }
        }
        return null;
    }

    public boolean themChiTiet(ChiTietPhieuNhapHang ct) {
        if (ct == null || timChiTiet(ct.getMaCT()) != null) return false;
        dsChiTiet = Arrays.copyOf(dsChiTiet, soChiTiet + 1);
        dsChiTiet[soChiTiet++] = ct;
        capNhatTongTien();
        return true;
    }

    public boolean xoaChiTiet(String maCT) {
        for (int i = 0; i < soChiTiet; i++) {
            if (dsChiTiet[i] != null && dsChiTiet[i].getMaCT().equalsIgnoreCase(maCT)) {
                for (int j = i; j < soChiTiet - 1; j++) {
                    dsChiTiet[j] = dsChiTiet[j + 1];
                }
                soChiTiet--;
                dsChiTiet = Arrays.copyOf(dsChiTiet, soChiTiet);
                capNhatTongTien();
                return true;
            }
        }
        return false;
    }

    public double capNhatTongTien() {
        double tong = 0;
        for (int i = 0; i < soChiTiet; i++) {
            if (dsChiTiet[i] != null) tong += dsChiTiet[i].getThanhTien();
        }
        this.tongTien = tong;
        return tong;
    }

    public String getChuoiCacChiTiet() {
        if (soChiTiet == 0) return "(Trong)";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < soChiTiet; i++) {
            if (dsChiTiet[i] != null) {
                if (sb.length() > 0) sb.append(", ");
                sb.append(dsChiTiet[i].getMaCT());
            }
        }
        return sb.toString();
    }

    public void xuatChiTiet() {
        if (soChiTiet == 0) {
            System.out.println("  (Phieu nhap " + maPNH + " chua co chi tiet sach nhap)");
            return;
        }
        System.out.println("  Chi tiet phieu nhap " + maPNH + " (Tong tien: " + (long) tongTien + " VND):");
        System.out.println("  +------------+------------+----------+--------------+----------------+");
        System.out.printf("  | %-10s | %-10s | %-8s | %-12s | %-14s |%n",
                "MA CT", "MA SACH", "SO LUONG", "DON GIA", "THANH TIEN");
        System.out.println("  +------------+------------+----------+--------------+----------------+");
        for (int i = 0; i < soChiTiet; i++) {
            if (dsChiTiet[i] != null) {
                System.out.print("  ");
                dsChiTiet[i].OutputTrongPhieu();
            }
        }
        System.out.println("  +------------+------------+----------+--------------+----------------+");
    }

    public void Input(Scanner sc) {
        while (true) {
            System.out.print("Nhap ma phieu nhap hang (VD: PNH001): ");
            maPNH = sc.nextLine().trim();
            if (!maPNH.isEmpty()) break;
            System.out.println("Ma phieu nhap khong duoc de trong!");
        }
        System.out.print("Nhap ma nha cung cap (VD: NCC01): ");
        maNCC = sc.nextLine().trim();
        System.out.print("Nhap ma nhan vien (VD: NV01): ");
        maNV = sc.nextLine().trim();
        ngayNhap = nhapNgay(sc);
        tongTien = 0;
    }

    private String nhapNgay(Scanner sc) {
        while (true) {
            System.out.print("Nhap ngay nhap (dd/MM/yyyy): ");
            String value = sc.nextLine().trim();
            if (PhieuMuon.isValidDate(value)) return value;
            System.out.println("Ngay khong hop le (dd/MM/yyyy).");
        }
    }

    private String catChuoi(String s, int maxLen) {
        if (s == null) return "";
        if (s.length() <= maxLen) return s;
        return s.substring(0, maxLen - 2) + "..";
    }

    public void output() {
        System.out.printf("| %-10s | %-8s | %-8s | %-10s | %-12.0f | %-28s |%n",
                catChuoi(maPNH, 10),
                catChuoi(maNCC, 8),
                catChuoi(maNV, 8),
                catChuoi(ngayNhap, 10),
                tongTien,
                catChuoi(getChuoiCacChiTiet(), 28));
    }

    public void Output() {
        output();
    }
}
