package danhsach;

import model.PhieuPhat;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

public class DSPhieuPhat {
    private int n;
    private PhieuPhat[] dsPhieuPhat;
    Scanner sc= new Scanner(System.in);
    

    // 1.Constructor
    public DSPhieuPhat(PhieuPhat[] dsPhieuPhat,int n) { 
        this.dsPhieuPhat= dsPhieuPhat;
         this.n=n;}
    public DSPhieuPhat() {
        dsPhieuPhat= new PhieuPhat[100];
         this.n=0;}
         
    //2.Getset
    public int getN() {return n;}
    public void setSoLuong(int n){this.n= n;}
    public PhieuPhat getPhieuPhat(int vt){if(vt>-1 && vt < n) return dsPhieuPhat[vt]; return null;}
    public PhieuPhat[] getDanhSachPP(){ return dsPhieuPhat;}
    

    // ===========================================
    // 2. INPUT
    // ===========================================
    public void Input(DSPhieuMuon dspm) {
        System.out.print("Nhap so luong phieu phat: ");
        int sl = Integer.parseInt(sc.nextLine().trim());
        dsPhieuPhat = new PhieuPhat[sl];
        this.n = 0;
        for (int i = 0; i < n; i++) {
            System.out.println("Nhap phieu phat thu " + (i + 1) + ":");
            Them(dspm);
        }
    }

    // ==========================================
    // 3. ADD
    // ==========================================
    public void Them(DSPhieuMuon dspm){
        System.out.println("Tien hanh nhap thong tin phieu phat");
        PhieuPhat pp= new PhieuPhat();
        pp.Input(this, dspm);
        Them(pp);
    
    }
    public void Them(PhieuPhat pp){
        if (n >= dsPhieuPhat.length) {
             dsPhieuPhat = Arrays.copyOf(dsPhieuPhat, dsPhieuPhat.length + 1);
        dsPhieuPhat[n++]= pp;   
    }}

    // ==========================================
    // 4.. OUTPUT
    // ==========================================

    //Xuất tất cả phiếu phạt trong danh sách
    public void Output() {
        System.out.println("DANH SACH PHIEU PHAT");
        System.out.printf("|%-12s|%-14s|%-14s|%-14s|%n", "MA PP", "MA PM", "NGAY PHAT", "SO TIEN PHAT");
        for (int i = 0; i < n; i++) {
            if (dsPhieuPhat[i] != null) {
                dsPhieuPhat[i].Output();
            }
        }
        if (n == 0) {
            System.out.println("DANH SACH RONG");
        }
    }


    
    // ==========================================
    // 4. TIM KIEM
    //  4.1|- theo mã pp
    //  4.2|- theo mã pm(fk)
    // ==========================================

//4.1 

   //return vị trí
public int Timvitri(String maPP){
    for (int i =0; i<n; i++)
        if (dsPhieuPhat[i].getMaPhieuPhat().equalsIgnoreCase(maPP))
            return i;
    return -1;    
}
    //return 1 object PhieuPhat
        public PhieuPhat TimtheoMaPP(String maPP) {
            for (int i = 0; i < n; i++) {
               if (dsPhieuPhat[i] != null && dsPhieuPhat[i].getMaPP().equalsIgnoreCase(maPP)) {
                   return dsPhieuPhat[i];}}
            return null;}
   // in ra Phiếuphat
        public void TimtheoMaPP() {
        System.out.print("Nhap ma phieu phat can tim: ");
        String mapp=sc.nextLine();
        int vt=Timvitri(mapp);
        if (vt== -1){
            System.out.println("khong tim thay phieu phat ma: "+mapp);
        return;}
        System.out.printf("|%-12s|%-14s|%-14s|%-14s|%n", "MA PP", "MA PM", "NGAY PHAT", "SO TIEN PHAT");
        dsPhieuPhat[vt].Output();
        }

//4.2.theo mã Phiếu mượn 
    // tham chiếu mã ,return ds PhieuPhat
    public PhieuPhat[] TimtheoMaPM(String maPM) {
        int count = 0;
        for (int i = 0; i < n; i++)
            if (dsPhieuPhat[i].getMaPM().equalsIgnoreCase(maPM))
                count++;
        PhieuPhat[] ds = new PhieuPhat[count];
        int index = 0;

        for (int i = 0; i < n; i++)
            if (dsPhieuPhat[i].getMaPM().equalsIgnoreCase(maPM))
                ds[index++] = dsPhieuPhat[i];

        return ds;}
    
