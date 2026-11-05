import java.util.Scanner;
public class TheLoai{
    private String MaTheLoai, TenTheLoai;
    Scanner sc = new Scanner(System.in);

    public TheLoai(){this.MaTheLoai="";this.TenTheLoai="";}
    public TheLoai(String MaTheLoai, String TenTheLoai){
        this.MaTheLoai=MaTheLoai;this.TenTheLoai=TenTheLoai;
    }
    public TheLoai(TheLoai tl){
        this.MaTheLoai=tl.MaTheLoai;this.TenTheLoai=tl.TenTheLoai;
    }

    public String getMaTheLoai(){return MaTheLoai;}
    public void setMaTheLoai(String MaTheLoai){this.MaTheLoai=MaTheLoai;}
    public String getTenTheLoai(){return TenTheLoai;}
    public void setTenTheLoai(String TenTheLoai){this.TenTheLoai=TenTheLoai;}

    public void Input(){
        System.out.print("Nhap ma the loai: ");
        MaTheLoai=sc.nextLine();
        System.out.print("Nhap ten the loai: ");
        TenTheLoai=sc.nextLine();
    }
    public void Output() {
        System.out.printf("| %-12s | %-25s |\n", MaTheLoai, TenTheLoai);
    }
}