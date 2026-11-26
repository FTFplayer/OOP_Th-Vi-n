package model;

import danhsach.DSBook;
import danhsach.DSQuyDinhPhat;
import java.util.Scanner;

public class ChiTietPhieuPhat {
    private String maCTPP;
    private String maPP;
    private String maSach;
    private double tienPhat;
    private String maLD;
    private String LD;
    private int soLuong; // So ngay tre (QD01) hoac so cuon vi pham (QD02, QD03)
    Scanner sc = new Scanner(System.in);

    public ChiTietPhieuPhat() {
        this.maCTPP = "";
        this.maPP = "";
        this.maSach = "";
        this.tienPhat = 0;
        this.maLD = "";
        this.LD = "";
        this.soLuong = 1;
    }

    public ChiTietPhieuPhat(String maPP, String maSach, double tienPhat, String maLD) {
        this("", maPP, maSach, tienPhat, maLD, "", 1);
    }

    public ChiTietPhieuPhat(String maPP, String maSach, double tienPhat, String maLD, String LD) {
        this("", maPP, maSach, tienPhat, maLD, LD, 1);
    }

    public ChiTietPhieuPhat(String maPP, String maSach, double tienPhat, String maLD, String LD, int soLuong) {
        this("", maPP, maSach, tienPhat, maLD, LD, soLuong);
    }

    public ChiTietPhieuPhat(String maCTPP, String maPP, String maSach, double tienPhat, String maLD, String LD, int soLuong) {
        this.maCTPP = (maCTPP != null) ? maCTPP : "";
        this.maPP = maPP;
        this.maSach = maSach;
        this.tienPhat = tienPhat;
        this.maLD = maLD;
        this.LD = (LD != null) ? LD : "";
        this.soLuong = (soLuong > 0) ? soLuong : 1;
    }

    public String getMaCTPP() { return (maCTPP != null) ? maCTPP : ""; }
    public void setMaCTPP(String maCTPP) { this.maCTPP = (maCTPP != null) ? maCTPP : ""; }

    public String getMaPP() { return maPP; }
    public void setMaPP(String maPP) { this.maPP = maPP; }

    public String getMaSach() { return maSach; }
    public void setMaSach(String maSach) { this.maSach = maSach; }

    public double getTienPhat() { return tienPhat; }
    public void setTienPhat(double tienPhat) { this.tienPhat = tienPhat; }

    public String getMaLD() { return maLD; }
    public void setMaLD(String maLD) { this.maLD = maLD; }

    public String getLD() { return (LD != null) ? LD : ""; }
    public void setLD(String LD) { this.LD = (LD != null) ? LD : ""; }

    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = (soLuong > 0) ? soLuong : 1; }

    public String getTienPhatKemSoLuong() {
        String donVi = (maLD != null && maLD.equalsIgnoreCase("QD01")) ? "ngay" : "cuon";
        return String.format("%.0f (%d %s)", tienPhat, soLuong, donVi);
    }

    public void Input() {
        System.out.print("Nhap ma sach: ");
        this.maSach = sc.nextLine().trim();
        System.out.print("Nhap ma ly do phat: ");
        this.maLD = sc.nextLine().trim();
        System.out.print("Nhap so luong (so ngay tre / so cuon): ");
        try {
            this.soLuong = Math.max(1, Integer.parseInt(sc.nextLine().trim()));
        } catch (NumberFormatException e) {
            this.soLuong = 1;
        }
        System.out.print("Nhap ly do / ghi chu cu the: ");
        this.LD = sc.nextLine().trim();
        System.out.print("Nhap tien phat: ");
        this.tienPhat = Double.parseDouble(sc.nextLine().trim());
    }

    public void Input(String maPP, DSQuyDinhPhat dsQDP, DSBook dsBook) {
        this.maPP = maPP;
        int found;
        do {
            System.out.print("Nhap ma sach: ");
            maSach = sc.nextLine().trim();
            found = dsBook.timkiem(maSach);
            if (found == -1) {
                System.out.println("Ma sach khong ton tai! Vui long nhap lai.");
            }
        } while (found == -1);

        dsQDP.Output();
        do {
            System.out.print("Dua vao bang quy dinh phat/Nhap ma ly do phat: ");
            maLD = sc.nextLine().trim();
        } while (dsQDP.TimViTri(maLD) == -1);

        System.out.print("Nhap so luong (so ngay tre / so cuon): ");
        try {
            this.soLuong = Math.max(1, Integer.parseInt(sc.nextLine().trim()));
        } catch (NumberFormatException e) {
            this.soLuong = 1;
        }
        System.out.print("Nhap ly do / ghi chu cu the: ");
        LD = sc.nextLine().trim();
    }

    private String catChuoi(String s, int maxLen) {
        if (s == null) return "";
        if (s.length() <= maxLen) return s;
        return s.substring(0, maxLen - 2) + "..";
    }

    // In khi xem toan bo DS chi tiet (co MA CTPP va MA PP)
    public void Output() {
        System.out.printf("| %-9s | %-8s | %-11s | %-10s | %-22s | %-35s |%n",
                catChuoi(getMaCTPP(), 9), maPP, maLD, maSach, getTienPhatKemSoLuong(), catChuoi(getLD(), 35));
    }

    // In ben trong 1 phieu phat cu the (co MA CTPP, bo cot MA PP du thua)
    public void OutputTrongPhieu() {
        System.out.printf("| %-9s | %-11s | %-10s | %-22s | %-35s |%n",
                catChuoi(getMaCTPP(), 9), maLD, maSach, getTienPhatKemSoLuong(), catChuoi(getLD(), 35));
    }

    // In ben trong 1 phieu phat kem cot SO LUONG MUON lay tu PhieuMuon
    public void OutputTrongPhieu(int soLuongMuon) {
        String strSLMuon = (soLuongMuon > 0) ? String.valueOf(soLuongMuon) : "-";
        System.out.printf("| %-9s | %-11s | %-10s | %-13s | %-22s | %-35s |%n",
                catChuoi(getMaCTPP(), 9), maLD, maSach, strSLMuon, getTienPhatKemSoLuong(), catChuoi(getLD(), 35));
    }
}
