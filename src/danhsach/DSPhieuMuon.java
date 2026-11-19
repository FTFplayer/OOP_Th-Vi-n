package danhsach;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import model.PhieuMuon;

public class DSPhieuMuon implements DanhSach {
    private PhieuMuon[] danhSach;
    private int soLuong;

    public DSPhieuMuon() {
        danhSach = new PhieuMuon[10];
        soLuong = 0;
    }

    public int getSoLuong() { return soLuong; }
    public PhieuMuon get(int index) {
        return index >= 0 && index < soLuong ? danhSach[index] : null;
    }

    public PhieuMuon TimTheoMa(String maPM) {
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i].getMaPM().equalsIgnoreCase(maPM)) return danhSach[i];
        }
        return null;
    }

    public PhieuMuon TimtheoMaPM(String maPM) {
        return TimTheoMa(maPM);
    }

    public boolean Them(PhieuMuon item) {
        if (item == null || item.getMaPM() == null || item.getMaPM().trim().isEmpty()
                || TimTheoMa(item.getMaPM()) != null) return false;
        if (!laDuLieuHopLe(item)) return false;
        moRongNeuCan();
        danhSach[soLuong++] = item;
        return true;
    }

    public void Them(Scanner sc) {
        PhieuMuon item = new PhieuMuon();
        item.Input(sc);
        System.out.println(Them(item) ? "Them phieu muon thanh cong!" : "Ma phieu muon da ton tai hoac ngay khong hop le.");
    }

    public boolean Xoa(String maPM) {
        int index = indexOf(maPM);
        if (index < 0) return false;
        dichTrai(index);
        return true;
    }

    public boolean Sua(String maPM, PhieuMuon itemMoi) {
        int index = indexOf(maPM);
        if (index < 0 || itemMoi == null) return false;
        if (!maPM.equalsIgnoreCase(itemMoi.getMaPM()) && TimTheoMa(itemMoi.getMaPM()) != null) return false;
        if (!laDuLieuHopLe(itemMoi)) return false;
        danhSach[index] = itemMoi;
        return true;
    }

    private boolean laDuLieuHopLe(PhieuMuon item) {
        if (!PhieuMuon.isValidDate(item.getNgayMuon())
                || !PhieuMuon.isValidDate(item.getNgayTra())
                || PhieuMuon.isBefore(item.getNgayTra(), item.getNgayMuon())) {
            return false;
        }
        String ngayThucTra = item.getNgayThucTra();
        return ngayThucTra == null
                || ngayThucTra.trim().isEmpty()
                || PhieuMuon.isValidNgayThucTra(ngayThucTra.trim(),
                        item.getNgayMuon(), item.getNgayTra());
    }

    private int indexOf(String maPM) {
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i].getMaPM().equalsIgnoreCase(maPM)) return i;
        }
        return -1;
    }

    private void moRongNeuCan() {
        if (soLuong == danhSach.length) danhSach = Arrays.copyOf(danhSach, danhSach.length * 2);
    }

    private void dichTrai(int index) {
        for (int i = index; i < soLuong - 1; i++) danhSach[i] = danhSach[i + 1];
        danhSach[--soLuong] = null;
    }

    @Override
    public void Output() {
        if (soLuong == 0) {
            System.out.println("Danh sach phieu muon dang trong!");
            return;
        }
        System.out.println("+------------+------------+------------+--------------+--------------+--------------+");
        System.out.printf("| %-10s | %-10s | %-10s | %-12s | %-12s | %-12s |%n",
                "MA PM", "MA DG", "MA NV", "NGAY MUON", "NGAY TRA", "THUC TRA");
        System.out.println("+------------+------------+------------+--------------+--------------+--------------+");
        for (int i = 0; i < soLuong; i++) danhSach[i].output();
        System.out.println("+------------+------------+------------+--------------+--------------+--------------+");
    }

    @Override
    public void ReadFile() {
        try {
            docFile("data/PhieuMuon.txt");
        } catch (IllegalStateException e) {
            System.out.println("Loi doc file data/PhieuMuon.txt: " + e.getMessage());
        }
    }

    @Override
    public void WriteFile() {
        try {
            ghiFile("data/PhieuMuon.txt");
        } catch (IllegalStateException e) {
            System.out.println("Loi ghi file data/PhieuMuon.txt: " + e.getMessage());
        }
    }

    public void ghiFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (int i = 0; i < soLuong; i++) {
                PhieuMuon item = danhSach[i];
                writer.println(String.join(";", item.getMaPM(), item.getMaDG(), item.getMaNV(),
                        item.getNgayMuon(), item.getNgayTra(), item.getNgayThucTra()));
            }
        } catch (IOException e) {
            throw new IllegalStateException("Khong the ghi danh sach phieu muon.", e);
        }
    }

    public void docFile(String filename) {
        soLuong = 0;
        Arrays.fill(danhSach, null);
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split(";", -1);
                if (parts.length != 6) throw new IOException("Du lieu phieu muon khong hop le.");
                if (!Them(new PhieuMuon(parts[0].trim(), parts[1].trim(), parts[2].trim(),
                        parts[3].trim(), parts[4].trim(), parts[5].trim()))) {
                    throw new IOException("Trung ma hoac ngay khong hop le: " + parts[0]);
                }
            }
        } catch (IOException e) {
            throw new IllegalStateException("Khong the doc danh sach phieu muon.", e);
        }
    }
}
