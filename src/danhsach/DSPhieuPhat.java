package danhsach;
import model.PhieuPhat;
import danhsach.DSChiTietPhieuPhat;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class DSPhieuPhat {
    private static final String TEN_FILE = "PhieuPhat.txt";
    private int soLuong;
    private PhieuPhat[] dsPhieuPhat;
    private DSChiTiet dsChiTiet;

    // Constructor khoi tao mang tho rong, KHONG tu dong doc file nua
    public DSPhieuPhat(int length) {
        this.dsPhieuPhat = new PhieuPhat[length];
        this.soLuong = 0;
    }

    public int getSoLuong() { return soLuong; }

    public PhieuPhat getPhieuPhat(int index) {
        if (index < 0 || index >= soLuong) return null;
        return dsPhieuPhat[index];
    }

    public void setDSChiTiet(DSChiTiet dsChiTiet) {
        this.dsChiTiet = dsChiTiet;
    }

    // ==========================================
    // 1. INPUT DANH SACH (Bang tay tu ban phim)
    // ==========================================
    public void Input(Scanner sc) {
        System.out.print("Nhap so luong phieu phat: ");
        int n = Integer.parseInt(sc.nextLine().trim());
        this.dsPhieuPhat = new PhieuPhat[Math.max(1, n)];
        this.soLuong = 0;
        for (int i = 0; i < n; i++) {
            System.out.println("Nhap phieu phat thu " + (i + 1) + ":");
            PhieuPhat p = new PhieuPhat();
            p.Input(sc);
            Them(p);
        }
    }

    // ==========================================
    // 2. OUTPUT / XUAT
    // ==========================================
    public void Output() {
        if (dsChiTiet != null) {
            for (int i = 0; i < soLuong; i++) {
                if (dsPhieuPhat[i] != null) {
                    dsPhieuPhat[i].capNhatTongTienPhat(dsChiTiet);
                }
            }
        }

        System.out.println("DANH SACH PHIEU PHAT");
        System.out.printf("|%-12s|%-14s|%-14s|%-14s|%n", "MA PP", "MA PM", "NGAY PHAT", "SO TIEN PHAT");
        for (int i = 0; i < soLuong; i++) {
            if (dsPhieuPhat[i] != null) {
                dsPhieuPhat[i].Output();
            }
        }
        if (soLuong == 0) {
            System.out.println("DANH SACH RONG");
        }
    }

    public void OutputtheoMaPP(String maPP) {
        int idx = TimtheoMaPP(maPP);
        if (idx != -1) {
            if (dsChiTiet != null) {
                dsPhieuPhat[idx].capNhatTongTienPhat(dsChiTiet);
            }
            System.out.printf("|%-12s|%-14s|%-14s|%-14s|%n", "MA PP", "MA PM", "NGAY PHAT", "SO TIEN PHAT");
            dsPhieuPhat[idx].Output();
        } else {
            System.out.println("Khong tim thay phieu phat co ma: " + maPP);
        }
    }

    // ==========================================
    // 3. THEM / ADD
    // ==========================================
    public void Them(Scanner sc) {
        PhieuPhat p = new PhieuPhat();
        p.Input(sc);
        while (p.getMaPP().isEmpty() || Tim(p.getMaPP()) != -1) {
            System.out.println("Ma phieu phat rong hoac da ton tai!");
            System.out.print("Nhap lai ma phieu phat: ");
            p.setMaPP(sc.nextLine().trim());
        }
        Them(p);
        System.out.println("Them phieu phat thanh cong!");
    }

    public void Them(PhieuPhat p) {
        if (soLuong >= dsPhieuPhat.length) {
            dsPhieuPhat = Arrays.copyOf(dsPhieuPhat, dsPhieuPhat.length + 1);
        }
        dsPhieuPhat[soLuong++] = p;
    }

    // ==========================================
    // 4. TIM KIEM
    // ==========================================
    public int TimtheoMaPP(String maPP) {
        for (int i = 0; i < soLuong; i++) {
            if (dsPhieuPhat[i] != null && dsPhieuPhat[i].getMaPP().equalsIgnoreCase(maPP)) {
                return i;
            }
        }
        return -1;
    }
    

    public void TimtheoMaPP(Scanner sc) {
        System.out.print("Nhap ma phieu phat can tim: ");
        String maPP = sc.nextLine().trim();
        Output(maPP);
    }


    public PhieuPhat[] TimtheoMaPM(String maPM)
    {   int count=0;
        for (int i=0; i< soLuong; i++)
            if(dsPhieuPhat[i].getMaPM().equalsIgnoreCase(maPM))
                count++;
          ds= new PhieuPhat[count];
          int index=0;
          
           for (int i=0; i< soLuong; i++)
            if(dsPhieuPhat[i].getMaPM().equalsIgnoreCase(maPM))
                ds[index++]= dsPhieuPhat[i];
    }

    public void TimtheoMaPM(Scanner sc)
    {
        System.out.println("Nhap ma phieu muon:");
        String maPM= sc.nextLine();
        PhieuPhat[] ds=TimtheoMaPM(maPM);
        if(ds.length == 0)
        {
            System.out.println("Ma PM:"+maPM+"khong co phieu phat");
            return;
        }
        
        for ( int i =0; i< ds.length; i++){
            ds[i].Output();
        }



    }

    // ==========================================
    // 5. XOA / DELETE
    // ==========================================
    public boolean Xoa(String maPP) {
        int viTri = Tim(maPP);
        if (viTri == -1) return false;
        
        for (int i = viTri; i < soLuong - 1; i++) {
            dsPhieuPhat[i] = dsPhieuPhat[i + 1];
        }
        dsPhieuPhat[--soLuong] = null;
        return true;
    }

    public void Xoa(Scanner sc) {
        System.out.print("Nhap ma phieu phat can xoa: ");
        String maPP = sc.nextLine().trim();
        if (Xoa(maPP)) {
            System.out.println("Xoa phieu phat thanh cong!");
        } else {
            System.out.println("Khong tim thay phieu phat de xoa!");
        }
    }

    // ==========================================
    // 6. SUA / EDIT
    // ==========================================
    public void Sua(Scanner sc) {
        System.out.print("Nhap ma phieu phat can sua: ");
        String maPP = sc.nextLine().trim();
        int viTri = Tim(maPP);
        if (viTri == -1) {
            System.out.println("Khong tim thay phieu phat de sua!");
            return;
        }
        PhieuPhat p = dsPhieuPhat[viTri];
        System.out.print("Nhap ma phieu muon moi (FK): ");
        p.setMaPM(sc.nextLine().trim());
        System.out.print("Nhap ngay phat moi: ");
        p.setNgayPhat(sc.nextLine().trim());
        System.out.println("Sua phieu phat thanh cong!");
    }

    public boolean Sua(String maPP, PhieuPhat pMoi) {
        int viTri = Tim(maPP);
        if (viTri == -1) return false;
        dsPhieuPhat[viTri] = pMoi;
        return true;
    }

    // ==========================================
    // 7. THONG KE
    // ==========================================
    public void ThongKe() {
        double tongTien = 0;
        if (dsChiTiet != null) {
            for (int i = 0; i < soLuong; i++) {
                if (dsPhieuPhat[i] != null) {
                    dsPhieuPhat[i].capNhatTongTienPhat(dsChiTiet);
                    tongTien += dsPhieuPhat[i].getSoTienPhat();
                }
            }
        } else {
            for (int i = 0; i < soLuong; i++) {
                if (dsPhieuPhat[i] != null) {
                    tongTien += dsPhieuPhat[i].getSoTienPhat();
                }
            }
        }
        System.out.println("Tong so phieu phat: " + soLuong);
        System.out.printf("Tong so tien phat: %.2f%n", tongTien);
    }

    // ==========================================
    // DOC / GHI FILE (Do Menu goi)
    // ==========================================
    public void ghiFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(TEN_FILE))) {
            pw.println(soLuong);
            for (int i = 0; i < soLuong; i++) {
                if (dsPhieuPhat[i] != null) {
                    PhieuPhat p = dsPhieuPhat[i];
                    pw.println(p.getMaPP() + ";" + p.getMaPM() + ";" + p.getNgayPhat() + ";" + p.getSoTienPhat());
                }
            }
            System.out.println("-> Ghi file phieu phat thanh cong!");
        } catch (IOException e) {
            System.out.println("Loi ghi file phieu phat: " + e.getMessage());
        }
    }

    public void docFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(TEN_FILE))) {
            String line = br.readLine();
            if (line == null) return;
            soLuong = 0;
            int sl = Integer.parseInt(line.trim());
            dsPhieuPhat = new PhieuPhat[Math.max(1, sl)];
            for (int i = 0; i < sl; i++) {
                String fileLine = br.readLine();
                if (fileLine == null) break;
                String[] p = fileLine.split(";", -1);
                if (p.length >= 4) {
                    PhieuPhat phieu = new PhieuPhat(p[0], p[1], p[2], Double.parseDouble(p[3]));
                    dsPhieuPhat[soLuong++] = phieu;
                }
            }
            System.out.println("-> Doc file phieu phat thanh cong!");
        } catch (IOException ignored) {}
    }
}