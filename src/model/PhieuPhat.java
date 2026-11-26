package model;

import danhsach.DSBook;
import danhsach.DSChiTietPhieuMuon;
import danhsach.DSChiTietPhieuPhat;
import danhsach.DSPhieuMuon;
import danhsach.DSPhieuPhat;
import danhsach.DSQuyDinhPhat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Scanner;

public class PhieuPhat {
    private String maPP;
    private String maPM;
    private LocalDate ngayPhat;
    private double tongSoTienPhat;
    Scanner sc = new Scanner(System.in);

    private ChiTietPhieuPhat[] dsChiTiet = new ChiTietPhieuPhat[0];
    private int soChiTiet = 0;

    public PhieuPhat() {}

    public PhieuPhat(String maPM, String maPP, LocalDate ngayPhat, double tongSoTienPhat) {
        this.maPM = maPM;
        this.maPP = maPP;
        this.ngayPhat = ngayPhat;
        this.tongSoTienPhat = tongSoTienPhat;
    }

    public String getMaPP() { return maPP; }
    public void setMaPP(String maPP) { this.maPP = maPP; }

    public String getMaPM() { return maPM; }
    public void setMaPM(String maPM) { this.maPM = maPM; }

    public LocalDate getNgayPhat() { return ngayPhat; }
    public void setNgayPhat(LocalDate ngayPhat) { this.ngayPhat = ngayPhat; }

    public double getTongSoTienPhat() { return tongSoTienPhat; }
    public void setTongSoTienPhat(double tongSoTienPhat) { this.tongSoTienPhat = tongSoTienPhat; }

    public ChiTietPhieuPhat[] getDsChiTiet() { return dsChiTiet; }
    public int getSoChiTiet() { return soChiTiet; }

    public void clearChiTiet() {
        this.dsChiTiet = new ChiTietPhieuPhat[0];
        this.soChiTiet = 0;
    }

    public ChiTietPhieuPhat timChiTietTheoMaCTPP(String maCTPP) {
        for (int i = 0; i < soChiTiet; i++) {
            if (dsChiTiet[i] != null && dsChiTiet[i].getMaCTPP().equalsIgnoreCase(maCTPP)) {
                return dsChiTiet[i];
            }
        }
        return null;
    }

    public ChiTietPhieuPhat timChiTietTheoSach(String maSach) {
        for (int i = 0; i < soChiTiet; i++) {
            if (dsChiTiet[i] != null && dsChiTiet[i].getMaSach().equalsIgnoreCase(maSach)) {
                return dsChiTiet[i];
            }
        }
        return null;
    }

    public boolean themChiTiet(ChiTietPhieuPhat ct) {
        if (ct == null) return false;
        dsChiTiet = Arrays.copyOf(dsChiTiet, soChiTiet + 1);
        dsChiTiet[soChiTiet++] = ct;
        capNhatTongTienPhat();
        return true;
    }

    public boolean xoaChiTietTheoMaCTPP(String maCTPP) {
        for (int i = 0; i < soChiTiet; i++) {
            if (dsChiTiet[i] != null && dsChiTiet[i].getMaCTPP().equalsIgnoreCase(maCTPP)) {
                for (int j = i; j < soChiTiet - 1; j++) {
                    dsChiTiet[j] = dsChiTiet[j + 1];
                }
                soChiTiet--;
                dsChiTiet = Arrays.copyOf(dsChiTiet, soChiTiet);
                capNhatTongTienPhat();
                return true;
            }
        }
        return false;
    }

    public boolean xoaChiTietTheoSach(String maSach) {
        for (int i = 0; i < soChiTiet; i++) {
            if (dsChiTiet[i] != null && dsChiTiet[i].getMaSach().equalsIgnoreCase(maSach)) {
                for (int j = i; j < soChiTiet - 1; j++) {
                    dsChiTiet[j] = dsChiTiet[j + 1];
                }
                soChiTiet--;
                dsChiTiet = Arrays.copyOf(dsChiTiet, soChiTiet);
                capNhatTongTienPhat();
                return true;
            }
        }
        return false;
    }

