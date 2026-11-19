package danhsach;

import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import model.Book;
import model.QuyDinhPhat;

public class DSQuyDinhPhat implements DanhSach {
    private QuyDinhPhat[] dsQDP;
    private int n;
    Scanner sc = new Scanner(System.in);

    public DSQuyDinhPhat() {
        this.n = 0;
        this.dsQDP = new QuyDinhPhat[100];
    }

    public DSQuyDinhPhat(QuyDinhPhat[] dsQDP, int n) {
        this.dsQDP = dsQDP;
        this.n = n;
    }

    public int getSoLuong() {
        return n;
    }

    public QuyDinhPhat getQDP(int index) {
        if (index < 0 || index >= n) {
            throw new IndexOutOfBoundsException("Vi tri quy dinh phat khong hop le.");
        }
        return dsQDP[index];
    }

    public void Input() {
        System.out.print("Nhap so luong quy dinh phat: ");
        int sl = Integer.parseInt(sc.nextLine().trim());
        this.dsQDP = new QuyDinhPhat[Math.max(sl, 1)];
        this.n = 0;
        for (int i = 0; i < sl; i++) {
            Them();
        }
    }

    @Override
    public void Output() {
        System.out.println("\nDANH SACH QUY DINH PHAT");
        System.out.println("+----------+------------------------------+------------------------------+");
        System.out.printf("|%-10s|%-30s|%-30s|%n", "MA QDP", "TEN LY DO", "CACH THUC PHAT");
        System.out.println("+----------+------------------------------+------------------------------+");
        for (int i = 0; i < n; i++) {
            if (dsQDP[i] != null) {
                dsQDP[i].Output();
            }
        }
        if (n == 0) {
            System.out.println(" Thu vien chua co quy dinh phat!!");
        }
        System.out.println("+----------+------------------------------+------------------------------+");
    }

    public int TimViTri(String maQDP) {
        for (int i = 0; i < n; i++) {
            if (dsQDP[i] != null && dsQDP[i].getMaQDP().equalsIgnoreCase(maQDP)) {
                return i;
            }
        }
        return -1;
    }

    public QuyDinhPhat Tim(String maQDP) {
        int viTri = TimViTri(maQDP);
        return viTri == -1 ? null : dsQDP[viTri];
    }

    public QuyDinhPhat Tim(Scanner sc) {
        System.out.print("Nhap ma quy dinh can tim: ");
        QuyDinhPhat quyDinh = Tim(sc.nextLine().trim());
        if (quyDinh == null) {
            System.out.println("Khong tim thay quy dinh phat.");
        }
        return quyDinh;
    }

    public void TimQuyDinh() {
        QuyDinhPhat quyDinh = Tim(sc);
        if (quyDinh != null) {
            System.out.println("+----------+------------------------------+------------------------------+");
            System.out.printf("|%-10s|%-30s|%-30s|%n", "MA QDP", "TEN LY DO", "CACH THUC PHAT");
            System.out.println("+----------+------------------------------+------------------------------+");
            quyDinh.Output();
            System.out.println("+----------+------------------------------+------------------------------+");
        }
    }

    public void Them() {
        QuyDinhPhat quyDinh = new QuyDinhPhat();
        quyDinh.Input();
        while (quyDinh.getMaQDP().isEmpty() || Tim(quyDinh.getMaQDP()) != null) {
            System.out.println("Ma quy dinh rong hoac da ton tai.");
            System.out.print("Nhap lai ma quy dinh: ");
            quyDinh.setMaQDP(sc.nextLine().trim());
        }
        Them(quyDinh);
    }

    public void Them(QuyDinhPhat quyDinh) {
        if (Tim(quyDinh.getMaQDP()) != null) {
            throw new IllegalArgumentException("Ma quy dinh phat da ton tai.");
        }
        if (n == dsQDP.length) {
            dsQDP = Arrays.copyOf(dsQDP, dsQDP.length + 1);
        }
        dsQDP[n++] = quyDinh;
    }

    public boolean Xoa(String maQDP) {
        int viTri = TimViTri(maQDP);
        if (viTri == -1) {
            return false;
        }
        for (int i = viTri; i < n - 1; i++) {
            dsQDP[i] = dsQDP[i + 1];
        }
        dsQDP[--n] = null;
        return true;
    }

    public void Xoa() {
        System.out.print("Nhap ma quy dinh can xoa: ");
        String maQDP = sc.nextLine().trim();
        if (Xoa(maQDP)) {
            System.out.println("Xoa quy dinh thanh cong.");
        } else {
            System.out.println("Khong tim thay quy dinh phat.");
        }
    }

    public void Sua() {
        QuyDinhPhat quyDinh = Tim(sc);
        if (quyDinh == null) {
            return;
        }
        System.out.print("Nhap ten ly do moi: ");
        quyDinh.setTenLD(sc.nextLine().trim());
        System.out.print("Nhap mo ta / cach thuc phat moi: ");
        quyDinh.setMoTa(sc.nextLine().trim());
        System.out.println("Sua quy dinh thanh cong.");
    }

    public void ThongKe() {
        System.out.println("Tong so quy dinh: " + n);
    }

    // Ham tinh tien phat dua theo ma quy dinh phat
    public double TinhTienPhat(String maQDP, double soNgayTre, String maSach, DSBook dsb) {
        if (TimViTri(maQDP) == -1) {
            return -1;
        }
        if (maQDP.equalsIgnoreCase("QD01")) {
            return soNgayTre * 10000;
        }
        Book sach = dsb.timkiemBook(maSach);
        if (sach == null) {
            return 0;
        }
        double giaSach = sach.getDonGia();
        if (maQDP.equalsIgnoreCase("QD02")) {
            return giaSach;
        } else {
            return giaSach * 2;
        }
    }

    @Override
    public void WriteFile() {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter("data/QuyDinhPhat.txt");
            for (int i = 0; i < n; i++) {
                if (dsQDP[i] != null) {
                    pw.println(
                            dsQDP[i].getMaQDP() + "," +
                            dsQDP[i].getTenLD() + "," +
                            dsQDP[i].getMoTa());
                }
            }
            System.out.println("Ghi file thanh cong!");
        } catch (Exception e) {
            System.out.println("Loi ghi file: " + e.getMessage());
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }

    @Override
    public void ReadFile() {
        Scanner scFile = null;
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream("data/QuyDinhPhat.txt");
            scFile = new Scanner(inputStream);
            n = 0;
            while (scFile.hasNextLine()) {
                String line = scFile.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }
                String[] data = line.split(",");
                if (data.length >= 3) {
                    if (n == dsQDP.length) {
                        dsQDP = Arrays.copyOf(dsQDP, dsQDP.length + 1);
                    }
                    dsQDP[n] = new QuyDinhPhat(data[0].trim(), data[1].trim(), data[2].trim());
                    n++;
                }
            }
        } catch (Exception e) {
            System.out.println("Loi doc file: " + e.getMessage());
        } finally {
            if (scFile != null) {
                scFile.close();
            }
        }
    }
}