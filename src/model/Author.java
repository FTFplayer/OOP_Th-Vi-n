package model;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

// MaTG, HoTG, TenTG, NgaySinh, GioiTinh, SDT
public class Author {
    // 1. Thuoc tinh
    private String MaTG;
    private String HoTG;
    private String TenTG;
    private LocalDate NgaySinh;
    private String GioiTinh;
    private String SDT;

    // -----------------------------------------------------------------------------------//
    // 2. Constructor
    // 2.1 Constructor mac dinh
    public Author() {
        this.MaTG = "";
        this.HoTG = "";
        this.TenTG = "";
        this.NgaySinh = null;
        this.GioiTinh = "";
        this.SDT = "";
    }

    // 2.2 Constructor co tham so
    public Author(String MaTG, String HoTG, String TenTG, LocalDate NgaySinh, String GioiTinh, String SDT) {
        this.MaTG = MaTG;
        this.HoTG = HoTG;
        this.TenTG = TenTG;
        this.NgaySinh = NgaySinh;
        this.GioiTinh = GioiTinh;
        this.SDT = SDT;
    }

    // 2.3 Constructor sao chep
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

    // -----------------------------------------------------------------------------------//
    // 3. Getter va Setter
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

    // -----------------------------------------------------------------------------------//
    // 4. Phuong thuc nhap
    public void Input() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Scanner sc = new Scanner(System.in);

        // 4.1 Nhap ma tac gia (Xu ly o DSAuthor)
        // System.out.print("Nhap ma tac gia: ");
        // this.MaTG = sc.nextLine().trim();

        // 4.2 Nhap ho tac gia
        System.out.print("Nhap ho tac gia: ");
        this.HoTG = sc.nextLine().trim();

        // 4.3 Nhap ten tac gia
        System.out.print("Nhap ten tac gia: ");
        this.TenTG = sc.nextLine().trim();

        // 4.4 Nhap ngay sinh
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
                System.out.println("Ngay sinh nhap khong dung dinh dang hoac khong ton tai. Vui long nhap lai!");
            }
        }

        // 4.5 Nhap gioi tinh
        while (true) {
            System.out.print("Nhap gioi tinh (Nam/Nu): ");
            String strGioiTinh = sc.nextLine().trim();

            if (strGioiTinh.equalsIgnoreCase("Nam")) {
                this.GioiTinh = "Nam";
                break;
            } else if (strGioiTinh.equalsIgnoreCase("Nu")) {
                this.GioiTinh = "Nu";
                break;
            }

            System.out.println("Gioi tinh khong hop le. Vui long nhap lai (Nam/Nu).");
        }

        // 4.6 Nhap so dien thoai
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
                System.out.println("So dien thoai bi sai ky tu. Vui long nhap lai!");
                continue;
            }
            break;
        }
    }

    // -----------------------------------------------------------------------------------//
    // 5. Phuong thuc xuat
    public void Output() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormat = "|%-10s|%-12s|%-10s|%-14s|%-10s|%-12s|%n";
        String strNgaySinh = (this.NgaySinh == null) ? "" : this.NgaySinh.format(formatter);

        System.out.printf(dataFormat, this.MaTG, this.HoTG, this.TenTG, strNgaySinh, this.GioiTinh, this.SDT);
    }

    // -----------------------------------------------------------------------------------//
    // 6. Tinh tuoi tac gia
    public int getAge() {
        if (this.NgaySinh == null) {
            return 0;
        }
        return Period.between(this.NgaySinh, LocalDate.now()).getYears();
    }
}