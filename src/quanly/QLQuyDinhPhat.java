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
            System.out.println("| 1) Xuat danh sach quy dinh                |");
            System.out.println("| 2) Them quy dinh moi                      |");
            System.out.println("| 3) Tim quy dinh theo ma                   |");
            System.out.println("| 4) Xoa quy dinh theo ma                   |");
            System.out.println("| 5) Sua quy dinh theo ma                   |");
            System.out.println("| 6) Thong ke so quy dinh                   |");
            System.out.println("| 0) Thoat menu quy dinh                    |");
            System.out.println("+-------------------------------------------+");
            System.out.print("Nhap lua chon tu 0 - 6: ");

            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                choice = -1;
            }

            switch (choice) {
                case 1 -> {
                    dsQDP.ReadFile();
                    dsQDP.Output();
                }
                case 2 -> {
                    dsQDP.Them();
                    dsQDP.WriteFile();
                }
                case 3 -> dsQDP.TimQuyDinh();
                case 4 -> {
                    dsQDP.Xoa();
                    dsQDP.WriteFile();
                }
                case 5 -> {
                    dsQDP.Sua();
                    dsQDP.WriteFile();
                }
                case 6 -> dsQDP.ThongKe();
                case 0 -> System.out.println("Thoat menu quy dinh phat.");
                default -> System.out.println("Lua chon khong hop le.");
            }
        } while (choice != 0);
    }
}