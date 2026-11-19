package danhsach;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import model.PhieuNhapHang;

public class DSPhieuNhapHang {
    private PhieuNhapHang[] danhSach;
    private int soLuong;

    public DSPhieuNhapHang() {
        danhSach = new PhieuNhapHang[10];
        soLuong = 0;
    }

    public int getSoLuong() { return soLuong; }
    public PhieuNhapHang get(int index) {
        return index >= 0 && index < soLuong ? danhSach[index] : null;
    }

    public PhieuNhapHang TimTheoMa(String maPNH) {
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i].getMaPNH().equalsIgnoreCase(maPNH)) return danhSach[i];
        }
        return null;
    }

    public boolean Them(PhieuNhapHang item) {
        if (item == null || item.getMaPNH() == null || item.getMaPNH().trim().isEmpty()
                || TimTheoMa(item.getMaPNH()) != null) return false;
        moRongNeuCan();
        danhSach[soLuong++] = item;
        return true;
    }

    public void Them(Scanner sc) {
        PhieuNhapHang item = new PhieuNhapHang();
        item.Input(sc);
        System.out.println(Them(item) ? "Them phieu nhap hang thanh cong!" : "Ma phieu nhap da ton tai.");
    }

    public boolean Xoa(String maPNH) {
        int index = indexOf(maPNH);
        if (index < 0) return false;
        for (int i = index; i < soLuong - 1; i++) danhSach[i] = danhSach[i + 1];
        danhSach[--soLuong] = null;
        return true;
    }

    public void CapNhatTongTien(DSChiTietPhieuNhapHang chiTiet, String maPNH) {
        PhieuNhapHang item = TimTheoMa(maPNH);
        if (item != null) item.setTongTien(chiTiet.TinhTongTien(maPNH));
    }

    private int indexOf(String maPNH) {
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i].getMaPNH().equalsIgnoreCase(maPNH)) return i;
        }
        return -1;
    }

    private void moRongNeuCan() {
        if (soLuong == danhSach.length) danhSach = Arrays.copyOf(danhSach, danhSach.length * 2);
    }

    public void Output() {
        System.out.printf("%-12s %-12s %-12s %-14s %-15s%n",
                "MA PNH", "MA NCC", "MA NV", "NGAY NHAP", "TONG TIEN");
        for (int i = 0; i < soLuong; i++) danhSach[i].output();
    }

    public void ghiFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(PhieuNhapHang))) {
            for (int i = 0; i < soLuong; i++) {
                PhieuNhapHang item = danhSach[i];
                writer.println(String.join(";", item.getMaPNH(), item.getMaNCC(), item.getMaNV(),
                        item.getNgayNhap(), String.valueOf(item.getTongTien())));
            }
        } catch (IOException e) {
            throw new IllegalStateException("Khong the ghi danh sach phieu nhap hang.", e);
        }
    }

    public void docFile(String filename) {
        soLuong = 0;
        Arrays.fill(danhSach, null);
        try (BufferedReader reader = new BufferedReader(new FileReader(PhieuNhapHang.txt))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";", -1);
                if (parts.length != 5) throw new IOException("Du lieu phieu nhap khong hop le.");
                if (!Them(new PhieuNhapHang(parts[0], parts[1], parts[2], parts[3],
                        Double.parseDouble(parts[4])))) {
                    throw new IOException("Trung ma phieu nhap: " + parts[0]);
                }
            }
        } catch (IOException | NumberFormatException e) {
            throw new IllegalStateException("Khong the doc danh sach phieu nhap hang.", e);
        }
    }
}
