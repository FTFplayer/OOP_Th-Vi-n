package model;

import java.util.Scanner;

public class ChiTietPhieuMuon {
    private String maCT;
    private String maPM;
    private String maSach;
    private int soLuong;
    private static final Scanner sc = new Scanner(System.in);

    public ChiTietPhieuMuon() {
        this("", "", "", 0);
    }

    public ChiTietPhieuMuon(String maCT, String maPM, String maSach, int soLuong) {
        this.maCT = maCT;
        this.maPM = maPM;
        this.maSach = maSach;
        setSoLuong(soLuong);
    }

    public String getMaCT() { return maCT; }
    public void setMaCT(String maCT) { this.maCT = maCT; }
    public String getMaPM() { return maPM; }
    public void setMaPM(String maPM) { this.maPM = maPM; }
    public String getMaSach() { return maSach; }
    public void setMaSach(String maSach) { this.maSach = maSach; }
    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) {
        if (soLuong < 0) {
            throw new IllegalArgumentException("So luong khong duoc am.");
        }
        this.soLuong = soLuong;
    }

    public void Input() {
        System.out.print("Nhap ma chi tiet: ");
        maCT = sc.nextLine().trim();
        System.out.print("Nhap ma phieu muon: ");
        maPM = sc.nextLine().trim();
        System.out.print("Nhap ma sach: ");
        maSach = sc.nextLine().trim();
        System.out.print("Nhap so luong: ");
        setSoLuong(Integer.parseInt(sc.nextLine().trim()));
    }

    public void Output() {
        System.out.printf("| %-10s | %-10s | %-10s | %-8d |%n", maCT, maPM, maSach, soLuong);
    }

    // In ben trong 1 phieu muon cu the (bo cot MA PM du thua, co khung dong bo)
    public void OutputTrongPhieu() {
        System.out.printf("| %-10s | %-10s | %-8d |%n", maCT, maSach, soLuong);
    }
}
