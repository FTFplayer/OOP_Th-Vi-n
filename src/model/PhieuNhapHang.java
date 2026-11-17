package model;

import java.util.Scanner;

public class PhieuNhapHang {
    private String maPNH;
    private String maNCC;
    private String maNV;
    private String ngayNhap;
    private double tongTien;

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
        if (tongTien < 0) {
            throw new IllegalArgumentException("Tong tien khong duoc am.");
        }
        this.tongTien = tongTien;
    }

    public void Input(Scanner sc) {
        System.out.print("Nhap ma phieu nhap hang: ");
        maPNH = sc.nextLine().trim();
        System.out.print("Nhap ma nha cung cap: ");
        maNCC = sc.nextLine().trim();
        System.out.print("Nhap ma nhan vien: ");
        maNV = sc.nextLine().trim();
        ngayNhap = nhapNgay(sc);
        tongTien = 0;
    }

    private String nhapNgay(Scanner sc) {
        while (true) {
            System.out.print("Nhap ngay nhap (dd/MM/yyyy): ");
            String value = sc.nextLine().trim();
            if (PhieuMuon.isValidDate(value)) {
                return value;
            }
            System.out.println("Ngay khong hop le.");
        }
    }

    public void output() {
        System.out.printf("%-12s %-12s %-12s %-14s %-15.2f%n",
                maPNH, maNCC, maNV, ngayNhap, tongTien);
    }

    public void Output() {
        output();
    }
}
