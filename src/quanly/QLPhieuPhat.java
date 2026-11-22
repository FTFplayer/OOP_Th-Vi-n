package quanly;

import danhsach.*;
import model.*;

public class QLPhieuPhat extends QLThuVien {
    public QLPhieuPhat() {
        super();
    }

    @Override
    public void menu() {
        int choice;
        do {
            System.out.println();
            System.out.println("+-------------------------------------------+");
            System.out.println("|          MENU QUAN LY PHIEU PHAT          |");
            System.out.println("+-------------------------------------------+");
            System.out.println("| 1) Xem danh sach phieu phat               |");
            System.out.println("| 2) Them phieu phat (kem chi tiet)         |");
            System.out.println("| 3) Tim kiem phieu phat (xem chi tiet)     |");
            System.out.println("| 4) Sua thong tin phieu phat               |");
            System.out.println("| 5) Xoa phieu phat                         |");
            System.out.println("| 6) Thong ke phieu phat                    |");
            System.out.println("| 7) Quan ly chi tiet cua phieu phat        |");
            System.out.println("| 0) Thoat                                  |");
            System.out.println("+-------------------------------------------+");

            System.out.print("Nhap lua chon tu 0 - 7: ");
            choice = nhapLuaChon();

            switch (choice) {
                case 1 -> {
                    dsPP.ReadFile();
                    dsCTPP.ReadFile();
                    dsPP.napChiTiet(dsCTPP);
                    dsPP.Output();
                }
                case 2 -> {
                    ThemPP(dsBook, dsPM, dsQDP, dsCTPP);
                    dsPP.WriteFile();
                    dsCTPP.WriteFile();
                }
                case 3 -> menuTimKiem();
                case 4 -> {
                    dsPP.Sua();
                    dsPP.WriteFile();
                }
                case 5 -> {
                    xoaPhieuPhat();
                    dsPP.WriteFile();
                    dsCTPP.WriteFile();
                }
                case 6 -> menuThongKe();
                case 7 -> menuChiTietCuaPhieu();
                case 0 -> System.out.println("Da thoat khoi Quan ly Phieu Phat.");
                default -> System.out.println("Lua chon khong hop le.");
            }
        } while (choice != 0);
    }

    public void Menu() {
        menu();
    }

