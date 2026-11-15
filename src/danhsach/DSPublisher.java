package danhsach;

import model.Publisher;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Arrays;

public class DSPublisher {
    // 1. Thuộc tính
    private int n;
    private Publisher dsnxb[] = new Publisher[0];

    // -----------------------------------------------------------------------------------//
    // 2. Constructor
    // 2.1 Constructor không tham số
    public DSPublisher() {
        this.n = 0;
        this.dsnxb = new Publisher[0];
    }

    // 2.2 Constructor có tham số
    public DSPublisher(int n, Publisher[] dsnxb) {
        this.n = n;
        this.dsnxb = dsnxb;
    }

    // 2.3 Constructor sao chép
    public DSPublisher(DSPublisher copy) {
        if (copy != null) {
            this.n = copy.n;
            this.dsnxb = new Publisher[copy.n];
            for (int i = 0; i < n; i++) {
                if (copy.dsnxb[i] != null) {
                    this.dsnxb[i] = new Publisher(copy.dsnxb[i]);
                }
            }
        }
    }

    // -----------------------------------------------------------------------------------//
    // 3. In tiêu đề bảng
    public void PrintHeader() {
        System.out.format(
                "+--------+----------+----------+------------------------------+--------------------+%n");
        System.out.printf("|%-8s|%-10s|%-10s|%-30s|%-20s|%n",
                "Ma NXB", "Ten NXB", "SDT", "Email", "Dia Chi");
        System.out.format(
                "+--------+----------+----------+------------------------------+--------------------+%n");
    }

