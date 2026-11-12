package danhsach;

import model.Reader;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.Arrays;

public class DSReader {
    // 1. Thuộc tính
    private int n;
    private Reader dsdocgia[] = new Reader[0];

    // -----------------------------------------------------------------------------------//

    // 2. Constructor
    // 2.1 Constructor không tham số
    public DSReader() {
        this.n = 0;
        this.dsdocgia = new Reader[0];
    }

    // 2.2 Constructor có tham số
    public DSReader(int n, Reader[] dsdocgia) {
        this.n = n;
        this.dsdocgia = dsdocgia;
    }

    // 2.3 Constructor sao chép
    public DSReader(DSReader copy) {
        if (copy != null) {
            this.n = copy.n;
            this.dsdocgia = new Reader[copy.n];
            for (int i = 0; i < n; i++) {
                if (copy.dsdocgia[i] != null) {
                    this.dsdocgia[i] = new Reader(copy.dsdocgia[i]);
                }
            }
        }
    }

    // -----------------------------------------------------------------------------------//

    // 3. In kích thước
    public void PrintHeader() {
        System.out.format(
                "+-----+----------+----------+----------+---------+----------------------+----------+------------+%n");
        System.out.printf("|%-5s|%-10s|%-10s|%-10s|%-9s|%-22s|%-10s|%-12s|%n",
                "Ma DG", "Ho", "Ten", "Ngay Sinh", "Gioi Tinh", "Dia Chi", "Ngay Lap", "SDT");
        System.out.format(
                "+-----+----------+----------+----------+---------+----------------------+----------+------------+%n");
    }

    // -----------------------------------------------------------------------------------//

    // 4. Nhập danh sách
    public void Input() {
        Scanner sc = new Scanner(System.in);
        this.n = sc.nextInt();
        sc.nextLine();

        this.dsdocgia = new Reader[n];
        for (int i = 0; i < n; i++) {
            System.out.println("\n--- Nhap doc gia thu " + (i + 1) + " ---");
            this.dsdocgia[i] = new Reader();
            this.dsdocgia[i].Input();
        }
    }

    // -----------------------------------------------------------------------------------//

    // 5. Xem danh sách
    public void Output() {
        if (this.n == 0) {
            System.out.println("Danh sach doc gia dang trong!");
            return;
        }
        this.PrintHeader();
        for (int i = 0; i < n; i++) {
            if (this.dsdocgia[i] != null) {
                this.dsdocgia[i].Output();
            }
        }
        System.out.format(
                "+-----+----------+----------+----------+---------+----------------------+----------+------------+%n");
    }

    // -----------------------------------------------------------------------------------//

    // 6. Tìm kiếm

    // 6.1 Tìm kiếm theo mã
    public void TimReaderTheoMa() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma doc gia can tim: ");
        String MaDocGiaCanTim = sc.nextLine().trim();

