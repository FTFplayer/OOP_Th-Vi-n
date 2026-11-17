package quanly;

import danhsach.DSChiTietPhieuNhapHang;
import danhsach.DSPhieuNhapHang;
import java.util.Scanner;
import model.ChiTietPhieuNhapHang;
import model.PhieuNhapHang;

public class QLPhieuNhapHang {
    private static final String FILE_PHIEU_NHAP = "data/PhieuNhapHang.txt";
    private static final String FILE_CHI_TIET = "data/ChiTietPhieuNhapHang.txt";

    private final DSPhieuNhapHang dsPhieuNhap;
    private final DSChiTietPhieuNhapHang dsChiTiet;

    public QLPhieuNhapHang() {
        this(new DSPhieuNhapHang(), new DSChiTietPhieuNhapHang());
    }

    public QLPhieuNhapHang(DSPhieuNhapHang dsPhieuNhap, DSChiTietPhieuNhapHang dsChiTiet) {
        this.dsPhieuNhap = dsPhieuNhap;
        this.dsChiTiet = dsChiTiet;
    }

    public DSPhieuNhapHang getDsPhieuNhap() {
        return dsPhieuNhap;
    }

    public DSChiTietPhieuNhapHang getDsChiTiet() {
        return dsChiTiet;
    }

    public void menu() {
        Menu(new Scanner(System.in));
    }

    public void Menu(Scanner sc) {
        int choice;
        do {
            System.out.println("\n--- QUAN LY PHIEU NHAP HANG ---");
            System.out.println("1. Xuat danh sach phieu nhap");
            System.out.println("2. Them phieu nhap");
            System.out.println("3. Tim phieu nhap theo ma");
            System.out.println("4. Sua phieu nhap");
            System.out.println("5. Xoa phieu nhap");
            System.out.println("6. Xuat chi tiet phieu nhap");
            System.out.println("7. Them chi tiet phieu nhap");
            System.out.println("8. Tim chi tiet theo ma phieu");
            System.out.println("9. Sua chi tiet phieu nhap");
            System.out.println("10. Xoa chi tiet phieu nhap");
            System.out.println("11. Thong ke tong tien phieu nhap");
            System.out.println("12. Ghi file");
            System.out.println("13. Doc file");
            System.out.println("0. Thoat");
            System.out.print("Chon: ");
            choice = nhapSo(sc);

            switch (choice) {
                case 1 -> dsPhieuNhap.Output();
                case 2 -> themPhieuNhap(sc);
                case 3 -> timPhieuNhap(sc);
                case 4 -> suaPhieuNhap(sc);
                case 5 -> xoaPhieuNhap(sc);
                case 6 -> dsChiTiet.Output();
                case 7 -> themChiTiet(sc);
                case 8 -> timChiTiet(sc);
                case 9 -> suaChiTiet(sc);
                case 10 -> xoaChiTiet(sc);
                case 11 -> thongKe(sc);
                case 12 -> ghiFile();
                case 13 -> docFile();
                case 0 -> System.out.println("Thoat menu phieu nhap hang.");
                default -> System.out.println("Lua chon khong hop le.");
            }
        } while (choice != 0);
    }

    private void themPhieuNhap(Scanner sc) {
        PhieuNhapHang item = new PhieuNhapHang();
        item.Input(sc);
        System.out.println(dsPhieuNhap.Them(item)
                ? "Them phieu nhap thanh cong!" : "Ma phieu nhap da ton tai.");
    }

    private void timPhieuNhap(Scanner sc) {
        System.out.print("Nhap ma phieu nhap: ");
        PhieuNhapHang item = dsPhieuNhap.TimTheoMa(sc.nextLine().trim());
        if (item == null) {
            System.out.println("Khong tim thay phieu nhap.");
        } else {
            item.Output();
        }
    }

    private void suaPhieuNhap(Scanner sc) {
        System.out.print("Nhap ma phieu nhap can sua: ");
        PhieuNhapHang item = dsPhieuNhap.TimTheoMa(sc.nextLine().trim());
        if (item == null) {
            System.out.println("Khong tim thay phieu nhap.");
            return;
        }
        System.out.print("Nhap ma nha cung cap moi: ");
        item.setMaNCC(sc.nextLine().trim());
        System.out.print("Nhap ma nhan vien moi: ");
        item.setMaNV(sc.nextLine().trim());
        System.out.print("Nhap ngay nhap moi: ");
        item.setNgayNhap(sc.nextLine().trim());
        dsPhieuNhap.CapNhatTongTien(dsChiTiet, item.getMaPNH());
        System.out.println("Sua phieu nhap thanh cong.");
    }

