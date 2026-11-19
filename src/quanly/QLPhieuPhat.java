package quanly;

import danhsach.*;
import model.*;

public class QLPhieuPhat extends QLThuVien {
    public QLPhieuPhat() {
        super();
    }

    @Override
    public void menu() {
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
            System.out.println("| 7) Quan ly chi tiet phieu phat.           |");
            System.out.println("| 0) Thoat.                                 |");
            System.out.println("+-------------------------------------------+");

            System.out.print("Nhap lua chon tu 0 - 7: ");
            choice = nhapLuaChon();

            switch (choice) {
                case 1 -> {
                    dsPP.ReadFile();
                    dsPP.Output();
                }
                case 2 -> {
                    ThemPP(dsBook, dsPM, dsQDP, dsCTPP);
                    dsPP.WriteFile();
                    dsCTPP.WriteFile();
                }
                case 3 -> menuTimKiem();
                case 4 -> {
                    dsPP.Sua();
                    dsPP.WriteFile();
                }
                case 5 -> {
                    dsPP.Xoa();
                    dsPP.WriteFile();
                }
                case 6 -> menuThongKe();
                case 7 -> {
                    QLChiTietPP qlct = new QLChiTietPP(dsCTPP);
                    qlct.Menu(sc, dsQDP, dsBook);
                }
                case 0 -> System.out.println("Da thoat khoi Quan ly Phieu Phat.");
                default -> System.out.println("Lua chon khong hop le.");
            }
        } while (choice != 0);
    }

    public void Menu() {
        menu();
    }

    public void ThemPP(DSBook dsBook, DSPhieuMuon dsPM, DSQuyDinhPhat dsQDP, DSChiTietPhieuPhat dsCTPP) {
        System.out.println("THUC HIEN THEM PHIEU PHAT");
        PhieuPhat pp = new PhieuPhat();
        pp.Input(dsPP, dsPM);

        long soNgayTre = 0;
        PhieuMuon pm = dsPM.TimtheoMaPM(pp.getMaPM());
        if (pm != null && pm.getNgayTraLocalDate() != null) {
            soNgayTre = pp.tinhSoTraNgayTre(pm.getNgayTraLocalDate());
        }

        System.out.print("Nhap so chi tiet cua phieu phat: ");
        int k = nhapLuaChon();
        if (k < 0) k = 0;

        double tongTienPhat = 0;
        for (int i = 0; i < k; i++) {
            ChiTietPhieuPhat ct = new ChiTietPhieuPhat();
            ct.Input(pp.getMaPP(), dsQDP, dsBook);
            double tien = dsQDP.TinhTienPhat(ct.getMaLD(), soNgayTre, ct.getMaSach(), dsBook);
            ct.setTienPhat(Math.max(0, tien));
            tongTienPhat += ct.getTienPhat();
            dsCTPP.Them(ct);
        }

        pp.setTongSoTienPhat(tongTienPhat);
        dsPP.Them(pp);
        System.out.println("=> Them phieu phat thanh cong!");
    }

    private void menuTimKiem() {
        int choice;
        do {
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
        } while (choice != 0);
    }

    private void menuThongKe() {
        int choice;
        do {
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
            }
        } while (choice != 0);
    }

    private int nhapLuaChon() {
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
