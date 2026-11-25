package quanly;

import danhsach.DSChiTietPhieuNhapHang;
import danhsach.DSPhieuNhapHang;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import model.Book;
import model.ChiTietPhieuNhapHang;
import model.PhieuMuon;
import model.PhieuNhapHang;

public class QLPhieuNhapHang extends QLThuVien {
    private static final DateTimeFormatter FORMAT_NGAY_THONG_KE =
            DateTimeFormatter.ofPattern("dd/MM/uuuu");

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
                    dsBook.WriteFile();
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
                    dsBook.WriteFile();
                }
                case 6 -> menuThongKe(sc);
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
        dsBook.ReadFile();
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
                    if (nhapVaThemChiTiet(sc, pnh)) {
                        dsCTPNH.WriteFile();
                        dsPNH.WriteFile();
                        dsBook.WriteFile();
                        System.out.println("=> Cap nhat tong tien phieu nhap: " + (long) pnh.getTongTien() + " VND");
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
                    String maSachCu = ct.getMaSach();
                    int soLuongCu = ct.getSoLuong();
                    String maSachMoi = nhapMaSachCoSanHoacBo(sc);
                    if (maSachMoi == null) break;
                    int soLuongMoi = nhapSoDuong(sc, "Nhap so luong moi: ");
                    double donGiaMoi = nhapSoThucDuong(sc, "Nhap don gia moi: ");

                    if (!capNhatKhoKhiSuaNhap(maSachCu, soLuongCu, maSachMoi, soLuongMoi)) {
                        break;
                    }
                    ct.setMaSach(maSachMoi);
                    ct.setSoLuong(soLuongMoi);
                    ct.setDonGia(donGiaMoi);
                    pnh.capNhatTongTien();
                    dsCTPNH.WriteFile();
                    dsPNH.WriteFile();
                    dsBook.WriteFile();
                    System.out.println("=> Sua chi tiet thanh cong! Tong tien moi: " + (long) pnh.getTongTien() + " VND");
                }
                case 4 -> {
                    pnh.xuatChiTiet();
                    if (pnh.getSoChiTiet() == 0) break;
                    System.out.print("Nhap ma chi tiet can xoa: ");
                    String maCT = sc.nextLine().trim();
                    ChiTietPhieuNhapHang ct = pnh.timChiTiet(maCT);
                    if (ct == null) {
                        System.out.println("Khong tim thay chi tiet " + maCT + " trong phieu " + pnh.getMaPNH());
                        break;
                    }
                    if (!dsBook.giamKhoKhiHuyNhap(ct.getMaSach(), ct.getSoLuong())) {
                        break;
                    }
                    pnh.xoaChiTiet(maCT);
                    dsCTPNH.Xoa(maCT);
                    dsCTPNH.WriteFile();
                    dsPNH.WriteFile();
                    dsBook.WriteFile();
                    System.out.println("=> Xoa chi tiet thanh cong! Tong tien moi: " + (long) pnh.getTongTien() + " VND");
                }
                case 0 -> System.out.println("Quay lai menu phieu nhap hang.");
                default -> System.out.println("Lua chon khong hop le!");
            }
        } while (choice != 0);
    }

    // Nhap 1 dong chi tiet nhap hang: tu dong phan nhanh sach cu / sach moi
    private boolean nhapVaThemChiTiet(Scanner sc, PhieuNhapHang pnh) {
        String maCTMoi = dsCTPNH.taoMaCTMoi();
        System.out.println("-> Ma chi tiet tu dong: " + maCTMoi);

        while (true) {
            System.out.print("Nhap ma sach (VD: S01): ");
            String maSach = sc.nextLine().trim();
            Book sach = dsBook.timkiemBook(maSach);

            if (sach != null) {
                // Truong hop 1: Sach da co trong DSBook -> cong don SoLuong + SoLuongConLai
                System.out.println("-> Sach hien co: " + sach.getTenSach()
                        + " (Ton kho: " + sach.getSoLuongConLai() + "/" + sach.getSoLuong() + ")");
                int slNhap = nhapSoDuong(sc, "Nhap so luong nhap: ");
                double donGiaNhap = nhapSoThucDuong(sc, "Nhap don gia nhap: ");

                ChiTietPhieuNhapHang ct = new ChiTietPhieuNhapHang(maCTMoi, pnh.getMaPNH(), sach.getMaSach(), slNhap, donGiaNhap);
                if (dsCTPNH.Them(ct)) {
                    dsBook.nhapHangSachCu(sach.getMaSach(), slNhap);
                    pnh.themChiTiet(ct);
                    System.out.println("=> Da cong them " + slNhap + " cuon vao kho cho sach " + sach.getMaSach());
                    return true;
                }
                return false;
            } else {
                // Truong hop 2: Sach chua co trong DSBook -> hoi xac nhan tao sach moi
                System.out.print("Ma sach " + maSach + " chua co trong kho, them sach moi luon khong? (Y/N): ");
                String chon = sc.nextLine().trim();
                if (!chon.equalsIgnoreCase("Y")) {
                    System.out.print("Ban co muon nhap lai ma sach khac khong? (Y/N): ");
                    if (sc.nextLine().trim().equalsIgnoreCase("Y")) {
                        continue;
                    }
                    return false;
                }
                while (!dsBook.CheckHopLe(maSach) || dsBook.CheckMaSach(maSach)) {
                    System.out.print("Ma sach khong hop le (phai co dang Sxx). Nhap lai ma sach: ");
                    maSach = sc.nextLine().trim();
                }
                int slNhap = nhapSoDuong(sc, "Nhap so luong nhap: ");
                double donGiaNhap = nhapSoThucDuong(sc, "Nhap don gia nhap: ");

                if (dsBook.themSachTuPhieuNhap(maSach, slNhap, donGiaNhap, dstl)) {
                    ChiTietPhieuNhapHang ct = new ChiTietPhieuNhapHang(maCTMoi, pnh.getMaPNH(), maSach, slNhap, donGiaNhap);
                    if (dsCTPNH.Them(ct)) {
                        pnh.themChiTiet(ct);
                        return true;
                    }
                }
                return false;
            }
        }
    }

    private String nhapMaSachCoSanHoacBo(Scanner sc) {
        while (true) {
            System.out.print("Nhap ma sach moi (phai ton tai trong DS sach, bo trong de huy): ");
            String ma = sc.nextLine().trim();
            if (ma.isEmpty()) return null;
            if (dsBook.timkiemBook(ma) != null) return ma;
            System.out.println("Khong tim thay ma sach " + ma + " trong kho!");
        }
    }

    private boolean capNhatKhoKhiSuaNhap(String maSachCu, int soLuongCu, String maSachMoi, int soLuongMoi) {
        if (maSachCu.equalsIgnoreCase(maSachMoi)) {
            if (soLuongMoi > soLuongCu) {
                return dsBook.nhapHangSachCu(maSachMoi, soLuongMoi - soLuongCu);
            }
            if (soLuongMoi < soLuongCu) {
                return dsBook.giamKhoKhiHuyNhap(maSachMoi, soLuongCu - soLuongMoi);
            }
            return true;
        }
        if (!dsBook.giamKhoKhiHuyNhap(maSachCu, soLuongCu)) {
            return false;
        }
        if (!dsBook.nhapHangSachCu(maSachMoi, soLuongMoi)) {
            dsBook.nhapHangSachCu(maSachCu, soLuongCu);
            return false;
        }
        return true;
    }

    private String nhapMaNCCHopLe(Scanner sc) {
        dsProvider.ReadFile();
        while (true) {
            System.out.print("Nhap ma nha cung cap (VD: NCC01): ");
            String maNCC = sc.nextLine().trim();
            if (dsProvider.Tim(maNCC) != -1) return maNCC;
            System.out.println("Ma nha cung cap khong ton tai! Vui long nhap lai.");
        }
    }

    private String nhapMaNVHopLe(Scanner sc) {
        dsEmployee.ReadFile();
        while (true) {
            System.out.print("Nhap ma nhan vien (VD: NV01): ");
            String maNV = sc.nextLine().trim();
            if (dsEmployee.TimEmployeeTheoMa(maNV) != -1) return maNV;
            System.out.println("Ma nhan vien khong ton tai! Vui long nhap lai.");
        }
    }

    private void themPhieuNhap(Scanner sc) {
        dsBook.ReadFile();
        PhieuNhapHang pnh = new PhieuNhapHang();
        String maPNH;
        while (true) {
            System.out.print("Nhap ma phieu nhap hang (VD: PNH021): ");
            maPNH = sc.nextLine().trim();
            if (!maPNH.isEmpty() && dsPNH.TimTheoMa(maPNH) == null) break;
            System.out.println("Ma phieu nhap rong hoac da ton tai! Vui long nhap lai.");
        }
        pnh.setMaPNH(maPNH);
        pnh.setMaNCC(nhapMaNCCHopLe(sc));
        pnh.setMaNV(nhapMaNVHopLe(sc));
        while (true) {
            System.out.print("Nhap ngay nhap (dd/MM/yyyy): ");
            String ngay = sc.nextLine().trim();
            if (PhieuMuon.isValidDate(ngay)) {
                pnh.setNgayNhap(ngay);
                break;
            }
            System.out.println("Ngay khong hop le (dd/MM/yyyy)!");
        }
        pnh.setTongTien(0);

        if (!dsPNH.Them(pnh)) {
            System.out.println("Khong the them phieu nhap!");
            return;
        }
        System.out.print("Phieu nay nhap bao nhieu dau sach? ");
        int k = nhapSo(sc);
        for (int i = 0; i < k; i++) {
            System.out.println("--- Chi tiet sach thu " + (i + 1) + " ---");
            nhapVaThemChiTiet(sc, pnh);
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
        item.setMaNCC(nhapMaNCCHopLe(sc));
        item.setMaNV(nhapMaNVHopLe(sc));
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
        dsBook.ReadFile();
        System.out.print("Nhap ma phieu nhap can xoa: ");
        String maPNH = sc.nextLine().trim();
        PhieuNhapHang pnh = dsPNH.TimTheoMa(maPNH);
        if (pnh == null) {
            System.out.println("Khong tim thay phieu nhap.");
            return;
        }
        ChiTietPhieuNhapHang[] cts = dsCTPNH.TimTheoMaPNH(maPNH);
        for (ChiTietPhieuNhapHang ct : cts) {
            if (!dsBook.coTheGiamKhoKhiHuyNhap(ct.getMaSach(), ct.getSoLuong())) {
                System.out.println("Khong the xoa phieu nhap " + maPNH
                        + " vi sach " + ct.getMaSach() + " khong du ton kho de tru nguoc (da co nguoi muon)!");
                return;
            }
        }
        for (ChiTietPhieuNhapHang ct : cts) {
            dsBook.giamKhoKhiHuyNhap(ct.getMaSach(), ct.getSoLuong());
            dsCTPNH.Xoa(ct.getMaCT());
        }
        dsPNH.Xoa(maPNH);
        System.out.println("Da xoa phieu nhap " + maPNH + ", xoa chi tiet va cap nhat lai kho sach.");
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

    private void menuThongKe(Scanner sc) {
        int choice;
        do {
            System.out.println();
            System.out.println("+-------------------------------------------+");
            System.out.println("|     MENU THONG KE PHIEU NHAP HANG         |");
            System.out.println("+-------------------------------------------+");
            System.out.println("| 1) Thong ke sach trong phieu nhap         |");
            System.out.println("| 2) Thong ke theo ngay nhap                |");
            System.out.println("| 0) Quay lai                               |");
            System.out.println("+-------------------------------------------+");
            System.out.print("Nhap lua chon tu 0 - 2: ");
            choice = nhapSo(sc);
            switch (choice) {
                case 1 -> thongKe(sc);
                case 2 -> thongKeTheoNgay(sc);
                case 0 -> { }
                default -> System.out.println("Lua chon khong hop le.");
            }
        } while (choice != 0);
    }

    private void thongKeTheoNgay(Scanner sc) {
        dsPNH.ReadFile();
        dsCTPNH.ReadFile();
        dsPNH.napChiTiet(dsCTPNH);

        LocalDate ngayThongKe = nhapNgayThongKe(sc);
        int soPhieu = 0;
        int soSach = 0;
        double tongTien = 0;
        int soPhieuNgayKhongHopLe = 0;
        for (int i = 0; i < dsPNH.getSoLuong(); i++) {
            PhieuNhapHang pnh = dsPNH.get(i);
            LocalDate ngayNhap = PhieuMuon.parseDateSafe(pnh.getNgayNhap());
            if (ngayNhap == null) {
                soPhieuNgayKhongHopLe++;
                continue;
            }
            if (!ngayNhap.equals(ngayThongKe)) continue;

            soPhieu++;
            for (int j = 0; j < pnh.getSoChiTiet(); j++) {
                ChiTietPhieuNhapHang chiTiet = pnh.getDsChiTiet()[j];
                if (chiTiet != null) soSach += chiTiet.getSoLuong();
            }
            tongTien += pnh.capNhatTongTien();
        }

        System.out.printf("%nTHONG KE PHIEU NHAP NGAY %s%n",
                ngayThongKe.format(FORMAT_NGAY_THONG_KE));
        System.out.printf("So phieu nhap: %d%nTong so sach nhap: %d cuon%nTong tien: %.0f VND%n",
                soPhieu, soSach, tongTien);
        if (soPhieuNgayKhongHopLe > 0) {
            System.out.printf("Bo qua %d phieu co ngay nhap khong hop le.%n", soPhieuNgayKhongHopLe);
        }
    }

    private LocalDate nhapNgayThongKe(Scanner sc) {
        while (true) {
            System.out.print("Nhap ngay nhap can thong ke (dd/MM/yyyy): ");
            String value = sc.nextLine().trim();
            if (PhieuMuon.isValidDate(value)) return PhieuMuon.parseDate(value);
            System.out.println("Ngay khong hop le (dd/MM/yyyy).");
        }
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
