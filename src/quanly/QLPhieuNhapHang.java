package quanly;

import danhsach.DSChiTietPhieuNhapHang;
import danhsach.DSPhieuNhapHang;
import java.util.Scanner;
import model.ChiTietPhieuNhapHang;
import model.PhieuMuon;
import model.PhieuNhapHang;

public class QLPhieuNhapHang extends QLThuVien {
    public QLPhieuNhapHang() {
        super();
    }

    public QLPhieuNhapHang(DSPhieuNhapHang dsPhieuNhap, DSChiTietPhieuNhapHang dsChiTiet) {
        this.dsPNH = dsPhieuNhap;
        this.dsCTPNH = dsChiTiet;
        this.dsPNH.napChiTiet(this.dsCTPNH);
    }

    @Override
    public void menu() {
        Menu(sc);
    }

    public void Menu(Scanner sc) {
        int choice;
        do {
            System.out.println();
            System.out.println("+-------------------------------------------+");
            System.out.println("|       MENU QUAN LY PHIEU NHAP HANG        |");
            System.out.println("+-------------------------------------------+");
            System.out.println("| 1) Xem danh sach phieu nhap hang          |");
            System.out.println("| 2) Them phieu nhap hang (kem chi tiet)    |");
            System.out.println("| 3) Tim kiem phieu nhap (xem chi tiet)     |");
            System.out.println("| 4) Sua thong tin phieu nhap               |");
            System.out.println("| 5) Xoa phieu nhap hang                    |");
            System.out.println("| 6) Thong ke phieu nhap hang               |");
            System.out.println("| 7) Quan ly chi tiet cua phieu nhap hang   |");
            System.out.println("| 0) Thoat                                  |");
            System.out.println("+-------------------------------------------+");
            System.out.print("Nhap lua chon tu 0 - 7: ");
            choice = nhapSo(sc);

            switch (choice) {
                case 1 -> {
                    dsPNH.ReadFile();
                    dsCTPNH.ReadFile();
                    dsPNH.napChiTiet(dsCTPNH);
                    dsPNH.Output();
                }
                case 2 -> {
                    themPhieuNhap(sc);
                    dsPNH.WriteFile();
                    dsCTPNH.WriteFile();
                }
                case 3 -> timPhieuNhap(sc);
                case 4 -> {
                    suaPhieuNhap(sc);
                    dsPNH.WriteFile();
                }
                case 5 -> {
                    xoaPhieuNhap(sc);
                    dsPNH.WriteFile();
                    dsCTPNH.WriteFile();
                }
                case 6 -> thongKe(sc);
                case 7 -> menuChiTietCuaPhieu(sc);
                case 0 -> System.out.println("Da thoat khoi Quan ly Phieu Nhap Hang.");
                default -> System.out.println("Lua chon khong hop le.");
            }
        } while (choice != 0);
    }

