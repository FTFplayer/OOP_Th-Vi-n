package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Scanner;

public class PhieuMuon {
    private String maPM;
    private String maDG;
    private String maNV;
    private String ngayMuon;
    private String ngayTra;
    private String ngayThucTra;

    public PhieuMuon() {
        this("", "", "", "", "", "");
    }

    public PhieuMuon(String maPM, String maDG, String maNV, String ngayMuon,
            String ngayTra, String ngayThucTra) {
        this.maPM = maPM;
        this.maDG = maDG;
        this.maNV = maNV;
        this.ngayMuon = ngayMuon;
        this.ngayTra = ngayTra;
        this.ngayThucTra = ngayThucTra;
    }

    public String getMaPM() { return maPM; }
    public void setMaPM(String maPM) { this.maPM = maPM; }
    public String getMaPhieuMuon() { return maPM; }
    public void setMaPhieuMuon(String maPM) { this.maPM = maPM; }
    public String getMaDG() { return maDG; }
    public void setMaDG(String maDG) { this.maDG = maDG; }
    public String getMaNV() { return maNV; }
    public void setMaNV(String maNV) { this.maNV = maNV; }
    public String getNgayMuon() { return ngayMuon; }
    public void setNgayMuon(String ngayMuon) { this.ngayMuon = ngayMuon; }
    public String getNgayTra() { return ngayTra; }
    public void setNgayTra(String ngayTra) { this.ngayTra = ngayTra; }
    public String getNgayThucTra() { return ngayThucTra; }
    public void setNgayThucTra(String ngayThucTra) { this.ngayThucTra = ngayThucTra; }

    public void Input(Scanner sc) {
        System.out.print("Nhap ma phieu muon: ");
        maPM = sc.nextLine().trim();
        System.out.print("Nhap ma doc gia: ");
        maDG = sc.nextLine().trim();
        System.out.print("Nhap ma nhan vien: ");
        maNV = sc.nextLine().trim();
        ngayMuon = nhapNgay(sc, "Nhap ngay muon (dd/MM/yyyy): ");
        while(true) {
        ngayTra = nhapNgay(sc, "Nhap ngay tra (dd/MM/yyyy): ");
            if(!isBefore(NgayTra, NgayMuon)) {
                break;
            }
            System.out.println("Ngay tra khong the truoc ngay muon !! ");
        }
        NgayThucTra = NhapNgayThucTra(sc);
    }

    private String nhapNgay(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = sc.nextLine().trim();
            if (isValidDate(value)) {
                return value;
            }
            System.out.println("Ngay khong hop le.");
        }
    }

    public String nhapNgayThucTra(Scanner sc) {
        while (true) {
            String value = nhapNgay(sc, "Nhap ngay thuc tra (dd/MM/yyyy): ");
            if (isValidNgayThucTra(value, ngayMuon, ngayTra)) {
                return value;
            }
            System.out.println("Ngay thuc tra phai nam trong khoang tu ngay muon den ngay tra.");
        }
    }
    
    public static boolean isValidDate(String value) {
        try {
            LocalDate.parse(value, DateTimeFormatter.ofPattern("dd/MM/uuuu")
                    .withResolverStyle(ResolverStyle.STRICT));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean isBefore(String first, String second) {
        if (!isValidDate(first) || !isValidDate(second)) return false;
        LocalDate firstDate = parseDate(first);
        LocalDate secondDate = parseDate(second);
        return firstDate.isBefore(secondDate);
    }

    public static boolean isValidNgayThucTra(String ngayThucTra, String ngayMuon, String ngayTra) {
        if (!isValidDate(ngayThucTra) || !isValidDate(ngayMuon) || !isValidDate(ngayTra)) {
            return false;
        }
        LocalDate thucTra = parseDate(ngayThucTra);
        LocalDate muon = parseDate(ngayMuon);
        LocalDate tra = parseDate(ngayTra);
        return !thucTra.isBefore(muon) && !thucTra.isAfter(tra);
    }

    private static LocalDate parseDate(String value) {
        return LocalDate.parse(value, DateTimeFormatter.ofPattern("dd/MM/uuuu")
                .withResolverStyle(ResolverStyle.STRICT));
    }
    
    public void output() {
        System.out.printf("%-12s %-12s %-12s %-14s %-14s %-14s%n",
                maPM, maDG, maNV, ngayMuon, ngayTra, ngayThucTra);
    }

    public void Output() {
        output();
    }
}
