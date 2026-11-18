package model;

import danhsach.DSBook;
import danhsach.DSQuyDinhPhat;
import java.util.Scanner;

public class ChiTietPhieuPhat {
    private String maPP;
    private String maSach;
    private double tienPhat;
    private String maLD;
    Scanner sc= new Scanner(System.in);

    public ChiTietPhieuPhat() {
    }

    public ChiTietPhieuPhat(String maPP, String maSach, double tienPhat, String maLD) {
        this.maPP = maPP;
        this.maSach = maSach;
        this.tienPhat = tienPhat;
        this.maLD = maLD;
    }

    public String getMaPP() {
        return maPP;
    }

    public void setMaPP(String maPP) {
        this.maPP = maPP;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public double getTienPhat() {
        return tienPhat;
    }

    public void setTienPhat(double tienPhat) {
        this.tienPhat = tienPhat;
    }

    public String getMaLD() {
        return maLD;
    }

    public void setMaLD(String maLD) {
        this.maLD = maLD;
    }

    public void Input() {
        System.out.print("Nhap ma sach: ");
        this.maSach = sc.nextLine().trim();
        System.out.print("Nhap ma ly do phat: ");
        this.maLD = sc.nextLine().trim();
        System.out.print("Nhap tien phat: ");
        this.tienPhat = Double.parseDouble(sc.nextLine().trim());
    }

    // Input all thông tin
    public void Input(String maPP, DSQuyDinhPhat dsQDP,DSBook dsBook) {
        this.maPP = maPP;
          //nhập mã sách
          do{
         System.out.print("Nhap ma sach: ");
         maSach = sc.nextLine().trim();
          }
          while(dsBook.timkiem(maSach)== -1);
          // nhập mã lý do phạt
          dsQDP.Output();
          do{
          System.out.print("Dua vao bang quy dinh phat\nNhap ma ly do phat:"); 
          maLD=sc.nextLine().trim();}
          while(dsQDP.TimViTri(maLD)== -1);
          }
    

        ////////tienPhat        ////////tienPhat
        //     double tienphat=dsQDP.TinhTienPhat(maLD);tienphat=listQD.TinhTienPhat(maLD);
        //     System.out.println("-> Ma loi khong ton tai!");}
        //     while(tienphat<0);
        //     this.tienPhat = 0;
        //     return;
        // }

        // // if (maLD.equalsIgnoreCase("LD01")) {
        //     System.out.print("Nhap so ngay tre");
        //     int soNgayTre = Integer.parseInt(sc.nextLine());
        //     this.tienPhat = qd.tinhTienChiTiet(0, soNgayTre);
        // } else {
        //     Book book = dsBook.timkiemBook(maSach);
        //     if (book == null) {
        //         System.out.println("Khong ton tai ma sach:" + maSach);
        //         this.tienPhat = 0;
        //         return;
        //     }
        //     double giaSach = book.getDonGia();
        //     this.tienPhat = qd.tinhTienChiTiet(giaSach, 0);
        // }
    

    public void Output() {
        System.out.printf("| %-8s | %-10s | %-15.2f | %-8s |\n", maPP, maSach, tienPhat, maLD);
    }

}
