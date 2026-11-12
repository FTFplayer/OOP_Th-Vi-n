package danhsach;

import model.Author;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.Arrays;

public class DSAuthor {
    // 1. Thuoc tinh
    private int n;
    private Author dstacgia[] = new Author[0];

    // -----------------------------------------------------------------------------------//
    // 2. Constructor
    // 2.1 Constructor khong tham so
    public DSAuthor() {
        this.n = 0;
        this.dstacgia = new Author[0];
    }

    // 2.2 Constructor co tham so
    public DSAuthor(int n, Author[] dstacgia) {
        this.n = n;
        this.dstacgia = dstacgia;
    }

    // 2.3 Constructor sao chep
    public DSAuthor(DSAuthor copy) {
        if (copy != null) {
            this.n = copy.n;
            this.dstacgia = new Author[copy.n];
            for (int i = 0; i < n; i++) {
                if (copy.dstacgia[i] != null) {
                    this.dstacgia[i] = new Author(copy.dstacgia[i]);
                }
            }
        }
    }

    // -----------------------------------------------------------------------------------//
    // 3. In tieu de bang dong bo voi Output() cua Author
    public void PrintHeader() {
        System.out.format("+----------+------------+----------+--------------+----------+------------+%n");
        System.out.printf("|%-10s|%-12s|%-10s|%-14s|%-10s|%-12s|%n",
                "Ma TG", "Ho TG", "Ten TG", "Ngay Sinh", "Gioi Tinh", "SDT");
        System.out.format("+----------+------------+----------+--------------+----------+------------+%n");
    }

