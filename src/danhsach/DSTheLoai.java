package danhsach;

import model.TheLoai;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class DSTheLoai implements DanhSach {
    // 1. Thuoc tinh
    private int n;
    private TheLoai[] dstheloai;

    // 2. Constructor
    public DSTheLoai() {
        this.n = 0;
        this.dstheloai = new TheLoai[0];
    }

    public DSTheLoai(int n, TheLoai[] dstheloai) {
        this.n = n;
        this.dstheloai = dstheloai;
        TheLoai.setTongSoTheLoai(n);
    }

    public DSTheLoai(DSTheLoai copy) {
        if (copy != null) {
            this.n = copy.n;
            this.dstheloai = new TheLoai[copy.n];
            for (int i = 0; i < n; i++) {
                if (copy.dstheloai[i] != null) {
                    this.dstheloai[i] = new TheLoai(copy.dstheloai[i]);
                }
            }
        }
    }

    public int getN() {
        return n;
    }

    public TheLoai getTheLoai(int index) {
        if (index >= 0 && index < n) {
            return dstheloai[index];
        }
        return null;
    }

    // 3. In tieu de bang
    public void PrintHeader() {
        System.out.format("+--------------+---------------------------+%n");
        System.out.printf("| %-12s | %-25s |\n", "Ma The Loai", "Ten The Loai");
        System.out.format("+--------------+---------------------------+%n");
    }

    // 4. Nhap danh sach
    public void Input() {
        Scanner sc = new Scanner(System.in);
        int sl;
        while (true) {
            System.out.print("Nhap so luong the loai: ");
            try {
                sl = Integer.parseInt(sc.nextLine().trim());
                if (sl >= 0) {
                    break;
                }
                System.out.println("So luong the loai khong duoc am. Vui long nhap lai!");
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap mot so nguyen hop le!");
            }
        }

        this.dstheloai = new TheLoai[0];
        this.n = 0;
        for (int i = 0; i < sl; i++) {
            System.out.println("\n--- Nhap the loai thu " + (i + 1) + " ---");
            ThemTheLoai();
        }
    }

    // 5. Xuat danh sach
    @Override
    public void Output() {
        if (this.n == 0) {
            System.out.println("Danh sach the loai dang trong!");
            return;
        }
        this.PrintHeader();
        for (int i = 0; i < n; i++) {
            if (this.dstheloai[i] != null) {
                this.dstheloai[i].Output();
            }
        }
        System.out.format("+--------------+---------------------------+%n");
    }

    public void xuatDS() {
        Output();
    }

    // 6. Tim kiem
    public int TimTheLoaiTheoMa(String MaTLCanTim) {
        for (int i = 0; i < n; i++) {
            if (this.dstheloai[i] != null && this.dstheloai[i].getMaTheLoai().equalsIgnoreCase(MaTLCanTim)) {
                return i;
            }
        }
        return -1;
    }

    public void TimTheLoaiTheoMa() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma the loai can tim: ");
        String MaTLCanTim = sc.nextLine().trim();

        int index = TimTheLoaiTheoMa(MaTLCanTim);
        if (index != -1) {
            System.out.println("Thong tin the loai co ma " + MaTLCanTim + ":");
            this.PrintHeader();
            this.dstheloai[index].Output();
            System.out.format("+--------------+---------------------------+%n");
        } else {
            System.out.println("Khong tim thay the loai co ma " + MaTLCanTim);
        }
    }

    public void TimTheLoaiTheoTen() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ten the loai can tim: ");
        String TenCanTim = sc.nextLine().trim();

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (this.dstheloai[i] != null
                    && this.dstheloai[i].getTenTheLoai().toLowerCase().contains(TenCanTim.toLowerCase())) {
                if (count == 0) {
                    this.PrintHeader();
                }
                this.dstheloai[i].Output();
                count++;
            }
        }
        if (count > 0) {
            System.out.format("+--------------+---------------------------+%n");
        } else {
            System.out.println("Khong tim thay the loai co ten " + TenCanTim);
        }
    }

    public String timTenTheLoai(String ma) {
        for (int i = 0; i < n; i++) {
            if (dstheloai[i] != null && dstheloai[i].getMaTheLoai().equalsIgnoreCase(ma)) {
                return dstheloai[i].getTenTheLoai();
            }
        }
        return "Khong ro";
    }

    public boolean kiemTraTonTai(String ma) {
        return TimTheLoaiTheoMa(ma) != -1;
    }

    public void hienThiDanhSachChon() {
        System.out.println("\n--- DANH SACH THE LOAI HIEN CO ---");
        for (int i = 0; i < n; i++) {
            if (dstheloai[i] != null) {
                System.out.println(dstheloai[i].getMaTheLoai() + " : " + dstheloai[i].getTenTheLoai());
            }
        }
    }

    // 7. Them the loai
    public void ThemTheLoai() {
        Scanner sc = new Scanner(System.in);
        dstheloai = Arrays.copyOf(dstheloai, n + 1);
        dstheloai[n] = new TheLoai();

        System.out.print("Nhap ma the loai (VD: TL01): ");
        String MaTL = sc.nextLine().trim();

        while (CheckMaTL(MaTL) || !CheckHopLe(MaTL)) {
            if (CheckMaTL(MaTL)) {
                System.out.print("Ma the loai bi trung. Vui long nhap lai: ");
            } else {
                System.out.print("Ma the loai khong hop le (phai co dang TLxx). Vui long nhap lai: ");
            }
            MaTL = sc.nextLine().trim();
        }

        dstheloai[n].setMaTheLoai(MaTL);
        dstheloai[n].Input();
        n++;
        TheLoai.setTongSoTheLoai(n);
        System.out.println("=> Them the loai thanh cong!");
    }

    public void ThemTheLoai(TheLoai tl) {
        if (tl != null && !CheckMaTL(tl.getMaTheLoai())) {
            dstheloai = Arrays.copyOf(dstheloai, n + 1);
            dstheloai[n] = new TheLoai(tl);
            n++;
            TheLoai.setTongSoTheLoai(n);
        }
    }

    public void them() {
        ThemTheLoai();
    }

    // 8. Xoa the loai
    public void XoaTheLoai(int pos) {
        if (pos >= n || pos < 0) {
            System.out.println("Vi tri xoa khong hop le.");
            return;
        }
        for (int i = pos; i < n - 1; i++) {
            dstheloai[i] = dstheloai[i + 1];
        }
        dstheloai[n - 1] = null;
        n--;
        dstheloai = Arrays.copyOf(dstheloai, n);
        TheLoai.setTongSoTheLoai(n);
    }

    public void XoaTheLoaiTheoMa() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma the loai can xoa: ");
        String MaTLCanXoa = sc.nextLine().trim();
        xoa(MaTLCanXoa);
    }

    public void xoa(String ma) {
        int index = TimTheLoaiTheoMa(ma);
        if (index != -1) {
            XoaTheLoai(index);
            System.out.println("Da xoa thanh cong the loai co ma " + ma);
        } else {
            System.out.println("Khong tim thay the loai co ma " + ma);
        }
    }

    public void XoaTheLoaiTheoTen() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ten the loai can xoa: ");
        String TenCanXoa = sc.nextLine().trim();

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (this.dstheloai[i] != null && this.dstheloai[i].getTenTheLoai().equalsIgnoreCase(TenCanXoa)) {
                XoaTheLoai(i);
                i--;
                count++;
            }
        }
        if (count == 0) {
            System.out.println("Khong tim thay the loai co ten " + TenCanXoa);
        } else {
            System.out.println("Da xoa " + count + " the loai co ten " + TenCanXoa);
        }
    }

    // 9. Sua the loai
    public void SuaTheLoai() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma the loai can sua: ");
        String MaTLCanSua = sc.nextLine().trim();

        int index = TimTheLoaiTheoMa(MaTLCanSua);
        if (index == -1) {
            System.out.println("Khong tim thay the loai co ma " + MaTLCanSua);
            return;
        }

        System.out.println("Ten the loai hien tai: " + this.dstheloai[index].getTenTheLoai());
        System.out.print("Nhap ten the loai moi: ");
        String tenMoi = sc.nextLine().trim();
        if (!tenMoi.isEmpty()) {
            this.dstheloai[index].setTenTheLoai(tenMoi);
            System.out.println("Cap nhat ten the loai thanh cong!");
        } else {
            System.out.println("Ten the loai khong duoc de trong!");
        }
    }

    // 10. Thong ke
    public void ThongKeTheLoai() {
        System.out.println("Tong so the loai hien co: " + this.n);
    }

    // 11. Doc va ghi file
    @Override
    public void ReadFile() {
        int count = 0;
        Scanner sc = null;
        FileInputStream Instream = null;

        try {
            Instream = new FileInputStream("data/TheLoai.txt");
            sc = new Scanner(Instream);
            this.dstheloai = new TheLoai[0];

            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }
                String[] data = line.split(",");
                if (data.length >= 2) {
                    this.dstheloai = Arrays.copyOf(this.dstheloai, count + 1);
                    this.dstheloai[count] = new TheLoai();
                    this.dstheloai[count].setMaTheLoai(data[0].trim());
                    this.dstheloai[count].setTenTheLoai(data[1].trim());
                    count++;
                }
            }
            this.n = count;
            TheLoai.setTongSoTheLoai(this.n);
        } catch (FileNotFoundException e) {
            System.out.println("Loi: Khong tim thay file data/TheLoai.txt!");
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
    }

    @Override
    public void WriteFile() {
        FileOutputStream Outstream = null;
        PrintWriter writer = null;

        try {
            Outstream = new FileOutputStream("data/TheLoai.txt");
            writer = new PrintWriter(Outstream);

            for (int i = 0; i < this.n; i++) {
                if (this.dstheloai[i] != null) {
                    writer.println(this.dstheloai[i].toFileString());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Loi: Khong tim thay duong dan file data/TheLoai.txt de ghi!");
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    // 12. Kiem tra ma trung
    public boolean CheckMaTL(String MaTL) {
        for (int i = 0; i < this.n; i++) {
            if (dstheloai[i] != null && dstheloai[i].getMaTheLoai().equalsIgnoreCase(MaTL)) {
                return true;
            }
        }
        return false;
    }

    // 13. Kiem tra tinh hop le cua ma (bat dau bang TL + so, VD: TL01)
    public boolean CheckHopLe(String MaTL) {
        if (MaTL == null || MaTL.length() < 3 || !MaTL.substring(0, 2).equals("TL")) {
            return false;
        }
        for (int i = 2; i < MaTL.length(); i++) {
            char c = MaTL.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }
}
