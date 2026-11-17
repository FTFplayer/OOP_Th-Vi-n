package danhsach;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import model.ChiTietPhieuMuon;

public class DSChiTietPhieuMuon {
    private ChiTietPhieuMuon[] danhSach;
    private int soLuong;

    public DSChiTietPhieuMuon() {
        danhSach = new ChiTietPhieuMuon[10];
        soLuong = 0;
    }

    public int getSoLuong() { return soLuong; }
    public ChiTietPhieuMuon get(int index) {
        return index >= 0 && index < soLuong ? danhSach[index] : null;
    }

    public ChiTietPhieuMuon TimTheoMaCT(String maCT) {
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i].getMaCT().equalsIgnoreCase(maCT)) return danhSach[i];
        }
        return null;
    }

    public ChiTietPhieuMuon[] TimTheoMaPM(String maPM) {
        int count = 0;
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i].getMaPM().equalsIgnoreCase(maPM)) count++;
        }
        ChiTietPhieuMuon[] result = new ChiTietPhieuMuon[count];
        int index = 0;
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i].getMaPM().equalsIgnoreCase(maPM)) result[index++] = danhSach[i];
        }
        return result;
    }

    public boolean Them(ChiTietPhieuMuon item) {
        if (item == null || item.getMaCT() == null || item.getMaCT().trim().isEmpty()
                || TimTheoMaCT(item.getMaCT()) != null) return false;
        moRongNeuCan();
        danhSach[soLuong++] = item;
        return true;
    }

    public void Them(Scanner sc) {
        ChiTietPhieuMuon item = new ChiTietPhieuMuon();
        item.Input(sc);
        System.out.println(Them(item) ? "Them chi tiet phieu muon thanh cong!" : "Ma chi tiet da ton tai.");
    }

    public boolean Xoa(String maCT) {
        int index = indexOf(maCT);
        if (index < 0) return false;
        for (int i = index; i < soLuong - 1; i++) danhSach[i] = danhSach[i + 1];
        danhSach[--soLuong] = null;
        return true;
    }

    public int TongSoSachMuon(String maPM) {
        int total = 0;
        ChiTietPhieuMuon[] items = TimTheoMaPM(maPM);
        for (ChiTietPhieuMuon item : items) total += item.getSoLuong();
        return total;
    }

    private int indexOf(String maCT) {
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i].getMaCT().equalsIgnoreCase(maCT)) return i;
        }
        return -1;
    }

    private void moRongNeuCan() {
        if (soLuong == danhSach.length) danhSach = Arrays.copyOf(danhSach, danhSach.length * 2);
    }

    public void Output() {
        System.out.printf("%-12s %-12s %-12s %-10s%n", "MA CT", "MA PM", "MA SACH", "SO LUONG");
        for (int i = 0; i < soLuong; i++) danhSach[i].output();
    }

    public void ghiFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (int i = 0; i < soLuong; i++) {
                ChiTietPhieuMuon item = danhSach[i];
                writer.println(String.join(";", item.getMaCT(), item.getMaPM(), item.getMaSach(),
                        String.valueOf(item.getSoLuong())));
            }
        } catch (IOException e) {
            throw new IllegalStateException("Khong the ghi chi tiet phieu muon.", e);
        }
    }

    public void docFile(String filename) {
        soLuong = 0;
        Arrays.fill(danhSach, null);
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";", -1);
                if (parts.length != 4) throw new IOException("Du lieu chi tiet phieu muon khong hop le.");
                if (!Them(new ChiTietPhieuMuon(parts[0], parts[1], parts[2], Integer.parseInt(parts[3])))) {
                    throw new IOException("Trung ma chi tiet: " + parts[0]);
                }
            }
        } catch (IOException | NumberFormatException e) {
            throw new IllegalStateException("Khong the doc chi tiet phieu muon.", e);
        }
    }
}