    public double capNhatTongTienPhat() {
        double tong = 0;
        for (int i = 0; i < soChiTiet; i++) {
            if (dsChiTiet[i] != null) tong += dsChiTiet[i].getTienPhat();
        }
        this.tongSoTienPhat = tong;
        return tong;
    }

    // Gom nhom theo 3 quy dinh mac dinh QD01, QD02, QD03 -> VD: QD01(S01, S02), QD02(S03), QD03(S04)
    public String getChuoiCacChiTiet() {
        if (soChiTiet == 0) return "(Trong)";
        String[] qdMacDinh = {"QD01", "QD02", "QD03"};
        StringBuilder sb = new StringBuilder();
        for (String qd : qdMacDinh) {
            StringBuilder dsSach = new StringBuilder();
            for (int i = 0; i < soChiTiet; i++) {
                if (dsChiTiet[i] != null && qd.equalsIgnoreCase(dsChiTiet[i].getMaLD())) {
                    if (dsSach.length() > 0) dsSach.append(", ");
                    dsSach.append(dsChiTiet[i].getMaSach());
                }
            }
            if (dsSach.length() > 0) {
                if (sb.length() > 0) sb.append(", ");
                sb.append(qd).append("(").append(dsSach).append(")");
            }
        }
        // Cac ma quy dinh ngoai QD01/QD02/QD03 (neu co)
        for (int i = 0; i < soChiTiet; i++) {
            if (dsChiTiet[i] == null) continue;
            String ld = dsChiTiet[i].getMaLD();
            if (!"QD01".equalsIgnoreCase(ld) && !"QD02".equalsIgnoreCase(ld) && !"QD03".equalsIgnoreCase(ld)) {
                if (sb.length() > 0) sb.append(", ");
                sb.append(ld).append("(").append(dsChiTiet[i].getMaSach()).append(")");
            }
        }
        return sb.toString();
    }

    private int laySoLuongMuonTheoSach(ChiTietPhieuMuon[] dsMuon, String maSach) {
        if (dsMuon == null || maSach == null) return -1;
        for (ChiTietPhieuMuon ct : dsMuon) {
            if (ct != null && ct.getMaSach().equalsIgnoreCase(maSach)) {
                return ct.getSoLuong();
            }
        }
        return -1;
    }

    // Bang chi tiet cua rieng 1 phieu phat (gom nhom QD01->QD03, co MA CTPP va SO LUONG MUON, bo MA PP)
    public void xuatChiTiet() {
        if (soChiTiet == 0) {
            System.out.println("  (Phieu phat " + maPP + " chua co chi tiet vi pham)");
            return;
        }
        DSChiTietPhieuMuon dsCTPM = new DSChiTietPhieuMuon();
        dsCTPM.ReadFile();
        ChiTietPhieuMuon[] dsMuon = dsCTPM.TimTheoMaPM(this.maPM);

        System.out.println("  Chi tiet phieu phat " + maPP + " (Tong tien phat: " + (long) tongSoTienPhat + " VND):");
        System.out.println("  Tom tat theo quy dinh: " + getChuoiCacChiTiet());
        System.out.println("  +-----------+-------------+------------+---------------+------------------------+-------------------------------------+");
        System.out.printf("  | %-9s | %-11s | %-10s | %-13s | %-22s | %-35s |%n",
                "MA CTPP", "MA QUY DINH", "MA SACH", "SO LUONG MUON", "TIEN PHAT", "LY DO CHI TIET");
        System.out.println("  +-----------+-------------+------------+---------------+------------------------+-------------------------------------+");
        String[] qdMacDinh = {"QD01", "QD02", "QD03"};
        for (String qd : qdMacDinh) {
            for (int i = 0; i < soChiTiet; i++) {
                if (dsChiTiet[i] != null && qd.equalsIgnoreCase(dsChiTiet[i].getMaLD())) {
                    int slMuon = laySoLuongMuonTheoSach(dsMuon, dsChiTiet[i].getMaSach());
                    System.out.print("  ");
                    dsChiTiet[i].OutputTrongPhieu(slMuon);
                }
            }
        }
        for (int i = 0; i < soChiTiet; i++) {
            if (dsChiTiet[i] == null) continue;
            String ld = dsChiTiet[i].getMaLD();
            if (!"QD01".equalsIgnoreCase(ld) && !"QD02".equalsIgnoreCase(ld) && !"QD03".equalsIgnoreCase(ld)) {
                int slMuon = laySoLuongMuonTheoSach(dsMuon, dsChiTiet[i].getMaSach());
                System.out.print("  ");
                dsChiTiet[i].OutputTrongPhieu(slMuon);
            }
        }
        System.out.println("  +-----------+-------------+------------+---------------+------------------------+-------------------------------------+");
    }

