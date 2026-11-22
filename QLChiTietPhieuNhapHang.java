package quanly;

import danhsach.DSChiTietPhieuNhapHang;
import java.util.Scanner;
import model.ChiTietPhieuNhapHang;

public class QLChiTietPhieuNhapHang extends QLThuVien {
    public QLChiTietPhieuNhapHang() {
        super();
    }

    public QLChiTietPhieuNhapHang(DSChiTietPhieuNhapHang dsCTPNH) {
        this.dsCTPNH = dsCTPNH;
    }

    @Override
    public void menu() {
        Menu(sc);
    }

    public void Menu(Scanner sc) {
        int choice;
        do {
            System.out.println("\n+-------------------------------------------+");
            System.out.println("|   MENU QUAN LY CHI TIET PHIEU NHAP HANG   |");
            System.out.println("+-------------------------------------------+");
            System.out.println("| 1) Xuat danh sach chi tiet phieu nhap     |");
            System.out.println("| 2) Them chi tiet phieu nhap               |");
            System.out.println("| 3) Tim chi tiet theo ma phieu nhap        |");
            System.out.println("| 4) Sua chi tiet phieu nhap                |");
            System.out.println("| 5) Xoa chi tiet phieu nhap                |");
            System.out.println("| 6) Thong ke tong tien theo ma phieu nhap  |");
            System.out.println("| 0) Thoat                                  |");
            System.out.println("+-------------------------------------------+");
            System.out.print("Nhap lua chon tu 0 - 6: ");
            choice = nhapSo(sc);

            switch (choice) {
                case 1 -> {
                    dsCTPNH.ReadFile();
                    dsCTPNH.Output();
                }
                case 2 -> {
                    themChiTiet(sc);
                    dsCTPNH.WriteFile();
                    dsPNH.WriteFile();
                }
                case 3 -> timTheoMaPNH(sc);
                case 4 -> {
                    suaChiTiet(sc);
                    dsCTPNH.WriteFile();
                    dsPNH.WriteFile();
                }
                case 5 -> {
                    xoaChiTiet(sc);
                    dsCTPNH.WriteFile();
                    dsPNH.WriteFile();
                }
                case 6 -> thongKe(sc);
                case 0 -> System.out.println("Da thoat khoi Quan ly Chi tiet Phieu nhap hang.");
                default -> System.out.println("Lua chon khong hop le!");
            }
        } while (choice != 0);
    }

    private void themChiTiet(Scanner sc) {
        ChiTietPhieuNhapHang item = new ChiTietPhieuNhapHang();
        item.Input(sc);
        if (dsPNH.TimTheoMa(item.getMaPNH()) == null) {
            System.out.println("Canh bao: Ma phieu nhap " + item.getMaPNH() + " chua ton tai!");
            return;
        }
        if (dsCTPNH.Them(item)) {
            dsPNH.CapNhatTongTien(dsCTPNH, item.getMaPNH());
            System.out.println("Them chi tiet phieu nhap thanh cong!");
        } else {
            System.out.println("Ma chi tiet da ton tai!");
        }
    }

    private void timTheoMaPNH(Scanner sc) {
        System.out.print("Nhap ma phieu nhap hang can tim: ");
        ChiTietPhieuNhapHang[] items = dsCTPNH.TimTheoMaPNH(sc.nextLine().trim());
        if (items.length == 0) {
            System.out.println("Khong co chi tiet nao cho phieu nhap nay.");
            return;
        }
        System.out.println("+------------+------------+------------+----------+--------------+----------------+");
        System.out.printf("| %-10s | %-10s | %-10s | %-8s | %-12s | %-14s |%n",
                "MA CT", "MA PNH", "MA SACH", "SO LUONG", "DON GIA", "THANH TIEN");
        System.out.println("+------------+------------+------------+----------+--------------+----------------+");
        for (ChiTietPhieuNhapHang item : items) {
            item.Output();
        }
        System.out.println("+------------+------------+------------+----------+--------------+----------------+");
    }

    private void suaChiTiet(Scanner sc) {
        System.out.print("Nhap ma chi tiet can sua: ");
        ChiTietPhieuNhapHang item = dsCTPNH.TimTheoMaCT(sc.nextLine().trim());
        if (item == null) {
            System.out.println("Khong tim thay chi tiet.");
            return;
        }
        String maPNHCu = item.getMaPNH();
        System.out.print("Nhap ma phieu nhap moi: ");
        item.setMaPNH(sc.nextLine().trim());
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
        while (true) {
            System.out.print("Nhap don gia moi: ");
            try {
                double gia = Double.parseDouble(sc.nextLine().trim());
                if (gia > 0) {
                    item.setDonGia(gia);
                    break;
                }
                System.out.println("Don gia phai lon hon 0!");
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap so hop le!");
            }
        }
        dsPNH.CapNhatTongTien(dsCTPNH, maPNHCu);
        dsPNH.CapNhatTongTien(dsCTPNH, item.getMaPNH());
        System.out.println("Sua chi tiet phieu nhap thanh cong!");
    }

    private void xoaChiTiet(Scanner sc) {
        System.out.print("Nhap ma chi tiet can xoa: ");
        String maCT = sc.nextLine().trim();
        ChiTietPhieuNhapHang item = dsCTPNH.TimTheoMaCT(maCT);
        if (item == null) {
            System.out.println("Khong tim thay chi tiet.");
            return;
        }
        String maPNH = item.getMaPNH();
        dsCTPNH.Xoa(maCT);
        dsPNH.CapNhatTongTien(dsCTPNH, maPNH);
        System.out.println("Xoa chi tiet thanh cong!");
    }

    private void thongKe(Scanner sc) {
        System.out.print("Nhap ma phieu nhap: ");
        String maPNH = sc.nextLine().trim();
        double tongTien = dsCTPNH.TinhTongTien(maPNH);
        dsPNH.CapNhatTongTien(dsCTPNH, maPNH);
        System.out.printf("Tong tien phieu nhap %s: %.0f VND%n", maPNH, tongTien);
    }

    private int nhapSo(Scanner sc) {
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}