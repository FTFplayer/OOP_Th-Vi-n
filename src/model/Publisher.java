package model;

import java.util.Scanner;

//MaNXB, TenNXB, SDT, Email, DiaChi
public class Publisher {
    // 1. Thuộc tính
    private String MaNXB;
    private String TenNXB;
    private String SDT;
    private String Email;
    private String DiaChi;

    // 2. Constructor
    // 2.1 Constructor không tham số
    public Publisher() {
        this.MaNXB = "";
        this.TenNXB = "";
        this.SDT = "";
        this.Email = "";
        this.DiaChi = "";
    }

    // 2.2 Constructor có tham số
    public Publisher(String maNXB, String tenNXB, String SDT, String email, String diaChi) {
        this.MaNXB = maNXB;
        this.TenNXB = tenNXB;
        this.SDT = SDT;
        this.Email = email;
        this.DiaChi = diaChi;
    }

    // 2.3 Constructor sao chép
    public Publisher(Publisher other) {
        this.MaNXB = other.MaNXB;
        this.TenNXB = other.TenNXB;
        this.SDT = other.SDT;
        this.Email = other.Email;
        this.DiaChi = other.DiaChi;
    }

    // -----------------------------------------------------------------------------------//
    // 3.Getter and Setter
    public String getMaNXB() {
        return MaNXB;
    }

    public void setMaNXB(String maNXB) {
        this.MaNXB = maNXB;
    }

    public String getTenNXB() {
        return TenNXB;
    }

    public void setTenNXB(String tenNXB) {
        this.TenNXB = tenNXB;
    }

    public String getSdt() {
        return SDT;
    }

    public void setSdt(String SDT) {
        this.SDT = SDT;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        this.DiaChi = diaChi;
    }

    // -----------------------------------------------------------------------------------//
    // 4. Input
    public void Input() {
        Scanner scanner = new Scanner(System.in);

        // System.out.print("Nhập mã nhà xuất bản: ");
        // this.MaNXB = scanner.nextLine().trim();

        System.out.print("Nhập tên nhà xuất bản: ");
        this.TenNXB = scanner.nextLine().trim();

        while (true) {
            System.out.print("Nhập số điện thoại: ");
            this.SDT = scanner.nextLine();

            if (SDT.length() != 10 && SDT.charAt(0) == '0') {
                System.out.println("So dien thoai phai du 10 ky tu va ky tu dau la 0. Vui long nhap lai!");
                continue;
            }

            boolean isDigit = true;

            for (int i = 0; i < SDT.length(); i++) {
                char c = SDT.charAt(i);
                if (c < '0' || c > '9') {
                    isDigit = false;
                    break;
                }
            }
            if (!isDigit) {
                System.out.println("Nhap loi ky tu so dien thoai. Vui long nhap lai");
                continue;
            }
            break;
        }

        while (true) {
            System.out.println("Nhap email: ");
            this.Email = scanner.nextLine().trim();

            int atIndex = this.Email.indexOf('@');
            int dotIndex = this.Email.lastIndexOf('.');

            if (atIndex > 0 && dotIndex > atIndex + 1 && dotIndex < this.Email.length() - 1
                    && !this.Email.contains(" ")) {
                break;
            }
            System.out.println("Email khong hop le. Vui long nhap lai");
        }

        System.out.print("Nhập địa chỉ: ");
        DiaChi = scanner.nextLine();
    }

    // -----------------------------------------------------------------------------------//
    // 5. Output
    public void Output() {
        String DataFormat = "|%-8s|%-10s|%-10s|%-30s|%-20s|%n";
        System.out.printf(DataFormat, this.MaNXB, this.TenNXB, this.SDT, this.Email, this.DiaChi);
    }
}