    // -----------------------------------------------------------------------------------//
    // 4. Nhap danh sach tu ban phim
    public void Input() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Nhap so luong tac gia: ");
            try {
                this.n = Integer.parseInt(sc.nextLine().trim());
                if (this.n >= 0) {
                    break;
                }
                System.out.println("So luong tac gia khong duoc am. Vui long nhap lai!");
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap mot so nguyen hop le!");
            }
        }

        this.dstacgia = new Author[n];
        for (int i = 0; i < n; i++) {
            System.out.println("\n--- Nhap tac gia thu " + (i + 1) + " ---");
            this.dstacgia[i] = new Author();
            this.dstacgia[i].Input();
        }
    }

    // -----------------------------------------------------------------------------------//
    // 5. Xuat danh sach
    public void Output() {
        if (this.n == 0) {
            System.out.println("Danh sach tac gia dang trong!");
            return;
        }
        this.PrintHeader();
        for (int i = 0; i < n; i++) {
            if (this.dstacgia[i] != null) {
                this.dstacgia[i].Output();
            }
        }
        System.out.format("+----------+------------+----------+--------------+----------+------------+%n");
    }

    // -----------------------------------------------------------------------------------//
    // 6. Tim kiem

    // 6.1 Tim theo ma tac gia
    public void TimAuthorTheoMa() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma tac gia can tim: ");
        String MaTGCanTim = sc.nextLine().trim();

        int index = TimAuthorTheoMa(MaTGCanTim);
        if (index != -1) {
            System.out.println("Thong tin tac gia co ma " + MaTGCanTim + ":");
            this.PrintHeader();
            this.dstacgia[index].Output();
            System.out.format("+----------+------------+----------+--------------+----------+------------+%n");
        } else {
            System.out.println("Khong tim thay tac gia co ma " + MaTGCanTim);
        }
    }

    public int TimAuthorTheoMa(String MaTGCanTim) {
        for (int i = 0; i < n; i++) {
            if (this.dstacgia[i] != null && this.dstacgia[i].getMaTG().equalsIgnoreCase(MaTGCanTim)) {
                return i;
            }
        }
        return -1;
    }

    // 6.2 Tim theo ho tac gia
    public void TimAuthorTheoHo() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ho tac gia can tim: ");
        String HoCanTim = sc.nextLine().trim();

        int count = 0;
        this.PrintHeader();
        for (int i = 0; i < n; i++) {
            if (this.dstacgia[i] != null && this.dstacgia[i].getHoTG().equalsIgnoreCase(HoCanTim)) {
                this.dstacgia[i].Output();
                count++;
            }
        }
        System.out.format("+----------+------------+----------+--------------+----------+------------+%n");

        if (count == 0) {
            System.out.println("Khong tim thay tac gia co ho " + HoCanTim);
        }
    }

    // 6.3 Tim theo ten tac gia
    public void TimAuthorTheoTen() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ten tac gia can tim: ");
        String TenCanTim = sc.nextLine().trim();

        int count = 0;
        this.PrintHeader();
        for (int i = 0; i < n; i++) {
            if (this.dstacgia[i] != null && this.dstacgia[i].getTenTG().equalsIgnoreCase(TenCanTim)) {
                this.dstacgia[i].Output();
                count++;
            }
        }
        System.out.format("+----------+------------+----------+--------------+----------+------------+%n");

        if (count == 0) {
            System.out.println("Khong tim thay tac gia co ten " + TenCanTim);
        }
    }

    // 6.4 Tim theo SDT
    public void TimAuthorTheoSDT() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap SDT tac gia can tim: ");
        String SDTCanTim = sc.nextLine().trim();

        int index = TimAuthorTheoSDT(SDTCanTim);
        if (index != -1) {
            System.out.println("Thong tin tac gia co SDT " + SDTCanTim + ":");
            this.PrintHeader();
            this.dstacgia[index].Output();
            System.out.format("+----------+------------+----------+--------------+----------+------------+%n");
        } else {
            System.out.println("Khong tim thay tac gia co SDT " + SDTCanTim);
        }
    }

    public int TimAuthorTheoSDT(String SDTCanTim) {
        for (int i = 0; i < n; i++) {
            if (this.dstacgia[i] != null && this.dstacgia[i].getSDT().equals(SDTCanTim)) {
                return i;
            }
        }
        return -1;
    }

    // -----------------------------------------------------------------------------------//
    // 7. Them

    public void ThemAuthor() {
        dstacgia = Arrays.copyOf(dstacgia, n + 1);
        dstacgia[n] = new Author();
        System.out.println("Nhap thong tin tac gia moi:");
        dstacgia[n].Input();
        n++;
    }

    public void ThemAuthor(Author author) {
        if (author != null) {
            dstacgia = Arrays.copyOf(dstacgia, n + 1);
            dstacgia[n] = new Author(author);
            n++;
        }
    }

    // -----------------------------------------------------------------------------------//
    // 8. Xoa

    public void XoaAuthor(int pos) {
        if (pos >= n || pos < 0) {
            System.out.println("Vi tri xoa khong hop le.");
            return;
        }

        for (int i = pos; i < n - 1; i++) {
            dstacgia[i] = dstacgia[i + 1];
        }
        dstacgia[n - 1] = null;
        n--;
        dstacgia = Arrays.copyOf(dstacgia, n);
    }

    // 8.1 Xoa theo ma tac gia
    public void XoaAuthorTheoMa() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma tac gia can xoa: ");
        String MaTGCanXoa = sc.nextLine().trim();

        int index = TimAuthorTheoMa(MaTGCanXoa);
        if (index != -1) {
            XoaAuthor(index);
            System.out.println("Da xoa thanh cong tac gia co ma " + MaTGCanXoa);
        } else {
            System.out.println("Khong tim thay tac gia co ma " + MaTGCanXoa);
        }
    }

    // 8.2 Xoa theo ho tac gia
    public void XoaAuthorTheoHo() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ho tac gia can xoa: ");
        String HoCanXoa = sc.nextLine().trim();

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (this.dstacgia[i] != null && this.dstacgia[i].getHoTG().equalsIgnoreCase(HoCanXoa)) {
                XoaAuthor(i);
                i--;
                count++;
            }
        }

        if (count == 0) {
            System.out.println("Khong tim thay tac gia co ho " + HoCanXoa);
        } else {
            System.out.println("Da xoa " + count + " tac gia co ho " + HoCanXoa);
        }
    }

    // 8.3 Xoa theo ten tac gia
    public void XoaAuthorTheoTen() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ten tac gia can xoa: ");
        String TenCanXoa = sc.nextLine().trim();

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (this.dstacgia[i] != null && this.dstacgia[i].getTenTG().equalsIgnoreCase(TenCanXoa)) {
                XoaAuthor(i);
                i--;
                count++;
            }
        }

        if (count == 0) {
            System.out.println("Khong tim thay tac gia co ten " + TenCanXoa);
        } else {
            System.out.println("Da xoa " + count + " tac gia co ten " + TenCanXoa);
        }
    }

    // 8.4 Xoa theo gioi tinh
    public void XoaAuthorTheoGioiTinh() {
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
            if (this.dstacgia[i] != null && (this.dstacgia[i].getGioiTinh().equalsIgnoreCase(GioiTinhCanXoa)
                    || (GioiTinhCanXoa.equalsIgnoreCase("Nu")
                            && this.dstacgia[i].getGioiTinh().equalsIgnoreCase("Nữ")))) {
                XoaAuthor(i);
                i--;
                count++;
            }
        }

        if (count == 0) {
            System.out.println("Khong tim thay tac gia co gioi tinh " + GioiTinhCanXoa);
        } else {
            System.out.println("Da xoa " + count + " tac gia co gioi tinh " + GioiTinhCanXoa);
        }
    }

    // 8.5 Xoa theo ngay sinh
    public void XoaAuthorTheoNgaySinh() {
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate NgaySinhCanXoa;

        while (true) {
            System.out.print("Nhap ngay sinh tac gia can xoa (dd/MM/yyyy): ");
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
            if (this.dstacgia[i] != null && this.dstacgia[i].getNgaySinh() != null
                    && this.dstacgia[i].getNgaySinh().equals(NgaySinhCanXoa)) {
                XoaAuthor(i);
                i--;
                count++;
            }
        }

        if (count == 0) {
            System.out.println("Khong tim thay tac gia co ngay sinh nay.");
        } else {
            System.out.println("Da xoa " + count + " tac gia thoa dieu kien.");
        }
    }

    // 8.6 Xoa theo SDT
    public void XoaAuthorTheoSDT() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap so dien thoai tac gia can xoa: ");
        String SDTCanXoa = sc.nextLine().trim();

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (this.dstacgia[i] != null && this.dstacgia[i].getSDT().equals(SDTCanXoa)) {
                XoaAuthor(i);
                i--;
                count++;
            }
        }

        if (count == 0) {
            System.out.println("Khong tim thay tac gia co so dien thoai " + SDTCanXoa);
        } else {
            System.out.println("Da xoa " + count + " tac gia co so dien thoai " + SDTCanXoa);
        }
    }

    // -----------------------------------------------------------------------------------//
    // 9. Sua
    public void SuaAuthor() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma tac gia can sua: ");
        String MaTGCanSua = sc.nextLine().trim();

        int index = TimAuthorTheoMa(MaTGCanSua);
        if (index == -1) {
            System.out.println("Khong tim thay tac gia co ma " + MaTGCanSua);
            return;
        }

        while (true) {
            System.out.println();
            System.out.println("+-------------------------------------------+");
            System.out.println("|         MENU SUA THONG TIN TAC GIA        |");
            System.out.println("+-------------------------------------------+");
            System.out.println("| 1) Sua ho tac gia.                        |");
            System.out.println("| 2) Sua ten tac gia.                       |");
            System.out.println("| 3) Sua ngay sinh tac gia.                 |");
            System.out.println("| 4) Sua gioi tinh tac gia.                 |");
            System.out.println("| 5) Sua so dien thoai tac gia.             |");
            System.out.println("| 6) Thoat.                                 |");
            System.out.println("+-------------------------------------------+");
            System.out.print("Nhap lua chon tu 1 - 6: ");

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
                    this.dstacgia[index].setHoTG(sc.nextLine().trim());
                    System.out.println("Cap nhat ho thanh cong!");
                    break;
                case 2:
                    System.out.print("Nhap ten moi: ");
                    this.dstacgia[index].setTenTG(sc.nextLine().trim());
                    System.out.println("Cap nhat ten thanh cong!");
                    break;
                case 3:
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
                            this.dstacgia[index].setNgaySinh(ns);
                            System.out.println("Cap nhat ngay sinh thanh cong!");
                            break;
                        } catch (DateTimeParseException e) {
                            System.out.println("Ngay sinh khong dung dinh dang. Vui long nhap lai!");
                        }
                    }
                    break;
                case 4:
                    while (true) {
                        System.out.print("Nhap gioi tinh moi (Nam/Nu): ");
                        String gt = sc.nextLine().trim();
                        if (gt.equalsIgnoreCase("Nam")) {
                            this.dstacgia[index].setGioiTinh("Nam");
                            System.out.println("Cap nhat gioi tinh thanh cong!");
                            break;
                        } else if (gt.equalsIgnoreCase("Nu")) {
                            this.dstacgia[index].setGioiTinh("Nu");
                            System.out.println("Cap nhat gioi tinh thanh cong!");
                            break;
                        }
                        System.out.println("Gioi tinh khong hop le. Vui long nhap lai (Nam/Nu).");
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
                            char c = sdt.charAt(i);
                            if (c < '0' || c > '9') {
                                isDigit = false;
                                break;
                            }
                        }
                        if (!isDigit) {
                            System.out.println("SDT chi chua ky tu so. Vui long nhap lai!");
                            continue;
                        }
                        this.dstacgia[index].setSDT(sdt);
                        System.out.println("Cap nhat SDT thanh cong!");
                        break;
                    }
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Lua chon khong hop le. Vui long chon lai tu 1 den 6!");
            }
        }
    }

    // -----------------------------------------------------------------------------------//
    // 10. Thong ke

    // 10.1 Thong ke theo ho tac gia
    public void ThongKeAuthorTheoHo() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ho tac gia can thong ke: ");
        String HoCanThongKe = sc.nextLine().trim();

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (this.dstacgia[i] != null && this.dstacgia[i].getHoTG().equalsIgnoreCase(HoCanThongKe)) {
                count++;
            }
        }
        System.out.println("So luong tac gia co ho " + HoCanThongKe + ": " + count);
    }

    // 10.2 Thong ke theo ten tac gia
    public void ThongKeAuthorTheoTen() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ten tac gia can thong ke: ");
        String TenCanThongKe = sc.nextLine().trim();

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (this.dstacgia[i] != null && this.dstacgia[i].getTenTG().equalsIgnoreCase(TenCanThongKe)) {
                count++;
            }
        }
        System.out.println("So luong tac gia co ten " + TenCanThongKe + ": " + count);
    }

    // 10.3 Thong ke theo gioi tinh
    public void ThongKeAuthorTheoGioiTinh() {
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
            if (this.dstacgia[i] != null && (this.dstacgia[i].getGioiTinh().equalsIgnoreCase(GioiTinhCanThongKe)
                    || (GioiTinhCanThongKe.equalsIgnoreCase("Nu")
                            && this.dstacgia[i].getGioiTinh().equalsIgnoreCase("Nữ")))) {
                count++;
            }
        }
        System.out.println("So luong tac gia co gioi tinh " + GioiTinhCanThongKe + ": " + count);
    }

    // 10.4 Thong ke theo tuoi
    public void ThongKeAuthorTheoTuoi() {
        Scanner sc = new Scanner(System.in);
        int tuoi;

        while (true) {
            System.out.print("Nhap so tuoi can thong ke: ");
            try {
                tuoi = Integer.parseInt(sc.nextLine().trim());
                if (tuoi <= 0) {
                    System.out.println("Tuoi khong duoc be hon hoac bang 0. Vui long nhap lai!");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Tuoi phai la mot so nguyen hop le!");
            }
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (this.dstacgia[i] != null && this.dstacgia[i].getAge() == tuoi) {
                count++;
            }
        }
        System.out.println("So luong tac gia " + tuoi + " tuoi: " + count);
    }

    // -----------------------------------------------------------------------------------//
    // 11. Doc va Ghi file

    // 11.1 Doc file Author/Author.txt
    public void ReadFile() {
        int count = 0;
        Scanner sc = null;
        FileInputStream Instream = null;

        try {
            Instream = new FileInputStream("data/Author.txt");
            sc = new Scanner(Instream);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            this.dstacgia = new Author[0];

            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }

                String[] data = line.split(",");
                if (data.length >= 6) {
                    this.dstacgia = Arrays.copyOf(this.dstacgia, count + 1);
                    this.dstacgia[count] = new Author();

                    this.dstacgia[count].setMaTG(data[0].trim());
                    this.dstacgia[count].setHoTG(data[1].trim());
                    this.dstacgia[count].setTenTG(data[2].trim());

                    String strNgaySinh = data[3].trim();
                    if (!strNgaySinh.isEmpty()) {
                        try {
                            this.dstacgia[count].setNgaySinh(LocalDate.parse(strNgaySinh, formatter));
                        } catch (DateTimeParseException e) {
                            this.dstacgia[count].setNgaySinh(null);
                        }
                    }

                    this.dstacgia[count].setGioiTinh(data[4].trim());
                    this.dstacgia[count].setSDT(data[5].trim());

                    count++;
                }
            }

            this.n = count;
            System.out.println("Lay du lieu thanh cong! Tong cong: " + this.n + " tac gia.");

        } catch (FileNotFoundException e) {
            System.out.println("Loi: Khong tim thay file Author/Author.txt!");
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
    }

    // 11.2 Ghi file Author/Author.txt
    public void WriteFile() {
        if (this.n == 0) {
            System.out.println("Danh sach tac gia dang trong, khong co du lieu de ghi!");
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        FileOutputStream Outstream = null;
        PrintWriter writer = null;

        try {
            Outstream = new FileOutputStream("data/Author.txt");
            writer = new PrintWriter(Outstream);

            for (int i = 0; i < this.n; i++) {
                if (this.dstacgia[i] != null) {
                    String strNgaySinh = (this.dstacgia[i].getNgaySinh() != null)
                            ? this.dstacgia[i].getNgaySinh().format(formatter)
                            : "";

                    String line = this.dstacgia[i].getMaTG() + ","
                            + this.dstacgia[i].getHoTG() + ","
                            + this.dstacgia[i].getTenTG() + ","
                            + strNgaySinh + ","
                            + this.dstacgia[i].getGioiTinh() + ","
                            + this.dstacgia[i].getSDT();

                    writer.println(line);
                }
            }
            System.out.println("Ghi danh sach vao file Author/Author.txt thanh cong!");
        } catch (FileNotFoundException e) {
            System.out.println("Loi: Khong tim thay duong dan file de ghi!");
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}