    private void menuChiTietCuaPhieu() {
        dsPP.ReadFile();
        dsCTPP.ReadFile();
        dsPP.napChiTiet(dsCTPP);
        dsPP.Output();

        System.out.print("Nhap ma phieu phat can quan ly chi tiet (VD: PP001): ");
        String maPP = sc.nextLine().trim();
        PhieuPhat pp = dsPP.TimtheoMaPP(maPP);
        if (pp == null) {
            System.out.println("Khong tim thay phieu phat co ma: " + maPP);
            return;
        }

        long soNgayTre = 0;
        PhieuMuon pm = dsPM.TimtheoMaPM(pp.getMaPM());
        if (pm != null && pm.getNgayTraLocalDate() != null) {
            soNgayTre = pp.tinhSoTraNgayTre(pm.getNgayTraLocalDate());
        }

        int choice;
        do {
            System.out.println();
            System.out.println("+-------------------------------------------+");
            System.out.printf("|     CHI TIET PHIEU PHAT [%-14s]  |%n", pp.getMaPP());
            System.out.println("+-------------------------------------------+");
            System.out.println("| 1) Xem chi tiet cua phieu nay             |");
            System.out.println("| 2) Them chi tiet vao phieu nay            |");
            System.out.println("| 3) Sua chi tiet trong phieu nay           |");
            System.out.println("| 4) Xoa chi tiet khoi phieu nay            |");
            System.out.println("| 0) Quay lai menu phieu phat               |");
            System.out.println("+-------------------------------------------+");
            System.out.print("Nhap lua chon tu 0 - 4: ");
            choice = nhapLuaChon();

            switch (choice) {
                case 1 -> pp.xuatChiTiet();
                case 2 -> {
                    ChiTietPhieuPhat ct = new ChiTietPhieuPhat();
                    ct.Input(pp.getMaPP(), dsQDP, dsBook);
                    double tien = dsQDP.TinhTienPhat(ct.getMaLD(), soNgayTre, ct.getMaSach(), dsBook);
                    ct.setTienPhat(Math.max(0, tien));
                    pp.themChiTiet(ct);
                    dsCTPP.Them(ct);
                    dsPP.WriteFile();
                    dsCTPP.WriteFile();
                    System.out.println("=> Them chi tiet thanh cong! Tong tien phat moi: " + (long) pp.getTongSoTienPhat() + " VND");
                }
                case 3 -> {
                    pp.xuatChiTiet();
                    if (pp.getSoChiTiet() == 0) break;
                    System.out.print("Nhap ma sach cua chi tiet can sua: ");
                    String maSachCu = sc.nextLine().trim();
                    if (pp.timChiTietTheoSach(maSachCu) == null) {
                        System.out.println("Khong tim thay chi tiet co ma sach " + maSachCu + " trong phieu " + pp.getMaPP());
                        break;
                    }
                    System.out.println("Nhap lai thong tin chi tiet moi:");
                    ChiTietPhieuPhat ctMoi = new ChiTietPhieuPhat();
                    ctMoi.Input(pp.getMaPP(), dsQDP, dsBook);
                    double tien = dsQDP.TinhTienPhat(ctMoi.getMaLD(), soNgayTre, ctMoi.getMaSach(), dsBook);
                    ctMoi.setTienPhat(Math.max(0, tien));

                    pp.xoaChiTietTheoSach(maSachCu);
                    dsCTPP.Xoa(pp.getMaPP(), maSachCu);
                    pp.themChiTiet(ctMoi);
                    dsCTPP.Them(ctMoi);
                    dsPP.WriteFile();
                    dsCTPP.WriteFile();
                    System.out.println("=> Sua chi tiet thanh cong! Tong tien phat moi: " + (long) pp.getTongSoTienPhat() + " VND");
                }
                case 4 -> {
                    pp.xuatChiTiet();
                    if (pp.getSoChiTiet() == 0) break;
                    System.out.print("Nhap ma sach can xoa khoi chi tiet: ");
                    String maSach = sc.nextLine().trim();
                    if (pp.xoaChiTietTheoSach(maSach)) {
                        dsCTPP.Xoa(pp.getMaPP(), maSach);
                        dsPP.WriteFile();
                        dsCTPP.WriteFile();
                        System.out.println("=> Xoa chi tiet thanh cong! Tong tien phat moi: " + (long) pp.getTongSoTienPhat() + " VND");
                    } else {
                        System.out.println("Khong tim thay chi tiet de xoa!");
                    }
                }
                case 0 -> System.out.println("Quay lai menu phieu phat.");
                default -> System.out.println("Lua chon khong hop le!");
            }
        } while (choice != 0);
    }

    public void ThemPP(DSBook dsBook, DSPhieuMuon dsPM, DSQuyDinhPhat dsQDP, DSChiTietPhieuPhat dsCTPP) {
        System.out.println("THUC HIEN THEM PHIEU PHAT");
        PhieuPhat pp = new PhieuPhat();
        pp.Input(dsPP, dsPM);

        long soNgayTre = 0;
        PhieuMuon pm = dsPM.TimtheoMaPM(pp.getMaPM());
        if (pm != null && pm.getNgayTraLocalDate() != null) {
            soNgayTre = pp.tinhSoTraNgayTre(pm.getNgayTraLocalDate());
        }

        System.out.print("Nhap so chi tiet vi pham cua phieu phat: ");
        int k = nhapLuaChon();
        if (k < 0) k = 0;

        for (int i = 0; i < k; i++) {
            System.out.println("--- Chi tiet vi pham thu " + (i + 1) + " ---");
            ChiTietPhieuPhat ct = new ChiTietPhieuPhat();
            ct.Input(pp.getMaPP(), dsQDP, dsBook);
            double tien = dsQDP.TinhTienPhat(ct.getMaLD(), soNgayTre, ct.getMaSach(), dsBook);
            ct.setTienPhat(Math.max(0, tien));
            pp.themChiTiet(ct);
            dsCTPP.Them(ct);
        }

        dsPP.Them(pp);
        System.out.println("=> Them phieu phat thanh cong! Tong tien phat: " + (long) pp.getTongSoTienPhat() + " VND");
    }

