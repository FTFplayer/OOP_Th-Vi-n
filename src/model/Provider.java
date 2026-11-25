package model;

import danhsach.DSProvider;
import java.util.Scanner;
public class Provider{
//1.Atrribute
    private String MaNCC, tenNCC, diaChi, soDienThoai;

    

// 2.Contructor
    public Provider(String MaNCC, String tenNCC, String diaChi, String soDienThoai) {
        this.MaNCC = MaNCC;
        this.tenNCC = tenNCC;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
    }
    public Provider() {}
 

//------------------.Method----------------------
//1.GET-SET
public String getMaNCC(){ return MaNCC; }
public void setMaNCC(String MaNCC){ this.MaNCC = MaNCC; }

public String getTenNCC(){ return tenNCC; }
public void setTenNCC(String tenNCC){ this.tenNCC = tenNCC; }

public String getDiaChi(){ return diaChi; }
public void setDiaChi(String diaChi){ this.diaChi = diaChi; }

public String getSoDienThoai(){ return soDienThoai; }
public void setSoDienThoai(String soDienThoai){ this.soDienThoai = soDienThoai; }


//2.INPUT<OUTPUT>
public void Input(Scanner sc, DSProvider ds){
    do{
    System.out.print("Nhap ma NCC: ");
    MaNCC= sc.nextLine();
// xử lý khóa chính
    if(MaNCC.trim().isEmpty() || ds.Tim(MaNCC) != -1){
        System.out.println("Ma NCC da ton tai !! Tien hanh nhap lai ma NCC.");
    }
    else {
        break;
    }
 
    }
    while(true);

    System.out.print("Nhap ten NCC: ");
    tenNCC=sc.nextLine();
    System.out.print("Nhap dia chi:");
    diaChi= sc.nextLine();
    System.out.print("Nhap so dien thoai: ");
    soDienThoai= sc.nextLine();
}



public void Output(){
    String ma = MaNCC != null ? MaNCC : "";
    String ten = tenNCC != null ? tenNCC : "";
    String diaChiHienThi = diaChi != null ? diaChi : "";
    String sdt = soDienThoai != null ? soDienThoai : "";
    System.out.printf("|%-10s|%-30s|%-55s|%-15s|%n", ma, ten, diaChiHienThi, sdt);
}


}