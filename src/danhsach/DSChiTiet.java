import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class DSChiTiet {
  
    private ChiTietPhieuPhat[] dsChiTiet;
    private int soLuong;



     public DSChiTiet() {
        this.dsChiTiet = new ChiTietPhieuPhat[100];
        this.soLuong = 0;
    }
    public DSChiTiet(int length) {
        soLuong=0;
        dsChiTiet= new ChiTietPhieuPhat[length];
    }
    public DSChiTiet(ChiTietPhieuPhat[] dsChiTiet, int soLuong) {
        this.dsChiTiet = dsChiTiet;
        this.soLuong = soLuong;
    }
    


   

    public ChiTietPhieuPhat getChiTiet(int index) {
        if (index < 0 || index >= soLuong) {
            return null;
        }
        return dsChiTiet[index];
    }
    public int getSoLuong() {return soLuong;}
  





// ==========================================
    // IN / OUT / HIỂN THỊ
    // ==========================================
    // Khong tham so (In toan bo danh sach)
    public void Input(String maPPCurrent, ListQuyDinhPhat listQD,DSBook dsBook, Scanner sc) {
        System.out.print("Nhap so luong sach vi pham: ");
        int sl = Integer.parseInt(sc.nextLine());
        dsChiTiet= new ChiTietPhieuPhat[sl];
        this.soLuong=sl;
        for ( int i=0; i<sl; i++){
            System.out.println("  Sach vi pham thu " + (i + 1) + ":");
            dsChiTiet[i] = new ChiTietPhieuPhat();
            dsChiTiet[i].Input(maPPCurrent, listQD, dsBook, sc);
        }
    }
    public void Output() {
        System.out.println("DANH SACH CHI TIET PHIEU PHAT:");
        System.out.printf("| %-8s | %-10s | %-15s | %-8s |\n","MA PP", "MA SACH", "TIEN PHAT", "MA LYDO");
        for (int i = 0; i < soLuong; i++) {
            if (dsChiTiet[i] != null) {
                dsChiTiet[i].Output();
            }
        }

    }


    public void Output(String maPP){
        boolean found = false;
        System.out.println("DANH SACH CHI TIET THEO MA PP: " + maPP);
        System.out.printf("| %-8s | %-10s | %-15s | %-8s |\n","MA PP", "MA SACH", "TIEN PHAT", "MA LYDO");
        for (int i=0; i<soLuong; i++) {
            if (dsChiTiet[i] != null && dsChiTiet[i].getMaPP().equalsIgnoreCase(maPP)) {
                dsChiTiet[i].Output();
                found = true;
            }
        }

        if (!found)
            System.out.println("Khong tim thay chi tiet nao cho ma PP:" + maPP);
    }


// ==========================================
    // THEM / ADD
    // ==========================================
    // Khong tham so (tu nhap tu ban phim)
   public void Them(Scanner sc, ListQuyDinhPhat listQD, DSBook dsBook) {
        System.out.print("Nhap ma phieu phat: ");
        String maPP = sc.nextLine().trim();
        ChiTietPhieuPhat ct = new ChiTietPhieuPhat();
        ct.Input(maPP, listQD, dsBook, sc);
        Them(ct);
        System.out.println("Them thanh cong!");
    }
    public void Them(ChiTietPhieuPhat ct) {
        if (soLuong >= dsChiTiet.length) {
            dsChiTiet = Arrays.copyOf(dsChiTiet, dsChiTiet.length + 1);
        }
        dsChiTiet[soLuong++] = ct;
    }

   // ==========================================
    // TIM KIEM 
    // TRA VE DANH SACH CAC CHI TIET TRUNG MA PP
    //IN RA DANH SACH CHI TIẾT
    // ==========================================

    public ChiTietPhieuPhat[] Tim(String maPP) {
        // Dem xem co bao nhieu phan tu trung khop truoc
        int count = 0;
        for (int i = 0; i < soLuong; i++) {
            if (dsChiTiet[i] != null && dsChiTiet[i].getMaPP().equalsIgnoreCase(maPP)) {
                count++;
            }
        }

        if (count == 0) return null; // Khong tim thay

        // Tao mang ket qua chua dung so luong tim thay
        ChiTietPhieuPhat[] ketQua = new ChiTietPhieuPhat[count];
        int idx = 0;
        for (int i = 0; i < soLuong; i++) {
            if (dsChiTiet[i] != null && dsChiTiet[i].getMaPP().equalsIgnoreCase(maPP)) {
                ketQua[idx++] = dsChiTiet[i];
            }
        }
        return ketQua;}
    public void Tim(Scanner sc) {
        System.out.print("Nhap ma phieu phat can tim: ");
        String maPP = sc.nextLine().trim();
        Output(maPP);}
       
// ==========================================
    // XOA / DELETE
    // ==========================================
    // Co tham so (xoa dua tren maPP va maSach)
public boolean Xoa(String maPP, String maSach) {
        int idx = -1;
        for (int i = 0; i < soLuong; i++) {
            if (dsChiTiet[i] != null && 
                dsChiTiet[i].getMaPP().equalsIgnoreCase(maPP) && 
                dsChiTiet[i].getMaSach().equalsIgnoreCase(maSach)) {
                idx = i;
                break;
            }
        }
        if (idx == -1) return false;
        for (int i = idx; i < soLuong - 1; i++) {
            dsChiTiet[i] = dsChiTiet[i + 1];
        }
        dsChiTiet[--soLuong] = null;
        return true;
    }    
public void Xoa(Scanner sc) {
        System.out.print("Nhap ma phieu phat can xoa: ");
        String maPP = sc.nextLine().trim();
        System.out.print("Nhap ma sach can xoa: ");
        String maSach = sc.nextLine().trim();
        if (Xoa(maPP, maSach)) {
            System.out.println("Xoa thanh cong!");
        } else {
            System.out.println("Khong tim thay chi tiet de xoa!");
        }
    }



    // ==========================================
    // SUA / EDIT
    // ==========================================
        public void Sua(Scanner sc, ListQuyDinhPhat listQD, DSBook dsBook) {
        System.out.print("Nhap ma phieu phat can sua: ");
        String maPP = sc.nextLine().trim();
        System.out.print("Nhap ma sach can sua: ");
        String maSach = sc.nextLine().trim();

        int idx = -1;
        for (int i = 0; i < soLuong; i++) {
            if (dsChiTiet[i] != null && 
                dsChiTiet[i].getMaPP().equalsIgnoreCase(maPP) && 
                dsChiTiet[i].getMaSach().equalsIgnoreCase(maSach)) {
                idx = i;
                break;
            }
        }
        if (idx == -1) {
            System.out.println("Khong tim thay chi tiet de sua!");
            return;
        }
        System.out.println("Nhap thong tin moi:");
        ChiTietPhieuPhat ctMoi = new ChiTietPhieuPhat();
        ctMoi.Input(maPP, listQD, dsBook, sc);
        dsChiTiet[idx] = ctMoi;
        System.out.println("Sua thanh cong!");
    }




    public double Thongke_tienPhat_theo_MaPhieuPhat(String maPP){
        double tongTien = 0;
        ChiTietPhieuPhat[] ds = Tim(maPP);
        if (ds != null) {
            for (int i = 0; i < ds.length; i++) {
                if (ds[i] != null) {
                    tongTien += ds[i].getTienPhat();
                }
            }
        }
        return tongTien;
    }
    public double Thongke_tienPhat_theo_MaPhieuPhat(Scanner sc){
        System.out.print("Nhap ma phieu phat can thong ke tong tien: ");
        String maPP = sc.nextLine().trim();
        double tong = Thongke_tienPhat_theo_MaPhieuPhat(maPP);
        System.out.println("Tong tien phat cho ma PP [" + maPP + "] la: " + tong);
        return tong;
    }
    














        public void ghiFile(String filename) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            pw.println(soLuong);
            for (int i = 0; i < soLuong; i++) {
                if (dsChiTiet[i] != null) {
                    ChiTietPhieuPhat ct = dsChiTiet[i];
                    pw.println(ct.getMaPP() + ";" + ct.getMaSach() + ";" + ct.getTienPhat() + ";" + ct.getMaLD());
                }
            }
            System.out.println("-> Ghi file chi tiet thanh cong!");
        } catch (IOException e) {
            System.out.println("-> Loi ghi file: " + e.getMessage());
        }
    }

    public void docFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine();
            if (line == null) return;
            soLuong = 0;
            int sl = Integer.parseInt(line.trim());
            dsChiTiet = new ChiTietPhieuPhat[Math.max(1, sl)];
            for (int i = 0; i < sl; i++) {
                String fileLine = br.readLine();
                if (fileLine == null) break;
                String[] p = fileLine.split(";", -1);
                if (p.length >= 4) {
                    ChiTietPhieuPhat ct = new ChiTietPhieuPhat(p[0], p[1], Double.parseDouble(p[2]), p[3]);
                    dsChiTiet[soLuong++] = ct;
                }
            }
            System.out.println("-> Doc file chi tiet thanh cong!");
        } catch (IOException e) {
            System.out.println("-> Loi doc file (hoac file chua ton tai): " + e.getMessage());
        }
    }

}




