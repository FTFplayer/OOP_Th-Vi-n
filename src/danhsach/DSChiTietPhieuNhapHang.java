package danhsach;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import model.ChiTietPhieuNhapHang;

public class DSChiTietPhieuNhapHang implements DanhSach {
    private ChiTietPhieuNhapHang[] danhSach;
    private int soLuong;

    public DSChiTietPhieuNhapHang() {
        danhSach = new ChiTietPhieuNhapHang[10];
        soLuong = 0;
    }

    public int getSoLuong() { return soLuong; }
    public ChiTietPhieuNhapHang get(int index) {
        return index >= 0 && index < soLuong ? danhSach[index] : null;
    }

    // Tu dong sinh ma chi tiet tiep theo (VD: CTPN030 -> CTPN031)
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
        return String.format("CTPN%03d", max + 1);
    }

    public ChiTietPhieuNhapHang TimTheoMaCT(String maCT) {
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i].getMaCT().equalsIgnoreCase(maCT)) return danhSach[i];
        }
        return null;
    }

    public ChiTietPhieuNhapHang[] TimTheoMaPNH(String maPNH) {
        int count = 0;
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i].getMaPNH().equalsIgnoreCase(maPNH)) count++;
        }
        ChiTietPhieuNhapHang[] result = new ChiTietPhieuNhapHang[count];
        int index = 0;
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i].getMaPNH().equalsIgnoreCase(maPNH)) result[index++] = danhSach[i];
        }
        return result;
    }

    public boolean Them(ChiTietPhieuNhapHang item) {
        if (item == null || item.getMaCT() == null || item.getMaCT().trim().isEmpty()
                || TimTheoMaCT(item.getMaCT()) != null) return false;
        moRongNeuCan();
        danhSach[soLuong++] = item;
        return true;
    }

    public void Them() {
        ChiTietPhieuNhapHang item = new ChiTietPhieuNhapHang();
        item.Input();
        System.out.println(Them(item) ? "Them chi tiet phieu nhap thanh cong!" : "Ma chi tiet da ton tai.");
    }

    public boolean Xoa(String maCT) {
        int index = indexOf(maCT);
        if (index < 0) return false;
        for (int i = index; i < soLuong - 1; i++) danhSach[i] = danhSach[i + 1];
        danhSach[--soLuong] = null;
        return true;
    }

    public double TinhTongTien(String maPNH) {
        double total = 0;
        ChiTietPhieuNhapHang[] items = TimTheoMaPNH(maPNH);
        for (ChiTietPhieuNhapHang item : items) total += item.getThanhTien();
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
            System.out.println("Danh sach chi tiet phieu nhap dang trong!");
            return;
        }
        System.out.println("+------------+------------+------------+----------+--------------+----------------+");
        System.out.printf("| %-10s | %-10s | %-10s | %-8s | %-12s | %-14s |%n",
                "MA CT", "MA PNH", "MA SACH", "SO LUONG", "DON GIA", "THANH TIEN");
        System.out.println("+------------+------------+------------+----------+--------------+----------------+");
        for (int i = 0; i < soLuong; i++) danhSach[i].Output();
        System.out.println("+------------+------------+------------+----------+--------------+----------------+");
    }

    @Override
    public void ReadFile() {
        soLuong = 0;
        Arrays.fill(danhSach, null);
        try (BufferedReader reader = new BufferedReader(new FileReader("data/ChiTietPhieuNhapHang.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split(";", -1);
                if (parts.length != 5) throw new IOException("Du lieu chi tiet phieu nhap khong hop le.");
                if (!Them(new ChiTietPhieuNhapHang(parts[0].trim(), parts[1].trim(), parts[2].trim(),
                        Integer.parseInt(parts[3].trim()), Double.parseDouble(parts[4].trim())))) {
                    throw new IOException("Trung ma chi tiet: " + parts[0]);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Loi doc file data/ChiTietPhieuNhapHang.txt: " + e.getMessage());
        }
    }

    @Override
    public void WriteFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("data/ChiTietPhieuNhapHang.txt"))) {
            for (int i = 0; i < soLuong; i++) {
                ChiTietPhieuNhapHang item = danhSach[i];
                writer.println(String.join(";", item.getMaCT(), item.getMaPNH(), item.getMaSach(),
                        String.valueOf(item.getSoLuong()), String.valueOf(item.getDonGia())));
            }
        } catch (IOException e) {
            System.out.println("Loi ghi file data/ChiTietPhieuNhapHang.txt: " + e.getMessage());
        }
    }
}
