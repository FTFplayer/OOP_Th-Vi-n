package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Arrays;
import java.util.Scanner;

public class PhieuMuon {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/uuuu")
            .withResolverStyle(ResolverStyle.STRICT);
    private static final DateTimeFormatter PRINT_FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private String maPM;
    private String maDG;
    private String maNV;
    private LocalDate ngayMuon;
    private LocalDate ngayTra;
    private LocalDate ngayThucTra; // null = "Chua tra"
    private static final Scanner sc = new Scanner(System.in);

    private ChiTietPhieuMuon[] dsChiTiet = new ChiTietPhieuMuon[0];
    private int soChiTiet = 0;

    public PhieuMuon() {
        this.maPM = "";
        this.maDG = "";
        this.maNV = "";
        this.ngayMuon = null;
        this.ngayTra = null;
        this.ngayThucTra = null;
    }

    public PhieuMuon(String maPM, String maDG, String maNV, LocalDate ngayMuon,
            LocalDate ngayTra, LocalDate ngayThucTra) {
        this.maPM = maPM;
        this.maDG = maDG;
        this.maNV = maNV;
        this.ngayMuon = ngayMuon;
        this.ngayTra = (ngayTra != null) ? ngayTra : (ngayMuon != null ? ngayMuon.plusDays(7) : null);
        this.ngayThucTra = ngayThucTra;
    }

    public PhieuMuon(String maPM, String maDG, String maNV, String ngayMuon,
            String ngayTra, String ngayThucTra) {
        this.maPM = maPM;
        this.maDG = maDG;
        this.maNV = maNV;
        this.ngayMuon = parseDateSafe(ngayMuon);
        LocalDate parsedTra = parseDateSafe(ngayTra);
        this.ngayTra = (parsedTra != null) ? parsedTra : (this.ngayMuon != null ? this.ngayMuon.plusDays(7) : null);
        if (ngayThucTra == null || ngayThucTra.trim().isEmpty()
                || ngayThucTra.trim().equalsIgnoreCase("Chua tra")) {
            this.ngayThucTra = null;
        } else {
            this.ngayThucTra = parseDateSafe(ngayThucTra);
        }
    }

    public PhieuMuon(PhieuMuon copy) {
        if (copy != null) {
            this.maPM = copy.maPM;
            this.maDG = copy.maDG;
            this.maNV = copy.maNV;
            this.ngayMuon = copy.ngayMuon;
            this.ngayTra = copy.ngayTra;
            this.ngayThucTra = copy.ngayThucTra;
            this.soChiTiet = copy.soChiTiet;
            this.dsChiTiet = Arrays.copyOf(copy.dsChiTiet, copy.soChiTiet);
        }
    }

    // --- Getters / Setters ---
    public String getMaPM() { return maPM; }
    public void setMaPM(String maPM) { this.maPM = maPM; }
    public String getMaPhieuMuon() { return maPM; }
    public void setMaPhieuMuon(String maPM) { this.maPM = maPM; }
    public String getMaDG() { return maDG; }
    public void setMaDG(String maDG) { this.maDG = maDG; }
    public String getMaNV() { return maNV; }
    public void setMaNV(String maNV) { this.maNV = maNV; }

    public LocalDate getNgayMuon() { return ngayMuon; }
    public void setNgayMuon(LocalDate ngayMuon) {
        this.ngayMuon = ngayMuon;
        if (ngayMuon != null) {
            this.ngayTra = tinhNgayTraMacDinh(ngayMuon);
        }
    }
    public void setNgayMuon(String ngayMuon) {
        setNgayMuon(parseDateSafe(ngayMuon));
    }

    public LocalDate getNgayTra() { return ngayTra; }
    public LocalDate getNgayTraLocalDate() { return ngayTra; }
    public void setNgayTra(LocalDate ngayTra) { this.ngayTra = ngayTra; }
    public void setNgayTra(String ngayTra) { this.ngayTra = parseDateSafe(ngayTra); }

    public LocalDate getNgayThucTra() { return ngayThucTra; }
    public void setNgayThucTra(LocalDate ngayThucTra) { this.ngayThucTra = ngayThucTra; }
    public void setNgayThucTra(String ngayThucTra) {
        if (ngayThucTra == null || ngayThucTra.trim().isEmpty()
                || ngayThucTra.trim().equalsIgnoreCase("Chua tra")) {
            this.ngayThucTra = null;
        } else {
            this.ngayThucTra = parseDateSafe(ngayThucTra);
        }
    }

    public String getNgayMuonStr() {
        return (ngayMuon != null) ? ngayMuon.format(PRINT_FMT) : "";
    }

    public String getNgayTraStr() {
        return (ngayTra != null) ? ngayTra.format(PRINT_FMT) : "";
    }

    public String getNgayThucTraStr() {
        return (ngayThucTra != null) ? ngayThucTra.format(PRINT_FMT) : "Chua tra";
    }

    // Ham tinh ngay tra mac dinh = ngay muon + 7 ngay (1 tuan)
    public static LocalDate tinhNgayTraMacDinh(LocalDate ngayMuon) {
        return (ngayMuon != null) ? ngayMuon.plusDays(7) : null;
    }

