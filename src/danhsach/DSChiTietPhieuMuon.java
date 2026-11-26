package danhsach;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import model.ChiTietPhieuMuon;

public class DSChiTietPhieuMuon implements DanhSach {
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

    // Tu dong sinh ma chi tiet tiep theo (VD: CTPM030 -> CTPM031)
    public String taoMaCTMoi() {
        int max = 0;
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i] != null && danhSach[i].getMaCT() != null) {
                String digits = danhSach[i].getMaCT().replaceAll("[^0-9]", "");
                if (!digits.isEmpty()) {
                    try {
                        int val = Integer.parseInt(digits);
                        if (val > max) max = val;
                    } catch (NumberFormatException ignored) {}
                }
            }
        }
        return String.format("CTPM%03d", max + 1);
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

    public void Them() {
        ChiTietPhieuMuon item = new ChiTietPhieuMuon();
        item.Input();
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

    @Override
    public void Output() {
        if (soLuong == 0) {
            System.out.println("Danh sach chi tiet phieu muon dang trong!");
            return;
        }
        System.out.println("DANH SACH CHI TIET PHIEU MUON:");
        System.out.println("+------------+------------+------------+----------+");
        System.out.printf("| %-10s | %-10s | %-10s | %-8s |%n", "MA CT", "MA PM", "MA SACH", "SO LUONG");
        System.out.println("+------------+------------+------------+----------+");
        for (int i = 0; i < soLuong; i++) danhSach[i].Output();
        System.out.println("+------------+------------+------------+----------+");
    }

    @Override
    public void ReadFile() {
        soLuong = 0;
        Arrays.fill(danhSach, null);
        try (BufferedReader reader = new BufferedReader(new FileReader("data/ChiTietPhieuMuon.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split(";", -1);
                if (parts.length != 4) throw new IOException("Du lieu chi tiet phieu muon khong hop le.");
                if (!Them(new ChiTietPhieuMuon(parts[0].trim(), parts[1].trim(), parts[2].trim(),
                        Integer.parseInt(parts[3].trim())))) {
                    throw new IOException("Trung ma chi tiet: " + parts[0]);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Loi doc file data/ChiTietPhieuMuon.txt: " + e.getMessage());
        }
    }

    @Override
    public void WriteFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("data/ChiTietPhieuMuon.txt"))) {
            for (int i = 0; i < soLuong; i++) {
                ChiTietPhieuMuon item = danhSach[i];
                writer.println(String.join(";", item.getMaCT(), item.getMaPM(), item.getMaSach(),
                        String.valueOf(item.getSoLuong())));
            }
        } catch (IOException e) {
            System.out.println("Loi ghi file data/ChiTietPhieuMuon.txt: " + e.getMessage());
        }
    }
}
