package quanly;

import danhsach.DSAuthor;

import java.util.Scanner;

public class QLAuthor {
    private static DSAuthor dsAuthor = new DSAuthor();
    private Scanner sc = new Scanner(System.in);

    // -----------------------------------------------------------------------------------//
    // 1. Menu dieu khien chinh
    public void menu() {
        int choice;
        do {
            System.out.println();
            System.out.println("+-------------------------------------------+");
            System.out.println("|            MENU QUAN LY TAC GIA           |");
            System.out.println("+-------------------------------------------+");
            System.out.println("| 1) Xem danh sach tac gia.                 |");
            System.out.println("| 2) Them tac gia.                          |");
            System.out.println("| 3) Tim kiem thong tin tac gia.            |");
            System.out.println("| 4) Sua thong tin tac gia.                 |");
            System.out.println("| 5) Xoa thong tin tac gia.                 |");
            System.out.println("| 6) Thong ke thong tin tac gia.            |");
            System.out.println("| 0) Thoat.                                 |");
            System.out.println("+-------------------------------------------+");

            System.out.print("Nhap lua chon tu 0 - 6: ");
            choice = nhapLuaChon();

            switch (choice) {
                case 1:
                    System.out.println("\n--- [1] XEM DANH SACH TAC GIA ---");
                    dsAuthor.ReadFile();
                    dsAuthor.Output();
                    break;
                case 2:
                    System.out.println("\n--- [2] THEM TAC GIA MOI ---");
                    System.out.print("Ban muon nhap bao nhieu tac gia: ");
                    int twice = nhapLuaChon();
                    for (int i = 0; i < twice; i++) {
                        System.out.println("Nhap tac gia thu " + (i + 1) + ": ");
                        dsAuthor.ThemAuthor();
                        dsAuthor.WriteFile();
                    }
                    break;
                case 3:
                    menuTimKiem();
                    break;
                case 4:
                    System.out.println("\n--- [4] SUA THONG TIN TAC GIA ---");
                    dsAuthor.SuaAuthor();
                    dsAuthor.WriteFile();
                    break;
                case 5:
                    menuXoa();
                    break;
                case 6:
                    menuThongKe();
                    break;
                case 0:
                    System.out.println("Da thoat khoi Quan ly Tac gia.");
                    break;
                default:
                    System.out.println("Lua chon khong hop le. Vui long chon lai tu 0 den 6!");
            }
        } while (choice != 0);
    }

    // -----------------------------------------------------------------------------------//
    // 2. Tim kiem
    private void menuTimKiem() {
        int choice;
        do {
            System.out.println("+-------------------------------------------+");
            System.out.println("|           MENU TIM KIEM TAC GIA           |");
            System.out.println("+-------------------------------------------+");
            System.out.println("| 1) Tim kiem theo ma tac gia.              |");
            System.out.println("| 2) Tim kiem theo ho tac gia.              |");
            System.out.println("| 3) Tim kiem theo ten tac gia.             |");
            System.out.println("| 4) Tim kiem theo so dien thoai.           |");
            System.out.println("| 0) Thoat.                                 |");
            System.out.println("+-------------------------------------------+");

            System.out.print("Nhap lua chon tu 0 - 4: ");
            choice = nhapLuaChon();

            switch (choice) {
                case 1:
                    dsAuthor.TimAuthorTheoMa();
                    break;
                case 2:
                    dsAuthor.TimAuthorTheoHo();
                    break;
                case 3:
                    dsAuthor.TimAuthorTheoTen();
                    break;
                case 4:
                    dsAuthor.TimAuthorTheoSDT();
                    break;
                case 0:
                    System.out.println("Quay lai menu quan ly tac gia.");
                    break;
                default:
                    System.out.println("Lua chon khong hop le. Vui long chon lai tu 0 den 4!");
            }
        } while (choice != 0);
    }

    // -----------------------------------------------------------------------------------//
    // 3. Xoa
    private void menuXoa() {
        int choice;
        do {
            System.out.println("+-------------------------------------------+");
            System.out.println("|               MENU XOA TAC GIA            |");
            System.out.println("+-------------------------------------------+");
            System.out.println("| 1) Xoa theo ma tac gia.                   |");
            System.out.println("| 2) Xoa theo ho tac gia.                   |");
            System.out.println("| 3) Xoa theo ten tac gia.                  |");
            System.out.println("| 4) Xoa theo gioi tinh.                    |");
            System.out.println("| 5) Xoa theo ngay sinh.                    |");
            System.out.println("| 6) Xoa theo so dien thoai.                |");
            System.out.println("| 0) Thoat.                                 |");
            System.out.println("+-------------------------------------------+");

            System.out.print("Nhap lua chon tu 0 - 6: ");
            choice = nhapLuaChon();

            switch (choice) {
                case 1:
                    dsAuthor.XoaAuthorTheoMa();
                    dsAuthor.WriteFile();
                    break;
                case 2:
                    dsAuthor.XoaAuthorTheoHo();
                    dsAuthor.WriteFile();
                    break;
                case 3:
                    dsAuthor.XoaAuthorTheoTen();
                    dsAuthor.WriteFile();
                    break;
                case 4:
                    dsAuthor.XoaAuthorTheoGioiTinh();
                    dsAuthor.WriteFile();
                    break;
                case 5:
                    dsAuthor.XoaAuthorTheoNgaySinh();
                    dsAuthor.WriteFile();
                    break;
                case 6:
                    dsAuthor.XoaAuthorTheoSDT();
                    dsAuthor.WriteFile();
                    break;
                case 0:
                    System.out.println("Quay lai menu quan ly tac gia.");
                    break;
                default:
                    System.out.println("Lua chon khong hop le. Vui long chon lai tu 0 den 6!");
            }

        } while (choice != 0);
    }

    // -----------------------------------------------------------------------------------//
    // 4. Thong ke
    private void menuThongKe() {
        int choice;
        do {
            System.out.println("+-------------------------------------------+");
            System.out.println("|            MENU THONG KE TAC GIA          |");
            System.out.println("+-------------------------------------------+");
            System.out.println("| 1) Thong ke theo gioi tinh.               |");
            System.out.println("| 2) Thong ke theo tuoi tac gia.            |");
            System.out.println("| 0) Thoat.                                 |");
            System.out.println("+-------------------------------------------+");

            System.out.print("Nhap lua chon tu 0 - 4: ");
            choice = nhapLuaChon();

            switch (choice) {
                case 1:
                    dsAuthor.ThongKeAuthorTheoGioiTinh();
                    break;
                case 2:
                    dsAuthor.ThongKeAuthorTheoTuoi();
                    break;
                case 0:
                    System.out.println("Quay lai menu quan ly tac gia.");
                    break;
                default:
                    System.out.println("Lua chon khong hop le. Vui long chon lai tu 0 den 4!");
            }
        } while (choice != 0);
    }

    // -----------------------------------------------------------------------------------//
    // 5. Nhap lua chon an toan
    private int nhapLuaChon() {
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}