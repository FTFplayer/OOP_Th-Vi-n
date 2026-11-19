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
            System.out.println("|          --- DANH SACH CHINH ---          |");
            System.out.println("| 1)  Quan ly sach                          |");
            System.out.println("| 2)  Quan ly doc gia                       |");
            System.out.println("| 3)  Quan ly nhan vien                     |");
            System.out.println("| 4)  Quan ly phieu muon                    |");
            System.out.println("| 5)  Quan ly phieu phat                    |");
            System.out.println("| 6)  Quan ly phieu nhap hang               |");
            System.out.println("+-------------------------------------------+");
            System.out.println("|           --- DANH SACH PHU ---           |");
            System.out.println("| 7)  Quan ly the loai                      |");
            System.out.println("| 8)  Quan ly tac gia                       |");
            System.out.println("| 9)  Quan ly nha xuat ban                  |");
            System.out.println("| 10) Quan ly nha cung cap                  |");
            System.out.println("| 11) Quan ly quy dinh phat                 |");
            System.out.println("+-------------------------------------------+");
            System.out.println("| 0)  Thoat.                                |");
            System.out.println("+-------------------------------------------+");

            System.out.print("Nhap lua chon tu 0 - 11: ");
            choice = nhapLuaChon();

            switch (choice) {
                // --- DANH SACH CHINH ---
                case 1:
                    qltv = new QLBook();
                    qltv.menu();
                    break;
                case 2:
                    qltv = new QLReader();
                    qltv.menu();
                    break;
                case 3:
                    qltv = new QLEmployee();
                    qltv.menu();
                    break;
                case 4:
                    // Quan ly phieu muon
                    break;
                case 5:
                    qltv = new QLPhieuPhat();
                    qltv.menu();
                    break;
                case 6:
                    // Quan ly phieu nhap hang
                    break;

                // --- DANH SACH PHU ---
                case 7:
                    qltv = new QLTheLoai();
                    qltv.menu();
                    break;
                case 8:
                    qltv = new QLAuthor();
                    qltv.menu();
                    break;
                case 9:
                    qltv = new QLPublisher();
                    qltv.menu();
                    break;
                case 10:
                    qltv = new QLProvider();
                    qltv.menu();
                    break;
                case 11:
                    qltv = new QLQuyDinhPhat();
                    qltv.menu();
                    break;

                case 0:
                    System.out.println("Da thoat chuong trinh quan ly thu vien.");
                    break;
                default:
                    System.out.println("Nhap khong hop le!! Vui long nhap lai tu 0 - 11.");
                    break;
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