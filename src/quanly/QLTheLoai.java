package quanly;

import java.util.Scanner;

public class QLTheLoai extends QLThuVien {
    private Scanner sc = new Scanner(System.in);

    @Override
    public void menu() {
        dstl.ReadFile();
        int choice;
        do {
            System.out.println();
            System.out.println("+-------------------------------------------+");
            System.out.println("|           MENU QUAN LY THE LOAI           |");
            System.out.println("+-------------------------------------------+");
            System.out.println("| 1) Xem danh sach the loai.                |");
            System.out.println("| 2) Them the loai.                         |");
            System.out.println("| 3) Tim kiem thong tin the loai.           |");
            System.out.println("| 4) Sua thong tin the loai.                |");
            System.out.println("| 5) Xoa thong tin the loai.                |");
            System.out.println("| 6) Thong ke the loai.                     |");
            System.out.println("| 0) Thoat.                                 |");
            System.out.println("+-------------------------------------------+");

            System.out.print("Nhap lua chon tu 0 - 6: ");
            choice = nhapLuaChon();

            switch (choice) {
                case 1:
                    System.out.println("\n--- [1] XEM DANH SACH THE LOAI ---");
                    dstl.ReadFile();
                    dstl.Output();
                    break;
                case 2:
                    System.out.println("\n--- [2] THEM THE LOAI MOI ---");
                    System.out.print("Ban muon nhap bao nhieu the loai: ");
                    int twice = nhapLuaChon();
                    for (int i = 0; i < twice; i++) {
                        System.out.println("Nhap the loai thu " + (i + 1) + ": ");
                        dstl.ThemTheLoai();
                        dstl.WriteFile();
                    }
                    break;
                case 3:
                    menuTimKiem();
                    break;
                case 4:
                    System.out.println("\n--- [4] SUA THONG TIN THE LOAI ---");
                    dstl.SuaTheLoai();
                    dstl.WriteFile();
                    break;
                case 5:
                    menuXoa();
                    break;
                case 6:
                    System.out.println("\n--- [6] THONG KE THE LOAI ---");
                    dstl.ThongKeTheLoai();
                    break;
                case 0:
                    System.out.println("Da thoat khoi Quan ly The loai.");
                    break;
                default:
                    System.out.println("Lua chon khong hop le. Vui long chon lai tu 0 den 6!");
            }
        } while (choice != 0);
    }

    private void menuTimKiem() {
        int choice;
        do {
            System.out.println("+-------------------------------------------+");
            System.out.println("|          MENU TIM KIEM THE LOAI           |");
            System.out.println("+-------------------------------------------+");
            System.out.println("| 1) Tim kiem theo ma the loai.             |");
            System.out.println("| 2) Tim kiem theo ten the loai.            |");
            System.out.println("| 0) Thoat.                                 |");
            System.out.println("+-------------------------------------------+");

            System.out.print("Nhap lua chon tu 0 - 2: ");
            choice = nhapLuaChon();

            switch (choice) {
                case 1:
                    dstl.TimTheLoaiTheoMa();
                    break;
                case 2:
                    dstl.TimTheLoaiTheoTen();
                    break;
                case 0:
                    System.out.println("Quay lai menu quan ly the loai.");
                    break;
                default:
                    System.out.println("Lua chon khong hop le. Vui long chon lai tu 0 den 2!");
            }
        } while (choice != 0);
    }

    private void menuXoa() {
        int choice;
        do {
            System.out.println("+-------------------------------------------+");
            System.out.println("|             MENU XOA THE LOAI             |");
            System.out.println("+-------------------------------------------+");
            System.out.println("| 1) Xoa theo ma the loai.                  |");
            System.out.println("| 2) Xoa theo ten the loai.                 |");
            System.out.println("| 0) Thoat.                                 |");
            System.out.println("+-------------------------------------------+");

            System.out.print("Nhap lua chon tu 0 - 2: ");
            choice = nhapLuaChon();

            switch (choice) {
                case 1:
                    dstl.XoaTheLoaiTheoMa();
                    dstl.WriteFile();
                    break;
                case 2:
                    dstl.XoaTheLoaiTheoTen();
                    dstl.WriteFile();
                    break;
                case 0:
                    System.out.println("Quay lai menu quan ly the loai.");
                    break;
                default:
                    System.out.println("Lua chon khong hop le. Vui long chon lai tu 0 den 2!");
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
