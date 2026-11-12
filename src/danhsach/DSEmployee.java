package danhsach;

import model.Employee;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.Arrays;

public class DSEmployee {
    // 1. Thuoc tinh
    private int n;
    private Employee dsnv[] = new Employee[0];

    // -----------------------------------------------------------------------------------//
    // 2. Constructor
    // 2.1 Constructor khong tham so
    public DSEmployee() {
        this.n = 0;
        this.dsnv = new Employee[0];
    }

    // 2.2 Constructor co tham so
    public DSEmployee(int n, Employee[] dsnv) {
        this.n = n;
        this.dsnv = dsnv;
    }

    // 2.3 Constructor sao chep
    public DSEmployee(DSEmployee copy) {
        if (copy != null) {
            this.n = copy.n;
            this.dsnv = new Employee[copy.n];
            for (int i = 0; i < n; i++) {
                if (copy.dsnv[i] != null) {
                    this.dsnv[i] = new Employee(copy.dsnv[i]);
                }
            }
        }
    }

    // -----------------------------------------------------------------------------------//
    // 3. In tieu de bang dong bo voi Output() cua Employee
    public void PrintHeader() {
        System.out.format(
                "+----------+------------+----------+----------+------------+------------+--------------------+---------------+%n");
        System.out.printf("|%-10s|%-12s|%-10s|%-10s|%-12s|%-12s|%-20s|%-15s|%n",
                "Ma NV", "Ho NV", "Ten NV", "Gioi Tinh", "Ngay Sinh", "SDT", "Dia Chi", "Luong");
        System.out.format(
                "+----------+------------+----------+----------+------------+------------+--------------------+---------------+%n");
    }

