package model;

import java.util.Scanner;

// MaNXB, TenNXB, SDT, Email, DiaChi
public class Publisher {
    // 1. Thuoc tinh
    private String MaNXB;
    private String TenNXB;
    private String SDT;
    private String Email;
    private String DiaChi;

    // -----------------------------------------------------------------------------------//
    // 2. Constructor
    // 2.1 Constructor khong tham so
    public Publisher() {
        this.MaNXB = "";
        this.TenNXB = "";
        this.SDT = "";
        this.Email = "";
        this.DiaChi = "";
    }

    // 2.2 Constructor co tham so
    public Publisher(String maNXB, String tenNXB, String SDT, String email, String diaChi) {
        this.MaNXB = maNXB;
        this.TenNXB = tenNXB;
        this.SDT = SDT;
        this.Email = email;
        this.DiaChi = diaChi;
    }

    // 2.3 Constructor sao chep
    public Publisher(Publisher other) {
        if (other != null) {
            this.MaNXB = other.MaNXB;
            this.TenNXB = other.TenNXB;
            this.SDT = other.SDT;
            this.Email = other.Email;
            this.DiaChi = other.DiaChi;
        }
    }

    // -----------------------------------------------------------------------------------//
    // 3. Getter and Setter
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
    // 4. Phuong thuc nhap
    public void Input() {
        Scanner scanner = new Scanner(System.in);

        // 4.1 Nhap ma NXB (Xu ly o DSPublisher)
        // System.out.print("Nhap ma nha xuat ban: ");
        // this.MaNXB = scanner.nextLine().trim();

        // 4.2 Nhap ten nha xuat ban
        System.out.print("Nhap ten nha xuat ban: ");
        this.TenNXB = scanner.nextLine().trim();

        // 4.3 Nhap so dien thoai
        while (true) {
            System.out.print("Nhap so dien thoai: ");
            this.SDT = scanner.nextLine().trim();

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

        // 4.4 Nhap email
        while (true) {
            System.out.print("Nhap email: ");
            this.Email = scanner.nextLine().trim();

            int atIndex = this.Email.indexOf('@');
            int dotIndex = this.Email.lastIndexOf('.');

            if (atIndex > 0 && dotIndex > atIndex + 1 && dotIndex < this.Email.length() - 1
                    && !this.Email.contains(" ")) {
                break;
            }
            System.out.println("Email khong hop le. Vui long nhap lai!");
        }

        // 4.5 Nhap dia chi
        System.out.print("Nhap dia chi: ");
        this.DiaChi = scanner.nextLine().trim();
    }

    // -----------------------------------------------------------------------------------//
    // 5. Phuong thuc xuat
    public void Output() {
        String DataFormat = "|%-8s|%-20s|%-12s|%-30s|%-25s|%n";
        System.out.printf(DataFormat, this.MaNXB, this.TenNXB, this.SDT, this.Email, this.DiaChi);
    }
}