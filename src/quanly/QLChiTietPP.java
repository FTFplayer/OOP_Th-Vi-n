package quanly;
import danhsach.DSChiTietPhieuPhat;
import danhsach.DSBook;
import danhsach.DSQuyDinhPhat;
import java.util.Scanner;

public class QLChiTietPP {
    private final DSChiTietPhieuPhat dsChiTiet;

    public QLChiTietPP(int length) {
        dsChiTiet = new DSChiTietPhieuPhat(length);
        dsChiTiet.ReadFile("ChiTietPhieuPhat.txt");
    }

    public DSChiTietPhieuPhat getDsChiTiet() {
        return dsChiTiet;
    }

    public void Menu(Scanner sc, DSQuyDinhPhat listQD, DSBook dsBook) {
        int choice;
        do {
            System.out.println("\n--- QUAN LY CHI TIET PHIEU PHAT ---");
            System.out.println("1. Nhap danh sach chi tiet phieu phat");
            System.out.println("2. Xuat danh sach chi tiet");
            System.out.println("3. Them chi tiet moi");
            System.out.println("4. Tim kiem theo ma phieu phat");
            System.out.println("5. Xoa chi tiet theo ma PP va ma sach");
            System.out.println("6. Sua chi tiet");
            System.out.println("7. Thong ke tong tien phat theo ma PP");
            System.out.println("8. Ghi file");
            System.out.println("9. Doc file");
            System.out.println("0. Thoat menu chi tiet");
            System.out.print("Chon: ");

            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                choice = -1;
            }

            switch (choice) {
                case 1 -> {
                    System.out.print("Nhap ma phieu phat chung: ");
                    String maPP = sc.nextLine().trim();
                    dsChiTiet.Input(maPP, listQD, dsBook, sc);
                }
                case 2 -> dsChiTiet.Output();
                case 3 -> dsChiTiet.Them(sc, listQD, dsBook);
                case 4 -> dsChiTiet.Tim(sc);
                case 5 -> dsChiTiet.Xoa(sc);
                case 6 -> dsChiTiet.Sua(sc, listQD, dsBook);
                case 7 -> {
                    System.out.print("Nhap ma phieu phat can thong ke: ");
                    String maPP = sc.nextLine().trim();
                    double tong = dsChiTiet.Thongke_tienPhat_theo_MaPhieuPhat(maPP);
                    System.out.println("-> Tong tien phat cho ma PP [" + maPP + "] la: " + tong);
                }
                case 8 -> dsChiTiet.WriteFile("ChiTietPhieuPhat.txt");
                case 9 -> dsChiTiet.ReadFile("ChiTietPhieuPhat.txt");
                case 0 -> System.out.println("Thoat menu chi tiet phieu phat.");
                default -> System.out.println("Lua chon khong hop le.");
            }
        } while (choice != 0);
    }
}