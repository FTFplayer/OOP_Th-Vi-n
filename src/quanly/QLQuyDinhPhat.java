package quanly;

import danhsach.DSQuyDinhPhat;

import java.util.Scanner;

public class QLQuyDinhPhat {
    private final DSQuyDinhPhat listQD;

    public QLQuyDinhPhat(int length) {
        listQD = new DSQuyDinhPhat(length);
        listQD.docFile("quydinh.txt");
    }

    public DSQuyDinhPhat getListQD() {
        return listQD;
    }

    public void Menu(Scanner sc) {
        int choice;
        do {
            System.out.println("\n--- QUAN LY QUY DINH PHAT ---");
            System.out.println("1. Nhap danh sach quy dinh");
            System.out.println("2. Xuat danh sach quy dinh");
            System.out.println("3. Them quy dinh moi");
            System.out.println("4. Tim quy dinh theo ma");
            System.out.println("5. Xoa quy dinh theo ma");
            System.out.println("6. Sua quy dinh theo ma");
            System.out.println("7. Thong ke so quy dinh");
            System.out.println("8. Ghi file");
            System.out.println("9. Doc file");
            System.out.println("0. Thoat menu quy dinh");
            System.out.print("Chon: ");

            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                choice = -1;
            }

            switch (choice) {
                case 1 -> listQD.Input(sc);
                case 2 -> listQD.Output();
                case 3 -> listQD.Them(sc);
                case 4 -> listQD.TimQuyDinh(sc);
                case 5 -> listQD.Xoa(sc);
                case 6 -> listQD.Sua(sc);
                case 7 -> listQD.ThongKe();
                case 8 -> listQD.ghiFile("quydinh.txt");
                case 9 -> listQD.docFile("quydinh.txt");
                case 0 -> System.out.println("Thoat menu quy dinh phat.");
                default -> System.out.println("Lua chon khong hop le.");
            }
        } while (choice != 0);
    }
}
