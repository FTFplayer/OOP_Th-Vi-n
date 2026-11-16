package model;
import danhsach.DSPhieuPhat;
import java.time.LocalDate;
import java.util.Scanner;

public class PhieuPhat {
    private String maPP;
    private String maPM;
    private LocalDate ngayThucTra;
    private double tongSoTienPhat;
    Scanner sc= new Scanner(System.in);

    //1.constructor
    public PhieuPhat() {}
    public PhieuPhat(String maPM, String maPP, LocalDate ngayThucTra, double tongSoTienPhat) {
        this.maPM = maPM;
        this.maPP = maPP;
        this.ngayThucTra = ngayThucTra;
        this.tongSoTienPhat = tongSoTienPhat;}
    
    //2.Get_Set
    public String getMaPP() { return maPP; }
    public void setMaPP(String maPP) { this.maPP = maPP; }

    public String getMaPM() { return maPM; }
    public void setMaPM(String maPM) { this.maPM= maPM; }

    public LocalDate getNgayThucTra() {return ngayThucTra;}
    public void setNgayThucTra(LocalDate ngayThucTra){ this.ngayThucTra=ngayThucTra;}
 
    public double  getTongSoTienPhat() { return tongSoTienPhat; }
    public void setTongSoTienPhat(double tongSoTienPhat) { this.tongSoTienPhat = tongSoTienPhat; }

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
        ngayThucTra= LocalDate.parse(sc.nextLine());
        }
    
       //4.Output 
    public void Output() {
        System.out.printf("|%-12s|%-14s|%-14s|%-14.2f|%n",
                maPP, maPM, ngayThucTra, tongSoTienPhat);
    }
    

    //Tinh so ngay trả trễ theo ngày hẹn trả:
    public int TinhSoNgayTraTre(){}



}


