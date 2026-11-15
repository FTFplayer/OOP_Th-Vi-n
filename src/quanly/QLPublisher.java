package quanly;

import danhsach.DSPublisher;

import java.util.Scanner;

public class QLPublisher {
    private DSPublisher dsPublisher = new DSPublisher();
    private Scanner sc = new Scanner(System.in);

    // -----------------------------------------------------------------------------------//
    // 1. Menu quản lý
    public void menu() {
        int choice;
        dsPublisher.ReadFile();
        do {
            System.out.println();
            System.out.println("+-------------------------------------------+");
            System.out.println("|         MENU QUAN LY NHA XUAT BAN         |");
            System.out.println("+-------------------------------------------+");
            System.out.println("| 1) Xem danh sach nha xuat ban.            |");
            System.out.println("| 2) Them nha xuat ban.                     |");
            System.out.println("| 3) Tim kiem thong tin nha xuat ban.       |");
            System.out.println("| 4) Sua thong tin nha xuat ban.            |");
            System.out.println("| 5) Xoa thong tin nha xuat ban.            |");
            System.out.println("| 0) Thoat.                                 |");
            System.out.println("+-------------------------------------------+");

            System.out.print("Nhap lua chon tu 0 - 5: ");
            choice = nhapLuaChon();

            switch (choice) {
                case 1:
                    System.out.println("\n--- [1] XEM DANH SACH NHA XUAT BAN ---");
                    dsPublisher.ReadFile();
                    dsPublisher.Output();
                    break;
                case 2:
                    System.out.println("\n--- [2] THEM NHA XUAT BAN MOI ---");
                    System.out.print("Ban muon nhap bao nhieu nha xuat ban: ");
                    int twice = nhapLuaChon();
                    for (int i = 0; i < twice; i++) {
                        System.out.println("Nhap nha xuat ban thu " + (i + 1) + ": ");
                        dsPublisher.ThemPublisher();
                        dsPublisher.WriteFile();
                    }
                    break;
                case 3:
                    menuTimKiem();
                    break;
                case 4:
                    System.out.println("\n--- [4] SUA THONG TIN NHA XUAT BAN ---");
                    dsPublisher.SuaPublisher();
                    dsPublisher.WriteFile();
                    break;
                case 5:
                    menuXoa();
                    break;
                case 0:
                    System.out.println("Da thoat khoi Quan ly Nha xuat ban.");
                    break;
                default:
                    System.out.println("Lua chon khong hop le. Vui long chon lai tu 0 den 5!");
            }
        } while (choice != 0);
    }

    // -----------------------------------------------------------------------------------//
    // 2. Menu Tìm kiếm
    private void menuTimKiem() {
        int choice;
        do {
            System.out.println("+-------------------------------------------+");
            System.out.println("|         MENU TIM KIEM NHA XUAT BAN        |");
            System.out.println("+-------------------------------------------+");
            System.out.println("| 1) Tim kiem theo ma NXB.                  |");
            System.out.println("| 2) Tim kiem theo ten NXB.                 |");
            System.out.println("| 3) Tim kiem theo so dien thoai.           |");
            System.out.println("| 0) Thoat.                                 |");
            System.out.println("+-------------------------------------------+");

            System.out.print("Nhap lua chon tu 0 - 3: ");
            choice = nhapLuaChon();

            switch (choice) {
                case 1:
                    dsPublisher.TimPublisherTheoMa();
                    break;
                case 2:
                    dsPublisher.TimPublisherTheoTen();
                    break;
                case 3:
                    dsPublisher.TimPublisherTheoSDT();
                    break;
                case 0:
                    System.out.println("Quay lai menu quan ly nha xuat ban.");
                    break;
                default:
                    System.out.println("Lua chon khong hop le. Vui long chon lai tu 0 den 3!");
            }
        } while (choice != 0);
    }

    // -----------------------------------------------------------------------------------//
    // 3. Menu Xóa
    private void menuXoa() {
        int choice;
        do {
            System.out.println("+-------------------------------------------+");
            System.out.println("|            MENU XOA NHA XUAT BAN          |");
            System.out.println("+-------------------------------------------+");
            System.out.println("| 1) Xoa theo ma NXB.                       |");
            System.out.println("| 2) Xoa theo ten NXB.                      |");
            System.out.println("| 3) Xoa theo so dien thoai.                |");
            System.out.println("| 4) Xoa theo email.                        |");
            System.out.println("| 5) Xoa theo dia chi.                      |");
            System.out.println("| 0) Thoat.                                 |");
            System.out.println("+-------------------------------------------+");

            System.out.print("Nhap lua chon tu 0 - 5: ");
            choice = nhapLuaChon();

            switch (choice) {
                case 1:
                    dsPublisher.XoaPublisherTheoMa();
                    dsPublisher.WriteFile();
                    break;
                case 2:
                    dsPublisher.XoaPublisherTheoTen();
                    dsPublisher.WriteFile();
                    break;
                case 3:
                    dsPublisher.XoaPublisherTheoSDT();
                    dsPublisher.WriteFile();
                    break;
                case 4:
                    dsPublisher.XoaPublisherTheoEmail();
                    dsPublisher.WriteFile();
                    break;
                case 5:
                    dsPublisher.XoaPublisherTheoDiaChi();
                    dsPublisher.WriteFile();
                    break;
                case 0:
                    System.out.println("Quay lai menu quan ly nha xuat ban.");
                    break;
                default:
                    System.out.println("Lua chon khong hop le. Vui long chon lai tu 0 den 5!");
            }

        } while (choice != 0);
    }

    // -----------------------------------------------------------------------------------//
    // 4. Check phần nhập
    private int nhapLuaChon() {
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}