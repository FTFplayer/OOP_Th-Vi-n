package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.Period;
import java.util.Scanner;

// MaNV, HoNV, TenNV, GioiTinh, NgaySinh, SDT, DiaChi, Luong
public class Employee {
    // 1. Thuoc tinh
    private String MaNV;
    private String HoNV;
    private String TenNV;
    private String GioiTinh;
    private LocalDate NgaySinh;
    private String SDT;
    private String DiaChi;
    private float Luong;

    // -----------------------------------------------------------------------------------//
    // 2. Constructors
    // 2.1 Constructor mac dinh
    public Employee() {
        this.MaNV = "";
        this.HoNV = "";
        this.TenNV = "";
        this.GioiTinh = "";
        this.NgaySinh = null;
        this.SDT = "";
        this.DiaChi = "";
        this.Luong = 0;
    }

    // 2.2 Constructor day du tham so
    public Employee(String MaNV, String HoNV, String TenNV, String GioiTinh, LocalDate NgaySinh, String SDT,
            String DiaChi, float Luong) {
        this.MaNV = MaNV;
        this.HoNV = HoNV;
        this.TenNV = TenNV;
        this.GioiTinh = GioiTinh;
        this.NgaySinh = NgaySinh;
        this.SDT = SDT;
        this.DiaChi = DiaChi;
        this.Luong = Luong;
    }

    // 2.3 Constructor sao chep
    public Employee(Employee copy) {
        this.MaNV = copy.MaNV;
        this.HoNV = copy.HoNV;
        this.TenNV = copy.TenNV;
        this.GioiTinh = copy.GioiTinh;
        this.NgaySinh = copy.NgaySinh;
        this.SDT = copy.SDT;
        this.DiaChi = copy.DiaChi;
        this.Luong = copy.Luong;
    }

    // -----------------------------------------------------------------------------------//
    // 3. Getter va Setter
    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String maNV) {
        this.MaNV = maNV;
    }

    public String getHoNV() {
        return HoNV;
    }

    public void setHoNV(String hoNV) {
        this.HoNV = hoNV;
    }

    public String getTenNV() {
        return TenNV;
    }

    public void setTenNV(String tenNV) {
        this.TenNV = tenNV;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public LocalDate getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(LocalDate ngaySinh) {
        this.NgaySinh = ngaySinh;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        this.DiaChi = diaChi;
    }

    public float getLuong() {
        return Luong;
    }

    public void setLuong(float Luong) {
        this.Luong = Luong;
    }

    // -----------------------------------------------------------------------------------//
    // 4. Phuong thuc nhap
    public void Input() {
        Scanner sc = new Scanner(System.in);

        // 4.1 Nhap ho nhan vien
        System.out.print("Nhap ho nhan vien: ");
        this.HoNV = sc.nextLine().trim();

        // 4.2 Nhap ten nhan vien
        System.out.print("Nhap ten nhan vien: ");
        this.TenNV = sc.nextLine().trim();

        // 4.3 Nhap gioi tinh nhan vien
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

        // 4.4 Nhap ngay sinh nhan vien
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while (true) {
            System.out.print("Nhap ngay sinh (dd/MM/yyyy): ");
            String strNgaySinh = sc.nextLine().trim();

            try {
                LocalDate temp = LocalDate.parse(strNgaySinh, dateFormatter);
                if (temp.isAfter(LocalDate.now())) {
                    System.out.println("Ngay sinh khong the lon hon ngay hien tai. Vui long nhap lai!");
                    continue;
                }
                this.NgaySinh = temp;
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Dinh dang ngay khong hop le hoac ngay khong ton tai. Vui long nhap lai!");
            }
        }

        // 4.5 Nhap so dien thoai
        while (true) {
            System.out.print("Nhap so dien thoai: ");
            this.SDT = sc.nextLine().trim();

            if (this.SDT.length() != 10 || this.SDT.charAt(0) != '0') {
                System.out.println("So dien thoai phai du 10 chu so va bat dau bang so 0. Vui long nhap lai!");
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

        // 4.6 Nhap dia chi nhan vien
        System.out.print("Nhap dia chi nhan vien: ");
        this.DiaChi = sc.nextLine().trim();

        // 4.7 Nhap luong nhan vien
        while (true) {
            System.out.print("Nhap muc luong nhan vien: ");
            try {
                this.Luong = Float.parseFloat(sc.nextLine().trim());
                if (this.Luong < 0) {
                    System.out.println("Muc luong khong duoc am. Vui long nhap lai!");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Muc luong phai la mot con so hop le. Vui long nhap lai!");
            }
        }
    }

    // -----------------------------------------------------------------------------------//
    // 5. Phuong thuc xuat
    public void Output() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormat = "|%-10s|%-12s|%-10s|%-10s|%-12s|%-12s|%-20s|%-15.2f|%n";
        String strNgaySinh = (this.NgaySinh == null) ? "" : this.NgaySinh.format(dateFormatter);

        System.out.printf(dataFormat, this.MaNV, this.HoNV, this.TenNV, this.GioiTinh, strNgaySinh, this.SDT,
                this.DiaChi, this.Luong);
    }

    // -----------------------------------------------------------------------------------//
    // 6. Tinh tuoi nhan vien
    public int TinhTuoi() {
        if (this.NgaySinh == null) {
            return 0;
        }
        LocalDate today = LocalDate.now();
        return Period.between(this.NgaySinh, today).getYears();
    }
}