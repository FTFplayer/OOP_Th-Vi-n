import java.util.Scanner;
public abstract class Book {
    private String MaSach, TenSach, TheLoai, MaTG, NgayXB, TrangThai, MaNXB;
    private double DonGia;
    private int SoLuong, SoLuongConLai;
    Scanner sc = new Scanner(System.in);

    public Book(){
        this.MaSach=""; this.TenSach=""; this.TheLoai=""; this.MaTG=""; this.NgayXB=""; this.TrangThai=""; this.MaNXB="";
        this.DonGia=0; this.SoLuong=0; this.SoLuongConLai=0;
    }   
    public Book(String maSach, String tenSach, String theLoai, String maTG, String ngayXB,
                String trangThai, String maNXB, double donGia, int soLuong, int soLuongConLai) {
        this.MaSach = maSach; this.TenSach = tenSach; this.TheLoai = theLoai; this.MaTG = maTG;
        this.NgayXB = ngayXB; this.TrangThai = trangThai; this.MaNXB = maNXB; this.DonGia = donGia;
        this.SoLuong = soLuong; this.SoLuongConLai = soLuongConLai;
    }
    public Book(Book b){
        this.MaSach=b.MaSach; this.TenSach=b.TenSach; this.TheLoai=b.TheLoai;
        this.MaTG=b.MaTG; this.NgayXB=b.NgayXB; this.TrangThai=b.TrangThai; this.MaNXB=b.MaNXB;
        this.DonGia=b.DonGia; this.SoLuong=b.SoLuong; this.SoLuongConLai=b.SoLuongConLai;
    }
    
    public String getMaSach(){return MaSach;}
    public void setMaSach(String MaSach){this.MaSach=MaSach;}
    public String getTenSach(){return TenSach;}
    public void setTenSach(String TenSach){this.TenSach=TenSach;}
    public String getTheLoai(){return TheLoai;}
    public void setTheLoai(String TheLoai){this.TheLoai=TheLoai;}
    public String getMaTG(){return MaTG;}
    public void setMaTG(String MaTG){this.MaTG=MaTG;}
    public String getNgayXB(){return NgayXB;}
    public void setNgayXB(String NgayXB){this.NgayXB=NgayXB;}
    public String getTrangThai(){return TrangThai;}
    public void setTrangThai(String TrangThai){this.TrangThai=TrangThai;}
    public String getMaNXB(){return MaNXB;}
    public void setMaNXB(String MaNXB){this.MaNXB=MaNXB;}
    public double getDonGia(){return DonGia;}
    public void setDonGia(double DonGia){this.DonGia=DonGia;}
    public int getSoLuong(){return SoLuong;}
    public void setSoLuong(int SoLuong){this.SoLuong=SoLuong;}
    public int getSoLuongConLai(){return SoLuongConLai;}
    public void setSoLuongConLai(int SoLuongConLai){this.SoLuongConLai=SoLuongConLai;}

    public void Input() {
        System.out.print("Nhap ma sach: "); MaSach = sc.nextLine();
        System.out.print("Nhap ten sach: "); TenSach = sc.nextLine();
        System.out.print("Nhap ma the loai: "); TheLoai = sc.nextLine();
        System.out.print("Nhap ten tac gia: "); MaTG = sc.nextLine();
        System.out.print("Nhap ngay xuat ban(dd/mm/yyyy): "); NgayXB = sc.nextLine();
        System.out.print("Nhap ten NXB: "); MaNXB = sc.nextLine();
        System.out.print("Nhap don gia: "); DonGia = sc.nextDouble();
        System.out.print("Nhap so luong: "); SoLuong = sc.nextInt();
        sc.nextLine();
        SoLuongConLai = SoLuong;
        updateStatus();
    }
    public void updateStatus(){
        if (this.SoLuongConLai > 0) this.TrangThai = "Con sach";
        else this.TrangThai = "Het sach";
    }
    public abstract String getThongTinRieng();
    public void Output() {
        updateStatus();
        System.out.printf("| %-4s | %-12s | %-8s | %-4s | %-8s | %-7.0f | %-3d | %-3d | %-10s | %-18s |\n",
                MaSach, TenSach, TheLoai, MaTG, NgayXB, DonGia, SoLuong, SoLuongConLai, TrangThai, getThongTinRieng());
    }
}