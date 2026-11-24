package quanly;

import java.util.Scanner;

public class QLQuyDinhPhat extends QLThuVien {
    Scanner sc = new Scanner(System.in);

    @Override
    public void menu() {
        int choice;
        do {
            System.out.println("\n+-------------------------------------------+");
            System.out.println("|         MENU QUAN LY QUY DINH PHAT        |");
            System.out.println("+-------------------------------------------+");
            System.out.println("| 1) Nhap danh sach quy dinh                |");
            System.out.println("| 2) Xuat danh sach quy dinh                |");
            System.out.println("| 3) Them quy dinh moi                      |");
            System.out.println("| 4) Tim quy dinh theo ma                   |");
            System.out.println("| 5) Xoa quy dinh theo ma                   |");
            System.out.println("| 6) Sua quy dinh theo ma                   |");
            System.out.println("| 7) Thong ke so quy dinh                   |");
            System.out.println("| 0) Thoat menu quy dinh                    |");
            System.out.println("+-------------------------------------------+");
            System.out.print("Nhap lua chon tu 0 - 9: ");

            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                choice = -1;
            }

            switch (choice) {
                case 1 -> {
                    dsQDP.Input();
                    dsQDP.WriteFile();
                }
                case 2 -> {
                    dsQDP.ReadFile();
                    dsQDP.Output();
                }
                case 3 -> {
                    dsQDP.Them();
                    dsQDP.WriteFile();
                }
                case 4 -> dsQDP.TimQuyDinh();
                case 5 -> {
                    dsQDP.Xoa();
                    dsQDP.WriteFile();
                }
                case 6 -> {
                    dsQDP.Sua();
                    dsQDP.WriteFile();
                }
                case 7 -> dsQDP.ThongKe();
                case 0 -> System.out.println("Thoat menu quy dinh phat.");
                default -> System.out.println("Lua chon khong hop le.");
            }
        } while (choice != 0);
    }
}