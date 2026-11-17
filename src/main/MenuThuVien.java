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
            System.out.println("| 1) Quan ly doc gia                        |");
            System.out.println("| 2) Quan ly nha xuat ban                   |");
            System.out.println("| 3) Quan ly nha cung cap                   |");
            System.out.println("| 4) Quan ly nhan vien                      |");
            System.out.println("| 5) Quan ly tac gia                        |");
            System.out.println("| 6) Quan ly sach                           |");
            System.out.println("| 7) Quan ly the loai                       |");
            System.out.println("| 8) Quan ly phieu muon                     |");
            System.out.println("| 9) Quan ly phieu phat                     |");
            System.out.println("| 10) Quan ly phieu nhap hang               |");
            System.out.println("| 11) Quan ly quy dinh phat                 |");
            System.out.println("| 0) Thoat.                                 |");
            System.out.println("+-------------------------------------------+");

            System.out.print("Nhap lua chon tu 0 - 10: ");
            choice = nhapLuaChon();

            switch (choice) {
                case 1:
                    qltv = new QLReader();
                    qltv.menu();
                    break;
                case 2:
                    qltv = new QLPublisher();
                    qltv.menu();
                    break;
                case 3:

                    break;
                case 4:
                    qltv = new QLEmployee();
                    qltv.menu();
                    break;
                case 5:
                    qltv = new QLAuthor();
                    qltv.menu();
                    break;
                case 6:
                    qltv = new QLBook();
                    qltv.menu();
                    break;
                case 7:
                    qltv = new QLTheLoai();
                    qltv.menu();
                    break;
                case 8:

                    break;
                case 9:

                    break;
                case 10:

                    break;
                case 11:

                    break;
                default:
                    System.out.println("Nhap khong hop le!! Vui long nhap lai");
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
