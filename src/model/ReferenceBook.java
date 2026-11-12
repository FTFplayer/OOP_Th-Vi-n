package model;

import java.util.Scanner;

public class ReferenceBook extends Book {
    private String Linhvuc;
    Scanner sc = new Scanner(System.in);

    public ReferenceBook() {
        super();
        this.Linhvuc = "";
    }

    public ReferenceBook(String maSach, String tenSach, String theLoai, String maTG, String ngayXB,
            String trangThai, String maNXB, double donGia, int soLuong, int soLuongConLai,
            String linhvuc) {
        super(maSach, tenSach, theLoai, maTG, ngayXB, trangThai, maNXB, donGia, soLuong, soLuongConLai);
        this.Linhvuc = linhvuc;
    }

    public ReferenceBook(ReferenceBook rb) {
        super(rb);
        this.Linhvuc = rb.Linhvuc;
    }

    public String getLinhvuc() {
        return Linhvuc;
    }

    public void setLinhvuc(String Linhvuc) {
        this.Linhvuc = Linhvuc;
    }

    @Override
    public void Input() {
        super.Input();
        System.out.print("Nhap linh vuc tham khao: ");
        Linhvuc = sc.nextLine();
    }

    @Override
    public String getThongTinRieng() {
        return "Linh vuc: " + Linhvuc;
    }
}