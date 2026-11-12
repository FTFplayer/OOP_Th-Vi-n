package danhsach;

import model.QuyDinhPhat;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter; // Dùng để kiểm tra file có tồn tại hay không
import java.io.IOException; // Mở luồng đọc file ký tự
import java.io.PrintWriter; // Đọc từng dòng văn bản tối ưu, nhanh chóng
import java.util.Arrays; // Mở luồng ghi file ký tự
import java.util.Scanner; // Ghi dữ liệu dạng dòng văn bản (println, printf)

public class ListQuyDinhPhat {
    private QuyDinhPhat[] dsQDP;
    private int soLuong;

    public ListQuyDinhPhat(int length) {
        this.soLuong = 0;
        this.dsQDP = new QuyDinhPhat[length];
    }

    public ListQuyDinhPhat(QuyDinhPhat[] dsQDP, int soLuong) {
        this.dsQDP = dsQDP;
        this.soLuong = soLuong;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public QuyDinhPhat getQDP(int index) {
        if (index < 0 || index >= soLuong) {
            throw new IndexOutOfBoundsException("Vi tri quy dinh phat khong hop le.");
        }
        return dsQDP[index];
    }

    public void Input(Scanner sc) {
        System.out.print("Nhap so luong quy dinh phat: ");
        int sl = Integer.parseInt(sc.nextLine());
        this.dsQDP = new QuyDinhPhat[sl];
        soLuong = 0;
        for (int i = 0; i < sl; i++) {
            Them(sc);
        }

    }

    public void Output() {
        System.out.println("DANH SACH QUY DINH PHAT");
        System.out.printf("|%-10s|%-30s|%-14s|%n", "MA LD", "TEN LY DO", "SO TIEN");
        for (int i = 0; i < soLuong; i++) {
            dsQDP[i].Output();
        }
        if (soLuong == 0) {
            System.out.println("DANH SACH RONG");
        }
    }

    private int TimViTri(String maLD) {
        for (int i = 0; i < soLuong; i++) {
            if (dsQDP[i].getMaLD().equalsIgnoreCase(maLD)) {
                return i;
            }
        }
        return -1;
    }

    public QuyDinhPhat Tim(String maLD) {
        int viTri = TimViTri(maLD);
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

    public void TimQuyDinh(Scanner sc) {
        QuyDinhPhat quyDinh = Tim(sc);
        if (quyDinh != null) {
            quyDinh.Output();
        } else
            System.out.println("Khong tim thay quy dinh phat");
    }

    public void Them(Scanner sc) {
        QuyDinhPhat quyDinh = new QuyDinhPhat();
        quyDinh.Input(sc);
        while (quyDinh.getMaLD().isEmpty() || Tim(quyDinh.getMaLD()) != null) {
            System.out.println("Ma ly do rong hoac da ton tai.");
            System.out.print("Nhap lai ma ly do: ");
            quyDinh.setMaLD(sc.nextLine().trim());
        }
        Them(quyDinh);
    }

    public void Them(QuyDinhPhat quyDinh) {
        if (Tim(quyDinh.getMaLD()) != null) {
            throw new IllegalArgumentException("Ma ly do phat da ton tai.");
        }
        if (soLuong == dsQDP.length) {
            dsQDP = Arrays.copyOf(dsQDP, dsQDP.length + 1);
        }
        dsQDP[soLuong++] = quyDinh;

    }

    public boolean Xoa(String maLD) {
        int viTri = TimViTri(maLD);
        if (viTri == -1) {
            return false;
        }
        for (int i = viTri; i < soLuong - 1; i++) {
            dsQDP[i] = dsQDP[i + 1];
        }
        dsQDP[--soLuong] = null;
        return true;
    }

    public void Xoa(Scanner sc) {
        System.out.print("Nhap ma ly do can xoa: ");
        String maLD = sc.nextLine().trim();
        if (Xoa(maLD))
            System.out.println("Xoa quy dinh thanh cong.");
        else {
            System.out.println("Khong tim thay quy dinh phat.");
        }
    }

    public void Sua(Scanner sc) {
        QuyDinhPhat quyDinh = Tim(sc);
        if (quyDinh == null) {
            return;
        }
        System.out.print("Nhap ten ly do moi: ");
        quyDinh.setTenLD(sc.nextLine().trim());
        System.out.print("Nhap so tien quy dinh moi: ");
        quyDinh.setSoTien(Double.parseDouble(sc.nextLine()));
        System.out.println("Sua quy dinh thanh cong.");
    }

    public void ThongKe() {
        System.out.println("Tong so quy dinh: " + soLuong);
    }

    public void ghiFile(String filename) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            pw.println(soLuong);
            for (int i = 0; i < soLuong; i++) {
                pw.println(dsQDP[i].getMaLD() + ";" + dsQDP[i].getTenLD() + ";" + dsQDP[i].getSoTien());
            }
            System.out.println("-> Ghi file quy dinh thanh cong!");
        } catch (IOException e) {
            System.out.println("-> Loi ghi file: " + e.getMessage());
        }
    }

    public void docFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine();
            if (line == null)
                return;
            soLuong = 0;
            int sl = Integer.parseInt(line);
            for (int i = 0; i < sl; i++) {
                String[] parts = br.readLine().split(";");
                dsQDP[soLuong++] = new QuyDinhPhat(parts[0], parts[1], Double.parseDouble(parts[2]));
            }
            System.out.println("-> Doc file quy dinh thanh cong!");
        } catch (IOException e) {
            System.out.println("-> Loi doc file (hoac file chua ton tai): " + e.getMessage());
        }
    }

}
