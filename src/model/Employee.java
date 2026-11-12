package Employee;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.Period;
import java.util.Scanner;

//MaNV, Ho, Ten, NgaySinh, GioiTinh, SoDienThoai, DiaChi, Luong
public class Employee {
    // 1. Thuộc tính
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
    // 2.1 Constructor không tham số (mặc định)
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

    // 2.2 Constructor đầy đủ tham số
    public Employee(String MaNV, String HoNV, String TenNV, String GioiTinh, LocalDate NgaySinh, String SDT,
            String DiaChi, float Luong) {
        this.MaNV = MaNV;
        this.HoNV = HoNV;
        this.TenNV = TenNV;
        this.GioiTinh = GioiTinh;
        this.SDT = SDT;
        this.NgaySinh = NgaySinh;
        this.DiaChi = DiaChi;
        this.Luong = Luong;
    }

    // 2.3 Constructor sao chép
    public Employee(Employee copy) {
        this.MaNV = copy.MaNV;
        this.HoNV = copy.HoNV;
        this.TenNV = copy.TenNV;
        this.NgaySinh = copy.NgaySinh;
        this.GioiTinh = copy.GioiTinh;
        this.SDT = copy.SDT;
        this.DiaChi = copy.DiaChi;
        this.Luong = copy.Luong;
    }

    // -----------------------------------------------------------------------------------//

    // 3. Getter and Setter
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

    // Đã đổi từ isGioiTinh() sang getGioiTinh()
    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    // Bổ sung Getter và Setter cho NgaySinh bị thiếu
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

    // 4. Input
    public void Input() {
        Scanner sc = new Scanner(System.in);

        // 4.1 Nhập mã nhân viên
        System.out.print("Nhap ma nhan vien: ");
        this.MaNV = sc.nextLine().trim();

        // 4.2 Nhập họ nhân viên
        System.out.print("Nhap ho nhan vien: ");
        this.HoNV = sc.nextLine().trim();

        // 4.3 Nhập tên nhân viên
        System.out.print("Nhap ten nhan vien: ");
        this.TenNV = sc.nextLine().trim();

        // 4.4 Nhập giới tính nhân viên
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

        // 4.5 Nhập ngày sinh nhân viên
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

        // 4.6 Bổ sung phần nhập số điện thoại
        while (true) {
            System.out.print("Nhap so dien thoai: ");
            this.SDT = sc.nextLine().trim();

            if (this.SDT.isEmpty() || this.SDT.length() != 10 || this.SDT.charAt(0) != '0') {
                System.out.println("So dien thoai phai du 10 ky tu va bat dau bang so 0. Vui long nhap lai!");
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

        // 4.7 Nhập địa chỉ nhân viên
        System.out.print("Nhap dia chi nhan vien: ");
        this.DiaChi = sc.nextLine().trim();

        // 4.8 Nhập lương nhân viên
        while (true) {
            System.out.println("Nhap muc luong cua ban: ");
            this.Luong = sc.nextFloat();

            if (Luong < 0) {
                System.out.println("Muc luong cua ban khong duoc am. Vui long nhap lai");
                continue;
            }

            break;
        }
    }

    // -----------------------------------------------------------------------------------//
    // 5. Output
    public void Output() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormat = "|%-10s|%-12s|%-10s|%-10s|%-12s|%-12s|%-20s|%-15.2f|%n";
        String strNgaySinh = (this.NgaySinh == null) ? "" : this.NgaySinh.format(dateFormatter);

        System.out.printf(dataFormat, this.MaNV, this.HoNV, this.TenNV, this.GioiTinh, strNgaySinh, this.SDT,
                this.DiaChi, this.Luong);
    }

    // -----------------------------------------------------------------------------------//
    // 6. Tính tuổi
    public int TinhTuoi() {
        if (this.NgaySinh == null) {
            return 0;
        }
        LocalDate today = LocalDate.now();
        return Period.between(this.NgaySinh, today).getYears();
    }

}