        int index = TimReaderTheoMa(MaDocGiaCanTim);
        if (index != -1) {
            System.out.println("Thong tin doc gia co ma " + MaDocGiaCanTim + ":");
            this.PrintHeader();
            this.dsdocgia[index].Output();
            System.out.format(
                    "+-----+----------+----------+----------+---------+----------------------+----------+------------+%n");
        } else {
            System.out.println("Khong tim thay doc gia co ma " + MaDocGiaCanTim);
        }
    }

    public int TimReaderTheoMa(String MaDocGiaCanTim) {
        for (int i = 0; i < n; i++) {
            if (this.dsdocgia[i] != null && this.dsdocgia[i].getMaDocGia().equalsIgnoreCase(MaDocGiaCanTim)) {
                return i;
            }
        }
        return -1;
    }

    // 6.2 Tim kiếm theo họ
    public void TimReaderTheoHo() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ho doc gia can tim: ");
        String HoDocGiaCanTim = sc.nextLine().trim();

        int C = 0;
        this.PrintHeader();
        for (int i = 0; i < this.n; i++) {
            if (dsdocgia[i] != null && dsdocgia[i].getHo().equalsIgnoreCase(HoDocGiaCanTim)) {
                C++;
                dsdocgia[i].Output();
            }
        }
        System.out.format(
                "+-----+----------+----------+----------+---------+----------------------+----------+------------+%n");

        if (C == 0) {
            System.out.println("Khong tim thay doc gia co ho " + HoDocGiaCanTim);
        }
    }

    // 6.3 Tìm kiếm theo tên
    public void TimReaderTheoTen() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ten doc gia can tim: ");
        String TenDocGiaCanTim = sc.nextLine().trim();

        int C = 0;
        this.PrintHeader();
        for (int i = 0; i < this.n; i++) {
            if (dsdocgia[i] != null && dsdocgia[i].getTen().equalsIgnoreCase(TenDocGiaCanTim)) {
                C++;
                dsdocgia[i].Output();
            }
        }
        System.out.format(
                "+-----+----------+----------+----------+---------+----------------------+----------+------------+%n");

        if (C == 0) {
            System.out.println("Khong tim thay doc gia co ten " + TenDocGiaCanTim);
        }
    }

    // -----------------------------------------------------------------------------------//

    // 7. Thêm
    public void ThemReader() {
        dsdocgia = Arrays.copyOf(dsdocgia, n + 1);
        dsdocgia[n] = new Reader();
        System.out.println("Nhap thong tin doc gia moi:");
        dsdocgia[n].Input();
        n++;
    }

    public void ThemReader(Reader reader) {
        if (reader != null) {
            dsdocgia = Arrays.copyOf(dsdocgia, n + 1);
            dsdocgia[n] = new Reader(reader);
            n++;
        }
    }

    // -----------------------------------------------------------------------------------//

    // 8. Xóa độc giả
    public void XoaReader(int pos) {
        if (pos >= n || pos < 0) {
            System.out.println("Vi tri xoa khong hop le.");
            return;
        }

        for (int i = pos; i < n - 1; i++) {
            dsdocgia[i] = dsdocgia[i + 1];
        }
        dsdocgia[n - 1] = null;
        n--;
        dsdocgia = Arrays.copyOf(dsdocgia, n);
    }

    // Xóa độc giả theo mã
    public void XoaReaderTheoMa() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma doc gia can xoa: ");
        String MaDocGiaCanXoa = sc.nextLine().trim();

        int index = TimReaderTheoMa(MaDocGiaCanXoa);
        if (index != -1) {
            XoaReader(index);
            System.out.println("Da xoa thanh cong doc gia co ma " + MaDocGiaCanXoa);
        } else {
            System.out.println("Khong tim thay doc gia co ma " + MaDocGiaCanXoa);
        }
    }

    // Xóa độc giả theo họ
    public void XoaReaderTheoHo() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ho doc gia can xoa: ");
        String HoCanXoa = sc.nextLine().trim();

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (this.dsdocgia[i] != null && this.dsdocgia[i].getHo().equalsIgnoreCase(HoCanXoa)) {
                XoaReader(i);
                i--;
                count++;
            }
        }

        if (count == 0) {
            System.out.println("Khong tim thay doc gia co ho " + HoCanXoa);
        } else {
            System.out.println("Da xoa " + count + " doc gia co ho " + HoCanXoa);
        }
    }

    // Xóa độc giả theo tên
    public void XoaReaderTheoTen() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ten doc gia can xoa: ");
        String TenCanXoa = sc.nextLine().trim();

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (this.dsdocgia[i] != null && this.dsdocgia[i].getTen().equalsIgnoreCase(TenCanXoa)) {
                XoaReader(i);
                i--;
                count++;
            }
        }

        if (count == 0) {
            System.out.println("Khong tim thay doc gia co ten " + TenCanXoa);
        } else {
            System.out.println("Da xoa " + count + " doc gia co ten " + TenCanXoa);
        }
    }

    // Xóa độc giả theo địa chỉ
    public void XoaReaderTheoDiaChi() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap dia chi doc gia can xoa: ");
        String DiaChiCanXoa = sc.nextLine().trim();

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (this.dsdocgia[i] != null && this.dsdocgia[i].getDiaChi().equalsIgnoreCase(DiaChiCanXoa)) {
                XoaReader(i);
                i--;
                count++;
            }
        }

        if (count == 0) {
            System.out.println("Khong tim thay doc gia co dia chi " + DiaChiCanXoa);
        } else {
            System.out.println("Da xoa " + count + " doc gia co dia chi " + DiaChiCanXoa);
        }
    }

    // Xóa độc giả theo SDT
    public void XoaReaderTheoSDT() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap so dien thoai doc gia can xoa: ");
        String SDTCanXoa = sc.nextLine().trim();

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (this.dsdocgia[i] != null && this.dsdocgia[i].getSDT().equals(SDTCanXoa)) {
                XoaReader(i);
                i--;
                count++;
            }
        }

        if (count == 0) {
            System.out.println("Khong tim thay doc gia co so dien thoai " + SDTCanXoa);
        } else {
            System.out.println("Da xoa " + count + " doc gia co so dien thoai " + SDTCanXoa);
        }
    }

    // Xóa độc giả theo ngày lập thẻ
    public void XoaReaderTheoNgayLapThe() {
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate NgayLapTheCanXoa;

        while (true) {
            System.out.print("Nhap ngay lap the doc gia can xoa (dd/MM/yyyy): ");
            String StrNgayLapTheCanXoa = sc.nextLine().trim();
            try {
                NgayLapTheCanXoa = LocalDate.parse(StrNgayLapTheCanXoa, formatter);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Ngay lap the khong hop le. Vui long nhap lai!");
            }
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (this.dsdocgia[i] != null && this.dsdocgia[i].getNgayLapThe() != null
                    && this.dsdocgia[i].getNgayLapThe().equals(NgayLapTheCanXoa)) {
                XoaReader(i);
                i--;
                count++;
            }
        }

        if (count == 0) {
            System.out.println("Khong tim thay doc gia co ngay lap the nay.");
        } else {
            System.out.println("Da xoa " + count + " doc gia thoa dieu kien.");
        }
    }

    // Xóa độc giả theo ngày sinh
    public void XoaReaderTheoNgaySinh() {
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate NgaySinhCanXoa;

        while (true) {
            System.out.print("Nhap ngay sinh doc gia can xoa (dd/MM/yyyy): ");
            String StrNgaySinhCanXoa = sc.nextLine().trim();
            try {
                NgaySinhCanXoa = LocalDate.parse(StrNgaySinhCanXoa, formatter);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Ngay sinh khong hop le. Vui long nhap lai!");
            }
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (this.dsdocgia[i] != null && this.dsdocgia[i].getNgaySinh() != null
                    && this.dsdocgia[i].getNgaySinh().equals(NgaySinhCanXoa)) {
                XoaReader(i);
                i--;
                count++;
            }
        }

        if (count == 0) {
            System.out.println("Khong tim thay doc gia co ngay sinh nay.");
        } else {
            System.out.println("Da xoa " + count + " doc gia thoa dieu kien.");
        }
    }

    // Xóa độc giả theo giới tính
    public void XoaReaderTheoGioiTinh() {
        String strGioiTinhCanXoa;
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Nhap gioi tinh can xoa (Nam/Nu): ");
            String input = sc.nextLine().trim();

            if (input.equalsIgnoreCase("Nam")) {
                strGioiTinhCanXoa = "Nam";
                break;
            } else if (input.equalsIgnoreCase("Nu")) {
                strGioiTinhCanXoa = "Nu";
                break;
            }
            System.out.println("Gioi tinh khong hop le. Vui long nhap lai (Nam/Nu).");
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (this.dsdocgia[i] != null && this.dsdocgia[i].getGioiTinh().equalsIgnoreCase(strGioiTinhCanXoa)) {
                XoaReader(i);
                i--;
                count++;
            }
        }

        if (count == 0) {
            System.out.println("Khong tim thay doc gia co gioi tinh " + strGioiTinhCanXoa);
        } else {
            System.out.println("Da xoa " + count + " doc gia co gioi tinh " + strGioiTinhCanXoa);
        }
    }

    // -----------------------------------------------------------------------------------//

    // 9. Sua
    public void SuaReader() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma doc gia can sua: ");
        String MaDocGiaCanSua = sc.nextLine().trim();

        int index = TimReaderTheoMa(MaDocGiaCanSua);
        if (index == -1) {
            System.out.println("Khong tim thay doc gia co ma " + MaDocGiaCanSua);
            return;
        }

        while (true) {
            System.out.println();
            System.out.println("+-------------------------------------------+");
            System.out.println("|         MENU SUA THONG TIN DOC GIA        |");
            System.out.println("+-------------------------------------------+");
            System.out.println("| 1) Sua ho doc gia.                        |");
            System.out.println("| 2) Sua ten doc gia.                       |");
            System.out.println("| 3) Sua ngay sinh doc gia.                 |");
            System.out.println("| 4) Sua gioi tinh doc gia.                 |");
            System.out.println("| 5) Sua dia chi doc gia.                   |");
            System.out.println("| 6) Sua ngay lap the doc gia.              |");
            System.out.println("| 7) Sua so dien thoai doc gia.             |");
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
                    dsdocgia[index].setHo(sc.nextLine().trim());
                    System.out.println("Cap nhat ho thanh cong!");
                    break;
                case 2:
                    System.out.print("Nhap ten moi: ");
                    dsdocgia[index].setTen(sc.nextLine().trim());
                    System.out.println("Cap nhat ten thanh cong!");
                    break;
                case 3:
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    while (true) {
                        System.out.print("Nhap ngay sinh moi (dd/MM/yyyy): ");
                        String strNgaySinhCanSua = sc.nextLine().trim();
                        try {
                            LocalDate ngaySinh = LocalDate.parse(strNgaySinhCanSua, formatter);

                            if (ngaySinh.isAfter(LocalDate.now())) {
                                System.out.println("Ngay sinh khong the o tuong lai. Vui long nhap lai.");
                            } else if (this.dsdocgia[index].getNgayLapThe() != null
                                    && ngaySinh.isAfter(this.dsdocgia[index].getNgayLapThe())) {
                                System.out.println("Ngay sinh khong duoc sau ngay lap the. Vui long nhap lai.");
                            } else {
                                this.dsdocgia[index].setNgaySinh(ngaySinh);
                                System.out.println("Cap nhat ngay sinh thanh cong!");
                                break;
                            }
                        } catch (DateTimeParseException e) {
                            System.out.println("Ngay sinh khong dung dinh dang. Vui long nhap lai.");
                        }
                    }
                    break;
                case 4:
                    while (true) {
                        System.out.print("Nhap gioi tinh moi (Nam/Nu): ");
                        String str = sc.nextLine().trim();
                        if (str.equalsIgnoreCase("Nam")) {
                            dsdocgia[index].setGioiTinh("Nam");
                            System.out.println("Cap nhat gioi tinh thanh cong!");
                            break;
                        } else if (str.equalsIgnoreCase("Nu")) {
                            dsdocgia[index].setGioiTinh("Nu");
                            System.out.println("Cap nhat gioi tinh thanh cong!");
                            break;
                        }
                        System.out.println("Gioi tinh khong hop le. Vui long nhap lai.");
                    }
                    break;
                case 5:
                    System.out.print("Nhap dia chi moi: ");
                    dsdocgia[index].setDiaChi(sc.nextLine().trim());
                    System.out.println("Cap nhat dia chi thanh cong!");
                    break;
                case 6:
                    DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    while (true) {
                        System.out.print("Nhap ngay lap the moi (dd/MM/yyyy): ");
                        String StrNgayLapTheCanSua = sc.nextLine().trim();
                        try {
                            LocalDate NgayLapThe = LocalDate.parse(StrNgayLapTheCanSua, formatter2);

                            if (NgayLapThe.isAfter(LocalDate.now())) {
                                System.out.println("Ngay lap the khong duoc o tuong lai. Vui long nhap lai!");
                                continue;
                            }
                            if (dsdocgia[index].getNgaySinh() != null
                                    && NgayLapThe.isBefore(dsdocgia[index].getNgaySinh())) {
                                System.out.println("Ngay lap the khong duoc truoc ngay sinh. Vui long nhap lai.");
                                continue;
                            }

                            dsdocgia[index].setNgayLapThe(NgayLapThe);
                            System.out.println("Cap nhat ngay lap the thanh cong!");
                            break;
                        } catch (DateTimeParseException e) {
                            System.out.println("Dinh dang ngay lap the khong hop le. Vui long nhap lai.");
                        }
                    }
                    break;
                case 7:
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
                        dsdocgia[index].setSDT(sdt);
                        System.out.println("Cap nhat SDT thanh cong!");
                        break;
                    }
                    break;
                case 8:
                    return;
                default:
                    System.out.println("Lua chon khong hop le. Vui long chon lai.");
            }
        }
    }

    // -----------------------------------------------------------------------------------//

    // 10. Thong ke

    // 10.1 Thong ke theo ngay lap the
    public int ThongKeReaderTheoNgayLapThe() {
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate NgayLapTheCanThongKe;

        while (true) {
            System.out.print("Nhap ngay lap the doc gia can thong ke (dd/MM/yyyy): ");
            String StrNgayLapTheCanThongKe = sc.nextLine().trim();
            try {
                NgayLapTheCanThongKe = LocalDate.parse(StrNgayLapTheCanThongKe, formatter);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Ngay lap the khong hop le. Vui long nhap lai!");
            }
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (this.dsdocgia[i] != null && this.dsdocgia[i].getNgayLapThe() != null
                    && this.dsdocgia[i].getNgayLapThe().equals(NgayLapTheCanThongKe)) {
                count++;
            }
        }
        return count;
    }

    // 10.2 Thong ke theo tuoi
    public int ThongKeReaderTheoTuoi() {
        Scanner sc = new Scanner(System.in);
        int TuoiCanThongKe;

        while (true) {
            System.out.print("Nhap tuoi can thong ke: ");
            TuoiCanThongKe = sc.nextInt();
            sc.nextLine();
            if (TuoiCanThongKe <= 0) {
                System.out.println("Tuoi khong duoc be hon 0. Vui long nhap lai!");
                continue;
            }
            break;
        }

        int count = 0;
        for (int i = 0; i < this.n; i++) {
            if (dsdocgia[i] != null && dsdocgia[i].TinhTuoi() == TuoiCanThongKe) {
                count++;
            }
        }
        return count;
    }

    // 10.3 Thong ke theo gioi tinh
    public void ThongKeReaderTheoGioiTinh() {
        Scanner sc = new Scanner(System.in);
        String GioiTinhCanThongKe;
        while (true) {
            System.out.print("Nhap gioi tinh can thong ke (Nam/Nu): ");
            String input = sc.nextLine().trim();

            if (input.equalsIgnoreCase("Nam")) {
                GioiTinhCanThongKe = "Nam";
                break;
            } else if (input.equalsIgnoreCase("Nu")) {
                GioiTinhCanThongKe = "Nu";
                break;
            }
            System.out.println("Gioi tinh khong hop le. Vui long nhap lai (Nam/Nu).");
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (this.dsdocgia[i] != null && this.dsdocgia[i].getGioiTinh().equalsIgnoreCase(GioiTinhCanThongKe)) {
                count++;
            }
        }
        System.out.println("So luong doc gia co gioi tinh " + GioiTinhCanThongKe + ": " + count);
    }

    // -----------------------------------------------------------------------------------//

    // 11. Doc va Ghi file

    // 11.1 Doc file
    public void ReadFile() {
        int count = 0;
        Scanner sc = null;
        FileInputStream Instream = null;

        try {
            Instream = new FileInputStream("data/Reader.txt");
            sc = new Scanner(Instream);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            this.dsdocgia = new Reader[0];

            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }

                String[] data = line.split(",");
                if (data.length >= 8) {
                    this.dsdocgia = Arrays.copyOf(this.dsdocgia, count + 1);
                    this.dsdocgia[count] = new Reader();

                    this.dsdocgia[count].setMaDocGia(data[0].trim());
                    this.dsdocgia[count].setHo(data[1].trim());
                    this.dsdocgia[count].setTen(data[2].trim());

                    // Xu ly ngay sinh
                    String strNgaySinh = data[3].trim();
                    if (!strNgaySinh.isEmpty()) {
                        try {
                            this.dsdocgia[count].setNgaySinh(LocalDate.parse(strNgaySinh, formatter));
                        } catch (DateTimeParseException e) {
                            this.dsdocgia[count].setNgaySinh(null);
                        }
                    }

                    this.dsdocgia[count].setGioiTinh(data[4].trim());
                    this.dsdocgia[count].setDiaChi(data[5].trim());

                    // Xu ly ngay lap the
                    String strNgayLapThe = data[6].trim();
                    if (!strNgayLapThe.isEmpty()) {
                        try {
                            this.dsdocgia[count].setNgayLapThe(LocalDate.parse(strNgayLapThe, formatter));
                        } catch (DateTimeParseException e) {
                            this.dsdocgia[count].setNgayLapThe(null);
                        }
                    }

                    this.dsdocgia[count].setSDT(data[7].trim());
                    count++;
                }
            }

            this.n = count;
            System.out.println("Lay du lieu thanh cong! Tong cong: " + this.n + " doc gia.");

        } catch (FileNotFoundException e) {
            System.out.println("Loi: Khong tim thay file Reader/Reader.txt!");
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
    }

    // 11.2 Ghi file
    public void WriteFile() {
        if (this.n == 0) {
            System.out.println("Danh sach doc gia dang trong, khong co du lieu de ghi!");
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        FileOutputStream Outstream = null;
        PrintWriter writer = null;

        try {
            Outstream = new FileOutputStream("data/Reader.txt");
            writer = new PrintWriter(Outstream);

            for (int i = 0; i < this.n; i++) {
                if (this.dsdocgia[i] != null) {
                    String strNgaySinh = (this.dsdocgia[i].getNgaySinh() != null
                            ? this.dsdocgia[i].getNgaySinh().format(formatter)
                            : "");
                    String strNgayLapThe = (this.dsdocgia[i].getNgayLapThe() != null
                            ? this.dsdocgia[i].getNgayLapThe().format(formatter)
                            : "");

                    String line = this.dsdocgia[i].getMaDocGia() + ","
                            + this.dsdocgia[i].getHo() + ","
                            + this.dsdocgia[i].getTen() + ","
                            + strNgaySinh + ","
                            + this.dsdocgia[i].getGioiTinh() + ","
                            + this.dsdocgia[i].getDiaChi() + ","
                            + strNgayLapThe + ","
                            + this.dsdocgia[i].getSDT();

                    writer.println(line);
                }
            }
            System.out.println("Ghi danh sach vao file Reader/Reader.txt thanh cong!");
        } catch (FileNotFoundException e) {
            System.out.println("Loi: Khong tim thay duong dan file de ghi!");
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}