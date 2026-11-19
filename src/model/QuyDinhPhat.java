package model;

import java.util.Scanner;

public class QuyDinhPhat {
    private String maQDP;
    private String tenLDP;
    private String moTa;
    Scanner sc = new Scanner(System.in);

    // Constructor
    public QuyDinhPhat() {
        this.maQDP = "";
        this.tenLDP = "";
        this.moTa = "";
    }

    public QuyDinhPhat(String maQDP, String tenLDP, String moTa) {
        this.maQDP = maQDP;
        this.tenLDP = tenLDP;
        this.moTa = moTa;
    }

    public QuyDinhPhat(QuyDinhPhat copy) {
        if (copy != null) {
            this.maQDP = copy.maQDP;
            this.tenLDP = copy.tenLDP;
            this.moTa = copy.moTa;
        }
    }

    // Get - Set
    public String getMaQDP() {
        return maQDP;
    }

    public void setMaQDP(String maQDP) {
        this.maQDP = maQDP;
    }

    public String getTenLD() {
        return tenLDP;
    }

    public void setTenLD(String tenLD) {
        this.tenLDP = tenLD;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    // Input
    public void Input() {
        System.out.print("Nhap ma quy dinh phat: ");
        maQDP = sc.nextLine().trim();
        System.out.print("Nhap ten ly do phat: ");
        tenLDP = sc.nextLine().trim();
        System.out.print("Nhap mo ta: ");
        moTa = sc.nextLine().trim();
    }

    // Output
    public void Output() {
        System.out.printf("|%-10s|%-30s|%-30s|%n", maQDP, tenLDP, moTa);
    }
}