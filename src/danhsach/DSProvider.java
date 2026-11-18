package danhsach;
import model.Provider;
import java.util.Arrays;
import java.util.Scanner;

public class DSProvider{
    //1.Atrribute
    private int n;
    private Provider[] dsProvider;
    Scanner sc= new Scanner(System.in);


    //2.Constructor
    public DSProvider() {
        this.dsProvider = new Provider[100];
        this.n = 0;}
    public DSProvider(Provider[] dsProvider, int n) {
        this.dsProvider = dsProvider;
        this.n = n;}
    
    //3.Get_Set
    public int getN() {return n;}
    public void setN(int n){this.n=n;}
    public Provider getProvider(int i) {return dsProvider[i];}
   
//-------------------METHOD-----------------------------
//0.INPUT và SHOW
// ```
// Input()
// Show()
// ```

public void Input(){
        System.out.print("Nhap so luong NCC: ");
        int sl;
        try {
            this.n = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("So luong khong hop le. Dat ve 0.");
            this.n = 0;
        }
        this.dsProvider= new Provider[n];
        System.out.println("Tien hanh nhap danh sach Provider");

        for (int i = 0; i < n; i++) {
            dsProvider[i] = new Provider();
            dsProvider[i].Input(sc, this);
            this.n++;
        }
    }

    public void Output() {
        System.out.println("----------------------------------------------DANH SACH NHA CUNG CAP-------------------------------------------");
        System.out.println("---------------------------------------------------------------------------------------------------------------");
        if (n == 0) {
            System.out.println("DANH SACH RONG");
            System.out.println("---------------------------------------------------------------------------------------------------------------");
            return;
        }

        System.out.printf("|%-10S|%-30s|%-55s|%-15s|\n", "MA NCC", "TEN NCC", "DIA CHI", "SO_DIEN_THOAI");
        for (int i = 0; i < n; i++) {
            dsProvider[i].Output();
        }
        System.out.println("---------------------------------------------------------------------------------------------------------------");
    }

    public void Show() {
        Output();
    }

    public void Them() {
        if (n == dsProvider.length) {
            dsProvider = Arrays.copyOf(dsProvider, dsProvider.length + 1);
        }

        dsProvider[n] = new Provider();
        dsProvider[n].Input(sc, this);
        n++;
    }

    public int Tim(String maNCC) {
        for (int i = 0; i < n; i++) {
            if (dsProvider[i].getMaNCC().equalsIgnoreCase(maNCC)) {
                return i;
            }
        }
        return -1;
    }

    public void TimMaNCC() {
        System.out.print("Nhap ma nha cung cap can tim: ");
        String maNCC = sc.nextLine();

        int kq = Tim(maNCC);
        if (kq != -1) {
            System.out.println("---------------------------------------------------------------------------------------------------------------");
            System.out.println("                           THONG TIN NHA CUNG CAP CO MA NCC:" + maNCC);
            System.out.println("---------------------------------------------------------------------------------------------------------------");
            System.out.printf("|%-10S|%-30s|%-55s|%-15s|\n", "MA NCC", "TEN NCC", "DIA CHI", "SO_DIEN_THOAI");
            dsProvider[kq].Output();
        } else {
            System.out.println("KHONG TON TAI NCC co ma NCC:" + maNCC);
        }
    }

    public void TimTenNCC() {
        System.out.print("Nhap ten NCC can tim: ");
        String ten = sc.nextLine().trim();
        boolean found = false;

        for (int i = 0; i < n; i++) {
            if (dsProvider[i].getTenNCC().toLowerCase().contains(ten.toLowerCase())) {
                if (!found) {
                    System.out.println("---------------------------------------------------------------------------------------------------------------");
                    System.out.println("                                      DANH SACH CO TEN:" + ten);
                    System.out.println("---------------------------------------------------------------------------------------------------------------");
                    System.out.printf("|%-10S|%-30s|%-55s|%-15s|\n", "MA NCC", "TEN NCC", "DIA CHI", "SO_DIEN_THOAI");
                    found = true;
                }
                dsProvider[i].Output();
            }
        }

        if (!found) {
            System.out.println("Khong tim thay nha cung cap co ten " + ten);
        }
    }

    public void TimSoDienThoai() {
        System.out.print("Nhap so dien thoai can tim: ");
        String soDT = sc.nextLine().trim();
        boolean found = false;

        for (int i = 0; i < n; i++) {
            if (dsProvider[i].getSoDienThoai().equals(soDT)) {
                if (!found) {
                    System.out.println("---------------------------------------------------------------------------------------------------------------");
                    System.out.println("                               NHA CUNG CAP CO SO DIEN THOAI:" + soDT);
                    System.out.println("---------------------------------------------------------------------------------------------------------------");
                    System.out.printf("|%-10S|%-30s|%-55s|%-15s|\n", "MA NCC", "TEN NCC", "DIA CHI", "SO_DIEN_THOAI");
                    found = true;
                }
                dsProvider[i].Output();
            }
        }

        if (!found) {
            System.out.println("Khong tim thay nha cung cap co so dien thoai:" + soDT);
        }
    }

