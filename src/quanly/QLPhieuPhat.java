package quanly;
import danhsach.DSPhieuPhat;
import danhsach.DSChiTietPhieuPhat;

import java.util.Scanner;

public class QLPhieuPhat {
    private final DSPhieuPhat dsPhieuPhat;

    public QLPhieuPhat(int length) {
        this.dsPhieuPhat = new DSPhieuPhat(length);
        // Menu tu quan ly viec doc file ngay khi khoi tao
        this.dsPhieuPhat.docFile();
    }

    public DSPhieuPhat getDsPhieuPhat() {
        return dsPhieuPhat;
    }

    public void Menu(Scanner sc, DSChiTietPhieuPhat dsChiTiet) {
        dsPhieuPhat.setDSChiTiet(dsChiTiet);
        int choice;
        do {
            System.out.println("\n--- QUAN LY PHIEU PHAT ---");
            System.out.println("1. Nhap danh sach phieu phat");
            System.out.println("2. Xuat danh sach phieu phat");
            System.out.println("3. Them phieu phat moi");
            System.out.println("4. Tim kiem phieu phat theo ma PP");
            System.out.println("5. Xoa phieu phat");
            System.out.println("6. Sua phieu phat");
            System.out.println("7. Thong ke phieu phat");
            System.out.println("8. Ghi file");
            System.out.println("9. Doc file");
            System.out.println("0. Thoat menu phieu phat");
            System.out.print("Chon: ");

            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                choice = -1;
            }

            switch (choice) {
                case 1 -> dsPhieuPhat.Input(sc);
                case 2 -> dsPhieuPhat.Output();
                case 3 -> dsPhieuPhat.Them(sc);
                case 4 -> dsPhieuPhat.TimtheoMaPP(sc);
                case 5 -> dsPhieuPhat.Xoa(sc);
                case 6 -> dsPhieuPhat.Sua(sc);
                case 7 -> dsPhieuPhat.ThongKe();
                case 8 -> dsPhieuPhat.ghiFile(); // Menu chu dong goi ghi file
                case 9 -> dsPhieuPhat.docFile(); // Menu chu dong goi doc file
                case 0 -> System.out.println("Thoat menu phieu phat.");
                default -> System.out.println("Lua chon khong hop le.");
            }
        } while (choice != 0);
    }
}
