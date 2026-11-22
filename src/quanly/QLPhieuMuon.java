package quanly;

import danhsach.DSChiTietPhieuMuon;
import danhsach.DSPhieuMuon;
import java.time.LocalDate;
import java.util.Scanner;
import model.ChiTietPhieuMuon;
import model.PhieuMuon;

public class QLPhieuMuon extends QLThuVien {
    public QLPhieuMuon() {
        super();
    }

    public QLPhieuMuon(DSPhieuMuon dsPhieuMuon, DSChiTietPhieuMuon dsChiTiet) {
        this.dsPM = dsPhieuMuon;
        this.dsCTPM = dsChiTiet;
        this.dsPM.napChiTiet(this.dsCTPM);
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
            System.out.println("|          MENU QUAN LY PHIEU MUON          |");
            System.out.println("+-------------------------------------------+");
            System.out.println("| 1) Xem danh sach phieu muon               |");
            System.out.println("| 2) Them phieu muon (kem chi tiet)         |");
            System.out.println("| 3) Tim kiem phieu muon (xem chi tiet)     |");
            System.out.println("| 4) Sua thong tin phieu muon               |");
            System.out.println("| 5) Xoa phieu muon                         |");
            System.out.println("| 6) Thong ke phieu muon                    |");
            System.out.println("| 7) Quan ly chi tiet cua phieu muon        |");
            System.out.println("| 0) Thoat                                  |");
            System.out.println("+-------------------------------------------+");
            System.out.print("Nhap lua chon tu 0 - 7: ");
            choice = nhapSo(sc);

            switch (choice) {
                case 1 -> {
                    dsPM.ReadFile();
                    dsCTPM.ReadFile();
                    dsPM.napChiTiet(dsCTPM);
                    dsPM.Output();
                }
                case 2 -> {
                    themPhieuMuon(sc);
                    dsPM.WriteFile();
                    dsCTPM.WriteFile();
                }
                case 3 -> timPhieuMuon(sc);
                case 4 -> {
                    suaPhieuMuon(sc);
                    dsPM.WriteFile();
                }
                case 5 -> {
                    xoaPhieuMuon(sc);
                    dsPM.WriteFile();
                    dsCTPM.WriteFile();
                }
                case 6 -> thongKe(sc);
                case 7 -> menuChiTietCuaPhieu(sc);
                case 0 -> System.out.println("Da thoat khoi Quan ly Phieu Muon.");
                default -> System.out.println("Lua chon khong hop le.");
            }
        } while (choice != 0);
    }

    private void menuChiTietCuaPhieu(Scanner sc) {
        dsPM.ReadFile();
        dsCTPM.ReadFile();
        dsPM.napChiTiet(dsCTPM);
        dsPM.Output();

        System.out.print("Nhap ma phieu muon can quan ly chi tiet (VD: PM001): ");
        String maPM = sc.nextLine().trim();
        PhieuMuon pm = dsPM.TimTheoMa(maPM);
        if (pm == null) {
            System.out.println("Khong tim thay phieu muon co ma: " + maPM);
            return;
        }

        int choice;
        do {
            System.out.println();
            System.out.println("+-------------------------------------------+");
            System.out.printf("|     CHI TIET PHIEU MUON [%-14s]  |%n", pm.getMaPM());
            System.out.println("+-------------------------------------------+");
            System.out.println("| 1) Xem chi tiet cua phieu nay             |");
            System.out.println("| 2) Them chi tiet vao phieu nay            |");
            System.out.println("| 3) Sua chi tiet trong phieu nay           |");
            System.out.println("| 4) Xoa chi tiet khoi phieu nay            |");
            System.out.println("| 0) Quay lai menu phieu muon               |");
            System.out.println("+-------------------------------------------+");
            System.out.print("Nhap lua chon tu 0 - 4: ");
            choice = nhapSo(sc);

            switch (choice) {
                case 1 -> pm.xuatChiTiet();
                case 2 -> {
                    ChiTietPhieuMuon ct = new ChiTietPhieuMuon();
                    String maCTMoi = dsCTPM.taoMaCTMoi();
                    ct.setMaCT(maCTMoi);
                    ct.setMaPM(pm.getMaPM());
                    System.out.println("-> Ma chi tiet tu dong: " + maCTMoi);
                    System.out.print("Nhap ma sach (VD: S01): ");
                    ct.setMaSach(sc.nextLine().trim());
                    ct.setSoLuong(nhapSoDuong(sc, "Nhap so luong muon: "));

                    if (dsCTPM.Them(ct)) {
                        pm.themChiTiet(ct);
                        dsCTPM.WriteFile();
                        System.out.println("=> Them chi tiet " + maCTMoi + " thanh cong!");
                    } else {
                        System.out.println("Khong the them chi tiet!");
                    }
                }
                case 3 -> {
                    pm.xuatChiTiet();
                    if (pm.getSoChiTiet() == 0) break;
                    System.out.print("Nhap ma chi tiet can sua: ");
                    String maCT = sc.nextLine().trim();
                    ChiTietPhieuMuon ct = pm.timChiTiet(maCT);
                    if (ct == null) {
                        System.out.println("Khong tim thay chi tiet " + maCT + " trong phieu " + pm.getMaPM());
                        break;
                    }
                    System.out.print("Nhap ma sach moi: ");
                    ct.setMaSach(sc.nextLine().trim());
                    ct.setSoLuong(nhapSoDuong(sc, "Nhap so luong moi: "));
                    dsCTPM.WriteFile();
                    System.out.println("=> Sua chi tiet thanh cong!");
                }
                case 4 -> {
                    pm.xuatChiTiet();
                    if (pm.getSoChiTiet() == 0) break;
                    System.out.print("Nhap ma chi tiet can xoa: ");
                    String maCT = sc.nextLine().trim();
                    if (pm.xoaChiTiet(maCT)) {
                        dsCTPM.Xoa(maCT);
                        dsCTPM.WriteFile();
                        System.out.println("=> Xoa chi tiet thanh cong!");
                    } else {
                        System.out.println("Khong tim thay chi tiet " + maCT + " trong phieu " + pm.getMaPM());
                    }
                }
                case 0 -> System.out.println("Quay lai menu phieu muon.");
                default -> System.out.println("Lua chon khong hop le!");
            }
        } while (choice != 0);
    }

    private void themPhieuMuon(Scanner sc) {
        PhieuMuon pm = new PhieuMuon();
        pm.Input(sc);
        if (!dsPM.Them(pm)) {
            System.out.println("Ma phieu muon da ton tai hoac ngay khong hop le.");
            return;
        }
        System.out.print("Phieu nay muon bao nhieu dau sach? ");
        int k = nhapSo(sc);
        for (int i = 0; i < k; i++) {
            String maCTMoi = dsCTPM.taoMaCTMoi();
            System.out.println("--- Chi tiet sach thu " + (i + 1) + " (Ma tu dong: " + maCTMoi + ") ---");
            ChiTietPhieuMuon ct = new ChiTietPhieuMuon();
            ct.setMaCT(maCTMoi);
            ct.setMaPM(pm.getMaPM());
            System.out.print("Nhap ma sach (VD: S01): ");
            ct.setMaSach(sc.nextLine().trim());
            ct.setSoLuong(nhapSoDuong(sc, "Nhap so luong muon: "));

            if (dsCTPM.Them(ct)) {
                pm.themChiTiet(ct);
            }
        }
        System.out.println("=> Them phieu muon kem chi tiet thanh cong!");
    }

    private void timPhieuMuon(Scanner sc) {
        System.out.print("Nhap ma phieu muon: ");
        PhieuMuon pm = dsPM.TimTheoMa(sc.nextLine().trim());
        if (pm == null) {
            System.out.println("Khong tim thay phieu muon.");
            return;
        }
        dsPM.PrintHeader();
        pm.Output();
        dsPM.PrintFooter();
        pm.xuatChiTiet();
    }

    private void suaPhieuMuon(Scanner sc) {
        System.out.print("Nhap ma phieu muon can sua: ");
        PhieuMuon item = dsPM.TimTheoMa(sc.nextLine().trim());
        if (item == null) {
            System.out.println("Khong tim thay phieu muon.");
            return;
        }
        System.out.print("Nhap ma doc gia moi: ");
        item.setMaDG(sc.nextLine().trim());
        System.out.print("Nhap ma nhan vien moi: ");
        item.setMaNV(sc.nextLine().trim());
        while (true) {
            System.out.print("Nhap ngay muon moi (dd/MM/yyyy): ");
            String strMuon = sc.nextLine().trim();
            if (PhieuMuon.isValidDate(strMuon)) {
                LocalDate nm = PhieuMuon.parseDate(strMuon);
                item.setNgayMuon(nm); // Tu dong cap nhat ngayTra = nm + 7 ngay
                System.out.println("-> Han tra da cap nhat tu dong (+7 ngay): " + item.getNgayTraStr());
                break;
            }
            System.out.println("Ngay khong hop le!");
        }
        System.out.println("Sua phieu muon thanh cong.");
    }

    private void xoaPhieuMuon(Scanner sc) {
        System.out.print("Nhap ma phieu muon can xoa: ");
        String maPM = sc.nextLine().trim();
        PhieuMuon pm = dsPM.TimTheoMa(maPM);
        if (pm == null) {
            System.out.println("Khong tim thay phieu muon.");
            return;
        }
        ChiTietPhieuMuon[] cts = dsCTPM.TimTheoMaPM(maPM);
        for (ChiTietPhieuMuon ct : cts) {
            dsCTPM.Xoa(ct.getMaCT());
        }
        dsPM.Xoa(maPM);
        System.out.println("Da xoa phieu muon " + maPM + " va toan bo chi tiet lien quan.");
    }

    private void thongKe(Scanner sc) {
        System.out.print("Nhap ma phieu muon: ");
        String maPM = sc.nextLine().trim();
        PhieuMuon pm = dsPM.TimTheoMa(maPM);
        if (pm == null) {
            System.out.println("Khong tim thay phieu muon.");
            return;
        }
        System.out.println("Tong so sach muon cua phieu " + maPM + ": " + pm.tinhTongSoSach() + " cuon");
        pm.xuatChiTiet();
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
}
