package model;

import danhsach.DSPhieuMuon;
import danhsach.DSPhieuPhat;
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

    // Bang chi tiet cua rieng 1 phieu phat (gom nhom theo QD01 -> QD02 -> QD03, co MA CTPP, bo MA PP)
    public void xuatChiTiet() {
        if (soChiTiet == 0) {
            System.out.println("  (Phieu phat " + maPP + " chua co chi tiet vi pham)");
            return;
        }
        System.out.println("  Chi tiet phieu phat " + maPP + " (Tong tien phat: " + (long) tongSoTienPhat + " VND):");
        System.out.println("  Tom tat theo quy dinh: " + getChuoiCacChiTiet());
        System.out.println("  +-----------+-------------+------------+------------------------+-------------------------------------+");
        System.out.printf("  | %-9s | %-11s | %-10s | %-22s | %-35s |%n",
                "MA CTPP", "MA QUY DINH", "MA SACH", "TIEN PHAT", "LY DO CHI TIET");
        System.out.println("  +-----------+-------------+------------+------------------------+-------------------------------------+");
        String[] qdMacDinh = {"QD01", "QD02", "QD03"};
        for (String qd : qdMacDinh) {
            for (int i = 0; i < soChiTiet; i++) {
                if (dsChiTiet[i] != null && qd.equalsIgnoreCase(dsChiTiet[i].getMaLD())) {
                    System.out.print("  ");
                    dsChiTiet[i].OutputTrongPhieu();
                }
            }
        }
        for (int i = 0; i < soChiTiet; i++) {
            if (dsChiTiet[i] == null) continue;
            String ld = dsChiTiet[i].getMaLD();
            if (!"QD01".equalsIgnoreCase(ld) && !"QD02".equalsIgnoreCase(ld) && !"QD03".equalsIgnoreCase(ld)) {
                System.out.print("  ");
                dsChiTiet[i].OutputTrongPhieu();
            }
        }
        System.out.println("  +-----------+-------------+------------+------------------------+-------------------------------------+");
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
}