    public void Input(DSPhieuPhat ds1, DSPhieuMuon ds2) {
        do {
            System.out.print("Nhap ma phieu phat: ");
            this.maPP = sc.nextLine().trim();
            if (!this.maPP.isEmpty() && ds1.Timvitri(this.maPP) == -1) break;
            System.out.println("Ma phieu phat rong hoac da ton tai!! Vui long nhap lai.");
        } while (true);

        do {
            System.out.print("Nhap ma phieu muon: ");
            this.maPM = sc.nextLine().trim();
            if (ds2.TimtheoMaPM(this.maPM) != null) break;
            System.out.println("Ma phieu muon khong ton tai!! Vui long nhap lai.");
        } while (true);

        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while (true) {
            System.out.print("Nhap ngay lap phieu phat (dd/MM/yyyy): ");
            try {
                this.ngayPhat = LocalDate.parse(sc.nextLine().trim(), f);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Ngay khong dung dinh dang dd/MM/yyyy. Vui long nhap lai!");
            }
        }
    }

    private String catChuoi(String s, int maxLen) {
        if (s == null) return "";
        if (s.length() <= maxLen) return s;
        return s.substring(0, maxLen - 2) + "..";
    }

    // Cat gon sau dau phay cuoi cung de khong bi cat ngang ma sach (VD: S01(QD01), S02(QD02), ..)
    private String catChuoiTheoDauPhay(String s, int maxLen) {
        if (s == null) return "";
        if (s.length() <= maxLen) return s;
        String phanDau = s.substring(0, maxLen - 3);
        int lastComma = phanDau.lastIndexOf(",");
        if (lastComma > 0) {
            return phanDau.substring(0, lastComma + 1) + " ..";
        }
        return s.substring(0, maxLen - 2) + "..";
    }

    public void Output() {
        String strNgay = (ngayPhat != null) ? ngayPhat.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "";
        System.out.printf("| %-10s | %-10s | %-10s | %-12.0f | %-55s |%n",
                catChuoi(maPP, 10),
                catChuoi(maPM, 10),
                strNgay,
                tongSoTienPhat,
                catChuoiTheoDauPhay(getChuoiCacChiTiet(), 55));
    }

    public long tinhSoTraNgayTre(LocalDate ngayQuyDinhTra) {
        if (ngayQuyDinhTra == null || this.ngayPhat == null) return 0;
        return Math.max(0, ChronoUnit.DAYS.between(ngayQuyDinhTra, this.ngayPhat));
    }

