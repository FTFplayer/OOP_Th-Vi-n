package model;
import java.util.Scanner;


public class QuyDinhPhat {
    private String maQDP;
    private String tenLDP;
    private String moTa;
    Scanner sc= new Scanner(System.in);
     //constructor
    public QuyDinhPhat() {}
    public QuyDinhPhat(String maQDP, String tenLDP, String moTa) {
        this.maQDP = maQDP;
        this.tenLDP = tenLDP;
        this.moTa=moTa;}
    // get_set
    public String getMaQDP() { return maQDP; }
    public void setMaQDP(String maQDP) { this.maQDP = maQDP; }

    public String getTenLD() { return tenLDP; }
    public void setTenLD(String tenLD) { this.tenLDP = tenLD; }

    public String getMoTa() { return moTa; }
    public void setMoTa(String moTa) { this.moTa = moTa; }
    
    //input
    public void Input() {
    System.out.print("Nhap ma quy dinh phat: ");
    maQDP = sc.nextLine().trim();
    System.out.print("Nhap ten ly do phat: ");
    tenLDP = sc.nextLine().trim();
    System.out.print("Nhap mo ta: ");
    moTa = sc.nextLine().trim();
}
    public void Output() {
        System.out.printf("|%-10s|%-30s|%-14s|%n", maQDP, tenLDP, moTa);
    }

    // public double tinhTienChiTiet(double giaSach, int soNgayTre) {
    //     if (maLD.equalsIgnoreCase("LD01")) {
    //         return this.soTien * soNgayTre; // Loi 1: Tien mac dinh * so ngay tre
    //     } else if (maLD.equalsIgnoreCase("LD02")) {
    //         return giaSach; // Loi 2: Bang tien sach
    //     } else if (maLD.equalsIgnoreCase("LD03")) {
    //         return giaSach * 2.0; // Loi 3: Tien sach * 2
    //     }
    //     return 0;
    // }

}
