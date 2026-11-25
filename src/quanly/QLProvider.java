package quanly;

import java.util.Scanner;

public class QLProvider extends QLThuVien {
    private Scanner sc = new Scanner(System.in);

    @Override
    public void menu() {
        int choice;
        dsProvider.ReadFile();
        do {
            System.out.println();
            System.out.println("+-------------------------------------------+");
            System.out.println("|         MENU QUAN LY NHA CUNG CAP         |");
            System.out.println("+-------------------------------------------+");
            System.out.println("| 1) Xem danh sach nha cung cap.            |");
            System.out.println("| 2) Them nha cung cap.                     |");
            System.out.println("| 3) Tim kiem thong tin nha cung cap.       |");
            System.out.println("| 4) Sua thong tin nha cung cap.            |");
            System.out.println("| 5) Xoa thong tin nha cung cap.            |");
            System.out.println("| 6) Thong ke thong tin nha cung cap.       |");
            System.out.println("| 0) Thoat.                                 |");
            System.out.println("+-------------------------------------------+");

            System.out.print("Nhap lua chon tu 0 - 6: ");
            choice = nhapLuaChon();

            switch (choice) {
                case 1:
                    System.out.println("\n--- [1] XEM DANH SACH NHA CUNG CAP ---");
                    dsProvider.ReadFile();
                    dsProvider.Show();
                    break;
                case 2:
                    System.out.println("\n--- [2] THEM NHA CUNG CAP MOI ---");
                    System.out.print("Ban muon nhap bao nhieu nha cung cap: ");
                    int soLuong = nhapLuaChon();
                    if (soLuong < 0) {
                        System.out.println("So luong khong hop le.");
                        break;
                    }
                    for (int i = 0; i < soLuong; i++) {
                        System.out.println("Nhap nha cung cap thu " + (i + 1) + ": ");
                        dsProvider.Them();
                        dsProvider.WriteFile();
                    }
                    break;
                case 3:
                    menuTimKiem();
                    break;
                case 4:
                    System.out.println("\n--- [4] SUA THONG TIN NHA CUNG CAP ---");
                    dsProvider.SuaProvider();
                    dsProvider.WriteFile();
                    break;
                case 5:
                    System.out.println("\n--- [5] XOA THONG TIN NHA CUNG CAP ---");
                    dsProvider.XoaMaNCC();
                    dsProvider.WriteFile();
                    break;
                case 6:
                    menuThongKe();
                    break;
                case 0:
                    System.out.println("Da thoat khoi Quan ly Nha cung cap.");
                    break;
                default:
                    System.out.println("Lua chon khong hop le. Vui long chon lai tu 0 den 6!");
            }
        } while (choice != 0);
    }

    private void menuTimKiem() {
        int choice;
        do {
            System.out.println();
            System.out.println("+-------------------------------------------+");
            System.out.println("|         MENU TIM KIEM NHA CUNG CAP        |");
            System.out.println("+-------------------------------------------+");
            System.out.println("| 1) Tim kiem theo ma NCC.                  |");
            System.out.println("| 2) Tim kiem theo ten NCC.                 |");
            System.out.println("| 3) Tim kiem theo so dien thoai.           |");
            System.out.println("| 4) Tim kiem theo dia chi.                 |");
            System.out.println("| 0) Thoat.                                 |");
            System.out.println("+-------------------------------------------+");

            System.out.print("Nhap lua chon tu 0 - 4: ");
            choice = nhapLuaChon();

            switch (choice) {
                case 1:
                    dsProvider.TimMaNCC();
                    break;
                case 2:
                    dsProvider.TimTenNCC();
                    break;
                case 3:
                    dsProvider.TimSoDienThoai();
                    break;
                case 4:
                    dsProvider.TimDiaChi();
                    break;
                case 0:
                    System.out.println("Quay lai menu quan ly nha cung cap.");
                    break;
                default:
                    System.out.println("Lua chon khong hop le. Vui long chon lai tu 0 den 4!");
            }
        } while (choice != 0);
    }

    private void menuThongKe() {
        int choice;
        do {
            System.out.println();
            System.out.println("+-------------------------------------------+");
            System.out.println("|         MENU THONG KE NHA CUNG CAP        |");
            System.out.println("+-------------------------------------------+");
            System.out.println("| 1) Thong ke tong so nha cung cap.         |");
            System.out.println("| 2) Thong ke nha cung cap theo dia chi.    |");
            System.out.println("| 0) Thoat.                                 |");
            System.out.println("+-------------------------------------------+");

            System.out.print("Nhap lua chon tu 0 - 2: ");
            choice = nhapLuaChon();

            switch (choice) {
                case 1:
                    dsProvider.ThongkeProvider_theo_tongNCC();
                    break;
                case 2:
                    dsProvider.Thongke_diaChi_theo_NCC();
                    break;
                case 0:
                    System.out.println("Quay lai menu quan ly nha cung cap.");
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

