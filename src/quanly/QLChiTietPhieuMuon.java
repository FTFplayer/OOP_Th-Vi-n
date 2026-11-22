package quanly;

import danhsach.DSChiTietPhieuMuon;
import java.util.Scanner;
import model.ChiTietPhieuMuon;

public class QLChiTietPhieuMuon extends QLThuVien {
    public QLChiTietPhieuMuon() {
        super();
    }

    public QLChiTietPhieuMuon(DSChiTietPhieuMuon dsCTPM) {
        this.dsCTPM = dsCTPM;
    }

    @Override
    public void menu() {
        Menu(sc);
    }

    public void Menu(Scanner sc) {
        int choice;
        do {
            System.out.println("\n+-------------------------------------------+");
            System.out.println("|     MENU QUAN LY CHI TIET PHIEU MUON      |");
            System.out.println("+-------------------------------------------+");
            System.out.println("| 1) Xuat danh sach chi tiet phieu muon     |");
            System.out.println("| 2) Them chi tiet phieu muon               |");
            System.out.println("| 3) Tim chi tiet theo ma phieu muon        |");
            System.out.println("| 4) Sua chi tiet phieu muon                |");
            System.out.println("| 5) Xoa chi tiet phieu muon                |");
            System.out.println("| 6) Thong ke tong so sach theo ma PM       |");
            System.out.println("| 0) Thoat                                  |");
            System.out.println("+-------------------------------------------+");
            System.out.print("Nhap lua chon tu 0 - 6: ");
            choice = nhapSo(sc);

            switch (choice) {
                case 1 -> {
                    dsCTPM.ReadFile();
                    dsCTPM.Output();
                }
                case 2 -> {
                    themChiTiet(sc);
                    dsCTPM.WriteFile();
                }
                case 3 -> timTheoMaPM(sc);
                case 4 -> {
                    suaChiTiet(sc);
                    dsCTPM.WriteFile();
                }
                case 5 -> {
                    xoaChiTiet(sc);
                    dsCTPM.WriteFile();
                }
                case 6 -> thongKe(sc);
                case 0 -> System.out.println("Da thoat khoi Quan ly Chi tiet Phieu muon.");
                default -> System.out.println("Lua chon khong hop le!");
            }
        } while (choice != 0);
    }

    private void themChiTiet(Scanner sc) {
        ChiTietPhieuMuon item = new ChiTietPhieuMuon();
        item.Input(sc);
        if (dsPM.TimTheoMa(item.getMaPM()) == null) {
            System.out.println("Canh bao: Ma phieu muon " + item.getMaPM() + " chua ton tai!");
            return;
        }
        System.out.println(dsCTPM.Them(item)
                ? "Them chi tiet phieu muon thanh cong!"
                : "Ma chi tiet da ton tai!");
    }

    private void timTheoMaPM(Scanner sc) {
        System.out.print("Nhap ma phieu muon can tim: ");
        ChiTietPhieuMuon[] items = dsCTPM.TimTheoMaPM(sc.nextLine().trim());
        if (items.length == 0) {
            System.out.println("Khong co chi tiet nao cho phieu muon nay.");
            return;
        }
        System.out.printf("%-12s %-12s %-12s %-10s%n", "MA CT", "MA PM", "MA SACH", "SO LUONG");
        for (ChiTietPhieuMuon item : items) {
            item.Output();
        }
    }

    private void suaChiTiet(Scanner sc) {
        System.out.print("Nhap ma chi tiet can sua: ");
        ChiTietPhieuMuon item = dsCTPM.TimTheoMaCT(sc.nextLine().trim());
        if (item == null) {
            System.out.println("Khong tim thay chi tiet.");
            return;
        }
        System.out.print("Nhap ma phieu muon moi: ");
        item.setMaPM(sc.nextLine().trim());
        System.out.print("Nhap ma sach moi: ");
        item.setMaSach(sc.nextLine().trim());
        while (true) {
            System.out.print("Nhap so luong moi: ");
            try {
                int sl = Integer.parseInt(sc.nextLine().trim());
                if (sl > 0) {
                    item.setSoLuong(sl);
                    break;
                }
                System.out.println("So luong phai lon hon 0!");
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap so nguyen hop le!");
            }
        }
        System.out.println("Sua chi tiet phieu muon thanh cong!");
    }

    private void xoaChiTiet(Scanner sc) {
        System.out.print("Nhap ma chi tiet can xoa: ");
        System.out.println(dsCTPM.Xoa(sc.nextLine().trim())
                ? "Xoa chi tiet thanh cong!"
                : "Khong tim thay chi tiet.");
    }

    private void thongKe(Scanner sc) {
        System.out.print("Nhap ma phieu muon: ");
        String maPM = sc.nextLine().trim();
        System.out.println("Tong so sach muon cua " + maPM + ": " + dsCTPM.TongSoSachMuon(maPM));
    }

    private int nhapSo(Scanner sc) {
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}