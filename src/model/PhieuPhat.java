package model;

import danhsach.DSPhieuMuon;
import danhsach.DSPhieuPhat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class PhieuPhat {
    private String maPP;
    private String maPM;
    private LocalDate ngayPhat;
    private double tongSoTienPhat;
    Scanner sc = new Scanner(System.in);

    // 1. Constructor
    public PhieuPhat() {}

    public PhieuPhat(String maPM, String maPP, LocalDate ngayPhat, double tongSoTienPhat) {
        this.maPM = maPM;
        this.maPP = maPP;
        this.ngayPhat = ngayPhat;
        this.tongSoTienPhat = tongSoTienPhat;
    }

    // 2. Get - Set
    public String getMaPP() { return maPP; }
    public void setMaPP(String maPP) { this.maPP = maPP; }

    public String getMaPM() { return maPM; }
    public void setMaPM(String maPM) { this.maPM = maPM; }

    public LocalDate getNgayPhat() { return ngayPhat; }
    public void setNgayPhat(LocalDate ngayPhat) { this.ngayPhat = ngayPhat; }

    public double getTongSoTienPhat() { return tongSoTienPhat; }
    public void setTongSoTienPhat(double tongSoTienPhat) { this.tongSoTienPhat = tongSoTienPhat; }

    // 3. Input
    public void Input(DSPhieuPhat ds1, DSPhieuMuon ds2) {
        do {
            System.out.print("Nhap ma phieu phat: ");
            this.maPP = sc.nextLine().trim();
            if (!this.maPP.isEmpty() && ds1.Timvitri(this.maPP) == -1) {
                break;
            }
            System.out.println("Ma phieu phat rong hoac da ton tai!! Vui long nhap lai.");
        } while (true);

        do {
            System.out.print("Nhap ma phieu muon: ");
            this.maPM = sc.nextLine().trim();
            if (ds2.TimtheoMaPM(this.maPM) != null) {
                break;
            }
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

    // 4. Output
    public void Output() {
        String strNgay = (ngayPhat != null) ? ngayPhat.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "";
        System.out.printf("|%-12s|%-14s|%-14s|%-14.0f|%n",
                maPP, maPM, strNgay, tongSoTienPhat);
    }

    // 5. Tinh so ngay tra tre sach
    public long tinhSoTraNgayTre(LocalDate ngayQuyDinhTra) {
        if (ngayQuyDinhTra == null || this.ngayPhat == null) {
            return 0;
        }
        return Math.max(0, ChronoUnit.DAYS.between(ngayQuyDinhTra, this.ngayPhat));
    }
}
