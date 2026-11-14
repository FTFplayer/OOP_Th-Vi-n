package model;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Author {
    private String MaTG;
    private String HoTG;
    private String TenTG;
    private LocalDate NgaySinh;
    private String GioiTinh;
    private String SDT;

    public Author() {
        this.MaTG = "";
        this.HoTG = "";
        this.TenTG = "";
        this.NgaySinh = null;
        this.GioiTinh = "";
        this.SDT = "";
    }

    public Author(String MaTG, String HoTG, String TenTG, LocalDate NgaySinh, String GioiTinh, String SDT) {
        this.MaTG = MaTG;
        this.HoTG = HoTG;
        this.TenTG = TenTG;
        this.NgaySinh = NgaySinh;
        this.GioiTinh = GioiTinh;
        this.SDT = SDT;
    }

    public Author(Author copy) {
        if (copy != null) {
            this.MaTG = copy.MaTG;
            this.HoTG = copy.HoTG;
            this.TenTG = copy.TenTG;
            this.NgaySinh = copy.NgaySinh;
            this.GioiTinh = copy.GioiTinh;
            this.SDT = copy.SDT;
        }
    }

    public String getMaTG() {
        return MaTG;
    }

    public void setMaTG(String MaTG) {
        this.MaTG = MaTG;
    }

    public String getHoTG() {
        return HoTG;
    }

    public void setHoTG(String HoTG) {
        this.HoTG = HoTG;
    }

    public String getTenTG() {
        return TenTG;
    }

    public void setTenTG(String TenTG) {
        this.TenTG = TenTG;
    }

    public LocalDate getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(LocalDate NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    // Doi isGioiTinh() thanh getGioiTinh()
    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public void Input() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Scanner sc = new Scanner(System.in);

        // System.out.print("Nhap ma tac gia: ");
        // this.MaTG = sc.nextLine().trim();

        System.out.print("Nhap ho tac gia: ");
        this.HoTG = sc.nextLine().trim();

        System.out.print("Nhap ten tac gia: ");
        this.TenTG = sc.nextLine().trim();

        // Nhap ngay sinh (co kiem tra tuong lai)
        while (true) {
            System.out.print("Nhap ngay sinh (dd/MM/yyyy): ");
            String strNgaySinh = sc.nextLine().trim();

            try {
                LocalDate temp = LocalDate.parse(strNgaySinh, formatter);
                if (temp.isAfter(LocalDate.now())) {
                    System.out.println("Ngay sinh khong the lon hon ngay hien tai. Vui long nhap lai!");
                    continue;
                }
                this.NgaySinh = temp;
                break;
            } catch (DateTimeParseException e) {
                System.out.println(
                        "Ngay sinh nhap khong dung dinh dang dd/MM/yyyy hoac khong ton tai. Vui long nhap lai!");
            }
        }

        // Nhap gioi tinh (khong bi loi font console va chuan hoa chuoi)
        while (true) {
            System.out.print("Nhap gioi tinh (Nam/Nu): ");
            String strGioiTinh = sc.nextLine().trim();

            if (strGioiTinh.equals("Nam")) {
                this.GioiTinh = "Nam";
                break;
            } else if (strGioiTinh.equals("Nu")) {
                this.GioiTinh = "Nữ";
                break;
            }

            System.out.println("Gioi tinh khong hop le. Vui long nhap lai (Nam/Nu).");
        }

        // Nhap so dien thoai
        while (true) {
            System.out.print("Nhap so dien thoai tac gia: ");
            this.SDT = sc.nextLine().trim();

            if (this.SDT.length() != 10 || this.SDT.charAt(0) != '0') {
                System.out.println("So dien thoai phai gom dung 10 chu so va bat dau bang so 0. Vui long nhap lai!");
                continue;
            }

            boolean isDigit = true;
            for (int i = 0; i < this.SDT.length(); i++) {
                char c = this.SDT.charAt(i);
                if (c < '0' || c > '9') {
                    isDigit = false;
                    break;
                }
            }

            if (!isDigit) {
                System.out.println("So dien thoai chi duoc chua ky tu so. Vui long nhap lai!");
                continue;
            }
            break;
        }
    }

    // Tinh tuoi tac gia
    public int getAge() {
        if (this.NgaySinh == null) {
            return 0;
        }
        return Period.between(this.NgaySinh, LocalDate.now()).getYears();
    }

    // Phuong thuc xuat dinh dang bang
    public void Output() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormat = "|%-10s|%-12s|%-10s|%-14s|%-10s|%-12s|%n";
        String strNgaySinh = (this.NgaySinh == null) ? "" : this.NgaySinh.format(formatter);

        System.out.printf(dataFormat, this.MaTG, this.HoTG, this.TenTG, strNgaySinh, this.GioiTinh, this.SDT);
    }

}