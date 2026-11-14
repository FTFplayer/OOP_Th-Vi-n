package model;

import java.util.Scanner;

public class QuyDinhPhat {
    private String maLD;
    private String tenLD;
    private double soTien;

    public QuyDinhPhat() {
    }

    public QuyDinhPhat(String maLD, String tenLD, double soTien) {
        this.maLD = maLD;
        this.tenLD = tenLD;
        this.soTien = soTien;
    }

    public String getMaLD() {
        return maLD;
    }

    public void setMaLD(String maLD) {
        this.maLD = maLD;
    }

    public String getTenLD() {
        return tenLD;
    }

    public void setTenLD(String tenLD) {
        this.tenLD = tenLD;
    }

    public double getSoTien() {
        return soTien;
    }

    public void setSoTien(double soTien) {
        this.soTien = soTien;
    }

    public void Input(Scanner sc) {
        System.out.print("Nhap ma ly do phat: ");
        maLD = sc.nextLine().trim();
        System.out.print("Nhap ten ly do phat: ");
        tenLD = sc.nextLine().trim();
        System.out.print("Nhap so tien mac dinh / he so: ");
        soTien = Double.parseDouble(sc.nextLine());
    }

    public void Output() {
        System.out.printf("|%-10s|%-30s|%-14.2f|%n", maLD, tenLD, soTien);
    }

    public double tinhTienChiTiet(double giaSach, int soNgayTre) {
        if (maLD.equalsIgnoreCase("LD01")) {
            return this.soTien * soNgayTre; // Loi 1: Tien mac dinh * so ngay tre
        } else if (maLD.equalsIgnoreCase("LD02")) {
            return giaSach; // Loi 2: Bang tien sach
        } else if (maLD.equalsIgnoreCase("LD03")) {
            return giaSach * 2.0; // Loi 3: Tien sach * 2
        }
        return 0;
    }

}
