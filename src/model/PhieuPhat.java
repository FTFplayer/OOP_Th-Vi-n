package model;
import danhsach.DSPhieuPhat;
import java.time.LocalDate;
import java.util.Scanner;

public class PhieuPhat {
    private String maPP;
    private String maPM;
    private LocalDate ngayPhat;
    private double soTienPhat;
    Scanner sc= new Scanner(System.in);

    //1.constructor
    public PhieuPhat() {}
    public PhieuPhat(String maPM, String maPP, LocalDate ngayPhat, double soTienPhat) {
        this.maPM = maPM;
        this.maPP = maPP;
        this.ngayPhat = ngayPhat;
        this.soTienPhat = soTienPhat;}
    
    //2.Get_Set
     public String getMaPP() { return maPP; }
    public void setMaPP(String maPP) { this.maPP = maPP; }

    public String getMaPhieuPhat() { return maPP; }
    public void setMaPhieuPhat(String maPhieuPhat) { this.maPP = maPhieuPhat; }

    public String getMaPM() { return maPM; }
    public String getMaPhieuMuon() { return maPM; }

    public void setMaPM(String maPM) { this.maPM = maPM; }
    public void setMaPhieuMuon(String maPhieuMuon) { this.maPM = maPhieuMuon; }

    public LocalDate getNgayPhat() {return ngayPhat;}
    public void setNgayPhat(LocalDate ngayPhat) {this.ngayPhat = ngayPhat;}
    public void setSoTienPhat(double soTienPhat) { this.soTienPhat = soTienPhat; }
    public void setTongTienPhat(double tongTienPhat) { this.soTienPhat = tongTienPhat; }

    //3.INPUT
    public void Input(DSPhieuPhat ds1,DSPhieuMuon ds2) {           ////////chu ý tham chiếu phiếu mượn
        //mã phiếu phạt
        do{
            System.out.print(" Nhap ma phieu phat: ");
             this.maPP = sc.nextLine().trim();
            if(ds1.TimtheoMaPP(maPP)==null){
                break;}
            System.out.print(" Ma phieu phat da ton tai!! Vui long nhap lai.");
            }
            while (true);
        // mã phiếu mượn
        do{
            System.out.print(" Nhap ma phieu muon: ");
            String mapm = sc.nextLine().trim();
            if (ds2.TimtheoMaPM(mapm) != null){           //gọi hàm tìm theo mã pm ở danh sách phiếu mượn
                this.maPM=mapm;
                break ;}
            System.out.print("Ma phieu muon khong ton tai !! Vui long nhap lai. ");}
            while(true);
        // Ngày lập phiếu
        System.out.print(" Nhap ngay lap phieu phat:"); 
        ngayPhat= LocalDate.parse(sc.nextLine());
        }
    
    public void Output() {
        System.out.printf("|%-12s|%-14s|%-14s|%-14.2f|%n",
                maPP, maPM, ngayPhat, soTienPhat);
    }



}


