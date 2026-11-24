package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public abstract class Book {
    private String MaSach, TenSach, MaTheLoai, MaTG, TrangThai, MaNXB;
    private LocalDate NgayXB;
    private double DonGia;
    private int SoLuong, SoLuongConLai;
    private static int tongSoDauSach = 0;

    public Book() {
        this.MaSach = "";
        this.TenSach = "";
        this.MaTheLoai = "";
        this.MaTG = "";
        this.NgayXB = null;
        this.TrangThai = "Het sach";
        this.MaNXB = "";
        this.DonGia = 0;
        this.SoLuong = 0;
        this.SoLuongConLai = 0;
    }

    public Book(String maSach, String tenSach, String MaTheLoai, String maTG, LocalDate ngayXB,
            String trangThai, String maNXB, double donGia, int soLuong, int soLuongConLai) {
        this.MaSach = maSach;
        this.TenSach = tenSach;
        this.MaTheLoai = MaTheLoai;
        this.MaTG = maTG;
        this.NgayXB = ngayXB;
        this.TrangThai = trangThai;
        this.MaNXB = maNXB;
        this.DonGia = donGia;
        this.SoLuong = soLuong;
        this.SoLuongConLai = soLuongConLai;
        updateStatus();
        tongSoDauSach++;
    }

    public Book(Book b) {
        if (b != null) {
            this.MaSach = b.MaSach;
            this.TenSach = b.TenSach;
            this.MaTheLoai = b.MaTheLoai;
            this.MaTG = b.MaTG;
            this.NgayXB = b.NgayXB;
            this.TrangThai = b.TrangThai;
            this.MaNXB = b.MaNXB;
            this.DonGia = b.DonGia;
            this.SoLuong = b.SoLuong;
            this.SoLuongConLai = b.SoLuongConLai;
        }
    }

    public static int getTongSoDauSach() {
        return tongSoDauSach;
    }

    public static void setTongSoDauSach(int tong) {
        tongSoDauSach = tong;
    }

    public static boolean kiemTraNgayHopLe(String ngay) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate.parse(ngay, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public String getMaSach() {
        return MaSach;
    }

    public void setMaSach(String MaSach) {
        this.MaSach = MaSach;
    }

    public String getTenSach() {
        return TenSach;
    }

    public void setTenSach(String TenSach) {
        this.TenSach = TenSach;
    }

    public String getMaTheLoai() {
        return MaTheLoai;
    }

    public void setMaTheLoai(String MaTheLoai) {
        this.MaTheLoai = MaTheLoai;
    }

    public String getMaTG() {
        return MaTG;
    }

    public void setMaTG(String MaTG) {
        this.MaTG = MaTG;
    }

    public LocalDate getNgayXB() {
        return NgayXB;
    }

    public void setNgayXB(LocalDate NgayXB) {
        this.NgayXB = NgayXB;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }

    public String getMaNXB() {
        return MaNXB;
    }

    public void setMaNXB(String MaNXB) {
        this.MaNXB = MaNXB;
    }

    public double getDonGia() {
        return DonGia;
    }

    public void setDonGia(double DonGia) {
        this.DonGia = DonGia;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public int getSoLuongConLai() {
        return SoLuongConLai;
    }

    public void setSoLuongConLai(int SoLuongConLai) {
        this.SoLuongConLai = SoLuongConLai;
        updateStatus();
    }

    public void Input() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ten sach: ");
        this.TenSach = sc.nextLine().trim();
        System.out.print("Nhap ma the loai: ");
        this.MaTheLoai = sc.nextLine().trim();
        System.out.print("Nhap ma tac gia: ");
        this.MaTG = sc.nextLine().trim();
        System.out.print("Nhap ma NXB: ");
        this.MaNXB = sc.nextLine().trim();
        InputConLai();
    }

    // Dung khi DSBook da kiem tra hop le va gan san MaSach, MaTheLoai, MaTG, MaNXB
    public void InputConLai() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("Nhap ten sach: ");
            this.TenSach = sc.nextLine().trim();
            if (!this.TenSach.isEmpty()) {
                break;
            }
            System.out.println("Ten sach khong duoc de trong!");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while (true) {
            System.out.print("Nhap ngay xuat ban (dd/MM/yyyy): ");
            String strNgay = sc.nextLine().trim();
            try {
                LocalDate temp = LocalDate.parse(strNgay, formatter);
                if (temp.isAfter(LocalDate.now())) {
                    System.out.println("Ngay xuat ban khong the lon hon ngay hien tai. Vui long nhap lai!");
                    continue;
                }
                this.NgayXB = temp;
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Ngay xuat ban khong dung dinh dang dd/MM/yyyy. Vui long nhap lai!");
            }
        }

        while (true) {
            System.out.print("Nhap don gia: ");
            try {
                this.DonGia = Double.parseDouble(sc.nextLine().trim());
                if (this.DonGia > 0) {
                    break;
                }
                System.out.println("Don gia phai lon hon 0!");
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap so hop le cho don gia!");
            }
        }

        while (true) {
            System.out.print("Nhap so luong: ");
            try {
                this.SoLuong = Integer.parseInt(sc.nextLine().trim());
                if (this.SoLuong >= 0) {
                    break;
                }
                System.out.println("So luong khong duoc am!");
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap so nguyen hop le!");
            }
        }

        this.SoLuongConLai = this.SoLuong;
        updateStatus();
    }

    public void InputTuPhieuNhap(double donGia, int soLuong) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Nhap ten sach: ");
            this.TenSach = sc.nextLine().trim();
            if (!this.TenSach.isEmpty()) {
                break;
            }
            System.out.println("Ten sach khong duoc de trong!");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while (true) {
            System.out.print("Nhap ngay xuat ban (dd/MM/yyyy): ");
            String strNgay = sc.nextLine().trim();
            try {
                LocalDate temp = LocalDate.parse(strNgay, formatter);
                if (temp.isAfter(LocalDate.now())) {
                    System.out.println("Ngay xuat ban khong the lon hon ngay hien tai. Vui long nhap lai!");
                    continue;
                }
                this.NgayXB = temp;
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Ngay xuat ban khong dung dinh dang dd/MM/yyyy. Vui long nhap lai!");
            }
        }
        this.DonGia = donGia;
        this.SoLuong = soLuong;
        this.SoLuongConLai = soLuong;
        updateStatus();
    }

    public void updateStatus() {
        if (this.SoLuongConLai > 0) {
            this.TrangThai = "Con sach";
        } else {
            this.TrangThai = "Het sach";
        }
    }

    public abstract String getThongTinRieng();

    public abstract String getLoaiSach();

    public String toFileString() {
    updateStatus();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    String strNgayXB = (NgayXB != null) ? NgayXB.format(formatter) : "";
    return getLoaiSach() + "," + MaSach + "," + TenSach + "," + MaTheLoai + "," + MaTG + ","
            + strNgayXB + "," + TrangThai + "," + MaNXB + "," + (long) DonGia + "," + SoLuong + "," + SoLuongConLai;
}

    private String catChuoi(String s, int maxLen) {
        if (s == null) {
            return "";
        }
        if (s.length() <= maxLen) {
            return s;
        }
        return s.substring(0, maxLen - 2) + "..";
    }

    public void Output() {
        Output(MaTheLoai, MaTG, MaNXB);
    }

    // Khung bang rong 146 ky tu (vua khit terminal 147x37, khong bi dau ..)
    public void Output(String tenTheLoai, String tenTacGia, String tenNXB) {
        updateStatus();
        System.out.printf("| %-3s | %-19s | %-19s | %-15s | %-8s | %-10s | %-7.0f | %-2d | %-3d | %-8s | %-30s |\n",
                catChuoi(MaSach, 3),
                catChuoi(TenSach, 19),
                catChuoi(tenTheLoai, 19),
                catChuoi(tenTacGia, 15),
                catChuoi(tenNXB, 8),
                catChuoi(NgayXB != null ? NgayXB.toString() : "", 10),
                DonGia,
                SoLuong,
                SoLuongConLai,
                catChuoi(TrangThai, 8),
                catChuoi(getThongTinRieng(), 30));
    }
}
