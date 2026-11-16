package main;

import java.util.Scanner;
import quanly.*;

public class MenuThuVien {
    Scanner sc = new Scanner(System.in);
    QLThuVien qltv = new QLThuVien();

    public void menu() {
        int choice;
        do {
            System.out.println();
            System.out.println("+-------------------------------------------+");
            System.out.println("|            MENU QUAN LY THU VIEN          |");
            System.out.println("+-------------------------------------------+");
            System.out.println("| 1) Xem thong tin doc gia                  |");
            System.out.println("| 2) Xem thong tin tac gia                  |");
            System.out.println("| 3) Xem thong tin nhan vien                |");
            System.out.println("| 4) Xem thong tin nha xuat ban             |");
            System.out.println("| 5) Xem thong tin sach                     |");
            System.out.println("| 6) Xem thong tin phieu muon               |");
            System.out.println("| 7) Xem thong tin the loai                 |");
            System.out.println("| 0) Thoat.                                 |");
            System.out.println("+-------------------------------------------+");

            System.out.print("Nhap lua chon tu 0 - 7: ");
            choice = nhapLuaChon();

            switch (choice) {
                case 1:
                    qltv = new QLReader();
                    qltv.menu();
                    break;
                case 2:
                    qltv = new QLAuthor();
                    qltv.menu();
                    break;
                case 3:
                    qltv = new QLEmployee();
                    qltv.menu();
                    break;
                case 4:
                    qltv = new QLPublisher();
                    qltv.menu();
                    break;
                case 5:
                    qltv = new QLBook();
                    qltv.menu();
                    break;
                case 6:

                    break;
                case 7:
                    qltv = new QLTheLoai();
                    qltv.menu();
                    break;

                default:
                    break;
            }

        } while (choice != 0);
    }

    private int nhapLuaChon() {
        Scanner sc = new Scanner(System.in);
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
