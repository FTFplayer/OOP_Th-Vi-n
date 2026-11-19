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

    public PhieuNhapHang(PhieuNhapHang copy) {
        if (copy != null) {
            this.maPNH = copy.maPNH;
            this.maNCC = copy.maNCC;
            this.maNV = copy.maNV;
            this.ngayNhap = copy.ngayNhap;
            this.tongTien = copy.tongTien;
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
        if (tongTien < 0) {
            throw new IllegalArgumentException("Tong tien khong duoc am.");
        }
        this.tongTien = tongTien;
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
            if (PhieuMuon.isValidDate(value)) {
                return value;
            }
            System.out.println("Ngay khong hop le (dd/MM/yyyy).");
        }
    }

    public void output() {
        System.out.printf("| %-10s | %-10s | %-10s | %-12s | %-14.0f |%n",
                maPNH, maNCC, maNV, ngayNhap, tongTien);
    }

    public void Output() {
        output();
    }
}