    // -----------------------------------------------------------------------------------//
    // 4. Nhap danh sach tu ban phim
    public void Input() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Nhap so luong nhan vien: ");
            try {
                this.n = Integer.parseInt(sc.nextLine().trim());
                if (this.n >= 0) {
                    break;
                }
                System.out.println("So luong nhan vien khong duoc am. Vui long nhap lai!");
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap mot so nguyen hop le!");
            }
        }

        this.dsnv = new Employee[n];
        for (int i = 0; i < n; i++) {
            System.out.println("\n--- Nhap nhan vien thu " + (i + 1) + " ---");
            this.dsnv[i] = new Employee();
            this.dsnv[i].Input();
        }
    }

    // -----------------------------------------------------------------------------------//
    // 5. Xuat danh sach
    public void Output() {
        if (this.n == 0) {
            System.out.println("Danh sach nhan vien dang trong!");
            return;
        }
        this.PrintHeader();
        for (int i = 0; i < n; i++) {
            if (this.dsnv[i] != null) {
                this.dsnv[i].Output();
            }
        }
        System.out.format(
                "+----------+------------+----------+----------+------------+------------+--------------------+---------------+%n");
    }

    // -----------------------------------------------------------------------------------//
    // 6. Tim kiem

    // 6.1 Tim theo ma nhan vien
    public void TimEmployeeTheoMa() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma nhan vien can tim: ");
        String MaNVCanTim = sc.nextLine().trim();

        int index = TimEmployeeTheoMa(MaNVCanTim);
        if (index != -1) {
            System.out.println("Thong tin nhan vien co ma " + MaNVCanTim + ":");
            this.PrintHeader();
            this.dsnv[index].Output();
            System.out.format(
                    "+----------+------------+----------+----------+------------+------------+--------------------+---------------+%n");
        } else {
            System.out.println("Khong tim thay nhan vien co ma " + MaNVCanTim);
        }
    }

    public int TimEmployeeTheoMa(String MaNVCanTim) {
        for (int i = 0; i < n; i++) {
            if (this.dsnv[i] != null && this.dsnv[i].getMaNV().equalsIgnoreCase(MaNVCanTim)) {
                return i;
            }
        }
        return -1;
    }

    // 6.2 Tim theo ho nhan vien
    public void TimEmployeeTheoHo() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ho nhan vien can tim: ");
        String HoCanTim = sc.nextLine().trim();

        int count = 0;
        this.PrintHeader();
        for (int i = 0; i < n; i++) {
            if (this.dsnv[i] != null && this.dsnv[i].getHoNV().equalsIgnoreCase(HoCanTim)) {
                this.dsnv[i].Output();
                count++;
            }
        }
        System.out.format(
                "+----------+------------+----------+----------+------------+------------+--------------------+---------------+%n");

        if (count == 0) {
            System.out.println("Khong tim thay nhan vien co ho " + HoCanTim);
        }
    }

    // 6.3 Tim theo ten nhan vien
    public void TimEmployeeTheoTen() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ten nhan vien can tim: ");
        String TenCanTim = sc.nextLine().trim();

        int count = 0;
        this.PrintHeader();
        for (int i = 0; i < n; i++) {
            if (this.dsnv[i] != null && this.dsnv[i].getTenNV().equalsIgnoreCase(TenCanTim)) {
                this.dsnv[i].Output();
                count++;
            }
        }
        System.out.format(
                "+----------+------------+----------+----------+------------+------------+--------------------+---------------+%n");

        if (count == 0) {
            System.out.println("Khong tim thay nhan vien co ten " + TenCanTim);
        }
    }

    // 6.4 Tim theo so dien thoai
    public void TimEmployeeTheoSDT() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap SDT nhan vien can tim: ");
        String SDTCanTim = sc.nextLine().trim();

        int index = TimEmployeeTheoSDT(SDTCanTim);
        if (index != -1) {
            System.out.println("Thong tin nhan vien co SDT " + SDTCanTim + ":");
            this.PrintHeader();
            this.dsnv[index].Output();
            System.out.format(
                    "+----------+------------+----------+----------+------------+------------+--------------------+---------------+%n");
        } else {
            System.out.println("Khong tim thay nhan vien co SDT " + SDTCanTim);
        }
    }

    public int TimEmployeeTheoSDT(String SDTCanTim) {
        for (int i = 0; i < n; i++) {
            if (this.dsnv[i] != null && this.dsnv[i].getSDT().equals(SDTCanTim)) {
                return i;
            }
        }
        return -1;
    }

    // -----------------------------------------------------------------------------------//
    // 7. Them

    public void ThemEmployee() {
        dsnv = Arrays.copyOf(dsnv, n + 1);
        dsnv[n] = new Employee();
        System.out.println("Nhap thong tin nhan vien moi:");
        dsnv[n].Input();
        n++;
    }

    public void ThemEmployee(Employee employee) {
        if (employee != null) {
            dsnv = Arrays.copyOf(dsnv, n + 1);
            dsnv[n] = new Employee(employee);
            n++;
        }
    }

    // -----------------------------------------------------------------------------------//
    // 8. Xoa

    public void XoaEmployee(int pos) {
        if (pos >= n || pos < 0) {
            System.out.println("Vi tri xoa khong hop le.");
            return;
        }

        for (int i = pos; i < n - 1; i++) {
            dsnv[i] = dsnv[i + 1];
        }
        dsnv[n - 1] = null;
        n--;
        dsnv = Arrays.copyOf(dsnv, n);
    }

    // 8.1 Xoa theo ma nhan vien
    public void XoaEmployeeTheoMa() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma nhan vien can xoa: ");
        String MaNVCanXoa = sc.nextLine().trim();

        int index = TimEmployeeTheoMa(MaNVCanXoa);
        if (index != -1) {
            XoaEmployee(index);
            System.out.println("Da xoa thanh cong nhan vien co ma " + MaNVCanXoa);
        } else {
            System.out.println("Khong tim thay nhan vien co ma " + MaNVCanXoa);
        }
    }

    // 8.2 Xoa theo ho
    public void XoaEmployeeTheoHo() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ho nhan vien can xoa: ");
        String HoCanXoa = sc.nextLine().trim();

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (this.dsnv[i] != null && this.dsnv[i].getHoNV().equalsIgnoreCase(HoCanXoa)) {
                XoaEmployee(i);
                i--;
                count++;
            }
        }

        if (count == 0) {
            System.out.println("Khong tim thay nhan vien co ho " + HoCanXoa);
        } else {
            System.out.println("Da xoa " + count + " nhan vien co ho " + HoCanXoa);
        }
    }

    // 8.3 Xoa theo ten
    public void XoaEmployeeTheoTen() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ten nhan vien can xoa: ");
        String TenCanXoa = sc.nextLine().trim();

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (this.dsnv[i] != null && this.dsnv[i].getTenNV().equalsIgnoreCase(TenCanXoa)) {
                XoaEmployee(i);
                i--;
                count++;
            }
        }

        if (count == 0) {
            System.out.println("Khong tim thay nhan vien co ten " + TenCanXoa);
        } else {
            System.out.println("Da xoa " + count + " nhan vien co ten " + TenCanXoa);
        }
    }

    // 8.4 Xoa theo gioi tinh
    public void XoaEmployeeTheoGioiTinh() {
        Scanner sc = new Scanner(System.in);
        String GioiTinhCanXoa;
        while (true) {
            System.out.print("Nhap gioi tinh can xoa (Nam/Nu): ");
            GioiTinhCanXoa = sc.nextLine().trim();

            if (GioiTinhCanXoa.equalsIgnoreCase("Nam")) {
                GioiTinhCanXoa = "Nam";
                break;
            } else if (GioiTinhCanXoa.equalsIgnoreCase("Nu")) {
                GioiTinhCanXoa = "Nu";
                break;
            }
            System.out.println("Gioi tinh khong hop le. Vui long nhap lai (Nam/Nu).");
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (this.dsnv[i] != null && this.dsnv[i].getGioiTinh().equalsIgnoreCase(GioiTinhCanXoa)) {
                XoaEmployee(i);
                i--;
                count++;
            }
        }

        if (count == 0) {
            System.out.println("Khong tim thay nhan vien co gioi tinh " + GioiTinhCanXoa);
        } else {
            System.out.println("Da xoa " + count + " nhan vien co gioi tinh " + GioiTinhCanXoa);
        }
    }

    // 8.5 Xoa theo ngay sinh
    public void XoaEmployeeTheoNgaySinh() {
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate NgaySinhCanXoa;

        while (true) {
            System.out.print("Nhap ngay sinh nhan vien can xoa (dd/MM/yyyy): ");
            String StrNgaySinh = sc.nextLine().trim();
            try {
                NgaySinhCanXoa = LocalDate.parse(StrNgaySinh, formatter);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Ngay sinh khong hop le. Vui long nhap lai!");
            }
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (this.dsnv[i] != null && this.dsnv[i].getNgaySinh() != null
                    && this.dsnv[i].getNgaySinh().equals(NgaySinhCanXoa)) {
                XoaEmployee(i);
                i--;
                count++;
            }
        }

        if (count == 0) {
            System.out.println("Khong tim thay nhan vien co ngay sinh nay.");
        } else {
            System.out.println("Da xoa " + count + " nhan vien co ngay sinh thoa dieu kien.");
        }
    }

    // 8.6 Xoa theo SDT
    public void XoaEmployeeTheoSDT() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap so dien thoai nhan vien can xoa: ");
        String SDTCanXoa = sc.nextLine().trim();

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (this.dsnv[i] != null && this.dsnv[i].getSDT().equals(SDTCanXoa)) {
                XoaEmployee(i);
                i--;
                count++;
            }
        }

        if (count == 0) {
            System.out.println("Khong tim thay nhan vien co so dien thoai " + SDTCanXoa);
        } else {
            System.out.println("Da xoa " + count + " nhan vien co so dien thoai " + SDTCanXoa);
        }
    }

    // 8.7 Xoa theo dia chi
    public void XoaEmployeeTheoDiaChi() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap dia chi nhan vien can xoa: ");
        String DiaChiCanXoa = sc.nextLine().trim();

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (this.dsnv[i] != null && this.dsnv[i].getDiaChi().equalsIgnoreCase(DiaChiCanXoa)) {
                XoaEmployee(i);
                i--;
                count++;
            }
        }

        if (count == 0) {
            System.out.println("Khong tim thay nhan vien co dia chi " + DiaChiCanXoa);
        } else {
            System.out.println("Da xoa " + count + " nhan vien co dia chi " + DiaChiCanXoa);
        }
    }

    // -----------------------------------------------------------------------------------//
    // 9. Sua
    public void SuaEmployee() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma nhan vien can sua: ");
        String MaNVCanSua = sc.nextLine().trim();

        int index = TimEmployeeTheoMa(MaNVCanSua);
        if (index == -1) {
            System.out.println("Khong tim thay nhan vien co ma " + MaNVCanSua);
            return;
        }

        while (true) {
            System.out.println();
            System.out.println("+-------------------------------------------+");
            System.out.println("|       MENU SUA THONG TIN NHAN VIEN        |");
            System.out.println("+-------------------------------------------+");
            System.out.println("| 1) Sua ho nhan vien.                      |");
            System.out.println("| 2) Sua ten nhan vien.                     |");
            System.out.println("| 3) Sua gioi tinh nhan vien.               |");
            System.out.println("| 4) Sua ngay sinh nhan vien.               |");
            System.out.println("| 5) Sua so dien thoai nhan vien.           |");
            System.out.println("| 6) Sua dia chi nhan vien.                 |");
            System.out.println("| 7) Sua luong nhan vien.                   |");
            System.out.println("| 8) Thoat.                                 |");
            System.out.println("+-------------------------------------------+");
            System.out.print("Nhap lua chon tu 1 - 8: ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Lua chon phai la chu so!");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Nhap ho moi: ");
                    this.dsnv[index].setHoNV(sc.nextLine().trim());
                    System.out.println("Cap nhat ho thanh cong!");
                    break;
                case 2:
                    System.out.print("Nhap ten moi: ");
                    this.dsnv[index].setTenNV(sc.nextLine().trim());
                    System.out.println("Cap nhat ten thanh cong!");
                    break;
                case 3:
                    while (true) {
                        System.out.print("Nhap gioi tinh moi (Nam/Nu): ");
                        String gt = sc.nextLine().trim();
                        if (gt.equalsIgnoreCase("Nam")) {
                            this.dsnv[index].setGioiTinh("Nam");
                            System.out.println("Cap nhat gioi tinh thanh cong!");
                            break;
                        } else if (gt.equalsIgnoreCase("Nu")) {
                            this.dsnv[index].setGioiTinh("Nu");
                            System.out.println("Cap nhat gioi tinh thanh cong!");
                            break;
                        }
                        System.out.println("Gioi tinh khong hop le. Vui long nhap lai (Nam/Nu).");
                    }
                    break;
                case 4:
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    while (true) {
                        System.out.print("Nhap ngay sinh moi (dd/MM/yyyy): ");
                        String strNgaySinh = sc.nextLine().trim();
                        try {
                            LocalDate ns = LocalDate.parse(strNgaySinh, formatter);
                            if (ns.isAfter(LocalDate.now())) {
                                System.out.println("Ngay sinh khong the o tuong lai. Vui long nhap lai!");
                                continue;
                            }
                            this.dsnv[index].setNgaySinh(ns);
                            System.out.println("Cap nhat ngay sinh thanh cong!");
                            break;
                        } catch (DateTimeParseException e) {
                            System.out.println("Ngay sinh khong dung dinh dang. Vui long nhap lai!");
                        }
                    }
                    break;
                case 5:
                    while (true) {
                        System.out.print("Nhap SDT moi: ");
                        String sdt = sc.nextLine().trim();
                        if (sdt.length() != 10 || sdt.charAt(0) != '0') {
                            System.out.println("SDT phai du 10 so va bat dau bang 0. Vui long nhap lai!");
                            continue;
                        }
                        boolean isDigit = true;
                        for (int i = 0; i < sdt.length(); i++) {
                            if (sdt.charAt(i) < '0' || sdt.charAt(i) > '9') {
                                isDigit = false;
                                break;
                            }
                        }
                        if (!isDigit) {
                            System.out.println("SDT chi chua ky tu so. Vui long nhap lai!");
                            continue;
                        }
                        this.dsnv[index].setSDT(sdt);
                        System.out.println("Cap nhat SDT thanh cong!");
                        break;
                    }
                    break;
                case 6:
                    System.out.print("Nhap dia chi moi: ");
                    this.dsnv[index].setDiaChi(sc.nextLine().trim());
                    System.out.println("Cap nhat dia chi thanh cong!");
                    break;
                case 7:
                    while (true) {
                        System.out.print("Nhap luong moi: ");
                        String strL = sc.nextLine().trim();
                        try {
                            float l = Float.parseFloat(strL);
                            if (l < 0) {
                                System.out.println("Luong khong duoc am. Vui long nhap lai!");
                                continue;
                            }
                            this.dsnv[index].setLuong(l);
                            System.out.println("Cap nhat luong thanh cong!");
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Luong phai la mot con so hop le!");
                        }
                    }
                    break;
                case 8:
                    return;
                default:
                    System.out.println("Lua chon khong hop le. Vui long chon lai tu 1 den 8!");
            }
        }
    }

    // -----------------------------------------------------------------------------------//
    // 10. Thong ke

    // 10.1 Thong ke theo gioi tinh
    public void ThongKeEmployeeTheoGioiTinh() {
        Scanner sc = new Scanner(System.in);
        String GioiTinhCanThongKe;
        while (true) {
            System.out.print("Nhap gioi tinh can thong ke (Nam/Nu): ");
            GioiTinhCanThongKe = sc.nextLine().trim();

            if (GioiTinhCanThongKe.equalsIgnoreCase("Nam")) {
                GioiTinhCanThongKe = "Nam";
                break;
            } else if (GioiTinhCanThongKe.equalsIgnoreCase("Nu")) {
                GioiTinhCanThongKe = "Nu";
                break;
            }
            System.out.println("Gioi tinh khong hop le. Vui long nhap lai (Nam/Nu).");
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (this.dsnv[i] != null && this.dsnv[i].getGioiTinh().equalsIgnoreCase(GioiTinhCanThongKe)) {
                count++;
            }
        }
        System.out.println("So luong nhan vien co gioi tinh " + GioiTinhCanThongKe + ": " + count);
    }

    // 10.2 Thong ke theo tuoi
    public int ThongKeEmployeeTheoTuoi() {
        Scanner sc = new Scanner(System.in);
        int TuoiCanThongKe;

        while (true) {
            System.out.print("Nhap tuoi can thong ke: ");
            try {
                TuoiCanThongKe = Integer.parseInt(sc.nextLine().trim());
                if (TuoiCanThongKe <= 0) {
                    System.out.println("Tuoi khong duoc be hon hoac bang 0. Vui long nhap lai!");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Tuoi phai la mot so nguyen hop le!");
            }
        }

        int count = 0;
        for (int i = 0; i < this.n; i++) {
            if (this.dsnv[i] != null && this.dsnv[i].TinhTuoi() == TuoiCanThongKe) {
                count++;
            }
        }
        return count;
    }

    // 10.3 Thong ke theo muc luong (Lon hon hoac bang)
    public int ThongKeEmployeeTheoLuongToiThieu() {
        Scanner sc = new Scanner(System.in);
        float luongMin;

        while (true) {
            System.out.print("Nhap muc luong toi thieu: ");
            try {
                luongMin = Float.parseFloat(sc.nextLine().trim());
                if (luongMin < 0) {
                    System.out.println("Muc luong khong duoc am. Vui long nhap lai!");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Luong phai la con so hop le!");
            }
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (this.dsnv[i] != null && this.dsnv[i].getLuong() >= luongMin) {
                count++;
            }
        }
        return count;
    }

    // -----------------------------------------------------------------------------------//
    // 11. Doc va Ghi file

    // 11.1 Doc file Employee/Employee.txt
    public void ReadFile() {
        int count = 0;
        Scanner sc = null;
        FileInputStream Instream = null;

        try {
            Instream = new FileInputStream("data/Employee.txt");
            sc = new Scanner(Instream);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            this.dsnv = new Employee[0];

            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }

                String[] data = line.split(",");
                if (data.length >= 8) {
                    this.dsnv = Arrays.copyOf(this.dsnv, count + 1);
                    this.dsnv[count] = new Employee();

                    this.dsnv[count].setMaNV(data[0].trim());
                    this.dsnv[count].setHoNV(data[1].trim());
                    this.dsnv[count].setTenNV(data[2].trim());
                    this.dsnv[count].setGioiTinh(data[3].trim());

                    // Xu ly ngay sinh
                    String strNgaySinh = data[4].trim();
                    if (!strNgaySinh.isEmpty()) {
                        try {
                            this.dsnv[count].setNgaySinh(LocalDate.parse(strNgaySinh, formatter));
                        } catch (DateTimeParseException e) {
                            this.dsnv[count].setNgaySinh(null);
                        }
                    }

                    this.dsnv[count].setSDT(data[5].trim());
                    this.dsnv[count].setDiaChi(data[6].trim());

                    // Xu ly luong
                    try {
                        this.dsnv[count].setLuong(Float.parseFloat(data[7].trim()));
                    } catch (NumberFormatException e) {
                        this.dsnv[count].setLuong(0);
                    }

                    count++;
                }
            }

            this.n = count;
            System.out.println("Lay du lieu thanh cong! Tong cong: " + this.n + " nhan vien.");

        } catch (FileNotFoundException e) {
            System.out.println("Loi: Khong tim thay file Employee/Employee.txt!");
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
    }

    // 11.2 Ghi file Employee/Employee.txt
    public void WriteFile() {
        if (this.n == 0) {
            System.out.println("Danh sach nhan vien dang trong, khong co du lieu de ghi!");
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        FileOutputStream Outstream = null;
        PrintWriter writer = null;

        try {
            Outstream = new FileOutputStream("data/Employee.txt");
            writer = new PrintWriter(Outstream);

            for (int i = 0; i < this.n; i++) {
                if (this.dsnv[i] != null) {
                    String strNgaySinh = (this.dsnv[i].getNgaySinh() != null)
                            ? this.dsnv[i].getNgaySinh().format(formatter)
                            : "";

                    String line = this.dsnv[i].getMaNV() + ","
                            + this.dsnv[i].getHoNV() + ","
                            + this.dsnv[i].getTenNV() + ","
                            + this.dsnv[i].getGioiTinh() + ","
                            + strNgaySinh + ","
                            + this.dsnv[i].getSDT() + ","
                            + this.dsnv[i].getDiaChi() + ","
                            + this.dsnv[i].getLuong();

                    writer.println(line);
                }
            }
            System.out.println("Ghi danh sach vao file Employee/Employee.txt thanh cong!");
        } catch (FileNotFoundException e) {
            System.out.println("Loi: Khong tim thay duong dan file de ghi!");
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}