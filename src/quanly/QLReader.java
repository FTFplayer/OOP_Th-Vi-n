package Reader;

import java.util.Scanner;

public class QLReader {
    private DSReader dsReader = new DSReader();
    private Scanner sc = new Scanner(System.in);

    // -----------------------------------------------------------------------------------//
    // 1. Menu chính
    public void menu() {
        int choice;
        do {
            System.out.println();
            System.out.println("+-------------------------------------------+");
            System.out.println("|            MENU QUAN LY DOC GIA           |");
            System.out.println("+-------------------------------------------+");
            System.out.println("| 1) Xem danh sach doc gia.                 |");
            System.out.println("| 2) Them doc gia.                          |");
            System.out.println("| 3) Tim kiem thong tin doc gia.            |");
            System.out.println("| 4) Sua thong tin doc gia.                 |");
            System.out.println("| 5) Xoa thong tin doc gia.                 |");
            System.out.println("| 6) Thong ke thong tin doc gia.            |");
            System.out.println("| 0) Thoat.                                 |");
            System.out.println("+-------------------------------------------+");

            System.out.print("Nhap lua chon tu 0 - 6: ");
            choice = nhapLuaChon();

            switch (choice) {
                case 1:
                    System.out.println("\n--- [1] XEM DANH SACH DOC GIA ---");
                    dsReader.ReadFile();
                    dsReader.Output();
                    break;
                case 2:
                    System.out.println("\n--- [2] THEM DOC GIA MOI ---");
                    System.out.print("Ban muon nhap bao nhieu doc gia: ");
                    int twice = nhapLuaChon();
                    for (int i = 0; i < twice; i++) {
                        System.out.println("Nhap doc gia thu " + (i + 1) + ": ");
                        dsReader.ThemReader();
                        dsReader.WriteFile();
                    }
                    break;
                case 3:
                    menuTimKiem();
                    break;
                case 4:
                    System.out.println("\n--- [4] SUA THONG TIN DOC GIA ---");
                    dsReader.SuaReader();
                    dsReader.WriteFile();
                    break;
                case 5:
                    menuXoa();
                    dsReader.WriteFile();
                    break;
                case 6:
                    menuThongKe();
                    break;
                case 0:
                    System.out.println("Da thoat khoi Quan ly Doc gia.");
                    break;
                default:
                    System.out.println("Lua chon khong hop le. Vui long chon lai tu 0 den 6!");
            }
        } while (choice != 0);
    }

    // -----------------------------------------------------------------------------------//
    // 2. Menu Tìm kiếm
    private void menuTimKiem() {
        int choice;
        do {
            System.out.println("+-------------------------------------------+");
            System.out.println("|            MENU TIM KIEM DOC GIA          |");
            System.out.println("+-------------------------------------------+");
            System.out.println("| 1) Tim kiem theo ma doc gia.              |");
            System.out.println("| 2) Tim kiem theo ho doc gia.              |");
            System.out.println("| 3) Tim kiem theo ten doc gia.             |");
            System.out.println("| 0) Thoat.                                 |");
            System.out.println("+-------------------------------------------+");

            System.out.print("Nhap lua chon tu 0 - 3: ");
            choice = nhapLuaChon();

            switch (choice) {
                case 1:
                    dsReader.TimReaderTheoMa();
                    break;
                case 2:
                    dsReader.TimReaderTheoHo();
                    break;
                case 3:
                    dsReader.TimReaderTheoTen();
                    break;
                case 0:
                    System.out.println("Quay lai menu quan ly doc gia.");
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
            System.out.println("|               MENU XOA DOC GIA            |");
            System.out.println("+-------------------------------------------+");
            System.out.println("| 1) Xoa theo ma doc gia.                   |");
            System.out.println("| 2) Xoa theo ho doc gia.                   |");
            System.out.println("| 3) Xoa theo ten doc gia.                  |");
            System.out.println("| 4) Xoa theo dia chi.                      |");
            System.out.println("| 5) Xoa theo so dien thoai.                |");
            System.out.println("| 6) Xoa theo ngay lap the.                 |");
            System.out.println("| 7) Xoa theo ngay sinh.                    |");
            System.out.println("| 8) Xoa theo gioi tinh.                    |");
            System.out.println("| 0) Thoat.                                 |");
            System.out.println("+-------------------------------------------+");

            System.out.print("Nhap lua chon tu 0 - 8: ");
            choice = nhapLuaChon();

            switch (choice) {
                case 1:
                    dsReader.XoaReaderTheoMa();
                    break;
                case 2:
                    dsReader.XoaReaderTheoHo();
                    break;
                case 3:
                    dsReader.XoaReaderTheoTen();
                    break;
                case 4:
                    dsReader.XoaReaderTheoDiaChi();
                    break;
                case 5:
                    dsReader.XoaReaderTheoSDT();
                    break;
                case 6:
                    dsReader.XoaReaderTheoNgayLapThe();
                    break;
                case 7:
                    dsReader.XoaReaderTheoNgaySinh();
                    break;
                case 8:
                    dsReader.XoaReaderTheoGioiTinh();
                    break;
                case 0:
                    System.out.println("Quay lai menu quan ly doc gia.");
                    break;
                default:
                    System.out.println("Lua chon khong hop le. Vui long chon lai tu 0 den 8!");
            }

            // Neu nguoi dung vua thuc hien 1 thao tac xoa (tu 1 den 8), tu dong cap nhat
            // lai file
            if (choice >= 1 && choice <= 8) {
                dsReader.WriteFile();
            }

        } while (choice != 0);
    }

    // -----------------------------------------------------------------------------------//
    // 6. Menu thống kê
    private void menuThongKe() {
        int choice;
        do {
            System.out.println("+-------------------------------------------+");
            System.out.println("|            MENU THONG KE DOC GIA          |");
            System.out.println("+-------------------------------------------+");
            System.out.println("| 1) Thong ke doc gia theo ngay lap the.    |");
            System.out.println("| 2) Thong ke doc gia theo tuoi.            |");
            System.out.println("| 3) Thong ke doc gia theo gioi tinh.       |");
            System.out.println("| 0) Thoat.                                 |");
            System.out.println("+-------------------------------------------+");

            System.out.print("Nhap lua chon tu 0 - 3: ");
            choice = nhapLuaChon();

            switch (choice) {
                case 1:
                    int slLapThe = dsReader.ThongKeReaderTheoNgayLapThe();
                    System.out.println("So luong doc gia co ngay lap the nay la: " + slLapThe);
                    break;
                case 2:
                    int slTuoi = dsReader.ThongKeReaderTheoTuoi();
                    System.out.println("So luong doc gia co do tuoi nay la: " + slTuoi);
                    break;
                case 3:
                    dsReader.ThongKeReaderTheoGioiTinh();
                    break;
                case 0:
                    System.out.println("Quay lai menu quan ly doc gia.");
                    break;
                default:
                    System.out.println("Lua chon khong hop le. Vui long chon lai tu 0 den 3!");
            }
        } while (choice != 0);
    }

    // -----------------------------------------------------------------------------------//
    // 7. Kiểm tra coi số có bị lỗi gì hong
    private int nhapLuaChon() {
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}