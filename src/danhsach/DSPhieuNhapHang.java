package danhsach;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Arrays;
import model.ChiTietPhieuNhapHang;
import model.PhieuMuon;
import model.PhieuNhapHang;

public class DSPhieuNhapHang implements DanhSach {
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

    public void napChiTiet(DSChiTietPhieuNhapHang dsCTPNH) {
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i] != null) danhSach[i].clearChiTiet();
        }
        if (dsCTPNH == null) return;
        for (int i = 0; i < dsCTPNH.getSoLuong(); i++) {
            ChiTietPhieuNhapHang ct = dsCTPNH.get(i);
            if (ct != null) {
                PhieuNhapHang pnh = TimTheoMa(ct.getMaPNH());
                if (pnh != null) pnh.themChiTiet(ct);
            }
        }
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

    public void Them() {
        PhieuNhapHang item = new PhieuNhapHang();
        item.Input();
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

    public void PrintHeader() {
        System.out.println("+------------+----------+----------+------------+--------------+------------------------------+");
        System.out.printf("| %-10s | %-8s | %-8s | %-10s | %-12s | %-28s |%n",
                "MA PNH", "MA NCC", "MA NV", "NGAY NHAP", "TONG TIEN", "CAC CHI TIET");
        System.out.println("+------------+----------+----------+------------+--------------+------------------------------+");
    }

    public void PrintFooter() {
        System.out.println("+------------+----------+----------+------------+--------------+------------------------------+");
    }

    @Override
    public void Output() {
        if (soLuong == 0) {
            System.out.println("Danh sach phieu nhap hang dang trong!");
            return;
        }
        PrintHeader();
        for (int i = 0; i < soLuong; i++) danhSach[i].output();
        PrintFooter();
    }

    public void ThongKeTheoQuy() {
        int q1 = 0, q2 = 0, q3 = 0, q4 = 0;
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i] == null) continue;
            LocalDate ngay = PhieuMuon.parseDateSafe(danhSach[i].getNgayNhap());
            if (ngay == null) continue;
            int thang = ngay.getMonthValue();
            int quy = (thang - 1) / 3 + 1;
            switch (quy) {
                case 1 -> q1++;
                case 2 -> q2++;
                case 3 -> q3++;
                case 4 -> q4++;
            }
        }
        System.out.println("\n=== THONG KE SO PHIEU NHAP HANG DUOC TAO THEO QUY ===");
        System.out.println("  Quy 1 (Thang 1 - 3)  : " + q1 + " phieu");
        System.out.println("  Quy 2 (Thang 4 - 6)  : " + q2 + " phieu");
        System.out.println("  Quy 3 (Thang 7 - 9)  : " + q3 + " phieu");
        System.out.println("  Quy 4 (Thang 10 - 12): " + q4 + " phieu");
    }

    @Override
    public void ReadFile() {
        soLuong = 0;
        Arrays.fill(danhSach, null);
        try (BufferedReader reader = new BufferedReader(new FileReader("data/PhieuNhapHang.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split(";", -1);
                if (parts.length != 5) throw new IOException("Du lieu phieu nhap khong hop le.");
                if (!Them(new PhieuNhapHang(parts[0].trim(), parts[1].trim(), parts[2].trim(),
                        parts[3].trim(), Double.parseDouble(parts[4].trim())))) {
                    throw new IOException("Trung ma phieu nhap: " + parts[0]);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Loi doc file data/PhieuNhapHang.txt: " + e.getMessage());
        }
    }

    @Override
    public void WriteFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("data/PhieuNhapHang.txt"))) {
            for (int i = 0; i < soLuong; i++) {
                PhieuNhapHang item = danhSach[i];
                writer.println(String.join(";", item.getMaPNH(), item.getMaNCC(), item.getMaNV(),
                        item.getNgayNhap(), String.valueOf(item.getTongTien())));
            }
        } catch (IOException e) {
            System.out.println("Loi ghi file data/PhieuNhapHang.txt: " + e.getMessage());
        }
    }
}
