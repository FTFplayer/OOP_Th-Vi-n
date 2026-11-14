package model;

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


 public Provider() {
 }

//3.Method
//1.GET-SET
public String getMaNCC(){
  return MaNCC;
  }
public void setMaNCC(String MaNCC){
 this.MaNCC= MaNCC;
}  
    
public String getTenNCC(){
    return tenNCC;
}
public void setTenNCC(String tenNCC)
{
    this.tenNCC= tenNCC;
}


public String getDiaChi(){
    return diaChi;
}
public void setDicChi(String diaChi){
  this.diaChi= diaChi;
}

public String getSoDienThoai()
{
    return soDienThoai;

}
public void setSoDienThoai(String soDienThoai){
    this.soDienThoai= soDienThoai;
}

//4.INPUT<OUTPUT>
public void Input(Scanner sc, DSProvider ds){
    System.out.print("---------TIEN HANH NHAP THONG TIN_PROVIDER---------\n");
    sc.nextLine();
    do{
    System.out.print("MA NHA CUNG CAP: ");
    MaNCC= sc.nextLine();
// xử lý khóa chính
    if(MaNCC.trim().isEmpty() || ds.Tim(MaNCC) != -1){
        System.out.println("Ma NCC da ton tai___Tien hanh nhap lai ma NCC");
    }
    else {
        break;
    }
 
    }
    while(true);

    System.out.print("TEN NHA CUNG CAP: ");
    tenNCC=sc.nextLine();
    System.out.print("DIA CHI:");
    diaChi= sc.nextLine();
    System.out.print("SO DIEN THOAI: ");
    soDienThoai= sc.nextLine();
}



public void Output(){
    System.out.printf("|%-10S|%-30s|%-55s|%-15s|\n", MaNCC,tenNCC,diaChi,soDienThoai);
}



// public static void main(String[] args) {
//     Provider nc1= new Provider();
//     nc1.Input();
//     nc1.Output();
// }


}