    private void xoaPhieuNhap(Scanner sc) {
        System.out.print("Nhap ma phieu nhap can xoa: ");
        String maPNH = sc.nextLine().trim();
        if (dsChiTiet.TimTheoMaPNH(maPNH).length > 0) {
            System.out.println("Khong the xoa phieu nhap dang co chi tiet.");
        } else {
            System.out.println(dsPhieuNhap.Xoa(maPNH)
                    ? "Xoa phieu nhap thanh cong." : "Khong tim thay phieu nhap.");
        }
    }

    private void themChiTiet(Scanner sc) {
        ChiTietPhieuNhapHang item = new ChiTietPhieuNhapHang();
        item.Input(sc);
        if (dsChiTiet.Them(item)) {
            dsPhieuNhap.CapNhatTongTien(dsChiTiet, item.getMaPNH());
            System.out.println("Them chi tiet thanh cong.");
        } else {
            System.out.println("Ma chi tiet da ton tai.");
        }
    }

    private void timChiTiet(Scanner sc) {
        System.out.print("Nhap ma phieu nhap: ");
        ChiTietPhieuNhapHang[] items = dsChiTiet.TimTheoMaPNH(sc.nextLine().trim());
        if (items.length == 0) {
            System.out.println("Khong co chi tiet cho phieu nhap nay.");
            return;
        }
        for (ChiTietPhieuNhapHang item : items) item.Output();
    }

    private void suaChiTiet(Scanner sc) {
        System.out.print("Nhap ma chi tiet can sua: ");
        ChiTietPhieuNhapHang item = dsChiTiet.TimTheoMaCT(sc.nextLine().trim());
        if (item == null) {
            System.out.println("Khong tim thay chi tiet.");
            return;
        }
        String maPNHCu = item.getMaPNH();
        System.out.print("Nhap ma phieu nhap moi: ");
        item.setMaPNH(sc.nextLine().trim());
        System.out.print("Nhap ma sach moi: ");
        item.setMaSach(sc.nextLine().trim());
        System.out.print("Nhap so luong moi: ");
        item.setSoLuong(Integer.parseInt(sc.nextLine().trim()));
        System.out.print("Nhap don gia moi: ");
        item.setDonGia(Double.parseDouble(sc.nextLine().trim()));
        dsPhieuNhap.CapNhatTongTien(dsChiTiet, maPNHCu);
        dsPhieuNhap.CapNhatTongTien(dsChiTiet, item.getMaPNH());
        System.out.println("Sua chi tiet thanh cong.");
    }

    private void xoaChiTiet(Scanner sc) {
        System.out.print("Nhap ma chi tiet can xoa: ");
        String maCT = sc.nextLine().trim();
        ChiTietPhieuNhapHang item = dsChiTiet.TimTheoMaCT(maCT);
        if (item == null) {
            System.out.println("Khong tim thay chi tiet.");
            return;
        }
        String maPNH = item.getMaPNH();
        dsChiTiet.Xoa(maCT);
        dsPhieuNhap.CapNhatTongTien(dsChiTiet, maPNH);
        System.out.println("Xoa chi tiet thanh cong.");
    }

    private void thongKe(Scanner sc) {
        System.out.print("Nhap ma phieu nhap: ");
        String maPNH = sc.nextLine().trim();
        double tongTien = dsChiTiet.TinhTongTien(maPNH);
        dsPhieuNhap.CapNhatTongTien(dsChiTiet, maPNH);
        System.out.printf("Tong tien phieu nhap %s: %.2f%n", maPNH, tongTien);
    }

    private void ghiFile() {
        dsPhieuNhap.ghiFile(FILE_PHIEU_NHAP);
        dsChiTiet.ghiFile(FILE_CHI_TIET);
        System.out.println("Ghi file phieu nhap thanh cong.");
    }

    private void docFile() {
        try {
            dsPhieuNhap.docFile(FILE_PHIEU_NHAP);
            dsChiTiet.docFile(FILE_CHI_TIET);
            System.out.println("Doc file phieu nhap thanh cong.");
        } catch (IllegalStateException e) {
            System.out.println("Loi doc file: " + e.getMessage());
        }
    }

    private int nhapSo(Scanner sc) {
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
