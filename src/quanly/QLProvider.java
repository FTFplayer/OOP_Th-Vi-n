package quanly;

import java.util.Scanner;

public class QLProvider {
    private final DSProvider dsProvider;
    private final Scanner sc;

    public QLProvider(DSProvider dsProvider, Scanner sc) {
        this.dsProvider = dsProvider;
        this.sc = sc;
    }

    public QLProvider(DSProvider dsProvider, Object dsPNH, Scanner sc) {
        this(dsProvider, sc);
    }

    public void HienThiMenu() {
        int chon;

        do {
            System.out.println("\n========== QUAN LY NHA CUNG CAP ==========");
            System.out.println("1. Nhap danh sach nha cung cap");
            System.out.println("2. Hien thi danh sach nha cung cap");
            System.out.println("3. Them nha cung cap");
            System.out.println("4. Tim kiem nha cung cap");
            System.out.println("5. Xoa nha cung cap");
            System.out.println("6. Sua thong tin nha cung cap");
            System.out.println("7. Thong ke nha cung cap");
            System.out.println("0. Thoat");
            System.out.print("Nhap lua chon: ");

            try {
                chon = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                chon = -1;
            }

            switch (chon) {
                case 1 -> dsProvider.Input();
                case 2 -> dsProvider.Show();
                case 3 -> dsProvider.Them();
                case 4 -> MenuTimKiem();
                case 5 -> dsProvider.XoaMaNCC();
                case 6 -> dsProvider.SuaProvider();
                case 7 -> MenuThongKe();
                case 0 -> System.out.println("Thoat menu nha cung cap!");
                default -> System.out.println("KHONG HOP LE\nHay nhap lai lua chon theo MENU");
            }
        } while (chon != 0);
    }

    public void MenuTimKiem() {
        int chon;

        do {
            System.out.println("\n========== TIM KIEM NHA CUNG CAP ==========");
            System.out.println("1. Tim theo ma NCC");
            System.out.println("2. Tim theo ten NCC");
            System.out.println("3. Tim theo so dien thoai");
            System.out.println("4. Tim theo dia chi");
            System.out.println("0. Quay lai");
            System.out.print("Nhap lua chon: ");

            try {
                chon = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                chon = -1;
            }

            switch (chon) {
                case 0 -> System.out.println("KET THUC TIM KIEM DS NCC");
                case 1 -> dsProvider.TimMaNCC();
                case 2 -> dsProvider.TimTenNCC();
                case 3 -> dsProvider.TimSoDienThoai();
                case 4 -> dsProvider.TimDiaChi();
                default -> System.out.println("KHONG HOP LE\nHay nhap lai lua chon theo MENU tim kiem");
            }
        } while (chon != 0);
    }

    public void MenuThongKe() {
        int chon;

        do {
            System.out.println("\n========== THONG KE NHA CUNG CAP ==========");
            System.out.println("1. Thong ke tong so nha cung cap");
            System.out.println("2. Thong ke nha cung cap theo dia chi");
            System.out.println("0. Quay lai");
            System.out.print("Nhap lua chon: ");

            try {
                chon = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                chon = -1;
            }

            switch (chon) {
                case 1 -> dsProvider.ThongkeProvider_theo_tongNCC();
                case 2 -> dsProvider.Thongke_diaChi_theo_NCC();
                case 0 -> System.out.println("KET THUC THONG KE NCC");
                default -> System.out.println("Lua chon khong hop le!");
            }
        } while (chon != 0);
    }
}

