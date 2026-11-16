package quanly;

import java.util.Scanner;

public class QLBook extends QLThuVien {
    private Scanner sc = new Scanner(System.in);

    @Override
    public void menu() {
        dsBook.ReadFile();
        dstl.ReadFile();
        int choice;
        do {
            System.out.println();
            System.out.println("+-------------------------------------------+");
            System.out.println("|             MENU QUAN LY SACH             |");
            System.out.println("+-------------------------------------------+");
            System.out.println("| 1) Xem danh sach sach.                    |");
            System.out.println("| 2) Nhap moi danh sach sach.               |");
            System.out.println("| 3) Them sach moi.                         |");
            System.out.println("| 4) Tim kiem thong tin sach.               |");
            System.out.println("| 5) Sua thong tin sach.                    |");
            System.out.println("| 6) Xoa thong tin sach.                    |");
            System.out.println("| 7) Thong ke thong tin sach.               |");
            System.out.println("| 0) Thoat.                                 |");
            System.out.println("+-------------------------------------------+");

            System.out.print("Nhap lua chon tu 0 - 7: ");
            choice = nhapLuaChon();

            switch (choice) {
                case 1:
                    System.out.println("\n--- [1] XEM DANH SACH SACH ---");
                    dsBook.ReadFile();
                    dsBook.Output();
                    break;
                case 2:
                    System.out.println("\n--- [2] NHAP MOI DANH SACH SACH ---");
                    dsBook.Input();
                    dsBook.WriteFile();
                    break;
                case 3:
                    System.out.println("\n--- [3] THEM SACH MOI ---");
                    System.out.print("Ban muon nhap bao nhieu sach: ");
                    int twice = nhapLuaChon();
                    for (int i = 0; i < twice; i++) {
                        System.out.println("Nhap sach thu " + (i + 1) + ":");
                        dsBook.them(dstl);
                        dsBook.WriteFile();
                    }
                    break;
                case 4:
                    menuTimKiem();
                    break;
                case 5:
                    System.out.println("\n--- [5] SUA THONG TIN SACH ---");
                    dsBook.sua();
                    dsBook.WriteFile();
                    break;
                case 6:
                    menuXoa();
                    break;
                case 7:
                    menuThongKe();
                    break;
                case 0:
                    System.out.println("Da thoat khoi Quan ly Sach.");
                    break;
                default:
                    System.out.println("Lua chon khong hop le. Vui long chon lai tu 0 den 7!");
            }
        } while (choice != 0);
    }

    private void menuTimKiem() {
        int choice;
        do {
            System.out.println();
            System.out.println("+--------------------------------------------+");
            System.out.println("|             MENU TIM KIEM SACH             |");
            System.out.println("+--------------------------------------------+");
            System.out.println("| 1) Tim kiem theo ma sach.                  |");
            System.out.println("| 2) Tim kiem theo ten sach.                 |");
            System.out.println("| 3) Tim kiem theo the loai (chon ma TL).    |");
            System.out.println("| 4) Tim kiem theo loai sach (1-SGK | 2-STK).|");
            System.out.println("| 5) Tim kiem theo ma tac gia.               |");
            System.out.println("| 6) Tim kiem theo khoang gia.               |");
            System.out.println("| 0) Quay lai.                               |");
            System.out.println("+--------------------------------------------+");

            System.out.print("Nhap lua chon tu 0 - 6: ");
            choice = nhapLuaChon();

            switch (choice) {
                case 1:
                    dsBook.timkiem();
                    break;
                case 2:
                    dsBook.timTheoTenSach();
                    break;
                case 3:
                    dsBook.timTheoTheLoai();
                    break;
                case 4:
                    dsBook.timTheoLoaiSach();
                    break;
                case 5:
                    dsBook.timTheoTacGia();
                    break;
                case 6:
                    dsBook.timTheoKhoangGia();
                    break;
                case 0:
                    System.out.println("Quay lai menu quan ly sach.");
                    break;
                default:
                    System.out.println("Lua chon khong hop le. Vui long chon lai tu 0 den 6!");
            }
        } while (choice != 0);
    }

    private void menuXoa() {
        int choice;
        do {
            System.out.println();
            System.out.println("+-------------------------------------------+");
            System.out.println("|               MENU XOA SACH               |");
            System.out.println("+-------------------------------------------+");
            System.out.println("| 1) Xoa theo ma sach.                      |");
            System.out.println("| 2) Xoa theo ma the loai.                  |");
            System.out.println("| 0) Quay lai.                              |");
            System.out.println("+-------------------------------------------+");

            System.out.print("Nhap lua chon tu 0 - 2: ");
            choice = nhapLuaChon();

            switch (choice) {
                case 1:
                    dsBook.xoa();
                    dsBook.WriteFile();
                    break;
                case 2:
                    dsBook.xoaTheoTheLoai();
                    dsBook.WriteFile();
                    break;
                case 0:
                    System.out.println("Quay lai menu quan ly sach.");
                    break;
                default:
                    System.out.println("Lua chon khong hop le. Vui long chon lai tu 0 den 2!");
            }
        } while (choice != 0);
    }

    private void menuThongKe() {
        int choice;
        do {
            System.out.println();
            System.out.println("+-------------------------------------------+");
            System.out.println("|            MENU THONG KE SACH             |");
            System.out.println("+-------------------------------------------+");
            System.out.println("| 1) Thong ke theo loai sach (SGK/TK).      |");
            System.out.println("| 2) Thong ke tinh trang kho.               |");
            System.out.println("| 3) Thong ke theo muc gia.                 |");
            System.out.println("| 0) Quay lai.                              |");
            System.out.println("+-------------------------------------------+");

            System.out.print("Nhap lua chon tu 0 - 3: ");
            choice = nhapLuaChon();

            switch (choice) {
                case 1:
                    dsBook.thongKeLoaiSach();
                    break;
                case 2:
                    dsBook.thongKeTinhTrang();
                    break;
                case 3:
                    dsBook.thongKeGiaTien();
                    break;
                case 0:
                    System.out.println("Quay lai menu quan ly sach.");
                    break;
                default:
                    System.out.println("Lua chon khong hop le. Vui long chon lai tu 0 den 3!");
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
