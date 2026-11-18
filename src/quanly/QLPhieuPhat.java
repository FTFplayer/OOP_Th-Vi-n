package quanly;
import danhsach.*;
import model.*;
  
public class QLPhieuPhat extends QLThuVien {
    //1.constructor , get
     //=================================================
    public QLPhieuPhat() {}

    //2.Menu
     //=================================================
    public void Menu() {
        int choice;
        do {
             System.out.println();
            System.out.println("+-------------------------------------------+");
            System.out.println("|          MENU QUAN LY PHIEU PHAT          |");
            System.out.println("+-------------------------------------------+");
            System.out.println("| 1) Xem danh sach phieu phat.              |");
            System.out.println("| 2) Them phieu phat.                       |");
            System.out.println("| 3) Tim kiem phieu phat.                   |");
            System.out.println("| 4) Sua thong tin phieu phat.              |");
            System.out.println("| 5) Xoa phieu phat.                        |");
            System.out.println("| 6) Thong ke phieu phat.                   |");
            System.out.println("| 0) Thoat.                                 |");
            System.out.println("+-------------------------------------------+");

            try {
                System.out.print("Nhap lua chon tu 0 - 6: ");
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                choice = -1;
            }

            switch (choice) {
                case 1 -> dsPP.Output();
                case 2 -> ThemPP(dsBook,dsPM,dsQDP,dsCTPP);
                case 3 -> menuTimKiem();
                case 4 -> dsPP.Sua();
                case 5 -> dsPP.Xoa();
                case 6 -> menuThongKe();
                case 0->
                    System.out.println("Da thoat khoi Quan ly Phieu Phat.");
                default -> System.out.println("Lua chon khong hop le.");
            }
        } while (choice != 0);
    }

    //2.Them phiếu phạt:
public void ThemPP(DSBook dsBook,DSPhieuMuon dsPM,DSQuyDinhPhat dsQDP,DSChiTietPhieuPhat dsCTPP){
    System.out.println("THUC HIEN THEM PHIEU PHAT");
    //Nhap pp
    PhieuPhat pp= new PhieuPhat();
     pp.Input(dsPP,dsPM);
        //Nhap chi tiet phieu phat-them vào dsCT
          //songayTre:
        //   String maPM= pp.getMaPM();
        //   int soNgayTre=pp.tinhSoTraNgayTre(dsPM.TimtheoMaPM(maPM).getNgayTra());   //xung đột localDate
        int soNgayTre= 0;  // ghi tạm để code ko xung đột
     System.out.print("Nhap so chi tiet cua phieu phat(0,+∞):");
     int k= Integer.parseInt(sc.nextLine());

     double TongTienPhat=0;
     for ( int i=0; i<k; i++)
     {
        ChiTietPhieuPhat ct= new ChiTietPhieuPhat();
        ct.Input(pp.getMaPP(),dsQDP,dsBook);
        ct.setTienPhat(dsQDP.TinhTienPhat(ct.getMaLD(), soNgayTre, ct.getMaSach(), dsBook));
        TongTienPhat+= ct.getTienPhat();
        dsCTPP.Them(ct);
     }

     pp.setTongSoTienPhat(TongTienPhat);
     dsPP.Them(pp);
        //update TONG TIENPHAT.
    }


//3.Menu Tìm kiếm
 //=================================================
 private void menuTimKiem(){

        int choice;

        do{

            System.out.println("+-------------------------------------------+");
            System.out.println("|         MENU TIM KIEM PHIEU PHAT          |");
            System.out.println("+-------------------------------------------+");
            System.out.println("| 1) Tim theo ma phieu phat.                |");
            System.out.println("| 2) Tim theo ma phieu muon.                |");
            System.out.println("| 3) Tim theo ngay phat.                    |");
            System.out.println("| 0) Thoat.                                 |");
            System.out.println("+-------------------------------------------+");

            System.out.print("Nhap lua chon: ");
            choice = nhapLuaChon();

            switch (choice) {
    case 1 -> dsPP.TimtheoMaPP();
    case 2 -> dsPP.TimtheoMaPM();
    case 3 -> dsPP.TimTheoNgayPhat();
    case 0 -> System.out.println("Quay lai menu quan ly.");
    default -> System.out.println("Lua chon khong hop le!");
}
      }while(choice != 0);
    }

   
    //4. MENU THONG KE
    //=================================================

    private void menuThongKe(){

        int choice;

        do{

            System.out.println("+-------------------------------------------+");
            System.out.println("|          MENU THONG KE PHIEU PHAT         |");
            System.out.println("+-------------------------------------------+");
            System.out.println("| 1) Tong so phieu phat.                    |");
            System.out.println("| 2) Tong tien phat.                        |");
            System.out.println("| 3) Phieu phat co tien phat lon nhat.      |");
            System.out.println("| 4) Thong ke theo ma phieu muon.           |");
            System.out.println("| 5) Thong ke theo khoang ngay.             |");
            System.out.println("| 6) Thong ke tong hop.                     |");
            System.out.println("| 0) Thoat.                                 |");
            System.out.println("+-------------------------------------------+");

            System.out.print("Nhap lua chon: ");
            choice = nhapLuaChon();

            switch (choice) {
    case 1 -> dsPP.ThongKeTongSoPP();
    case 2 -> dsPP.ThongKeTongTienPhat();
    case 3 -> dsPP.ThongKePhieuPhatMax();
    case 4 -> dsPP.ThongKePPtheoMaPM();
    case 5 -> dsPP.ThongKeTheoKhoangNgay();
    case 6 -> dsPP.ThongKeTongHop();
    case 0 -> System.out.println("Quay lai menu quan ly.");
    default -> System.out.println("Lua chon khong hop le!");
        }}while(choice != 0);
    }





    //Nhập lựa chọn
    private int nhapLuaChon(){

        try{
            return Integer.parseInt(sc.nextLine().trim());
        }
        catch(NumberFormatException e){
            return -1;
        }
    }
}

// 1. Nhập Phiếu Phạt
// 2. Thêm vào DSPhieuPhat
// 3. Nhập n Chi Tiết
// 4. Thêm vào DSChiTietPhieuPhat
// 5. Update tổng tiền