    // Tao phieu phat mac dinh (quet 3 quy dinh tu ma PM, tu gan ma PP) - dung trong QLPhieuPhat
    public boolean lapPhieuPhat(DSPhieuPhat dsPP, DSPhieuMuon dsPM, DSChiTietPhieuMuon dsCTPM,
            DSChiTietPhieuPhat dsCTPP, DSQuyDinhPhat dsQDP, DSBook dsBook) {
        PhieuMuon pm = null;
        ChiTietPhieuMuon[] chiTietList = null;
        while (true) {
            System.out.print("Nhap ma phieu muon can kiem tra lap phieu phat (bo trong de huy): ");
            String ma = sc.nextLine().trim();
            if (ma.isEmpty()) {
                System.out.println("Da huy lap phieu phat.");
                return false;
            }
            pm = dsPM.TimtheoMaPM(ma);
            if (pm == null) {
                System.out.println("Ma phieu muon khong ton tai! Vui long nhap lai.");
                continue;
            }
            chiTietList = dsCTPM.TimTheoMaPM(pm.getMaPM());
            if (chiTietList == null || chiTietList.length == 0) {
                System.out.println("Phieu muon " + pm.getMaPM() + " chua co chi tiet sach muon!");
                continue;
            }
            break;
        }

        LocalDate ngayLap;
        if (pm.getNgayThucTra() != null) {
            ngayLap = pm.getNgayThucTra();
            System.out.println("-> Ngay lap phieu phat (theo ngay thuc tra): "
                    + ngayLap.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        } else {
            DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            while (true) {
                System.out.print("Nhap ngay lap phieu phat (dd/MM/yyyy): ");
                try {
                    LocalDate temp = LocalDate.parse(sc.nextLine().trim(), f);
                    if (pm.getNgayMuon() != null && temp.isBefore(pm.getNgayMuon())) {
                        System.out.println("Ngay lap khong duoc truoc ngay muon (" + pm.getNgayMuonStr() + ")!");
                        continue;
                    }
                    ngayLap = temp;
                    break;
                } catch (DateTimeParseException e) {
                    System.out.println("Ngay khong dung dinh dang dd/MM/yyyy. Vui long nhap lai!");
                }
            }
        }

        long soNgayTre = 0;
        if (pm.getNgayTraLocalDate() != null && ngayLap.isAfter(pm.getNgayTraLocalDate())) {
            soNgayTre = ChronoUnit.DAYS.between(pm.getNgayTraLocalDate(), ngayLap);
        }

        System.out.print("Co sach nao bi HU HONG khong? (Y/N): ");
        boolean coHuHong = sc.nextLine().trim().equalsIgnoreCase("Y");

        System.out.print("Co sach nao bi MAT khong? (Y/N): ");
        boolean coMatSach = sc.nextLine().trim().equalsIgnoreCase("Y");

        StringBuilder dsViPham = new StringBuilder();
        if (soNgayTre > 0) {
            dsViPham.append("QD01 (Tra tre ").append(soNgayTre).append(" ngay)");
        }
        if (coHuHong) {
            if (dsViPham.length() > 0) dsViPham.append(", ");
            dsViPham.append("QD02 (Hu hong sach)");
        }
        if (coMatSach) {
            if (dsViPham.length() > 0) dsViPham.append(", ");
            dsViPham.append("QD03 (Mat sach)");
        }

        if (dsViPham.length() == 0) {
            System.out.println("=> Khong vi pham quy dinh nao.");
            return false;
        }
        System.out.println("=> Vi pham cac quy dinh: " + dsViPham);

        return lapPhieuPhatTheoQuyDinh(
                dsPP.taoMaPPMoi(), pm, chiTietList, ngayLap, soNgayTre, coHuHong, coMatSach,
                dsCTPP, dsQDP, dsBook, null);
    }

    // Ham loi tai su dung cho ca QLPhieuMuon (tu dong khi tra sach) va QLPhieuPhat (tao tay)
    public boolean lapPhieuPhatTheoQuyDinh(String maPPMoi, PhieuMuon pm, ChiTietPhieuMuon[] chiTietList,
            LocalDate ngayLap, long soNgayTre, boolean coHuHong, boolean coMatSach,
            DSChiTietPhieuPhat dsCTPP, DSQuyDinhPhat dsQDP, DSBook dsBook, int[] soLuongMat) {
        this.maPP = maPPMoi;
        this.maPM = pm.getMaPM();
        this.ngayPhat = ngayLap;
        this.tongSoTienPhat = 0;
        clearChiTiet();

        String strNgayPhat = ngayLap.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.println("================ LAP PHIEU PHAT ================");
        System.out.println("Ma phieu phat          : " + this.maPP);
        System.out.println("Ma phieu muon          : " + this.maPM);
        System.out.println("Ngay lap phieu phat    : " + strNgayPhat);

        if (soNgayTre > 0) {
            System.out.println("--- [QD01] TU DONG AP DUNG PHAT TRA TRE (" + soNgayTre + " ngay) CHO TOAN BO SACH MUON ---");
            for (ChiTietPhieuMuon ctMuon : chiTietList) {
                if (ctMuon == null) continue;
                double mucPhat1Cuon = dsQDP.TinhTienPhat("QD01", soNgayTre, ctMuon.getMaSach(), dsBook);
                double thanhTienPhat = Math.max(0, mucPhat1Cuon) * ctMuon.getSoLuong();
                String maCTPPMoi = dsCTPP.taoMaCTPPMoi();
                String lyDoTre = "Tra sach tre " + soNgayTre + " ngay";
                ChiTietPhieuPhat ctPhat = new ChiTietPhieuPhat(
                        maCTPPMoi, this.maPP, ctMuon.getMaSach(), thanhTienPhat, "QD01", lyDoTre, (int) soNgayTre);
                this.themChiTiet(ctPhat);
                dsCTPP.Them(ctPhat);
                System.out.printf("  -> Da them tu dong [%s | QD01 | %s | %d cuon | %s]%n",
                        maCTPPMoi, ctMuon.getMaSach(), ctMuon.getSoLuong(), ctPhat.getTienPhatKemSoLuong());
            }
        }
        if (coHuHong) {
            System.out.println("--- [QD02] XU LY PHAT HU HONG SACH ---");
            nhapChiTietPhatTheoQuyDinh(chiTietList, "QD02", dsCTPP, dsQDP, dsBook, soLuongMat);
        }
        if (coMatSach) {
            System.out.println("--- [QD03] XU LY PHAT MAT SACH ---");
            nhapChiTietPhatTheoQuyDinh(chiTietList, "QD03", dsCTPP, dsQDP, dsBook, soLuongMat);
        }

        return this.soChiTiet > 0;
    }

    private void nhapChiTietPhatTheoQuyDinh(ChiTietPhieuMuon[] chiTietList, String maLD,
            DSChiTietPhieuPhat dsCTPP, DSQuyDinhPhat dsQDP, DSBook dsBook, int[] soLuongMat) {
        int soDauSach = nhapSoDuong("Co bao nhieu dau sach vi pham quy dinh " + maLD + "? ");

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
                soLuongViPham = nhapSoDuong("  Nhap so luong sach vi pham (1 - " + slToiDa + "): ");
                if (soLuongViPham <= slToiDa) break;
                System.out.println("  So luong vi pham khong duoc vuot qua so luong da muon (" + slToiDa + ")!");
            }

            System.out.print("  Nhap ly do / ghi chu cu the: ");
            String lyDo = sc.nextLine().trim();

            double mucPhat1Cuon = dsQDP.TinhTienPhat(maLD, 0, maSach, dsBook);
            double thanhTienPhat = Math.max(0, mucPhat1Cuon) * soLuongViPham;

            String maCTPPMoi = dsCTPP.taoMaCTPPMoi();
            ChiTietPhieuPhat ct = new ChiTietPhieuPhat(
                    maCTPPMoi, this.maPP, chiTietList[vt].getMaSach(), thanhTienPhat, maLD, lyDo, soLuongViPham);
            this.themChiTiet(ct);
            dsCTPP.Them(ct);

            if (maLD.equalsIgnoreCase("QD03") && soLuongMat != null) {
                soLuongMat[vt] = Math.min(slToiDa, soLuongMat[vt] + soLuongViPham);
            }

            System.out.printf("  -> Da them chi tiet [%s | %s | %s]%n",
                    maLD, chiTietList[vt].getMaSach(), ct.getTienPhatKemSoLuong());
        }
    }

    private int nhapSoDuong(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int val = Integer.parseInt(sc.nextLine().trim());
                if (val > 0) return val;
            } catch (NumberFormatException ignored) {}
            System.out.println("Vui long nhap so nguyen > 0!");
        }
    }
}
