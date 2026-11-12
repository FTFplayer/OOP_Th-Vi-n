package model;

import java.util.Scanner;

public class EducationBook extends Book {
    private String TenMon, Lop;
    Scanner sc = new Scanner(System.in);

    public EducationBook() {
        super();
        this.TenMon = "";
        this.Lop = "";
    }

    public EducationBook(String maSach, String tenSach, String theLoai, String maTG, String ngayXB,
            String trangThai, String maNXB, double donGia, int soLuong, int soLuongConLai,
            String tenMon, String lop) {
        super(maSach, tenSach, theLoai, maTG, ngayXB, trangThai, maNXB, donGia, soLuong, soLuongConLai);
        this.TenMon = tenMon;
        this.Lop = lop;
    }

    public EducationBook(EducationBook eb) {
        super(eb);
        this.TenMon = eb.TenMon;
        this.Lop = eb.Lop;
    }

    public String getTenMon() {
        return TenMon;
    }

    public void setTenMon(String TenMon) {
        this.TenMon = TenMon;
    }

    public String getLop() {
        return Lop;
    }

    public void setLop(String Lop) {
        this.Lop = Lop;
    }

    @Override
    public void Input() {
        super.Input();
        System.out.print("Nhap ten mon hoc: ");
        TenMon = sc.nextLine();
        System.out.print("Nhap lop: ");
        Lop = sc.nextLine();
    }

    @Override
    public String getThongTinRieng() {
        return "Mon: " + TenMon + " - Lop: " + Lop;
    }
}