package danhsach;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import model.ChiTietPhieuMuon;
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

    public void napChiTiet(DSChiTietPhieuMuon dsCTPM) {
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i] != null) danhSach[i].clearChiTiet();
        }
        if (dsCTPM == null) return;
        for (int i = 0; i < dsCTPM.getSoLuong(); i++) {
            ChiTietPhieuMuon ct = dsCTPM.get(i);
            if (ct != null) {
                PhieuMuon pm = TimTheoMa(ct.getMaPM());
                if (pm != null) pm.themChiTiet(ct);
            }
        }
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

    public void Them() {
        PhieuMuon item = new PhieuMuon();
        item.Input();
        System.out.println(Them(item) ? "Them phieu muon thanh cong!" : "Ma phieu muon da ton tai hoac ngay khong hop le.");
    }

    public boolean Xoa(String maPM) {
        int index = indexOf(maPM);
        if (index < 0) return false;
        dichTrai(index);
        return true;
    }

    private boolean laDuLieuHopLe(PhieuMuon item) {
        if (item.getNgayMuon() == null || item.getNgayTra() == null) return false;
        if (item.getNgayTra().isBefore(item.getNgayMuon())) return false;
        if (item.getNgayThucTra() != null && item.getNgayThucTra().isBefore(item.getNgayMuon())) return false;
        return true;
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

    public void PrintHeader() {
        System.out.println("+----------+----------+----------+------------+------------+------------+------------------------------+");
        System.out.printf("| %-8s | %-8s | %-8s | %-10s | %-10s | %-10s | %-28s |%n",
                "MA PM", "MA DG", "MA NV", "NGAY MUON", "NGAY TRA", "THUC TRA", "CAC CHI TIET");
        System.out.println("+----------+----------+----------+------------+------------+------------+------------------------------+");
    }

    public void PrintFooter() {
        System.out.println("+----------+----------+----------+------------+------------+------------+------------------------------+");
    }

    @Override
    public void Output() {
        if (soLuong == 0) {
            System.out.println("Danh sach phieu muon dang trong!");
            return;
        }
        PrintHeader();
        for (int i = 0; i < soLuong; i++) danhSach[i].output();
        PrintFooter();
    }

    public void ThongKeTheoQuy() {
        int q1 = 0, q2 = 0, q3 = 0, q4 = 0;
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i] == null || danhSach[i].getNgayMuon() == null) continue;
            int thang = danhSach[i].getNgayMuon().getMonthValue();
            int quy = (thang - 1) / 3 + 1;
            switch (quy) {
                case 1 -> q1++;
                case 2 -> q2++;
                case 3 -> q3++;
                case 4 -> q4++;
            }
        }
        System.out.println("\n=== THONG KE SO PHIEU MUON DUOC TAO THEO QUY ===");
        System.out.println("  Quy 1 (Thang 1 - 3)  : " + q1 + " phieu");
        System.out.println("  Quy 2 (Thang 4 - 6)  : " + q2 + " phieu");
        System.out.println("  Quy 3 (Thang 7 - 9)  : " + q3 + " phieu");
        System.out.println("  Quy 4 (Thang 10 - 12): " + q4 + " phieu");
    }

    @Override
    public void ReadFile() {
        soLuong = 0;
        Arrays.fill(danhSach, null);
        try (BufferedReader reader = new BufferedReader(new FileReader("data/PhieuMuon.txt"))) {
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
            System.out.println("Loi doc file data/PhieuMuon.txt: " + e.getMessage());
        }
    }

    @Override
    public void WriteFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("data/PhieuMuon.txt"))) {
            for (int i = 0; i < soLuong; i++) {
                PhieuMuon item = danhSach[i];
                String thucTra = (item.getNgayThucTra() != null) ? item.getNgayThucTraStr() : "";
                writer.println(String.join(";", item.getMaPM(), item.getMaDG(), item.getMaNV(),
                        item.getNgayMuonStr(), item.getNgayTraStr(), thucTra));
            }
        } catch (IOException e) {
            System.out.println("Loi ghi file data/PhieuMuon.txt: " + e.getMessage());
        }
    }
}