    private void menuChiTietCuaPhieu(Scanner sc) {
        dsPNH.ReadFile();
        dsCTPNH.ReadFile();
        dsPNH.napChiTiet(dsCTPNH);
        dsPNH.Output();

        System.out.print("Nhap ma phieu nhap can quan ly chi tiet (VD: PNH001): ");
        String maPNH = sc.nextLine().trim();
        PhieuNhapHang pnh = dsPNH.TimTheoMa(maPNH);
        if (pnh == null) {
            System.out.println("Khong tim thay phieu nhap co ma: " + maPNH);
            return;
        }

        int choice;
        do {
            System.out.println();
            System.out.println("+-------------------------------------------+");
            System.out.printf("|    CHI TIET PHIEU NHAP [%-14s]   |%n", pnh.getMaPNH());
            System.out.println("+-------------------------------------------+");
            System.out.println("| 1) Xem chi tiet cua phieu nay             |");
            System.out.println("| 2) Them chi tiet vao phieu nay            |");
            System.out.println("| 3) Sua chi tiet trong phieu nay           |");
            System.out.println("| 4) Xoa chi tiet khoi phieu nay            |");
            System.out.println("| 0) Quay lai menu phieu nhap hang          |");
            System.out.println("+-------------------------------------------+");
            System.out.print("Nhap lua chon tu 0 - 4: ");
            choice = nhapSo(sc);

            switch (choice) {
                case 1 -> pnh.xuatChiTiet();
                case 2 -> {
                    ChiTietPhieuNhapHang ct = new ChiTietPhieuNhapHang();
                    String maCTMoi = dsCTPNH.taoMaCTMoi();
                    ct.setMaCT(maCTMoi);
                    ct.setMaPNH(pnh.getMaPNH());
                    System.out.println("-> Ma chi tiet tu dong: " + maCTMoi);
                    System.out.print("Nhap ma sach (VD: S01): ");
                    ct.setMaSach(sc.nextLine().trim());
                    ct.setSoLuong(nhapSoDuong(sc, "Nhap so luong nhap: "));
                    ct.setDonGia(nhapSoThucDuong(sc, "Nhap don gia nhap: "));

                    if (dsCTPNH.Them(ct)) {
                        pnh.themChiTiet(ct);
                        dsCTPNH.WriteFile();
                        dsPNH.WriteFile();
                        System.out.println("=> Them chi tiet " + maCTMoi + " thanh cong! Tong tien moi: " + (long) pnh.getTongTien() + " VND");
                    } else {
                        System.out.println("Khong the them chi tiet!");
                    }
                }
                case 3 -> {
                    pnh.xuatChiTiet();
                    if (pnh.getSoChiTiet() == 0) break;
                    System.out.print("Nhap ma chi tiet can sua: ");
                    String maCT = sc.nextLine().trim();
                    ChiTietPhieuNhapHang ct = pnh.timChiTiet(maCT);
                    if (ct == null) {
                        System.out.println("Khong tim thay chi tiet " + maCT + " trong phieu " + pnh.getMaPNH());
                        break;
                    }
                    System.out.print("Nhap ma sach moi: ");
                    ct.setMaSach(sc.nextLine().trim());
                    ct.setSoLuong(nhapSoDuong(sc, "Nhap so luong moi: "));
                    ct.setDonGia(nhapSoThucDuong(sc, "Nhap don gia moi: "));
                    pnh.capNhatTongTien();
                    dsCTPNH.WriteFile();
                    dsPNH.WriteFile();
                    System.out.println("=> Sua chi tiet thanh cong! Tong tien moi: " + (long) pnh.getTongTien() + " VND");
                }
                case 4 -> {
                    pnh.xuatChiTiet();
                    if (pnh.getSoChiTiet() == 0) break;
                    System.out.print("Nhap ma chi tiet can xoa: ");
                    String maCT = sc.nextLine().trim();
                    if (pnh.xoaChiTiet(maCT)) {
                        dsCTPNH.Xoa(maCT);
                        dsCTPNH.WriteFile();
                        dsPNH.WriteFile();
                        System.out.println("=> Xoa chi tiet thanh cong! Tong tien moi: " + (long) pnh.getTongTien() + " VND");
                    } else {
                        System.out.println("Khong tim thay chi tiet " + maCT + " trong phieu " + pnh.getMaPNH());
                    }
                }
                case 0 -> System.out.println("Quay lai menu phieu nhap hang.");
                default -> System.out.println("Lua chon khong hop le!");
            }
        } while (choice != 0);
    }

