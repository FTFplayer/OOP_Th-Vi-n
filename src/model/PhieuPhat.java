import java.util.Scanner;

public class PhieuPhat {
    private String maPP;
    private String maPM;
    private String ngayPhat;
    private double soTienPhat;


    public PhieuPhat() {
    }

    public PhieuPhat(String maPM, String maPP, String ngayPhat, double soTienPhat) {
        this.maPM = maPM;
        this.maPP = maPP;
        this.ngayPhat = ngayPhat;
        this.soTienPhat = soTienPhat;
    }


    public String getMaPP() {
        return maPP;
    }
   public void setMaPP(String maPP) {
        this.maPP = maPP;
    }
    public String getMaPhieuPhat() {
        return maPP;
    }
    public void setMaPhieuPhat(String maPhieuPhat) {
        this.maPP = maPhieuPhat;
    }
    public String getMaPM() {
        return maPM;
    }
    public String getMaPhieuMuon() {
        return maPM;
    }
    public void setMaPM(String maPM) {
        this.maPM = maPM;
    }
    public void setMaPhieuMuon(String maPhieuMuon) {
        this.maPM = maPhieuMuon;
    }
    public String getNgayPhat() {
        return ngayPhat;
    }
    public void setNgayPhat(String ngayPhat) {
        this.ngayPhat = ngayPhat;
    }
    public double getSoTienPhat() {
        return soTienPhat;
    }
    public double getTongTienPhat() {
        return soTienPhat;
    }
    public void setSoTienPhat(double soTienPhat) {
        this.soTienPhat = soTienPhat;
    }
    public void setTongTienPhat(double tongTienPhat) {
        this.soTienPhat = tongTienPhat;
    }


    
    
    public void capNhatTongTienPhat(DSChiTiet dsChiTiet) {
        double tongTien = 0;
        for (int i = 0; i < dsChiTiet.getSoLuong(); i++) {
            ChiTietPhieuPhat chiTiet = dsChiTiet.getChiTiet(i);
            if (chiTiet.getMaPP().equalsIgnoreCase(maPP)) {
                tongTien += chiTiet.getTienPhat();
            }
        }
        soTienPhat = tongTien;
    }

    public void Input(Scanner sc) {
        System.out.print("Nhap ma phieu phat: ");
        maPP = sc.nextLine().trim();
        System.out.print("Nhap ma phieu muon: ");
        maPM = sc.nextLine().trim();
        System.out.print("Nhap ngay lap phieu phat: ");
        ngayPhat = sc.nextLine().trim();
        soTienPhat = 0;
    }

    public void Output() {
        System.out.printf("|%-12s|%-14s|%-14s|%-14.2f|%n",
                maPP, maPM, ngayPhat, soTienPhat);
    }
}
