package quanly;
import danhsach.DSBook;
import danhsach.DSChiTietPhieuPhat;
import danhsach.DSPhieuPhat;
import java.util.Scanner;
import model.ChiTietPhieuPhat;
import model.PhieuPhat;

public class QLPhieuPhat extends QLThuVien {
    Scanner sc= new Scanner(System.in);

    //1.constructor , get
    public QLPhieuPhat() {}

    //2.Menu
    public void Menu() {
        int choice;
        do {
            System.out.println("+-------------------------------------------+");
            System.out.println("|            MENU QUAN LY PHIEU PHAT        |");
            System.out.println("+-------------------------------------------+");
            System.out.println("| 1) Xem danh sach.                         |");
            System.out.println("| 2) Them phieu phat.                       |");
            System.out.println("| 3) Tim kiem.                              |");
            System.out.println("| 4) Sua thong tin phieu phat.              |");
            System.out.println("| 5) Xoa phieu phat.                        |");
            System.out.println("| 6) Thong ke phieu phat.                   |");
            System.out.println("| 0) Thoat.                                 |");
            System.out.println("+-------------------------------------------+");

            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                choice = -1;
            }

            switch (choice) {
                case 1 -> dsPhieuPhat.Output();
                case 2 -> ThemPP();
                case 3 -> dsPhieuPhat.Them();
                case 4 -> dsPhieuPhat.TimtheoMaPP();
                case 5 -> dsPhieuPhat.Xoa();
                case 6 -> dsPhieuPhat.Sua();
                case 7 -> dsPhieuPhat.ThongKe();
                case 8 -> dsPhieuPhat.ghiFile(); // Menu chu dong goi ghi file
                case 9 -> dsPhieuPhat.docFile(); // Menu chu dong goi doc file
                case 0 -> System.out.println("Thoat menu phieu phat.");
                default -> System.out.println("Lua chon khong hop le.");
            }
        } while (choice != 0);
    }

public void ThemPP(DSBook dsBook){
    System.out.println("THUC HIEN THEM PHIEU PHAT");
    //Nhap pp
    PhieuPhat pp= new PhieuPhat();
     pp.Input(dsPP,dsPM);
        //Nhap chi tiet phieu phat-them vào dsCT
          //songayTre:
          int soNgayTre=pp.TinhSoNgayTraTre();
     System.out.print("Nhap so chi tiet cua phieu phat(0,+∞):");
     int k= Integer.parseInt(sc.nextLine());
     for ( int i=0; i<k; i++)
     {
        ChiTietPhieuPhat ct= new ChiTietPhieuPhat();
        ct.Input(maPP,dsQDP);
        ct.setTienPhat(dsQDP.TinhTienPhat(ct.getMaLD(), soNgayTre, ct.getMaSach(), dsBook))
        pp.setTongTienPhat(soNgayTre);
     }

        //update TONG TIENPHAT.
    }
}





// 1. Nhập Phiếu Phạt
// 2. Thêm vào DSPhieuPhat
// 3. Nhập n Chi Tiết
// 4. Thêm vào DSChiTietPhieuPhat
// 5. Update tổng tiền