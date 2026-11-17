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
        ngayTra = nhapNgay(sc, "Nhap ngay tra (dd/MM/yyyy): ");
        ngayThucTra = "";
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

    public static boolean isValidDate(String value) {
        try {
            LocalDate.parse(value, DateTimeFormatter.ofPattern("dd/MM/uuuu")
                    .withResolverStyle(ResolverStyle.STRICT));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public void output() {
        System.out.printf("%-12s %-12s %-12s %-14s %-14s %-14s%n",
                maPM, maDG, maNV, ngayMuon, ngayTra, ngayThucTra);
    }

    public void Output() {
        output();
    }
}
