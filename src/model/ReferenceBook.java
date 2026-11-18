package model;

import java.time.LocalDate;
import java.util.Scanner;

public class ReferenceBook extends Book {
    private String Linhvuc;

    public ReferenceBook() {
        super();
        this.Linhvuc = "";
    }

    public ReferenceBook(String maSach, String tenSach, String theLoai, String maTG, LocalDate ngayXB,
            String trangThai, String maNXB, double donGia, int soLuong, int soLuongConLai,
            String linhvuc) {
        super(maSach, tenSach, theLoai, maTG, ngayXB, trangThai, maNXB, donGia, soLuong, soLuongConLai);
        this.Linhvuc = linhvuc;
    }

    public ReferenceBook(ReferenceBook rb) {
        super(rb);
        if (rb != null) {
            this.Linhvuc = rb.Linhvuc;
        }
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
        nhapThongTinRieng();
    }

    @Override
    public void InputConLai() {
        super.InputConLai();
        nhapThongTinRieng();
    }

    private void nhapThongTinRieng() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap linh vuc tham khao: ");
        this.Linhvuc = sc.nextLine().trim();
    }

    @Override
    public String getThongTinRieng() {
        return "Linh vuc: " + Linhvuc;
    }

    @Override
    public String getLoaiSach() {
        return "STK";
    }

    @Override
    public String toFileString() {
        return super.toFileString() + "," + Linhvuc;
    }
}