    // -----------------------------------------------------------------------------------//
    // 4. Nhập danh sách từ bàn phím
    public void Input() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Nhap so luong nha xuat ban: ");
            try {
                this.n = Integer.parseInt(sc.nextLine().trim());
                if (this.n >= 0) {
                    break;
                }
                System.out.println("So luong NXB khong duoc am. Vui long nhap lai!");
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap mot so nguyen hop le!");
            }
        }

        this.dsnxb = new Publisher[n];
        for (int i = 0; i < n; i++) {
            System.out.println("\n--- Nhap nha xuat ban thu " + (i + 1) + " ---");
            this.dsnxb[i] = new Publisher();
            this.dsnxb[i].Input();
        }
    }

    // -----------------------------------------------------------------------------------//
    // 5. Xuất danh sách
    public void Output() {
        if (this.n == 0) {
            System.out.println("Danh sach nha xuat ban dang trong!");
            return;
        }
        this.PrintHeader();
        for (int i = 0; i < n; i++) {
            if (this.dsnxb[i] != null) {
                this.dsnxb[i].Output();
            }
        }
        System.out.format(
                "+--------+----------+----------+------------------------------+--------------------+%n");
    }

    // -----------------------------------------------------------------------------------//
    // 6. Tìm kiếm

    // 6.1 Tìm kiếm theo mã nhà xuất bản
    public void TimPublisherTheoMa() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma NXB can tim: ");
        String MaNXBCanTim = sc.nextLine().trim();

        int index = TimPublisherTheoMa(MaNXBCanTim);
        if (index != -1) {
            System.out.println("Thong tin NXB co ma " + MaNXBCanTim + ":");
            this.PrintHeader();
            this.dsnxb[index].Output();
            System.out.format(
                    "+--------+----------+----------+------------------------------+--------------------+%n");
        } else {
            System.out.println("Khong tim thay NXB co ma " + MaNXBCanTim);
        }
    }

    public int TimPublisherTheoMa(String MaNXBCanTim) {
        for (int i = 0; i < n; i++) {
            if (this.dsnxb[i] != null && this.dsnxb[i].getMaNXB().equalsIgnoreCase(MaNXBCanTim)) {
                return i;
            }
        }
        return -1;
    }

    // 6.2 Tìm kiếm theo tên nhà xuất bản
    public void TimPublisherTheoTen() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ten NXB can tim: ");
        String TenCanTim = sc.nextLine().trim();

        int count = 0;
        this.PrintHeader();
        for (int i = 0; i < n; i++) {
            if (this.dsnxb[i] != null && this.dsnxb[i].getTenNXB().equalsIgnoreCase(TenCanTim)) {
                this.dsnxb[i].Output();
                count++;
            }
        }
        System.out.format(
                "+--------+----------+----------+------------------------------+--------------------+%n");

        if (count == 0) {
            System.out.println("Khong tim thay NXB co ten " + TenCanTim);
        }
    }

    // 6.3 Tìm kiếm theo số điện thoại nhà xuất bản
    public void TimPublisherTheoSDT() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap SDT NXB can tim: ");
        String SDTCanTim = sc.nextLine().trim();

        int index = TimPublisherTheoSDT(SDTCanTim);
        if (index != -1) {
            System.out.println("Thong tin NXB co SDT " + SDTCanTim + ":");
            this.PrintHeader();
            this.dsnxb[index].Output();
            System.out.format(
                    "+--------+----------+----------+------------------------------+--------------------+%n");
        } else {
            System.out.println("Khong tim thay NXB co SDT " + SDTCanTim);
        }
    }

    public int TimPublisherTheoSDT(String SDTCanTim) {
        for (int i = 0; i < n; i++) {
            if (this.dsnxb[i] != null && this.dsnxb[i].getSdt().equals(SDTCanTim)) {
                return i;
            }
        }
        return -1;
    }

    // -----------------------------------------------------------------------------------//
    // 7. Thêm

    public void ThemPublisher() {
        Scanner sc = new Scanner(System.in);
        dsnxb = Arrays.copyOf(dsnxb, n + 1);
        dsnxb[n] = new Publisher();
        System.out.println("Nhap thong tin nha xuat ban moi:");
        System.out.println("Nhap thong tin ma nha xuat ban: ");
        String MaNXB = sc.nextLine().trim();

        while (CheckMaNXB(MaNXB) || !CheckHopLe(MaNXB)) {
            if (CheckMaNXB(MaNXB)) {
                System.out.print("Ma tac gia bi trung. Vui long nhap lai: ");
            } else {
                System.out.print("Ma tac gia khong hop le (phai co dang TGxx). Vui long nhap lai: ");
            }
            MaNXB = sc.nextLine().trim();
        }

        dsnxb[n].setMaNXB(MaNXB);
        dsnxb[n].Input();
        n++;
    }

    public void ThemPublisher(Publisher publisher) {
        if (publisher != null && CheckMaNXB(publisher.getMaNXB())) {
            dsnxb = Arrays.copyOf(dsnxb, n + 1);
            dsnxb[n] = new Publisher(publisher);
            n++;
        }
    }

    // -----------------------------------------------------------------------------------//
    // 8. Xóa

    public void XoaPublisher(int pos) {
        if (pos >= n || pos < 0) {
            System.out.println("Vi tri xoa khong hop le.");
            return;
        }

        for (int i = pos; i < n - 1; i++) {
            dsnxb[i] = dsnxb[i + 1];
        }
        dsnxb[n - 1] = null;
        n--;
        dsnxb = Arrays.copyOf(dsnxb, n);
    }

    // 8.1 Xóa theo mã NXB
    public void XoaPublisherTheoMa() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma NXB can xoa: ");
        String MaNXBCanXoa = sc.nextLine().trim();

        int index = TimPublisherTheoMa(MaNXBCanXoa);
        if (index != -1) {
            XoaPublisher(index);
            System.out.println("Da xoa thanh cong NXB co ma " + MaNXBCanXoa);
        } else {
            System.out.println("Khong tim thay NXB co ma " + MaNXBCanXoa);
        }
    }

    // 8.2 Xóa theo tên NXB
    public void XoaPublisherTheoTen() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ten NXB can xoa: ");
        String TenCanXoa = sc.nextLine().trim();

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (this.dsnxb[i] != null && this.dsnxb[i].getTenNXB().equalsIgnoreCase(TenCanXoa)) {
                XoaPublisher(i);
                i--;
                count++;
            }
        }

        if (count == 0) {
            System.out.println("Khong tim thay NXB co ten " + TenCanXoa);
        } else {
            System.out.println("Da xoa " + count + " NXB co ten " + TenCanXoa);
        }
    }

    // 8.3 Xóa theo SDT
    public void XoaPublisherTheoSDT() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap SDT NXB can xoa: ");
        String SDTCanXoa = sc.nextLine().trim();

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (this.dsnxb[i] != null && this.dsnxb[i].getSdt().equals(SDTCanXoa)) {
                XoaPublisher(i);
                i--;
                count++;
            }
        }

        if (count == 0) {
            System.out.println("Khong tim thay NXB co SDT " + SDTCanXoa);
        } else {
            System.out.println("Da xoa " + count + " NXB co SDT " + SDTCanXoa);
        }
    }

    // 8.4 Xóa theo email
    public void XoaPublisherTheoEmail() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap email NXB can xoa: ");
        String EmailCanXoa = sc.nextLine().trim();

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (this.dsnxb[i] != null && this.dsnxb[i].getEmail().equalsIgnoreCase(EmailCanXoa)) {
                XoaPublisher(i);
                i--;
                count++;
            }
        }

        if (count == 0) {
            System.out.println("Khong tim thay NXB co email " + EmailCanXoa);
        } else {
            System.out.println("Da xoa " + count + " NXB co email " + EmailCanXoa);
        }
    }

    // 8.5 Xóa theo địa chỉ
    public void XoaPublisherTheoDiaChi() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap dia chi NXB can xoa: ");
        String DiaChiCanXoa = sc.nextLine().trim();

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (this.dsnxb[i] != null && this.dsnxb[i].getDiaChi().equalsIgnoreCase(DiaChiCanXoa)) {
                XoaPublisher(i);
                i--;
                count++;
            }
        }

        if (count == 0) {
            System.out.println("Khong tim thay NXB co dia chi " + DiaChiCanXoa);
        } else {
            System.out.println("Da xoa " + count + " NXB co dia chi " + DiaChiCanXoa);
        }
    }

    // -----------------------------------------------------------------------------------//
    // 9. Sửa
    public void SuaPublisher() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma NXB can sua: ");
        String MaNXBCanSua = sc.nextLine().trim();

        int index = TimPublisherTheoMa(MaNXBCanSua);
        if (index == -1) {
            System.out.println("Khong tim thay NXB co ma " + MaNXBCanSua);
            return;
        }

        while (true) {
            System.out.println();
            System.out.println("+-------------------------------------------+");
            System.out.println("|         MENU SUA THONG TIN NXB            |");
            System.out.println("+-------------------------------------------+");
            System.out.println("| 1) Sua ten NXB.                           |");
            System.out.println("| 2) Sua so dien thoai.                     |");
            System.out.println("| 3) Sua email.                             |");
            System.out.println("| 4) Sua dia chi.                           |");
            System.out.println("| 5) Thoat.                                 |");
            System.out.println("+-------------------------------------------+");
            System.out.print("Nhap lua chon tu 1 - 5: ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Lua chon phai la chu so!");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Nhap ten NXB moi: ");
                    this.dsnxb[index].setTenNXB(sc.nextLine().trim());
                    System.out.println("Cap nhat ten NXB thanh cong!");
                    break;
                case 2:
                    while (true) {
                        System.out.print("Nhap SDT moi: ");
                        String SDTMoi = sc.nextLine().trim();

                        if (SDTMoi.length() != 10 || SDTMoi.charAt(0) != '0') {
                            System.out.println("So dien thoai phai du 10 chu so va bat dau bang 0. Vui long nhap lai!");
                            continue;
                        }

                        boolean isDigit = true;
                        for (int i = 0; i < SDTMoi.length(); i++) {
                            char c = SDTMoi.charAt(i);
                            if (c < '0' || c > '9') {
                                isDigit = false;
                                break;
                            }
                        }
                        if (!isDigit) {
                            System.out.println("SDT chi duoc chua ky tu so. Vui long nhap lai!");
                            continue;
                        }

                        this.dsnxb[index].setSdt(SDTMoi);
                        System.out.println("Cap nhat SDT thanh cong!");
                        break;
                    }
                    break;
                case 3:
                    while (true) {
                        System.out.print("Nhap email moi: ");
                        String EmailMoi = sc.nextLine().trim();

                        int atIndex = EmailMoi.indexOf('@');
                        int dotIndex = EmailMoi.lastIndexOf('.');

                        if (atIndex > 0 && dotIndex > atIndex + 1 && dotIndex < EmailMoi.length() - 1
                                && !EmailMoi.contains(" ")) {
                            this.dsnxb[index].setEmail(EmailMoi);
                            System.out.println("Cap nhat email thanh cong!");
                            break;
                        }
                        System.out.println("Email khong hop le. Vui long nhap lai!");
                    }
                    break;
                case 4:
                    System.out.print("Nhap dia chi moi: ");
                    this.dsnxb[index].setDiaChi(sc.nextLine().trim());
                    System.out.println("Cap nhat dia chi thanh cong!");
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Lua chon khong hop le. Vui long chon lai tu 1 den 5!");
            }
        }
    }

    // -----------------------------------------------------------------------------------//
    // 10. Doc va Ghi file

    // 10.1 Doc file Publisher/Publisher.txt
    public void ReadFile() {
        int count = 0;
        Scanner sc = null;
        FileInputStream Instream = null;

        try {
            Instream = new FileInputStream("data/Publisher.txt");
            sc = new Scanner(Instream);
            this.dsnxb = new Publisher[0];

            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }

                String[] data = line.split(",");
                if (data.length >= 5) {
                    this.dsnxb = Arrays.copyOf(this.dsnxb, count + 1);
                    this.dsnxb[count] = new Publisher();

                    this.dsnxb[count].setMaNXB(data[0].trim());
                    this.dsnxb[count].setTenNXB(data[1].trim());
                    this.dsnxb[count].setSdt(data[2].trim());
                    this.dsnxb[count].setEmail(data[3].trim());
                    this.dsnxb[count].setDiaChi(data[4].trim());

                    count++;
                }
            }

            this.n = count;
            System.out.println("Lay du lieu thanh cong! Tong cong: " + this.n + " NXB.");

        } catch (FileNotFoundException e) {
            System.out.println("Loi: Khong tim thay file Publisher/Publisher.txt!");
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
    }

    // 10.2 Ghi file Publisher/Publisher.txt
    public void WriteFile() {
        if (this.n == 0) {
            System.out.println("Danh sach NXB dang trong, khong co du lieu de ghi!");
            return;
        }

        FileOutputStream Outstream = null;
        PrintWriter writer = null;

        try {
            Outstream = new FileOutputStream("data/Publisher.txt");
            writer = new PrintWriter(Outstream);

            for (int i = 0; i < this.n; i++) {
                if (this.dsnxb[i] != null) {
                    String line = this.dsnxb[i].getMaNXB() + ","
                            + this.dsnxb[i].getTenNXB() + ","
                            + this.dsnxb[i].getSdt() + ","
                            + this.dsnxb[i].getEmail() + ","
                            + this.dsnxb[i].getDiaChi();

                    writer.println(line);
                }
            }
            System.out.println("Ghi danh sach vao file Publisher/Publisher.txt thanh cong!");
        } catch (FileNotFoundException e) {
            System.out.println("Loi: Khong tim thay duong dan file de ghi!");
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    // -----------------------------------------------------------------------------------//
    // 11. Kiểm tra mã trùng

    public boolean CheckMaNXB(String MaNXB) {
        for (int i = 0; i < this.n; i++) {
            if (dsnxb[i] != null && dsnxb[i].getMaNXB().equals(MaNXB)) {
                return true;
            }
        }
        return false;
    }

    public boolean CheckKiTuNXB(String MaNXB) {
        return true;
    }

    // -----------------------------------------------------------------------------------//
    // 12. Kiểm tra tính hợp lệ của mã
    public boolean CheckHopLe(String MaNXB) {
        if (MaNXB.length() < 4 || !MaNXB.substring(0, 3).equals("NXB")) {
            return false;
        }

        for (int i = 3; i < MaNXB.length(); i++) {
            char c = MaNXB.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }
}