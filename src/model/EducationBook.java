package model;

import java.util.Scanner;

public class EducationBook extends Book {
    private String TenMon;
    private String Lop;

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
        if (eb != null) {
            this.TenMon = eb.TenMon;
            this.Lop = eb.Lop;
        }
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
        nhapThongTinRieng();
    }

    @Override
    public void InputConLai() {
        super.InputConLai();
        nhapThongTinRieng();
    }

    private void nhapThongTinRieng() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ten mon hoc: ");
        this.TenMon = sc.nextLine().trim();
        System.out.print("Nhap lop / trinh do: ");
        this.Lop = sc.nextLine().trim();
    }

    @Override
    public String getThongTinRieng() {
        return "Mon: " + TenMon + " - Lop: " + Lop;
    }

    @Override
    public String getLoaiSach() {
        return "EB";
    }

    @Override
    public String toFileString() {
        return super.toFileString() + "," + TenMon + "," + Lop;
    }
}
