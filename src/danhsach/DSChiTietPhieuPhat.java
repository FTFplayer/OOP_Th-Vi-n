package danhsach;

import java.util.Arrays;
import java.util.Scanner;
import model.ChiTietPhieuPhat;

public class DSChiTietPhieuPhat {
  
    private ChiTietPhieuPhat[] dsChiTiet;
    private int n;
    Scanner sc= new Scanner(System.in);



     public DSChiTietPhieuPhat() {
        this.dsChiTiet = new ChiTietPhieuPhat[100];
        this.n = 0;
    }
    public DSChiTietPhieuPhat(int length) {
        n=0;
        dsChiTiet= new ChiTietPhieuPhat[length];
    }
    public DSChiTietPhieuPhat(ChiTietPhieuPhat[] dsChiTiet, int soLuong) {
        this.dsChiTiet = dsChiTiet;
        this.n = soLuong;
    }
    


   

    public ChiTietPhieuPhat getChiTiet(int index) {
        if (index < 0 || index >= n) {
            return null;
        }
        return dsChiTiet[index];
    }
    public int getSoLuong() {return n;}
  





// ==========================================
    // IN / OUT / HIỂN THỊ
    // ==========================================
    // Khong tham so (In toan bo danh sach)
    public void Input(String maPPCurrent, DSQuyDinhPhat listQD,DSBook dsBook) {
        System.out.print("Nhap so luong sach vi pham: ");
        int sl = Integer.parseInt(sc.nextLine());
        dsChiTiet= new ChiTietPhieuPhat[sl];
        this.n=sl;
        for ( int i=0; i<sl; i++){
            System.out.println("  Sach vi pham thu " + (i + 1) + ":");
            dsChiTiet[i] = new ChiTietPhieuPhat();
            dsChiTiet[i].Input(maPPCurrent, listQD, dsBook);
        }
    }
    public void Output() {
        System.out.println("DANH SACH CHI TIET PHIEU PHAT:");
        System.out.printf("| %-8s | %-10s | %-15s | %-8s |\n","MA PP", "MA SACH", "TIEN PHAT", "MA LYDO");
        for (int i = 0; i < n; i++) {
            if (dsChiTiet[i] != null) {
                dsChiTiet[i].Output();
            }
        }

    }


    public void Output(String maPP){
        boolean found = false;
        System.out.println("DANH SACH CHI TIET THEO MA PP: " + maPP);
        System.out.printf("| %-8s | %-10s | %-15s | %-8s |\n","MA PP", "MA SACH", "TIEN PHAT", "MA LYDO");
        for (int i=0; i<n; i++) {
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
   public void Them( DSQuyDinhPhat listQD, DSBook dsBook,String maPP) {
        ChiTietPhieuPhat ct = new ChiTietPhieuPhat();
        ct.Input(maPP, listQD, dsBook);
        Them(ct);
        System.out.println("Them thanh cong!");
    }
    public void Them(ChiTietPhieuPhat ct) {
        if (n >= dsChiTiet.length) {
            dsChiTiet = Arrays.copyOf(dsChiTiet, dsChiTiet.length + 1);
        }
        dsChiTiet[n++] = ct;
    }

   // ==========================================
    // TIM KIEM 
    // TRA VE DANH SACH CAC CHI TIET TRUNG MA PP
    //IN RA DANH SACH CHI TIẾT
    // ==========================================

    public ChiTietPhieuPhat[] Tim(String maPP) {
        // Dem xem co bao nhieu phan tu trung khop truoc
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (dsChiTiet[i] != null && dsChiTiet[i].getMaPP().equalsIgnoreCase(maPP)) {
                count++;
            }
        }

        if (count == 0) return null; // Khong tim thay

        // Tao mang ket qua chua dung so luong tim thay
        ChiTietPhieuPhat[] ketQua = new ChiTietPhieuPhat[count];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            if (dsChiTiet[i] != null && dsChiTiet[i].getMaPP().equalsIgnoreCase(maPP)) {
                ketQua[idx++] = dsChiTiet[i];
            }
        }
        return ketQua;}
    public void Tim() {
        System.out.print("Nhap ma phieu phat can tim: ");
        String maPP = sc.nextLine().trim();
        Output(maPP);}
       
// ==========================================
    // XOA / DELETE
    // ==========================================
    // Co tham so (xoa dua tren maPP va maSach)
public boolean Xoa(String maPP, String maSach) {
        int idx = -1;
        for (int i = 0; i < n; i++) {
            if (dsChiTiet[i] != null && 
                dsChiTiet[i].getMaPP().equalsIgnoreCase(maPP) && 
                dsChiTiet[i].getMaSach().equalsIgnoreCase(maSach)) {
                idx = i;
                break;
            }
        }
        if (idx == -1) return false;
        for (int i = idx; i < n - 1; i++) {
            dsChiTiet[i] = dsChiTiet[i + 1];
        }
        dsChiTiet[--n] = null;
        return true;
    }    
public void Xoa() {
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
        public void Sua( DSQuyDinhPhat listQD, DSBook dsBook) {
        System.out.print("Nhap ma phieu phat can sua: ");
        String maPP = sc.nextLine().trim();
        System.out.print("Nhap ma sach can sua: ");
        String maSach = sc.nextLine().trim();

        int idx = -1;
        for (int i = 0; i < n; i++) {
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
        ctMoi.Input(maPP, listQD, dsBook);
        dsChiTiet[idx] = ctMoi;
        System.out.println("Sua thanh cong!");
    }




    public double Thongke_tienPhat_theo_MaPhieuPhat(String maPP){
        double tongTien = 0;
        ChiTietPhieuPhat[] ds = Tim(maPP);
        if (ds != null) {
            for ( ChiTietPhieuPhat i:ds) {
                if (i != null) {
                    tongTien += i.getTienPhat();
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
    





    //update Tổng tiền 1 PP theo mã PP
    public double UpdateTongTienPhat(String maPP)
    {   double tong=0;
        for ( ChiTietPhieuPhat ct: dsChiTiet)
            if (ct.getMaPP().equalsIgnoreCase(maPP))
                 tong+= ct.getTienPhat();
        return tong;        
    }

}