    // --- Cac ham xu ly mang chi tiet trong PhieuMuon ---
    public ChiTietPhieuMuon[] getDsChiTiet() { return dsChiTiet; }
    public int getSoChiTiet() { return soChiTiet; }

    public void clearChiTiet() {
        this.dsChiTiet = new ChiTietPhieuMuon[0];
        this.soChiTiet = 0;
    }

    public ChiTietPhieuMuon timChiTiet(String maCT) {
        for (int i = 0; i < soChiTiet; i++) {
            if (dsChiTiet[i] != null && dsChiTiet[i].getMaCT().equalsIgnoreCase(maCT)) {
                return dsChiTiet[i];
            }
        }
        return null;
    }

    public boolean themChiTiet(ChiTietPhieuMuon ct) {
        if (ct == null || timChiTiet(ct.getMaCT()) != null) return false;
        dsChiTiet = Arrays.copyOf(dsChiTiet, soChiTiet + 1);
        dsChiTiet[soChiTiet++] = ct;
        return true;
    }

    public boolean xoaChiTiet(String maCT) {
        for (int i = 0; i < soChiTiet; i++) {
            if (dsChiTiet[i] != null && dsChiTiet[i].getMaCT().equalsIgnoreCase(maCT)) {
                for (int j = i; j < soChiTiet - 1; j++) {
                    dsChiTiet[j] = dsChiTiet[j + 1];
                }
                soChiTiet--;
                dsChiTiet = Arrays.copyOf(dsChiTiet, soChiTiet);
                return true;
            }
        }
        return false;
    }

    public int tinhTongSoSach() {
        int tong = 0;
        for (int i = 0; i < soChiTiet; i++) {
            if (dsChiTiet[i] != null) tong += dsChiTiet[i].getSoLuong();
        }
        return tong;
    }

    // Chuoi liet ke cac ma chi tiet de hien thi len cot "Cac chi tiet" trong bang
    public String getChuoiCacChiTiet() {
        if (soChiTiet == 0) return "(Trong)";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < soChiTiet; i++) {
            if (dsChiTiet[i] != null) {
                if (sb.length() > 0) sb.append(", ");
                sb.append(dsChiTiet[i].getMaCT());
            }
        }
        return sb.toString();
    }

    public void xuatChiTiet() {
        if (soChiTiet == 0) {
            System.out.println("  (Phieu muon " + maPM + " chua co chi tiet sach muon)");
            return;
        }
        System.out.println("  Chi tiet sach muon cua phieu " + maPM + " (Tong: " + tinhTongSoSach() + " cuon):");
        System.out.println("  +------------+------------+----------+");
        System.out.printf("  | %-10s | %-10s | %-8s |%n", "MA CT", "MA SACH", "SO LUONG");
        System.out.println("  +------------+------------+----------+");
        for (int i = 0; i < soChiTiet; i++) {
            if (dsChiTiet[i] != null) {
                System.out.print("  ");
                dsChiTiet[i].OutputTrongPhieu();
            }
        }
        System.out.println("  +------------+------------+----------+");
    }

    // Chi nhap ngay muon -> tu dong tinh ngay tra (+7 ngay) va mac dinh ngayThucTra = null ("Chua tra")
    public void Input() {
        System.out.print("Nhap ma phieu muon: ");
        maPM = sc.nextLine().trim();
        System.out.print("Nhap ma doc gia: ");
        maDG = sc.nextLine().trim();
        System.out.print("Nhap ma nhan vien: ");
        maNV = sc.nextLine().trim();
        this.ngayMuon = nhapNgay("Nhap ngay muon (dd/MM/yyyy): ");
        this.ngayTra = tinhNgayTraMacDinh(this.ngayMuon);
        this.ngayThucTra = null;
        System.out.println("-> Han tra tu dong (+7 ngay): " + getNgayTraStr() + " | Trang thai tra: Chua tra");
    }

    private LocalDate nhapNgay(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = sc.nextLine().trim();
            if (isValidDate(value)) return parseDate(value);
            System.out.println("Ngay khong hop le (dd/MM/yyyy).");
        }
    }

    public static boolean isValidDate(String value) {
        if (value == null || value.trim().isEmpty()) return false;
        try {
            LocalDate.parse(value.trim(), FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static LocalDate parseDate(String value) {
        return LocalDate.parse(value.trim(), FORMATTER);
    }

    public static LocalDate parseDateSafe(String value) {
        if (!isValidDate(value)) return null;
        return parseDate(value);
    }

    public static boolean isBefore(String first, String second) {
        if (!isValidDate(first) || !isValidDate(second)) return false;
        return parseDate(first).isBefore(parseDate(second));
    }

    private String catChuoi(String s, int maxLen) {
        if (s == null) return "";
        if (s.length() <= maxLen) return s;
        return s.substring(0, maxLen - 2) + "..";
    }

    public void output() {
        System.out.printf("| %-8s | %-8s | %-8s | %-10s | %-10s | %-10s | %-28s |%n",
                catChuoi(maPM, 8),
                catChuoi(maDG, 8),
                catChuoi(maNV, 8),
                getNgayMuonStr(),
                getNgayTraStr(),
                getNgayThucTraStr(),
                catChuoi(getChuoiCacChiTiet(), 28));
    }

    public void Output() {
        output();
    }
}
