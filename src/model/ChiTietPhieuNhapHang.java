package model;

import java.util.Scanner;

public class ChiTietPhieuNhapHang {
    private String maCT;
    private String maPNH;
    private String maSach;
    private int soLuong;
    private double donGia;

    public ChiTietPhieuNhapHang() {
        this("", "", "", 0, 0);
    }

    public ChiTietPhieuNhapHang(String maCT, String maPNH, String maSach,
            int soLuong, double donGia) {
        this.maCT = maCT;
        this.maPNH = maPNH;
        this.maSach = maSach;
        setSoLuong(soLuong);
        setDonGia(donGia);
    }

    public String getMaCT() { return maCT; }
    public void setMaCT(String maCT) { this.maCT = maCT; }
    public String getMaPNH() { return maPNH; }
    public void setMaPNH(String maPNH) { this.maPNH = maPNH; }
    public String getMaSach() { return maSach; }
    public void setMaSach(String maSach) { this.maSach = maSach; }
    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) {
        if (soLuong < 0) {
            throw new IllegalArgumentException("So luong khong duoc am.");
        }
        this.soLuong = soLuong;
    }
    public double getDonGia() { return donGia; }
    public void setDonGia(double donGia) {
        if (donGia < 0) {
            throw new IllegalArgumentException("Don gia khong duoc am.");
        }
        this.donGia = donGia;
    }
    public double getThanhTien() { return soLuong * donGia; }

    public void Input(Scanner sc) {
        System.out.print("Nhap ma chi tiet: ");
        maCT = sc.nextLine().trim();
        System.out.print("Nhap ma phieu nhap hang: ");
        maPNH = sc.nextLine().trim();
        System.out.print("Nhap ma sach: ");
        maSach = sc.nextLine().trim();
        System.out.print("Nhap so luong: ");
        setSoLuong(Integer.parseInt(sc.nextLine().trim()));
        System.out.print("Nhap don gia: ");
        setDonGia(Double.parseDouble(sc.nextLine().trim()));
    }

    public void input(Scanner sc) {
        Input(sc);
    }

    public void Output() {
        System.out.printf("%-12s %-12s %-12s %-10d %-15.2f %-15.2f%n",
                maCT, maPNH, maSach, soLuong, donGia, getThanhTien());
    }

    public void output() {
        Output();
    }
}
