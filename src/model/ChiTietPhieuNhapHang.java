package model;

import java.util.Scanner;

public class ChiTietPhieuNhapHang {
    private String maCT;
    private String maPNH;
    private String maSach;
    private int soLuong;
    private double donGia;
    private static final Scanner sc = new Scanner(System.in);

    public ChiTietPhieuNhapHang() {
        this("", "", "", 0, 0);
    }

    public ChiTietPhieuNhapHang(String maCT, String maPNH, String maSach,
            int soLuong, double donGia) {
        this.maCT = maCT;
        this.maPNH = maPNH;
        this.maSach = maSach;
        setSoLuong(soLuong);
        setDonGia(donGia);
    }

    public ChiTietPhieuNhapHang(ChiTietPhieuNhapHang copy) {
        if (copy != null) {
            this.maCT = copy.maCT;
            this.maPNH = copy.maPNH;
            this.maSach = copy.maSach;
            this.soLuong = copy.soLuong;
            this.donGia = copy.donGia;
        }
    }

    public String getMaCT() { return maCT; }
    public void setMaCT(String maCT) { this.maCT = maCT; }
    public String getMaPNH() { return maPNH; }
    public void setMaPNH(String maPNH) { this.maPNH = maPNH; }
    public String getMaSach() { return maSach; }
    public void setMaSach(String maSach) { this.maSach = maSach; }
    public int getSoLuong() { return soLuong; }

    public void setSoLuong(int soLuong) {
        if (soLuong < 0) {
            throw new IllegalArgumentException("So luong khong duoc am.");
        }
        this.soLuong = soLuong;
    }

    public double getDonGia() { return donGia; }

    public void setDonGia(double donGia) {
        if (donGia < 0) {
            throw new IllegalArgumentException("Don gia khong duoc am.");
        }
        this.donGia = donGia;
    }

    public double getThanhTien() { return soLuong * donGia; }

    public void Input() {
        while (true) {
            System.out.print("Nhap ma chi tiet (VD: CTPN001): ");
            maCT = sc.nextLine().trim();
            if (!maCT.isEmpty()) break;
            System.out.println("Ma chi tiet khong duoc de trong!");
        }
        System.out.print("Nhap ma phieu nhap hang (VD: PNH001): ");
        maPNH = sc.nextLine().trim();
        System.out.print("Nhap ma sach (VD: S01): ");
        maSach = sc.nextLine().trim();

        while (true) {
            System.out.print("Nhap so luong: ");
            try {
                int sl = Integer.parseInt(sc.nextLine().trim());
                if (sl > 0) {
                    setSoLuong(sl);
                    break;
                }
                System.out.println("So luong phai lon hon 0!");
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap so nguyen hop le!");
            }
        }

        while (true) {
            System.out.print("Nhap don gia: ");
            try {
                double gia = Double.parseDouble(sc.nextLine().trim());
                if (gia > 0) {
                    setDonGia(gia);
                    break;
                }
                System.out.println("Don gia phai lon hon 0!");
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap so hop le!");
            }
        }
    }

    public void Output() {
        System.out.printf("| %-10s | %-10s | %-10s | %-8d | %-12.0f | %-14.0f |%n",
                maCT, maPNH, maSach, soLuong, donGia, getThanhTien());
    }

    // In ben trong 1 phieu nhap cu the (bo cot MA PNH du thua)
    public void OutputTrongPhieu() {
        System.out.printf("| %-10s | %-10s | %-8d | %-12.0f | %-14.0f |%n",
                maCT, maSach, soLuong, donGia, getThanhTien());
    }
}