    public void TimDiaChi() {
        System.out.print("Nhap dia chi can tim: ");
        String diaChi = sc.nextLine().trim();
        boolean found = false;

        for (int i = 0; i < n; i++) {
            if (dsProvider[i].getDiaChi().toLowerCase().contains(diaChi.toLowerCase())) {
                if (!found) {
                    System.out.println("---------------------------------------------------------------------------------------------------------------");
                    System.out.println("                                  DANH SACH CO DIA CHI:" + diaChi);
                    System.out.println("---------------------------------------------------------------------------------------------------------------");
                    System.out.printf("|%-10S|%-30s|%-55s|%-15s|\n", "MA NCC", "TEN NCC", "DIA CHI", "SO_DIEN_THOAI");
                    found = true;
                }
                dsProvider[i].Output();
            }
        }

        if (!found) {
            System.out.println("Khong tim thay nha cung cap co dia chi:" + diaChi);
        }
    }

    private void Xoa(int vt) {
        for (int i = vt; i < n - 1; i++) {
            dsProvider[i] = dsProvider[i + 1];
        }
        dsProvider[n - 1] = null;
        n--;
    }

    public void XoaMaNCC() {
        System.out.println("TIEN HANH XOA DOI TUONG THEO MA NCC");
        System.out.print("Vui long NHAP MA NCC can xoa:");
        String maNCC = sc.nextLine();

        int kq = Tim(maNCC);
        if (kq != -1) {
            Xoa(kq);
            System.out.println("XOA THANH CONG");
        } else {
            System.out.println("KHONG THE XOA VI MA NCC: " + maNCC + " KHONG TON TAI");
        }
    }

    public void SuaProvider() {
        System.out.print("Nhap ma NCC can sua: ");
        String ma = sc.nextLine().trim();

        int vt = Tim(ma);
        if (vt == -1) {
            System.out.println("Khong tim thay NCC!");
            return;
        }

        int luaChon;
        System.out.println("TIEN HANH SUA THONG TIN");
        do {
            System.out.println("\n===== MENU SUA NCC =====");
            System.out.println("1. Sua ten NCC");
            System.out.println("2. Sua dia chi");
            System.out.println("3. Sua so dien thoai");
            System.out.println("0. Thoat");
            System.out.print("Nhap lua chon: ");

            try {
                luaChon = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                luaChon = -1;
            }

            switch (luaChon) {
                case 0 -> System.out.println("Ket thuc SUA provider");
                case 1 -> {
                    System.out.print("Nhap ten moi cua NCC :");
                    dsProvider[vt].setTenNCC(sc.nextLine());
                    System.out.println("Sua ten thanh cong");
                }
                case 2 -> {
                    System.out.print("Nhap dia chi moi:");
                    dsProvider[vt].setDicChi(sc.nextLine());
                    System.out.println("Sua dia chi thanh cong");
                }
                case 3 -> {
                    System.out.print("Nhap so dien thoai moi: ");
                    dsProvider[vt].setSoDienThoai(sc.nextLine());
                    System.out.println("Sua so dien thoai thanh cong");
                }
                default -> System.out.println("khong hop le\nVui long nhap lai lua chon theo menu");
            }
        } while (luaChon != 0);
    }

    public void ThongkeProvider_theo_tongNCC() {
        System.out.println("THONG KE SO LUONG NHA CUNG CAP");
        System.out.println("TONG SO NHA CUNG CAP: " + n);
        Output();
    }

    public void Thongke_diaChi_theo_NCC() {
        System.out.println("THONG KE NHA CUNG CAP THEO DIA CHI");
        if (n == 0) {
            System.out.println("DANH SACH NHA CUNG CAP RONG");
            return;
        }

        for (int i = 0; i < n; i++) {
            String diaChi = dsProvider[i].getDiaChi();
            boolean daThongKe = false;

            for (int j = 0; j < i; j++) {
                if (dsProvider[j].getDiaChi().equalsIgnoreCase(diaChi)) {
                    daThongKe = true;
                    break;
                }
            }

            if (daThongKe) {
                continue;
            }

            int dem = 0;
            System.out.println("THONG KE THEO DIA CHI :" + diaChi);
            for (int j = i; j < n; j++) {
                if (dsProvider[j].getDiaChi().equalsIgnoreCase(diaChi)) {
                    dem++;
                    dsProvider[j].Output();
                }
            }
            System.out.println("SO LUONG NCC CO DIA CHI: " + diaChi + ":" + dem);
        }
    }
}


