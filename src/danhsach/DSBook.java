package danhsach;

import model.Book;
import model.EducationBook;
import model.ReferenceBook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class DSBook implements DanhSach {
    private Book[] ds;
    private int n;

    public DSBook() {
        this.ds = new Book[0];
        this.n = 0;
    }

    public DSBook(Book[] ds, int n) {
        this.ds = ds;
        this.n = n;
        Book.setTongSoDauSach(n);
    }

    public DSBook(DSBook copy) {
        if (copy != null) {
            this.n = copy.n;
            this.ds = new Book[copy.n];
            for (int i = 0; i < n; i++) {
                if (copy.ds[i] != null) {
                    if (copy.ds[i] instanceof EducationBook) {
                        this.ds[i] = new EducationBook((EducationBook) copy.ds[i]);
                    } else if (copy.ds[i] instanceof ReferenceBook) {
                        this.ds[i] = new ReferenceBook((ReferenceBook) copy.ds[i]);
                    }
                }
            }
        }
    }

    public int getN() {
        return n;
    }

    public Book[] getDs() {
        return ds;
    }

    public Book getBook(int index) {
        if (index >= 0 && index < n) {
            return ds[index];
        }
        return null;
    }

    // -----------------------------------------------------------------------------------//
    // Ho tro tra cuu ten The Loai, Tac Gia, NXB de hien thi len bang
    private String layTenTheLoai(String maTL, DSTheLoai dstl) {
        if (dstl != null) {
            return dstl.timTenTheLoai(maTL);
        }
        return maTL;
    }

    private String layTenTacGia(String maTG) {
        Scanner scFile = null;
        try {
            scFile = new Scanner(new FileInputStream("data/Author.txt"));
            while (scFile.hasNextLine()) {
                String line = scFile.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }
                String[] data = line.split(",");
                if (data.length >= 3 && data[0].trim().equalsIgnoreCase(maTG)) {
                    return data[1].trim() + " " + data[2].trim();
                }
            }
        } catch (FileNotFoundException e) {
            // Giu nguyen ma neu khong doc duoc file
        } finally {
            if (scFile != null) {
                scFile.close();
            }
        }
        return maTG;
    }

    private String layTenNXB(String maNXB) {
        Scanner scFile = null;
        try {
            scFile = new Scanner(new FileInputStream("data/Publisher.txt"));
            while (scFile.hasNextLine()) {
                String line = scFile.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }
                String[] data = line.split(",");
                if (data.length >= 2 && data[0].trim().equalsIgnoreCase(maNXB)) {
                    return data[1].trim();
                }
            }
        } catch (FileNotFoundException e) {
            // Giu nguyen ma neu khong doc duoc file
        } finally {
            if (scFile != null) {
                scFile.close();
            }
        }
        return maNXB;
    }

    private DSTheLoai taoDSTheLoaiDaDoc() {
        DSTheLoai dstl = new DSTheLoai();
        dstl.ReadFile();
        return dstl;
    }

    // -----------------------------------------------------------------------------------//
    // In khung tieu de rong 163 ky tu khop voi terminal
    public void PrintHeader() {
        System.out.println(
                "+-------+--------------------------+----------------------+--------------------+--------------+------------+-----------+------+------+-----------+---------------------------+");
        System.out.printf("| %-5s | %-24s | %-20s | %-18s | %-12s | %-10s | %-9s | %-4s | %-4s | %-9s | %-25s |\n",
                "Ma", "Ten Sach", "The Loai", "Tac Gia", "NXB", "Ngay XB", "Don Gia", "SL", "Con", "TrangThai",
                "Thong Tin Rieng");
        System.out.println(
                "+-------+--------------------------+----------------------+--------------------+--------------+------------+-----------+------+------+-----------+---------------------------+");
    }

    private void PrintFooter() {
        System.out.println(
                "+-------+--------------------------+----------------------+--------------------+--------------+------------+-----------+------+------+-----------+---------------------------+");
    }

    private void inDongSach(Book b, DSTheLoai dstl) {
        if (b != null) {
            String tenTL = layTenTheLoai(b.getMaTheLoai(), dstl);
            String tenTG = layTenTacGia(b.getMaTG());
            String tenNXB = layTenNXB(b.getMaNXB());
            b.Output(tenTL, tenTG, tenNXB);
        }
    }

    // -----------------------------------------------------------------------------------//
    // Nhap danh sach
    public void Input() {
        Scanner sc = new Scanner(System.in);
        int sl;
        while (true) {
            System.out.print("Nhap so luong sach khac nhau: ");
            try {
                sl = Integer.parseInt(sc.nextLine().trim());
                if (sl >= 0) {
                    break;
                }
                System.out.println("So luong sach khong duoc am!");
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap mot so nguyen hop le!");
            }
        }

        this.ds = new Book[0];
        this.n = 0;
        for (int i = 0; i < sl; i++) {
            System.out.println("\n--- Nhap thong tin sach thu " + (i + 1) + " ---");
            them();
        }
    }

    @Override
    public void Output() {
        if (this.n == 0) {
            System.out.println("Danh sach sach dang trong!");
            return;
        }
        DSTheLoai dstl = taoDSTheLoaiDaDoc();
        System.out.println(
                "\n                                                                    DANH SACH QUAN LY SACH                                                                    ");
        PrintHeader();
        for (int i = 0; i < n; i++) {
            inDongSach(ds[i], dstl);
        }
        PrintFooter();
    }

    // -----------------------------------------------------------------------------------//
    // Check ma sach, ma TL, ma TG, ma NXB bang cac class DS tuong ung
    public boolean CheckMaSach(String ma) {
        for (int i = 0; i < this.n; i++) {
            if (ds[i] != null && ds[i].getMaSach().equalsIgnoreCase(ma)) {
                return true;
            }
        }
        return false;
    }

    // Kiem tra dinh dang ma sach: bat dau bang 'S' + cac ky tu so (VD: S01)
    public boolean CheckHopLe(String ma) {
        if (ma == null || ma.length() < 2 || !ma.substring(0, 1).equals("S")) {
            return false;
        }
        for (int i = 1; i < ma.length(); i++) {
            char c = ma.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    private String nhapMaSachHopLe(Scanner sc) {
        System.out.print("Nhap ma sach (VD: S01): ");
        String ma = sc.nextLine().trim();
        while (CheckMaSach(ma) || !CheckHopLe(ma)) {
            if (CheckMaSach(ma)) {
                System.out.print("Ma sach bi trung. Vui long nhap lai: ");
            } else {
                System.out.print("Ma sach khong hop le (phai co dang Sxx, ky tu 'S' + so). Vui long nhap lai: ");
            }
            ma = sc.nextLine().trim();
        }
        return ma;
    }

    private String nhapMaTheLoaiHopLe(Scanner sc, DSTheLoai dstl) {
        if (dstl.getN() > 0) {
            dstl.hienThiDanhSachChon();
        }
        System.out.print("Nhap ma the loai (VD: TL01): ");
        String maTL = sc.nextLine().trim();
        while (!dstl.CheckHopLe(maTL) || !dstl.CheckMaTL(maTL)) {
            if (!dstl.CheckHopLe(maTL)) {
                System.out.print("Ma the loai khong hop le (phai co dang TLxx). Vui long nhap lai: ");
            } else {
                System.out.print("Ma the loai khong ton tai trong danh sach. Vui long nhap lai: ");
            }
            maTL = sc.nextLine().trim();
        }
        return maTL;
    }

    private String nhapMaTacGiaHopLe(Scanner sc, DSAuthor dsAuthor) {
        System.out.print("Nhap ma tac gia (VD: TG01): ");
        String maTG = sc.nextLine().trim();
        while (!dsAuthor.CheckHopLe(maTG) || !dsAuthor.CheckMaTG(maTG)) {
            if (!dsAuthor.CheckHopLe(maTG)) {
                System.out.print("Ma tac gia khong hop le (phai co dang TGxx). Vui long nhap lai: ");
            } else {
                System.out.print("Ma tac gia khong ton tai trong danh sach. Vui long nhap lai: ");
            }
            maTG = sc.nextLine().trim();
        }
        return maTG;
    }

    private String nhapMaNXBHopLe(Scanner sc, DSPublisher dsPublisher) {
        System.out.print("Nhap ma NXB (VD: NXB01): ");
        String maNXB = sc.nextLine().trim();
        while (!dsPublisher.CheckHopLe(maNXB) || !dsPublisher.CheckMaNXB(maNXB)) {
            if (!dsPublisher.CheckHopLe(maNXB)) {
                System.out.print("Ma NXB khong hop le (phai co dang NXBxx). Vui long nhap lai: ");
            } else {
                System.out.print("Ma NXB khong ton tai trong danh sach. Vui long nhap lai: ");
            }
            maNXB = sc.nextLine().trim();
        }
        return maNXB;
    }

    private int chonLoaiSach(Scanner sc) {
        while (true) {
            System.out.print("Chon loai sach (1 - Sach giao khoa | 2 - Sach tham khao): ");
            try {
                int loai = Integer.parseInt(sc.nextLine().trim());
                if (loai == 1 || loai == 2) {
                    return loai;
                }
                System.out.println("Vui long chi chon 1 hoac 2!");
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap so nguyen 1 hoac 2!");
            }
        }
    }

    // -----------------------------------------------------------------------------------//
    // Them sach
    public void them() {
        DSTheLoai dstl = taoDSTheLoaiDaDoc();
        them(dstl);
    }

    public void them(DSTheLoai dstl) {
        if (dstl == null) {
            dstl = taoDSTheLoaiDaDoc();
        } else {
            dstl.ReadFile();
        }
        DSAuthor dsAuthor = new DSAuthor();
        dsAuthor.ReadFile();
        DSPublisher dsPublisher = new DSPublisher();
        dsPublisher.ReadFile();

        Scanner sc = new Scanner(System.in);
        int loai = chonLoaiSach(sc);
        Book sachMoi;
        if (loai == 1) {
            sachMoi = new EducationBook();
        } else {
            sachMoi = new ReferenceBook();
        }

        sachMoi.setMaSach(nhapMaSachHopLe(sc));
        sachMoi.setMaTheLoai(nhapMaTheLoaiHopLe(sc, dstl));
        sachMoi.setMaTG(nhapMaTacGiaHopLe(sc, dsAuthor));
        sachMoi.setMaNXB(nhapMaNXBHopLe(sc, dsPublisher));
        sachMoi.InputConLai();

        ds = Arrays.copyOf(ds, n + 1);
        ds[n] = sachMoi;
        n++;
        Book.setTongSoDauSach(n);
        System.out.println("=> Them sach thanh cong!");
    }

    public void them(Book x) {
        if (x != null && !CheckMaSach(x.getMaSach())) {
            ds = Arrays.copyOf(ds, n + 1);
            ds[n] = x;
            n++;
            Book.setTongSoDauSach(n);
            System.out.println("=> Them sach thanh cong!");
        } else {
            System.out.println("=> Sach null hoac ma sach da ton tai!");
        }
    }

    // -----------------------------------------------------------------------------------//
    // Tim kiem
    public int timkiem(String ma) {
        for (int i = 0; i < n; i++) {
            if (ds[i] != null && ds[i].getMaSach().equalsIgnoreCase(ma)) {
                return i;
            }
        }
        return -1;
    }

    public void timkiem() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma sach can tim: ");
        String ma = sc.nextLine().trim();
        int vt = timkiem(ma);
        if (vt != -1) {
            DSTheLoai dstl = taoDSTheLoaiDaDoc();
            System.out.println("\n===== KET QUA TIM KIEM =====");
            PrintHeader();
            inDongSach(ds[vt], dstl);
            PrintFooter();
        } else {
            System.out.println("Khong tim thay sach co ma: " + ma);
        }
    }

    public Book timkiemBook(String ma) {
        int vt = timkiem(ma);
        if (vt != -1) {
            return ds[vt];
        }
        return null;
    }

    public void timTheoTenSach() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ten sach can tim: ");
        String ten = sc.nextLine().trim();
        DSTheLoai dstl = taoDSTheLoaiDaDoc();
        boolean found = false;
        for (int i = 0; i < n; i++) {
            if (ds[i] != null && ds[i].getTenSach().toLowerCase().contains(ten.toLowerCase())) {
                if (!found) {
                    System.out.println("\n===== DANH SACH TIM THAY =====");
                    PrintHeader();
                    found = true;
                }
                inDongSach(ds[i], dstl);
            }
        }
        if (found) {
            PrintFooter();
        } else {
            System.out.println("Khong tim thay sach nao co ten chua: " + ten);
        }
    }

    // Tim theo the loai (hien thi danh sach the loai cho chon)
    public void timTheoTheLoai() {
        Scanner sc = new Scanner(System.in);
        DSTheLoai dstl = taoDSTheLoaiDaDoc();
        if (dstl.getN() > 0) {
            dstl.hienThiDanhSachChon();
        }
        System.out.print("Nhap ma the loai can tim (VD: TL01): ");
        String tl = sc.nextLine().trim();
        boolean found = false;
        for (int i = 0; i < n; i++) {
            if (ds[i] != null && ds[i].getMaTheLoai().equalsIgnoreCase(tl)) {
                if (!found) {
                    System.out.println("\n===== DANH SACH TIM THAY =====");
                    PrintHeader();
                    found = true;
                }
                inDongSach(ds[i], dstl);
            }
        }
        if (found) {
            PrintFooter();
        } else {
            System.out.println("Khong tim thay sach thuoc the loai: " + tl);
        }
    }

    // Tim theo loai sach 1 / 2 (1 - Sach giao khoa | 2 - Sach tham khao)
    public void timTheoLoaiSach() {
        Scanner sc = new Scanner(System.in);
        int loai = chonLoaiSach(sc);
        DSTheLoai dstl = taoDSTheLoaiDaDoc();
        boolean found = false;

        for (int i = 0; i < n; i++) {
            boolean khop = (loai == 1 && ds[i] instanceof EducationBook)
                    || (loai == 2 && ds[i] instanceof ReferenceBook);
            if (khop) {
                if (!found) {
                    System.out.println("\n===== DANH SACH TIM THAY =====");
                    PrintHeader();
                    found = true;
                }
                inDongSach(ds[i], dstl);
            }
        }
        if (found) {
            PrintFooter();
        } else {
            System.out.println("Khong tim thay sach thuoc loai da chon!");
        }
    }

    public void timTheoTacGia() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma tac gia can tim (VD: TG01): ");
        String maTG = sc.nextLine().trim();
        DSTheLoai dstl = taoDSTheLoaiDaDoc();
        boolean found = false;
        for (int i = 0; i < n; i++) {
            if (ds[i] != null && ds[i].getMaTG().equalsIgnoreCase(maTG)) {
                if (!found) {
                    System.out.println("\n===== DANH SACH TIM THAY =====");
                    PrintHeader();
                    found = true;
                }
                inDongSach(ds[i], dstl);
            }
        }
        if (found) {
            PrintFooter();
        } else {
            System.out.println("Khong tim thay sach cua tac gia: " + maTG);
        }
    }

    public void timTheoKhoangGia() {
        Scanner sc = new Scanner(System.in);
        double min = 0, max = 0;
        while (true) {
            try {
                System.out.print("Nhap gia thap nhat: ");
                min = Double.parseDouble(sc.nextLine().trim());
                System.out.print("Nhap gia cao nhat: ");
                max = Double.parseDouble(sc.nextLine().trim());
                if (min >= 0 && max >= min) {
                    break;
                }
                System.out.println("Khoang gia khong hop le (0 <= min <= max)!");
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap so hop le!");
            }
        }
        DSTheLoai dstl = taoDSTheLoaiDaDoc();
        boolean found = false;
        for (int i = 0; i < n; i++) {
            if (ds[i] != null && ds[i].getDonGia() >= min && ds[i].getDonGia() <= max) {
                if (!found) {
                    System.out.println("\n===== DANH SACH TIM THAY =====");
                    PrintHeader();
                    found = true;
                }
                inDongSach(ds[i], dstl);
            }
        }
        if (found) {
            PrintFooter();
        } else {
            System.out.println("Khong tim thay sach trong khoang gia nay!");
        }
    }

    // -----------------------------------------------------------------------------------//
    // Xoa sach
    public void xoa(String ma) {
        int vt = timkiem(ma);
        if (vt == -1) {
            System.out.println("Khong tim thay sach de xoa!");
            return;
        }
        for (int i = vt; i < n - 1; i++) {
            ds[i] = ds[i + 1];
        }
        n--;
        ds = Arrays.copyOf(ds, n);
        Book.setTongSoDauSach(n);
        System.out.println("=> Xoa sach thanh cong!");
    }

    public void xoa() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma sach can xoa: ");
        String ma = sc.nextLine().trim();
        xoa(ma);
    }

    public void xoaTheoTheLoai() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma the loai can xoa sach: ");
        String tl = sc.nextLine().trim();
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (ds[i] != null && ds[i].getMaTheLoai().equalsIgnoreCase(tl)) {
                for (int j = i; j < n - 1; j++) {
                    ds[j] = ds[j + 1];
                }
                n--;
                ds = Arrays.copyOf(ds, n);
                i--;
                count++;
            }
        }
        Book.setTongSoDauSach(n);
        if (count > 0) {
            System.out.println("=> Da xoa " + count + " sach thuoc the loai " + tl);
        } else {
            System.out.println("=> Khong tim thay sach nao thuoc the loai " + tl);
        }
    }

    // -----------------------------------------------------------------------------------//
    // Sua sach (co check hop le TL, TG, NXB)
    public void sua() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma sach can sua: ");
        String ma = sc.nextLine().trim();
        sua(ma);
    }

    public void sua(String ma) {
        int vt = timkiem(ma);
        if (vt == -1) {
            System.out.println("Khong tim thay sach de sua!");
            return;
        }

        DSTheLoai dstl = taoDSTheLoaiDaDoc();
        DSAuthor dsAuthor = new DSAuthor();
        dsAuthor.ReadFile();
        DSPublisher dsPublisher = new DSPublisher();
        dsPublisher.ReadFile();

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println();
            System.out.println("+-------------------------------------------+");
            System.out.println("|          MENU SUA THONG TIN SACH          |");
            System.out.println("+-------------------------------------------+");
            System.out.println("| 1) Sua ten sach.                          |");
            System.out.println("| 2) Sua ma the loai.                       |");
            System.out.println("| 3) Sua ma tac gia.                        |");
            System.out.println("| 4) Sua ma NXB.                            |");
            System.out.println("| 5) Sua ngay xuat ban.                     |");
            System.out.println("| 6) Sua don gia.                           |");
            System.out.println("| 7) Sua so luong & so luong con lai.       |");
            System.out.println("| 8) Sua thong tin rieng (Mon/Lop/LinhVuc). |");
            System.out.println("| 0) Hoan tat & quay lai.                   |");
            System.out.println("+-------------------------------------------+");
            System.out.print("Nhap lua chon tu 0 - 8: ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Lua chon phai la so nguyen!");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Nhap ten sach moi: ");
                    ds[vt].setTenSach(sc.nextLine().trim());
                    System.out.println("Cap nhat ten sach thanh cong!");
                    break;
                case 2:
                    ds[vt].setMaTheLoai(nhapMaTheLoaiHopLe(sc, dstl));
                    System.out.println("Cap nhat ma the loai thanh cong!");
                    break;
                case 3:
                    ds[vt].setMaTG(nhapMaTacGiaHopLe(sc, dsAuthor));
                    System.out.println("Cap nhat ma tac gia thanh cong!");
                    break;
                case 4:
                    ds[vt].setMaNXB(nhapMaNXBHopLe(sc, dsPublisher));
                    System.out.println("Cap nhat ma NXB thanh cong!");
                    break;
                case 5:
                    while (true) {
                        System.out.print("Nhap ngay xuat ban moi (dd/MM/yyyy): ");
                        String ngay = sc.nextLine().trim();
                        if (Book.kiemTraNgayHopLe(ngay)) {
                            ds[vt].setNgayXB(ngay);
                            System.out.println("Cap nhat ngay xuat ban thanh cong!");
                            break;
                        }
                        System.out.println("Ngay khong dung dinh dang dd/MM/yyyy!");
                    }
                    break;
                case 6:
                    while (true) {
                        System.out.print("Nhap don gia moi: ");
                        try {
                            double gia = Double.parseDouble(sc.nextLine().trim());
                            if (gia > 0) {
                                ds[vt].setDonGia(gia);
                                System.out.println("Cap nhat don gia thanh cong!");
                                break;
                            }
                            System.out.println("Don gia phai lon hon 0!");
                        } catch (NumberFormatException e) {
                            System.out.println("Don gia khong hop le!");
                        }
                    }
                    break;
                case 7:
                    while (true) {
                        try {
                            System.out.print("Nhap so luong tong moi: ");
                            int slMoi = Integer.parseInt(sc.nextLine().trim());
                            System.out.print("Nhap so luong con lai moi: ");
                            int conMoi = Integer.parseInt(sc.nextLine().trim());
                            if (slMoi >= 0 && conMoi >= 0 && conMoi <= slMoi) {
                                ds[vt].setSoLuong(slMoi);
                                ds[vt].setSoLuongConLai(conMoi);
                                System.out.println("Cap nhat so luong thanh cong!");
                                break;
                            }
                            System.out.println("So luong phai >= 0 va con lai <= tong so luong!");
                        } catch (NumberFormatException e) {
                            System.out.println("Vui long nhap so nguyen hop le!");
                        }
                    }
                    break;
                case 8:
                    if (ds[vt] instanceof EducationBook) {
                        System.out.print("Nhap ten mon moi: ");
                        ((EducationBook) ds[vt]).setTenMon(sc.nextLine().trim());
                        System.out.print("Nhap lop moi: ");
                        ((EducationBook) ds[vt]).setLop(sc.nextLine().trim());
                    } else if (ds[vt] instanceof ReferenceBook) {
                        System.out.print("Nhap linh vuc moi: ");
                        ((ReferenceBook) ds[vt]).setLinhvuc(sc.nextLine().trim());
                    }
                    System.out.println("Cap nhat thong tin rieng thanh cong!");
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        }
    }

    // -----------------------------------------------------------------------------------//
    // Thong ke
    public void thongKeLoaiSach() {
        int sgk = 0, stk = 0;
        for (int i = 0; i < n; i++) {
            if (ds[i] instanceof EducationBook) {
                sgk++;
            } else if (ds[i] instanceof ReferenceBook) {
                stk++;
            }
        }
        System.out.println("\n===== THONG KE THEO LOAI SACH =====");
        System.out.println("Tong so dau sach               : " + n);
        System.out.println("Sach giao khoa (EducationBook) : " + sgk + " dau sach");
        System.out.println("Sach tham khao (ReferenceBook) : " + stk + " dau sach");
    }

    public void thongKeTinhTrang() {
        int con = 0, het = 0;
        for (int i = 0; i < n; i++) {
            if (ds[i] != null && ds[i].getSoLuongConLai() > 0) {
                con++;
            } else {
                het++;
            }
        }
        System.out.println("\n===== THONG KE TINH TRANG KHO =====");
        System.out.println("Sach con trong kho : " + con + " ma sach");
        System.out.println("Sach da het hang   : " + het + " ma sach (Can nhap them!)");
    }

    public void thongKeGiaTien() {
        int re = 0, trungBinh = 0, cao = 0;
        for (int i = 0; i < n; i++) {
            if (ds[i] != null) {
                double gia = ds[i].getDonGia();
                if (gia < 50000) {
                    re++;
                } else if (gia <= 150000) {
                    trungBinh++;
                } else {
                    cao++;
                }
            }
        }
        System.out.println("\n===== THONG KE THEO MUC GIA =====");
        System.out.println("Sach duoi 50.000 VND         : " + re + " dau sach");
        System.out.println("Sach tu 50.000 - 150.000 VND : " + trungBinh + " dau sach");
        System.out.println("Sach tren 150.000 VND        : " + cao + " dau sach");
    }

    // -----------------------------------------------------------------------------------//
    // Doc va ghi file
    @Override
    public void ReadFile() {
        int count = 0;
        Scanner sc = null;
        FileInputStream inStream = null;
        try {
            inStream = new FileInputStream("data/Book.txt");
            sc = new Scanner(inStream);
            this.ds = new Book[0];

            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }
                String[] data = line.split(",");
                if (data.length >= 12) {
                    String loai = data[0].trim();
                    String maSach = data[1].trim();
                    String tenSach = data[2].trim();
                    String maTL = data[3].trim();
                    String maTG = data[4].trim();
                    String ngayXB = data[5].trim();
                    String trangThai = data[6].trim();
                    String maNXB = data[7].trim();
                    double donGia = Double.parseDouble(data[8].trim());
                    int soLuong = Integer.parseInt(data[9].trim());
                    int soLuongConLai = Integer.parseInt(data[10].trim());

                    Book b = null;
                    if (loai.equalsIgnoreCase("EB") && data.length >= 13) {
                        String tenMon = data[11].trim();
                        String lop = data[12].trim();
                        b = new EducationBook(maSach, tenSach, maTL, maTG, ngayXB, trangThai, maNXB,
                                donGia, soLuong, soLuongConLai, tenMon, lop);
                    } else if (loai.equalsIgnoreCase("RB")) {
                        String linhVuc = data[11].trim();
                        b = new ReferenceBook(maSach, tenSach, maTL, maTG, ngayXB, trangThai, maNXB,
                                donGia, soLuong, soLuongConLai, linhVuc);
                    }

                    if (b != null) {
                        this.ds = Arrays.copyOf(this.ds, count + 1);
                        this.ds[count] = b;
                        count++;
                    }
                }
            }
            this.n = count;
            Book.setTongSoDauSach(this.n);
        } catch (FileNotFoundException e) {
            System.out.println("Loi: Khong tim thay file data/Book.txt!");
        } catch (NumberFormatException e) {
            System.out.println("Loi dinh dang du lieu so trong file data/Book.txt: " + e.getMessage());
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
    }

    @Override
    public void WriteFile() {
        FileOutputStream outStream = null;
        PrintWriter writer = null;
        try {
            outStream = new FileOutputStream("data/Book.txt");
            writer = new PrintWriter(outStream);
            for (int i = 0; i < this.n; i++) {
                if (this.ds[i] != null) {
                    writer.println(this.ds[i].toFileString());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Loi: Khong tim thay duong dan file data/Book.txt de ghi!");
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
