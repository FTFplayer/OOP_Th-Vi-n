package quanly;

import danhsach.DSChiTietPhieuMuon;
import danhsach.DSPhieuMuon;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
import model.Book;
import model.ChiTietPhieuMuon;
import model.ChiTietPhieuPhat;
import model.PhieuMuon;
import model.PhieuPhat;
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
            System.out.println("| 2) Them phieu muon (xem chi tiet)         |");
            System.out.println("| 3) Tim kiem phieu muon (xem chi tiet)     |");
            System.out.println("| 4) Sua thong tin phieu muon               |");
            System.out.println("| 5) Xoa phieu muon                         |");
            System.out.println("| 6) Thong ke phieu muon                    |");
            System.out.println("| 7) Quan ly chi tiet cua phieu muon        |");
            System.out.println("| 8) Tra sach                               |");
            System.out.println("| 0) Thoat                                  |");
            System.out.println("+-------------------------------------------+");
            System.out.print("Nhap lua chon tu 0 - 8: ");
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
                case 8 -> traSach(sc);
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

                    if (pm.getNgayThucTra() != null) {
                        System.out.println("Phieu muon nay da tra, khong the them sach!");
                        break;
                    }
                    if (!dsBook.giamSoLuong(ct.getMaSach(), ct.getSoLuong())) {
                        break;
                    }
                    if (dsCTPM.Them(ct)) {
                        pm.themChiTiet(ct);
                        dsCTPM.WriteFile();
                        System.out.println("=> Them chi tiet " + maCTMoi + " thanh cong!");
                    } else {
                        dsBook.tangSoLuong(ct.getMaSach(), ct.getSoLuong());
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
                    if (pm.getNgayThucTra() != null) {
                        System.out.println("Phieu muon nay da tra, khong the sua chi tiet!");
                        break;
                    }
                    String maSachCu = ct.getMaSach();
                    int soLuongCu = ct.getSoLuong();
                    System.out.print("Nhap ma sach moi: ");
                    String maSachMoi = sc.nextLine().trim();
                    int soLuongMoi = nhapSoDuong(sc, "Nhap so luong moi: ");

                    if (!capNhatSoLuongKhiSua(maSachCu, soLuongCu, maSachMoi, soLuongMoi)) {
                        break;
                    }
                    ct.setMaSach(maSachMoi);
                    ct.setSoLuong(soLuongMoi);
                    dsCTPM.WriteFile();
                    System.out.println("=> Sua chi tiet thanh cong!");
                }
                case 4 -> {
                    pm.xuatChiTiet();
                    if (pm.getSoChiTiet() == 0) break;
                    System.out.print("Nhap ma chi tiet can xoa: ");
                    String maCT = sc.nextLine().trim();
                    ChiTietPhieuMuon ctCanXoa = pm.timChiTiet(maCT);
                    if (ctCanXoa == null) {
                        System.out.println("Khong tim thay chi tiet " + maCT + " trong phieu " + pm.getMaPM());
                        break;
                    }
                    if (pm.xoaChiTiet(maCT)) {
                        dsCTPM.Xoa(maCT);
                        dsCTPM.WriteFile();
                        if (pm.getNgayThucTra() == null) {
                            dsBook.tangSoLuong(ctCanXoa.getMaSach(), ctCanXoa.getSoLuong());
                        }
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

            if (!dsBook.giamSoLuong(ct.getMaSach(), ct.getSoLuong())) {
                continue;
            }
            if (dsCTPM.Them(ct)) {
                pm.themChiTiet(ct);
            } else {
                dsBook.tangSoLuong(ct.getMaSach(), ct.getSoLuong());
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
        if (pm.getNgayThucTra() == null) {
            for (ChiTietPhieuMuon ct : cts) {
                dsBook.tangSoLuong(ct.getMaSach(), ct.getSoLuong());
            }
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
    private void traSach(Scanner sc) {
        dsPM.ReadFile();
        dsCTPM.ReadFile();
        dsPM.napChiTiet(dsCTPM);
        dsBook.ReadFile();

        System.out.print("Nhap ma phieu muon can tra: ");
        String maPM = sc.nextLine().trim();
        PhieuMuon pm = dsPM.TimTheoMa(maPM);
        if (pm == null) {
            System.out.println("Khong tim thay phieu muon.");
            return;
        }
        if (pm.getNgayThucTra() != null) {
            System.out.println("Phieu " + maPM + " da duoc tra truoc do.");
            return;
        }

        ChiTietPhieuMuon[] chiTietList = dsCTPM.TimTheoMaPM(maPM);
        if (chiTietList.length == 0) {
            System.out.println("Phieu muon khong co sach de tra.");
            return;
        }

        // Hien thi cac sach trong phieu muon de thu thu kiem tra
        pm.xuatChiTiet();

        LocalDate ngayThucTra;
        while (true) {
            System.out.print("Nhap ngay thuc tra (dd/MM/yyyy): ");
            String strNgay = sc.nextLine().trim();
            if (!PhieuMuon.isValidDate(strNgay)) {
                System.out.println("Ngay khong dung dinh dang dd/MM/yyyy. Vui long nhap lai!");
                continue;
            }
            ngayThucTra = PhieuMuon.parseDate(strNgay);
            if (pm.getNgayMuon() != null && ngayThucTra.isBefore(pm.getNgayMuon())) {
                System.out.println("Ngay thuc tra khong duoc truoc ngay muon (" + pm.getNgayMuonStr() + ")!");
                continue;
            }
            break;
        }

        System.out.print("Xac nhan tra sach cua phieu " + maPM + "? (Y/N): ");
        String xacNhan = sc.nextLine().trim();
        if (!xacNhan.equalsIgnoreCase("Y")) {
            System.out.println("Da huy tra sach.");
            return;
        }

        // 1. Xet 3 quy dinh phat (QD01: Tra tre, QD02: Hu hong, QD03: Mat sach)
        long soNgayTre = 0;
        if (pm.getNgayTra() != null && ngayThucTra.isAfter(pm.getNgayTra())) {
            soNgayTre = ChronoUnit.DAYS.between(pm.getNgayTra(), ngayThucTra);
            System.out.println("=> Phat hien TRA TRE " + soNgayTre + " ngay! (Ap dung QD01)");
        }

        System.out.print("Co sach nao bi HU HONG khong? (Y/N): ");
        boolean coHuHong = sc.nextLine().trim().equalsIgnoreCase("Y");

        System.out.print("Co sach nao bi MAT khong? (Y/N): ");
        boolean coMatSach = sc.nextLine().trim().equalsIgnoreCase("Y");

        // Mang luu so luong mat cua tung dong chi tiet de tru kho cho chuan
        int[] soLuongMat = new int[chiTietList.length];

        pm.setNgayThucTra(ngayThucTra);
        // 2. Neu dinh it nhat 1 quy dinh -> lap Phieu Phat + Chi Tiet Phieu Phat
        if (soNgayTre > 0 || coHuHong || coMatSach) {
            lapPhieuPhatKhiTraSach(sc, pm, chiTietList, ngayThucTra, soNgayTre, coHuHong, coMatSach, soLuongMat);
        }

        // 3. Cap nhat kho sach (chi cong tra lai so cuon khong bi mat; cuon mat tru vao tong SoLuong)
        for (int i = 0; i < chiTietList.length; i++) {
            ChiTietPhieuMuon ct = chiTietList[i];
            int slTraVeKho = ct.getSoLuong() - soLuongMat[i];
            if (slTraVeKho > 0) {
                dsBook.tangSoLuong(ct.getMaSach(), slTraVeKho);
            }
            if (soLuongMat[i] > 0) {
                Book b = dsBook.timkiemBook(ct.getMaSach());
                if (b != null) {
                    b.setSoLuong(Math.max(0, b.getSoLuong() - soLuongMat[i]));
                }
            }
        }

        dsPM.WriteFile();
        dsBook.WriteFile();
        System.out.println("=> Tra sach thanh cong. Ngay thuc tra: " + pm.getNgayThucTraStr());
    }

    private void lapPhieuPhatKhiTraSach(Scanner sc, PhieuMuon pm, ChiTietPhieuMuon[] chiTietList,
            LocalDate ngayThucTra, long soNgayTre, boolean coHuHong, boolean coMatSach, int[] soLuongMat) {
        dsPP.ReadFile();
        dsCTPP.ReadFile();
        dsPP.napChiTiet(dsCTPP);
        dsQDP.ReadFile();

        String maPPMoi = dsPP.taoMaPPMoi();
        PhieuPhat pp = new PhieuPhat(pm.getMaPM(), maPPMoi, ngayThucTra, 0);

        System.out.println("\n================ LAP PHIEU PHAT TU DONG ================");
        System.out.println("Ma phieu phat (gan san): " + maPPMoi);
        System.out.println("Ma phieu muon          : " + pm.getMaPM());
        System.out.println("Ngay lap phieu phat    : " + pm.getNgayThucTraStr());

        if (soNgayTre > 0) {
            System.out.println("\n--- [QD01] XU LY PHAT TRA TRE (" + soNgayTre + " ngay) ---");
            nhapChiTietPhatTheoQuyDinh(sc, pp, chiTietList, "QD01", soNgayTre, soLuongMat);
        }
        if (coHuHong) {
            System.out.println("\n--- [QD02] XU LY PHAT HU HONG SACH ---");
            nhapChiTietPhatTheoQuyDinh(sc, pp, chiTietList, "QD02", 0, soLuongMat);
        }
        if (coMatSach) {
            System.out.println("\n--- [QD03] XU LY PHAT MAT SACH ---");
            nhapChiTietPhatTheoQuyDinh(sc, pp, chiTietList, "QD03", 0, soLuongMat);
        }

        if (pp.getSoChiTiet() > 0) {
            dsPP.Them(pp);
            dsPP.WriteFile();
            dsCTPP.WriteFile();
            System.out.println("\n=> Da tao phieu phat " + maPPMoi
                    + " | Tong tien phat: " + (long) pp.getTongSoTienPhat() + " VND");
            pp.xuatChiTiet();
        }
    }

    private void nhapChiTietPhatTheoQuyDinh(Scanner sc, PhieuPhat pp, ChiTietPhieuMuon[] chiTietList,
            String maLD, long soNgayTre, int[] soLuongMat) {
        System.out.print("Co bao nhieu dau sach vi pham quy dinh " + maLD + "? ");
        int soDauSach = nhapSoDuong(sc, "");

        for (int k = 0; k < soDauSach; k++) {
            System.out.println("  Chi tiet " + maLD + " thu " + (k + 1) + ":");
            int vt = -1;
            String maSach;
            while (true) {
                System.out.print("  Nhap ma sach (phai thuoc phieu muon): ");
                maSach = sc.nextLine().trim();
                for (int i = 0; i < chiTietList.length; i++) {
                    if (chiTietList[i].getMaSach().equalsIgnoreCase(maSach)) {
                        vt = i;
                        break;
                    }
                }
                if (vt != -1) break;
                System.out.println("  Ma sach khong nam trong phieu muon nay! Vui long nhap lai.");
            }

            int slToiDa = chiTietList[vt].getSoLuong();
            int soLuongViPham;
            while (true) {
                soLuongViPham = nhapSoDuong(sc, "  Nhap so luong sach vi pham (1 - " + slToiDa + "): ");
                if (soLuongViPham <= slToiDa) break;
                System.out.println("  So luong vi pham khong duoc vuot qua so luong da muon (" + slToiDa + ")!");
            }

            double mucPhat1Cuon = dsQDP.TinhTienPhat(maLD, soNgayTre, maSach, dsBook);
            double thanhTienPhat = Math.max(0, mucPhat1Cuon) * soLuongViPham;

            ChiTietPhieuPhat ct = new ChiTietPhieuPhat(pp.getMaPP(), chiTietList[vt].getMaSach(), thanhTienPhat, maLD);
            pp.themChiTiet(ct);
            dsCTPP.Them(ct);

            if (maLD.equalsIgnoreCase("QD03")) {
                soLuongMat[vt] = Math.min(slToiDa, soLuongMat[vt] + soLuongViPham);
            }

            System.out.printf("  -> Da them chi tiet [%s | %s | SL: %d] = %.0f VND%n",
                    maLD, chiTietList[vt].getMaSach(), soLuongViPham, thanhTienPhat);
        }
    }
    
    private boolean capNhatSoLuongKhiSua(String maSachCu, int soLuongCu,
                                         String maSachMoi, int soLuongMoi) {
        if (maSachCu.equalsIgnoreCase(maSachMoi)) {
            if (soLuongMoi > soLuongCu) {
                return dsBook.giamSoLuong(maSachMoi, soLuongMoi - soLuongCu);
            }
            if (soLuongMoi < soLuongCu) {
                return dsBook.tangSoLuong(maSachMoi, soLuongCu - soLuongMoi);
            }
            return true;
        }

        dsBook.tangSoLuong(maSachCu, soLuongCu);
        if (!dsBook.giamSoLuong(maSachMoi, soLuongMoi)) {
            dsBook.giamSoLuong(maSachCu, soLuongCu);
            return false;
        }
        return true;
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
    // private int TraSoLuong() {
    //     DSBook SL = new DSBook();
        
    // }
    // private int LaySoLuong() {
        
    // }
}
