package model;

import java.util.Scanner;

public class TheLoai {
    private String MaTheLoai;
    private String TenTheLoai;
    private static int tongSoTheLoai = 0;

    public TheLoai() {
        this.MaTheLoai = "";
        this.TenTheLoai = "";
    }

    public TheLoai(String MaTheLoai, String TenTheLoai) {
        this.MaTheLoai = MaTheLoai;
        this.TenTheLoai = TenTheLoai;
        tongSoTheLoai++;
    }

    public TheLoai(TheLoai tl) {
        if (tl != null) {
            this.MaTheLoai = tl.MaTheLoai;
            this.TenTheLoai = tl.TenTheLoai;
        }
    }

    public static int getTongSoTheLoai() {
        return tongSoTheLoai;
    }

    public static void setTongSoTheLoai(int tong) {
        tongSoTheLoai = tong;
    }

    public String getMaTheLoai() {
        return MaTheLoai;
    }

    public void setMaTheLoai(String MaTheLoai) {
        this.MaTheLoai = MaTheLoai;
    }

    public String getTenTheLoai() {
        return TenTheLoai;
    }

    public void setTenTheLoai(String TenTheLoai) {
        this.TenTheLoai = TenTheLoai;
    }

    public void Input() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Nhap ten the loai: ");
            this.TenTheLoai = sc.nextLine().trim();
            if (!this.TenTheLoai.isEmpty()) {
                break;
            }
            System.out.println("Ten the loai khong duoc de trong!");
        }
    }

    public void Output() {
        System.out.printf("| %-12s | %-25s |\n", MaTheLoai, TenTheLoai);
    }

    public String toFileString() {
        return MaTheLoai + "," + TenTheLoai;
    }
}
