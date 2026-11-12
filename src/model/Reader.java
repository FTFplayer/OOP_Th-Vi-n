package Reader;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

//MaDocGia, Ho, Ten, NgaySinh, GioiTinh, DiaChi, Sdt, NgayLapThe
public class Reader {
    // 1. Thuoc tinh
    private String MaDocGia;
    private String Ho;
    private String Ten;
    private LocalDate NgaySinh;
    private String GioiTinh;
    private String DiaChi;
    private LocalDate NgayLapThe;
    private String SDT;

    // 2. Constructor
    // 2.1 Constructor mặc định
    public Reader() {
        this.MaDocGia = "";
        this.Ho = "";
        this.Ten = "";
        this.NgaySinh = null;
        this.GioiTinh = "";
        this.DiaChi = "";
        this.NgayLapThe = null;
        this.SDT = "";
    }

    // 2.2 Constructor có tham số
    public Reader(String MaDocGia, String Ho, String Ten, LocalDate NgaySinh, String GioiTinh, String DiaChi,
            LocalDate NgayLapThe, String SDT) {
        this.MaDocGia = MaDocGia;
        this.Ho = Ho;
        this.Ten = Ten;
        this.NgaySinh = NgaySinh;
        this.GioiTinh = GioiTinh;
        this.DiaChi = DiaChi;
        this.NgayLapThe = NgayLapThe;
        this.SDT = SDT;
    }

    // 2.3 Constructor sao chép
    public Reader(Reader copy) {
        this.MaDocGia = copy.MaDocGia;
        this.Ho = copy.Ho;
        this.Ten = copy.Ten;
        this.NgaySinh = copy.NgaySinh;
        this.GioiTinh = copy.GioiTinh;
        this.DiaChi = copy.DiaChi;
        this.NgayLapThe = copy.NgayLapThe;
        this.SDT = copy.SDT;
    }

    // -----------------------------------------------------------------------------------//
    // 3. Getter va Setter
    public String getMaDocGia() {
        return MaDocGia;
    }

    public void setMaDocGia(String MaDocGia) {
        this.MaDocGia = MaDocGia;
    }

    public String getHo() {
        return Ho;
    }

    public void setHo(String Ho) {
        this.Ho = Ho;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String Ten) {
        this.Ten = Ten;
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

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public LocalDate getNgayLapThe() {
        return NgayLapThe;
    }

    public void setNgayLapThe(LocalDate NgayLapThe) {
        this.NgayLapThe = NgayLapThe;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    // -----------------------------------------------------------------------------------//
    // 4. Phuong thuc nhập
    public void Input() {
        Scanner scanner = new Scanner(System.in);

        // 4.1 Nhap thong tin ma doc gia
        System.out.print("Nhap ma doc gia: ");
        this.MaDocGia = scanner.nextLine().trim();

        // 4.2 Nhap thong tin ho
        System.out.print("Nhap ho: ");
        this.Ho = scanner.nextLine().trim();

        // 4.3 Nhap thong tin ten
        System.out.print("Nhap ten: ");
        this.Ten = scanner.nextLine().trim();

        // 4.4 Nhap thong tin ngay sinh
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while (true) {
            System.out.print("Nhap ngay sinh (dd/MM/yyyy): ");
            String StrNgaySinh = scanner.nextLine().trim();

            try {
                LocalDate Temp = LocalDate.parse(StrNgaySinh, formatter);
                if (Temp.isAfter(LocalDate.now())) {
                    System.out.println("Ngay sinh khong the lon hon ngay hien tai. Vui long nhap lai!");
                    continue;
                }
                this.NgaySinh = Temp;
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Ngay sinh nhap khong dung dinh dang hoac khong ton tai. Vui long nhap lai!");
            }
        }

        // 4.5 Nhap thong tin gioi tinh
        while (true) {
            System.out.print("Nhap gioi tinh (Nam/Nu): ");
            String strGioiTinh = scanner.nextLine().trim();

            if (strGioiTinh.equalsIgnoreCase("Nam")) {
                this.GioiTinh = "Nam";
                break;
            } else if (strGioiTinh.equalsIgnoreCase("Nu")) {
                this.GioiTinh = "Nu";
                break;
            }

            System.out.println("Gioi tinh khong hop le. Vui long nhap lai (Nam/Nu).");
        }

        // 4.6 Nhap thong tin dia chi
        System.out.print("Nhap dia chi: ");
        this.DiaChi = scanner.nextLine();

        // 4.7 Nhap thong tin ngay lap the
        while (true) {
            System.out.print("Nhap ngay lap the (dd/MM/yyyy): ");
            String StrNgayLapThe = scanner.nextLine().trim();

            try {
                LocalDate temp = LocalDate.parse(StrNgayLapThe, formatter);

                if (this.NgaySinh != null && temp.isBefore(this.NgaySinh)) {
                    System.out.println("Ngay lap the khong duoc truoc ngay sinh. Vui long nhap lai.");
                    continue;
                }

                if (temp.isAfter(LocalDate.now())) {
                    System.out.println("Ngay lap the khong the lon hon ngay hien tai. Vui long nhap lai.");
                    continue;
                }
                this.NgayLapThe = temp;
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Ngay lap the khong hop le. Vui long nhap lai.");
            }
        }

        // 4.8 Nhap thong tin so dien thoai
        while (true) {
            System.out.print("Nhap so dien thoai cua doc gia: ");
            this.SDT = scanner.nextLine().trim();

            // Sửa thành != '0'
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
        String DataFormat = "|%-5s|%-10s|%-10s|%-10s|%-9s|%-22s|%-10s|%-12s|%n";

        String StrNgaySinh = this.NgaySinh == null ? "" : this.NgaySinh.format(formatter);
        String StrNgayLapThe = this.NgayLapThe == null ? "" : this.NgayLapThe.format(formatter);

        System.out.printf(DataFormat, this.MaDocGia, this.Ho, this.Ten, StrNgaySinh, this.GioiTinh, this.DiaChi,
                StrNgayLapThe, this.SDT);
    }

    // -----------------------------------------------------------------------------------//
    // 6. Tinh tuoi doc gia
    public int TinhTuoi() {
        if (this.NgaySinh == null) {
            return 0;
        }
        LocalDate today = LocalDate.now();
        return Period.between(this.NgaySinh, today).getYears();
    }
}