    private void xoaPhieuPhat() {
        System.out.print("Nhap ma phieu phat can xoa: ");
        String maPP = sc.nextLine().trim();
        PhieuPhat pp = dsPP.TimtheoMaPP(maPP);
        if (pp == null) {
            System.out.println("Khong tim thay phieu phat de xoa!");
            return;
        }
        ChiTietPhieuPhat[] cts = dsCTPP.Tim(maPP);
        if (cts != null) {
            for (ChiTietPhieuPhat ct : cts) {
                dsCTPP.Xoa(ct.getMaPP(), ct.getMaSach());
            }
        }
        dsPP.Xoa(maPP);
        System.out.println("Da xoa phieu phat " + maPP + " va toan bo chi tiet lien quan.");
    }

    private void menuTimKiem() {
        int choice;
        do {
            System.out.println("+-------------------------------------------+");
            System.out.println("|         MENU TIM KIEM PHIEU PHAT          |");
            System.out.println("+-------------------------------------------+");
            System.out.println("| 1) Tim theo ma phieu phat.                |");
            System.out.println("| 2) Tim theo ma phieu muon.                |");
            System.out.println("| 3) Tim theo ngay phat.                    |");
            System.out.println("| 0) Thoat.                                 |");
            System.out.println("+-------------------------------------------+");

            System.out.print("Nhap lua chon: ");
            choice = nhapLuaChon();

            switch (choice) {
                case 1 -> dsPP.TimtheoMaPP();
                case 2 -> dsPP.TimtheoMaPM();
                case 3 -> dsPP.TimTheoNgayPhat();
                case 0 -> System.out.println("Quay lai menu quan ly.");
                default -> System.out.println("Lua chon khong hop le!");
            }
        } while (choice != 0);
    }

    private void menuThongKe() {
        int choice;
        do {
            System.out.println("+-------------------------------------------+");
            System.out.println("|          MENU THONG KE PHIEU PHAT         |");
            System.out.println("+-------------------------------------------+");
            System.out.println("| 1) Tong so phieu phat.                    |");
            System.out.println("| 2) Tong tien phat.                        |");
            System.out.println("| 3) Phieu phat co tien phat lon nhat.      |");
            System.out.println("| 4) Thong ke theo ma phieu muon.           |");
            System.out.println("| 5) Thong ke theo khoang ngay.             |");
            System.out.println("| 6) Thong ke tong hop.                     |");
            System.out.println("| 0) Thoat.                                 |");
            System.out.println("+-------------------------------------------+");

            System.out.print("Nhap lua chon: ");
            choice = nhapLuaChon();

            switch (choice) {
                case 1 -> dsPP.ThongKeTongSoPP();
                case 2 -> dsPP.ThongKeTongTienPhat();
                case 3 -> dsPP.ThongKePhieuPhatMax();
                case 4 -> dsPP.ThongKePPtheoMaPM();
                case 5 -> dsPP.ThongKeTheoKhoangNgay();
                case 6 -> dsPP.ThongKeTongHop();
                case 0 -> System.out.println("Quay lai menu quan ly.");
                default -> System.out.println("Lua chon khong hop le!");
            }
        } while (choice != 0);
    }

    private int nhapLuaChon() {
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
