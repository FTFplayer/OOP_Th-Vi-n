package model;

import danhsach.DSBook;
import danhsach.DSQuyDinhPhat;
import java.util.Scanner;

public class ChiTietPhieuPhat {
    private String maPP;
    private String maSach;
    private double tienPhat;
    private String maLD;
    private String LD;
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
     public String getLD() {
        return LD;
    }

    public void setLD(String LD) {
        this.LD = LD;
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
        //nhập lý do 
        System.out.print("Nhap ly do phat:");
        LD= sc.nextLine();
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
    

       

    public void Output() {
        System.out.printf("| %-8s | %-10s | %-15.2f | %-8s |%-50|\n", maPP, maSach, tienPhat, maLD,LD);
    }

   

}