    // nhập mã pm , in ra danh sách
    public void TimtheoMaPM(Scanner sc) {
        System.out.println("Nhap ma phieu muon:");
        String maPM = sc.nextLine();
        PhieuPhat[] ds = TimtheoMaPM(maPM);
        if (ds.length == 0) {
            System.out.println("Ma PM:" + maPM + "khong co phieu phat");
            return;
        }
        System.out.printf("|%-12s|%-14s|%-14s|%-14s|%n", "MA PP", "MA PM", "NGAY PHAT", "SO TIEN PHAT");
        for (int i = 0; i < ds.length; i++) {

            ds[i].Output();
        }

    }

    // 5. XOA / DELETE
    // ==========================================
    public boolean Xoa(String maPP) {
        int viTri = Timvitri(maPP);
        if (viTri == -1)
            return false;

        for (int i = viTri; i < n - 1; i++) {
            dsPhieuPhat[i] = dsPhieuPhat[i + 1];
        }
        dsPhieuPhat[--n] = null;
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
    // 6. EDIT
    // ============================================
    public void Sua() {
        System.out.print("Nhap ma phieu phat can sua: ");
        String maPP = sc.nextLine().trim();
        int viTri = Timvitri(maPP);
        if (viTri == -1) {
            System.out.println("Khong tim thay phieu phat de sua!");
            return;
        }
        int chon;
       do {
            PhieuPhat p = dsPhieuPhat[viTri];
            System.out.println();
            System.out.println("+-------------------------------------------+");
            System.out.println("|         MENU SUA THONG TIN PHIEU PHAT     |");
            System.out.println("+-------------------------------------------+");
            System.out.println("| 1) Sua ma phieu muon.                     |");
            System.out.println("| 2) Sua ngay lap phieu phat.               |");
            System.out.println("| 3) Sua tong tien phat.                    |");
            System.out.println("| 0) Thoat.                                 |");
            System.out.println("+-------------------------------------------+");
            try {
                System.out.print("Chon:");
                chon= Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                chon=-1;
            }

            switch(chon){
                case 1:
                     System.out.print("Nhap ma phieu muon moi (FK): ");
                     p.setMaPM(sc.nextLine().trim());
                     System.out.println("Da sua ma phieu muon.");
                     break;
                case 2:
                     System.out.print("Nhap ngay phat moi: ");
                     p.setNgayPhat(LocalDate.parse(sc.nextLine().trim()));
                     System.out.println("Da sua ngay tao phieu phat.");
                     break;  
            }}
            while(chon !=0);

        System.out.println("Sua phieu phat thanh cong!");
    }

   
    // ==========================================
    // 7. THONG KE
    // ==========================================
    // public void ThongKetheoMaPM() {
    //     double tongTien = 0;
    //     if (dsChiTiet != null) {
    //         for (int i = 0; i < n; i++) {
    //             if (dsPhieuPhat[i] != null) {
    //                 dsPhieuPhat[i].capNhatTongTienPhat(dsChiTiet);
    //                 tongTien += dsPhieuPhat[i].getSoTienPhat();
    //             }
    //         }
    //     } else {
    //         for (int i = 0; i < n; i++) {
    //             if (dsPhieuPhat[i] != null) {
    //                 tongTien += dsPhieuPhat[i].getSoTienPhat();
    //             }
    //         }
    //     }
    //     System.out.println("Tong so phieu phat: " + n);
    //     System.out.printf("Tong so tien phat: %.2f%n", tongTien);
    // }

    // ==========================================
    // DOC / GHI FILE (Do Menu goi)
    // ==========================================
   
    public void ThongKePPtheoMaPM(String maPM){
        PhieuPhat[] ds=TimtheoMaPM(maPM);
        if(ds.length == 0){
            System.out.println("Tong so phieu phat theo ma phieu muon " + maPM + ": 0");
            return;}
        System.out.println("THONG KE PHIEU PHAT CO MA PM:"+maPM);  
        for(PhieuPhat pp: ds)
            pp.Output();

        
    }
}
