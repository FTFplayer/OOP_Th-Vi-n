import java.util.Scanner;


public class ChiTietPhieuPhat {
    private String maPP;
    private String maSach;
    private double tienPhat;
    private String maLD;

    public ChiTietPhieuPhat() {}
    public ChiTietPhieuPhat(String maPP, String maSach, double tienPhat, String maLD) {
        this.maPP = maPP;
        this.maSach = maSach;
        this.tienPhat = tienPhat;
        this.maLD = maLD;}

    public String getMaPP() { return maPP; }
    public void setMaPP(String maPP) { this.maPP = maPP; }
    public String getMaSach() { return maSach; }
    public void setMaSach(String maSach) { this.maSach = maSach; }
    public double getTienPhat() { return tienPhat; }
    public void setTienPhat(double tienPhat) { this.tienPhat = tienPhat; }
    public String getMaLD() { return maLD; }
    public void setMaLD(String maLD) { this.maLD = maLD; }


    public void Input(Scanner sc) {
    System.out.print("Nhap ma phieu phat: ");
    this.maPP = sc.nextLine().trim();
    System.out.print("Nhap ma sach: ");
    this.maSach = sc.nextLine().trim();
    System.out.print("Nhap ma ly do phat: ");
    this.maLD = sc.nextLine().trim();
    System.out.print("Nhap tien phat: ");
    this.tienPhat = Double.parseDouble(sc.nextLine().trim());
}
    //    Input all thông tin
    public void Input(String maPPCurrent, ListQuyDinhPhat listQD,DSBook dsBook, Scanner sc) {
        this.maPP = maPPCurrent;
        System.out.print("Nhap ma sach: ");
        maSach = sc.nextLine().trim();
        System.out.print("Nhap ma ly do phat(LD01/LD02/LD03): ");
        maLD = sc.nextLine().trim();
////////tienPhat
        QuyDinhPhat qd= listQD.Tim(maLD);
        if(qd == null){
            System.out.println("-> Ma loi khong ton tai!");
            this.tienPhat = 0;
            return;
        }

       
            if (maLD.equalsIgnoreCase("LD01"))
               { System.out.print("Nhap so ngay tre");
                int soNgayTre= Integer.parseInt(sc.nextLine());
                this.tienPhat= qd.tinhTienChiTiet(0,soNgayTre );}
            else {
                Book book= dsBook.timkiemBook(maSach);
                if (book == null){
                    System.out.println("Khong ton tai ma sach:"+maSach);
                    this.tienPhat=0;
                    return;}
                double giaSach= book.getDonGia();  
                this.tienPhat= qd.tinhTienChiTiet(giaSach, 0);
             }
            }
            

    public void Output() {
        System.out.printf("| %-8s | %-10s | %-15.2f | %-8s |\n", maPP, maSach, tienPhat, maLD);
    }



}
