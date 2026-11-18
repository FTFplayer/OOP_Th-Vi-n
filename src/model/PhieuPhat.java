package model;
import danhsach.DSPhieuMuon;
import danhsach.DSPhieuPhat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class PhieuPhat {
    private String maPP;
    private String maPM;
    private LocalDate ngayPhat;
    private double tongSoTienPhat;
    Scanner sc= new Scanner(System.in);

    //1.CONSTRUCTOR
    public PhieuPhat() {}
    public PhieuPhat(String maPM, String maPP, LocalDate ngayPhat, double tongSoTienPhat) {
        this.maPM = maPM;
        this.maPP = maPP;
        this.ngayPhat = ngayPhat;
        this.tongSoTienPhat = tongSoTienPhat;}
    
    //2.GET_SET
    public String getMaPP() { return maPP; }
    public void setMaPP(String maPP) { this.maPP = maPP; }

    public String getMaPM() { return maPM; }
    public void setMaPM(String maPM) { this.maPM= maPM; }

    public LocalDate getNgayPhat() {return ngayPhat;}
    public void setNgayPhat(LocalDate ngayPhat){ this.ngayPhat=ngayPhat;}
 
    public double  getTongSoTienPhat() { return tongSoTienPhat; }
    public void setTongSoTienPhat(double tongSoTienPhat) { this.tongSoTienPhat = tongSoTienPhat; }

    //3.INPUT
    public void Input(DSPhieuPhat ds1,DSPhieuMuon ds2) {           ////////chu ý tham chiếu phiếu mượn
        //mã phiếu phạt
        do{
            System.out.print(" Nhap ma phieu phat: ");
             this.maPP = sc.nextLine().trim();
            if(ds1.Timvitri(this.maPP) == -1)
                break;
            System.out.print(" Ma phieu phat da ton tai!! Vui long nhap lai.");
            }
            while (true);
        // mã phiếu mượn
        do{
            System.out.print(" Nhap ma phieu muon: ");
            maPM = sc.nextLine().trim();
            if (ds2.TimtheoMaPM(this.maPM) != null)       //gọi hàm tìm theo mã pm ở danh sách phiếu mượn
                 break ;
            System.out.print("Ma phieu muon khong ton tai !! Vui long nhap lai. ");}
            while(true);
        // Ngày lập phiếu
        System.out.print(" Nhap ngay lap phieu phat:"); 

        ngayPhat= LocalDate.parse(sc.nextLine());
        }
    

    //4.OUTPUT
    public void Output() {
        System.out.printf("|%-12s|%-14s|%-14s|%-14.0f|%n",
                maPP, maPM, ngayPhat.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), tongSoTienPhat);
    }
    

    //5.TÍNH SÓ NGÀY TRẢ TRỄ SÁCH
    public long tinhSoTraNgayTre(LocalDate ngayQuyDinhTra) {
    return Math.max(0,ChronoUnit.DAYS.between(ngayQuyDinhTra, this.ngayPhat)); }

}


