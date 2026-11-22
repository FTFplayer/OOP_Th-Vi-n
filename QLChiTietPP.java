package quanly;

import danhsach.DSBook;
import danhsach.DSChiTietPhieuPhat;
import danhsach.DSQuyDinhPhat;
import java.util.Scanner;

public class QLChiTietPP extends QLThuVien {
    private final DSChiTietPhieuPhat dsChiTiet;

    public QLChiTietPP() {
        super();
        this.dsChiTiet = this.dsCTPP;
    }

    public QLChiTietPP(DSChiTietPhieuPhat dsChiTiet) {
        this.dsChiTiet = dsChiTiet;
    }

    public QLChiTietPP(int length) {
        this.dsChiTiet = new DSChiTietPhieuPhat(length);
        this.dsChiTiet.ReadFile();
    }

    public DSChiTietPhieuPhat getDsChiTiet() {
        return dsChiTiet;
    }

    @Override
    public void menu() {
        Menu(sc, dsQDP, dsBook);
    }

    public void Menu(Scanner sc, DSQuyDinhPhat listQD, DSBook dsBook) {
        int choice;
        do {
            System.out.println("--- QUAN LY CHI TIET PHIEU PHAT ---");
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
                    dsChiTiet.Input(maPP, listQD, dsBook);
                    dsChiTiet.WriteFile();
                }
                case 2 -> {
                    dsChiTiet.ReadFile();
                    dsChiTiet.Output();
                }
                case 3 -> {
                    System.out.print("Nhap ma phieu phat: ");
                    String maPP = sc.nextLine().trim();
                    dsChiTiet.Them(listQD, dsBook, maPP);
                    dsChiTiet.WriteFile();
                }
                case 4 -> dsChiTiet.Tim();
                case 5 -> {
                    dsChiTiet.Xoa();
                    dsChiTiet.WriteFile();
                }
                case 6 -> {
                    dsChiTiet.Sua(listQD, dsBook);
                    dsChiTiet.WriteFile();
                }
                case 7 -> {
                    System.out.print("Nhap ma phieu phat can thong ke: ");
                    String maPP = sc.nextLine().trim();
                    double tong = dsChiTiet.Thongke_tienPhat_theo_MaPhieuPhat(maPP);
                    System.out.printf("-> Tong tien phat cho ma PP [%s] la: %.0f%n", maPP, tong);
                }
                case 8 -> dsChiTiet.WriteFile();
                case 9 -> dsChiTiet.ReadFile();
                case 0 -> System.out.println("Thoat menu chi tiet phieu phat.");
                default -> System.out.println("Lua chon khong hop le.");
            }
        } while (choice != 0);
    }
}