    private void themPhieuNhap(Scanner sc) {
        PhieuNhapHang pnh = new PhieuNhapHang();
        pnh.Input(sc);
        if (!dsPNH.Them(pnh)) {
            System.out.println("Ma phieu nhap da ton tai.");
            return;
        }
        System.out.print("Phieu nay nhap bao nhieu dau sach? ");
        int k = nhapSo(sc);
        for (int i = 0; i < k; i++) {
            String maCTMoi = dsCTPNH.taoMaCTMoi();
            System.out.println("--- Chi tiet sach thu " + (i + 1) + " (Ma tu dong: " + maCTMoi + ") ---");
            ChiTietPhieuNhapHang ct = new ChiTietPhieuNhapHang();
            ct.setMaCT(maCTMoi);
            ct.setMaPNH(pnh.getMaPNH());
            System.out.print("Nhap ma sach (VD: S01): ");
            ct.setMaSach(sc.nextLine().trim());
            ct.setSoLuong(nhapSoDuong(sc, "Nhap so luong nhap: "));
            ct.setDonGia(nhapSoThucDuong(sc, "Nhap don gia nhap: "));

            if (dsCTPNH.Them(ct)) {
                pnh.themChiTiet(ct);
            }
        }
        System.out.println("=> Them phieu nhap kem chi tiet thanh cong! Tong tien: " + (long) pnh.getTongTien() + " VND");
    }

    private void timPhieuNhap(Scanner sc) {
        System.out.print("Nhap ma phieu nhap: ");
        PhieuNhapHang pnh = dsPNH.TimTheoMa(sc.nextLine().trim());
        if (pnh == null) {
            System.out.println("Khong tim thay phieu nhap.");
            return;
        }
        dsPNH.PrintHeader();
        pnh.Output();
        dsPNH.PrintFooter();
        pnh.xuatChiTiet();
    }

    private void suaPhieuNhap(Scanner sc) {
        System.out.print("Nhap ma phieu nhap can sua: ");
        PhieuNhapHang item = dsPNH.TimTheoMa(sc.nextLine().trim());
        if (item == null) {
            System.out.println("Khong tim thay phieu nhap.");
            return;
        }
        System.out.print("Nhap ma nha cung cap moi: ");
        item.setMaNCC(sc.nextLine().trim());
        System.out.print("Nhap ma nhan vien moi: ");
        item.setMaNV(sc.nextLine().trim());
        while (true) {
            System.out.print("Nhap ngay nhap moi (dd/MM/yyyy): ");
            String ngay = sc.nextLine().trim();
            if (PhieuMuon.isValidDate(ngay)) {
                item.setNgayNhap(ngay);
                break;
            }
            System.out.println("Ngay khong hop le!");
        }
        item.capNhatTongTien();
        System.out.println("Sua phieu nhap thanh cong.");
    }

    private void xoaPhieuNhap(Scanner sc) {
        System.out.print("Nhap ma phieu nhap can xoa: ");
        String maPNH = sc.nextLine().trim();
        PhieuNhapHang pnh = dsPNH.TimTheoMa(maPNH);
        if (pnh == null) {
            System.out.println("Khong tim thay phieu nhap.");
            return;
        }
        ChiTietPhieuNhapHang[] cts = dsCTPNH.TimTheoMaPNH(maPNH);
        for (ChiTietPhieuNhapHang ct : cts) {
            dsCTPNH.Xoa(ct.getMaCT());
        }
        dsPNH.Xoa(maPNH);
        System.out.println("Da xoa phieu nhap " + maPNH + " va toan bo chi tiet lien quan.");
    }

    private void thongKe(Scanner sc) {
        System.out.print("Nhap ma phieu nhap: ");
        String maPNH = sc.nextLine().trim();
        PhieuNhapHang pnh = dsPNH.TimTheoMa(maPNH);
        if (pnh == null) {
            System.out.println("Khong tim thay phieu nhap.");
            return;
        }
        System.out.printf("Tong tien phieu nhap %s: %.0f VND%n", maPNH, pnh.capNhatTongTien());
        pnh.xuatChiTiet();
    }

    private int nhapSo(Scanner sc) {
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private int nhapSoDuong(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            int val = nhapSo(sc);
            if (val > 0) return val;
            System.out.println("Vui long nhap so nguyen > 0!");
        }
    }

    private double nhapSoThucDuong(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double val = Double.parseDouble(sc.nextLine().trim());
                if (val > 0) return val;
                System.out.println("Vui long nhap so > 0!");
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap so hop le!");
            }
        }
    }
}
