package danhsach;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Scanner;
import model.PhieuPhat;

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
         
    //2.GETSET
    public int getN() {return n;}
    public void setN(int n){this.n= n;}
    public PhieuPhat getPhieuPhat(int vt){if(vt>-1 && vt < n) return this.dsPhieuPhat[vt]; return null;}
    public PhieuPhat[] getDanhSachPP(){ return this.dsPhieuPhat;}
    

  
    // 2. INPUT
    // ===========================================
    public void Input(DSPhieuMuon dspm) {
        System.out.print("Nhap so luong phieu phat: ");
        int sl = Integer.parseInt(sc.nextLine().trim());
        dsPhieuPhat = new PhieuPhat[sl];
        this.n = 0;
        for (int i = 0; i < sl; i++) {
            System.out.println("Nhap phieu phat thu " + (i + 1) + ":");
            Them(dspm);
        }
    }

  
    // 3. ADD
    // ==========================================
    public void Them(DSPhieuMuon dspm){
        System.out.println("Tien hanh nhap thong tin phieu phat");
        PhieuPhat pp= new PhieuPhat();
        pp.Input(this, dspm);
        Them(pp);
    
    }
    public void Them(PhieuPhat pp){
        if (n >= dsPhieuPhat.length) 
             dsPhieuPhat = Arrays.copyOf(dsPhieuPhat, dsPhieuPhat.length + 1);
        dsPhieuPhat[n++]= pp;   
    }

   
    // 4.. OUTPUT
    // ==========================================

    //Xuất tất cả phiếu phạt trong danh sách
    public void Output() {
         System.out.println("+------------------------------------------------------------------+");
        System.out.println("DANH SACH PHIEU PHAT");
        this.PrintHeader();
        for (int i = 0; i < n; i++) {
            if (dsPhieuPhat[i] != null) {
                dsPhieuPhat[i].Output();
            }
        }
        if (n == 0) {
            System.out.println("DANH SACH RONG");
        }
        System.out.println("+------------------------------------------------------------------+");
    }


    
    // 4. TÌM KIẾM
    //      4.1|- theo mã pp
    //      4.2|- theo mã pm(fk)
    //      4.3|-theo ngày phạt
    // ==========================================

          //4.1 theo mã phiếu phạt

   //return vị trí
     public int Timvitri(String maPP){
    for (int i =0; i<n; i++)
        if (dsPhieuPhat[i].getMaPP().equalsIgnoreCase(maPP))
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
    public void TimtheoMaPM() {
        System.out.println("Nhap ma phieu muon:");
        String maPM = sc.nextLine();
        PhieuPhat[] ds = TimtheoMaPM(maPM);
        if (ds.length == 0) {
            System.out.println("Ma PM:" + maPM + "khong co phieu phat");
            return;
        }
        System.out.println("      DANH SACH PHIEU PHAT( MA PHIEU MUON: "+maPM+" )");
        this.PrintHeader();
        for (PhieuPhat pp: ds) {
                pp.Output();
        }
        System.out.println("+------------------------------------------------------------------+");

    }
          
        //4.3 Theo ngày phạt:
        //có tham số
    public void TimTheoNgayPhat(LocalDate ngay){
    boolean found = false;
    for(int i = 0; i < this.n; i++){
        if(dsPhieuPhat[i].getNgayPhat().equals(ngay)){
            dsPhieuPhat[i].Output();
            found = true;
        }
    }
    if(!found)
        System.out.println("Khong tim thay!");
    }

        //ko tham số
    public void TimTheoNgayPhat(){

    DateTimeFormatter f =DateTimeFormatter.ofPattern("dd/MM/yyyy");
    try{
        System.out.print("Nhap ngay phat: ");
        LocalDate ngay =LocalDate.parse(sc.nextLine().trim(),f);
        TimTheoNgayPhat(ngay);
    }catch(DateTimeParseException e){
        System.out.println("Ngay khong hop le!");
    }
}
    // 5. DELETE
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

    public void Xoa() {
        System.out.print("Nhap ma phieu phat can xoa: ");
        String maPP = sc.nextLine().trim();
        if (Xoa(maPP)) {
            System.out.println("Xoa phieu phat thanh cong!");
        } else {
            System.out.println("Khong tim thay phieu phat de xoa!");
        }
    }

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
                case 1->{
                     System.out.print("Nhap ma phieu muon moi (FK): ");
                     p.setMaPM(sc.nextLine().trim());
                     System.out.println("Da sua ma phieu muon.");}
                   
                case 2->{
                     DateTimeFormatter f =DateTimeFormatter.ofPattern("dd/MM/yyyy");
                     System.out.print("Nhap ngay phat moi: ");
                     p.setNgayPhat(LocalDate.parse(sc.nextLine().trim(),f));
                     System.out.println("Da sua ngay tao phieu phat.");}  
            }}
            while(chon !=0);

        System.out.println("Sua phieu phat thanh cong!");
    }


    // 7. THONG KE
    //===========================================
  
   //0.Thống kê theo mã phiếu mượn
    public void ThongKePPtheoMaPM(String maPM){
        PhieuPhat[] ds=TimtheoMaPM(maPM);
        if(ds.length == 0){
            System.out.println("Tong so phieu phat theo ma phieu muon " + maPM + ": 0");
            return;}
        System.out.println("+------------------------------------------------------------------+");
        System.out.println("        THONG KE PHIEU PHAT CO MA PM:"+maPM);  
        this.PrintHeader();
        for(PhieuPhat pp: ds)
            pp.Output();
        System.out.println("+------------------------------------------------------------------+");
      }
    public void ThongKePPtheoMaPM(){
            System.out.print("Nhap ma phieu muon:");
            String maPM= sc.nextLine();
             ThongKePPtheoMaPM(maPM);
     }  
    //1.Thống kê tổng số phiếu phạt
    public void ThongKeTongSoPP() {
    System.out.println("Tong so phieu phat: " + n);}
    //2.Thống kê tổng số tiền phạt:
    public double TongTienPhat() {
    double tong = 0;

    for(int i = 0; i < n; i++) {
        tong += dsPhieuPhat[i].getTongSoTienPhat();
    }
    return tong;}
    public void ThongKeTongTienPhat() {
    System.out.println("Tong tien phat: " + TongTienPhat());}
    //3.Phiếu phạt có tiền phạt cao nhất
    public PhieuPhat TimPhieuPhatMax() {
        if(n == 0)
        return null;

    PhieuPhat max = dsPhieuPhat[0];

    for(int i = 1; i < n; i++) {
        if(dsPhieuPhat[i].getTongSoTienPhat()
                > max.getTongSoTienPhat()) {
            max = dsPhieuPhat[i];
        }
    }

    return max;
}
   public void ThongKePhieuPhatMax(){
        PhieuPhat max=TimPhieuPhatMax();
        if(max == null){
            System.out.println("Danh sach phieu phat rong !!");
            return;}
        System.out.println("+------------------------------------------------------------------+");
        System.out.println("|                      PHIEU PHAT: TIEN PHẠT LỚN NHẤT              |");    
        this.PrintHeader();    
        max.Output();    
        System.out.println("+------------------------------------------------------------------+");

   }
     //4. Thống kê theo khoảng ngày
    public void ThongKeTheoKhoangNgay(LocalDate tuNgay, LocalDate denNgay) {

    boolean found = false;

    System.out.println("THONG KE PHIEU PHAT TU "
            + tuNgay + " DEN " + denNgay);

    System.out.printf("|%-12s|%-14s|%-14s|%-14s|%n",
            "MA PP", "MA PM", "NGAY PHAT", "SO TIEN PHAT");

    for(int i = 0; i < n; i++) {

        LocalDate ngay = dsPhieuPhat[i].getNgayPhat();

        if((ngay.isEqual(tuNgay) || ngay.isAfter(tuNgay))
        && (ngay.isEqual(denNgay) || ngay.isBefore(denNgay))) {

            dsPhieuPhat[i].Output();
            found = true;
        }
    }

    if(!found)
        System.out.println("Khong co phieu phat nao trong khoang nay!");
    }


    public void ThongKeTheoKhoangNgay() {

    DateTimeFormatter f =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    try {

        System.out.print("Nhap tu ngay: ");
        LocalDate tuNgay =
                LocalDate.parse(sc.nextLine().trim(), f);

        System.out.print("Nhap den ngay: ");
        LocalDate denNgay =
                LocalDate.parse(sc.nextLine().trim(), f);

        ThongKeTheoKhoangNgay(tuNgay, denNgay);

    } catch(DateTimeParseException e) {

        System.out.println("Ngay khong hop le!");
    }
    }

    //5.Thống kê tổng hợp
    public void ThongKeTongHop(){
    System.out.println("+-----------------------------------------+");
    System.out.println("|      THONG KE PHIEU PHAT    v           |");
    System.out.println("+-----------------------------------------+");
    System.out.println("| Tong so phieu phat : " + n);
    System.out.println("| Tong tien phat     : " + TongTienPhat());
    System.out.println("+-----------------------------------------+");
}



     


  
    //8.READ/WRITEFILE

    public void ReadFile() {
    int count = 0;

    try (Scanner fileScanner =
            new Scanner(new FileInputStream("data/PhieuPhat.txt"))) {

        DateTimeFormatter f =
                DateTimeFormatter.ofPattern("dd/MM/yyyy");

        this.dsPhieuPhat = new PhieuPhat[0];

        while (fileScanner.hasNextLine()) {
            String[] data = fileScanner.nextLine().trim().split(",");

            if (data.length >= 4) {
                dsPhieuPhat = Arrays.copyOf(dsPhieuPhat, count + 1);
                dsPhieuPhat[count] = new PhieuPhat();

                dsPhieuPhat[count].setMaPP(data[0].trim());
                dsPhieuPhat[count].setMaPM(data[1].trim());

                try {
                    dsPhieuPhat[count].setNgayPhat(
                            LocalDate.parse(data[2].trim(), f));
                } catch (DateTimeParseException e) {
                    dsPhieuPhat[count].setNgayPhat(null);
                }

                dsPhieuPhat[count].setTongSoTienPhat(
                        Double.parseDouble(data[3].trim()));

                count++;
            }
        }

        this.n = count;

    } catch (FileNotFoundException e) {
        System.out.println("Khong tim thay file!");
    }
}

public void WriteFile() {

    DateTimeFormatter f =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");
    try (PrintWriter writer = new PrintWriter(new FileOutputStream("data/PhieuPhat.txt"))) {  
           for (int i = 0; i < n; i++) {
            
            String ngayPhat =
                    dsPhieuPhat[i].getNgayPhat() != null
                    ? dsPhieuPhat[i]
                            .getNgayPhat()
                            .format(f)
                    : "";

            writer.println(
                    dsPhieuPhat[i].getMaPP() + ","
                    + dsPhieuPhat[i].getMaPM() + ","
                    + ngayPhat + ","
                    + dsPhieuPhat[i].getTongSoTienPhat()
            );
    }

} catch (FileNotFoundException e) {
    System.out.println("Khong tim thay file!");
}
}



 //9.Format phieu phạt:
   public void PrintHeader(){
    System.out.println("+------------------------------------------------------------------+");
    System.out.printf("| %-12s | %-14s | %-14s | %-14s |%n",
        "MA PP", "MA PM", "NGAY PHAT", "SO TIEN PHAT");
    System.out.println("+------------------------------------------------------------------+");
   }
   


}
