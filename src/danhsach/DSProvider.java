package danhsach;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import model.Provider;

public class DSProvider implements DanhSach {
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
            dsProvider[i].Input(this);
            this.n++;
        }
    }

    public void PrintHeader() {
        System.out.format("+----------+------------------------------+-------------------------------------------------------+---------------+%n");
        System.out.printf("|%-10s|%-30s|%-55s|%-15s|%n",
                "Ma NCC", "Ten NCC", "Dia Chi", "SDT");
        System.out.format("+----------+------------------------------+-------------------------------------------------------+---------------+%n");
    }

    public void Output() {
        if (n == 0) {
            System.out.println("Danh sach nha cung cap dang trong!");
            return;
        }

        PrintHeader();
        for (int i = 0; i < n; i++) {
            if (dsProvider[i] != null) {
                dsProvider[i].Output();
            }
        }
        System.out.format("+----------+------------------------------+-------------------------------------------------------+---------------+%n");
    }

    public void Show() {
        Output();
    }

    public void Them() {
        if (n == dsProvider.length) {
            dsProvider = Arrays.copyOf(dsProvider, dsProvider.length + 1);
        }

        dsProvider[n] = new Provider();
        dsProvider[n].Input(this);
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
            System.out.println("Thong tin nha cung cap co ma " + maNCC + ":");
            PrintHeader();
            dsProvider[kq].Output();
            System.out.format("+----------+------------------------------+-------------------------------------------------------+---------------+%n");
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
                    System.out.println("Danh sach nha cung cap co ten: " + ten);
                    PrintHeader();
                    found = true;
                }
                dsProvider[i].Output();
            }
        }

        if (!found) {
            System.out.println("Khong tim thay nha cung cap co ten " + ten);
        } else {
            System.out.format("+----------+------------------------------+-------------------------------------------------------+---------------+%n");
        }
    }

    public void TimSoDienThoai() {
        System.out.print("Nhap so dien thoai can tim: ");
        String soDT = sc.nextLine().trim();
        boolean found = false;

        for (int i = 0; i < n; i++) {
            if (dsProvider[i].getSoDienThoai().equals(soDT)) {
                if (!found) {
                    System.out.println("Nha cung cap co so dien thoai: " + soDT);
                    PrintHeader();
                    found = true;
                }
                dsProvider[i].Output();
            }
        }

        if (!found) {
            System.out.println("Khong tim thay nha cung cap co so dien thoai:" + soDT);
        } else {
            System.out.format("+----------+------------------------------+-------------------------------------------------------+---------------+%n");
        }
    }

    public void TimDiaChi() {
        System.out.print("Nhap dia chi can tim: ");
        String diaChi = sc.nextLine().trim();
        boolean found = false;

        for (int i = 0; i < n; i++) {
            if (dsProvider[i].getDiaChi().toLowerCase().contains(diaChi.toLowerCase())) {
                if (!found) {
                    System.out.println("Danh sach nha cung cap co dia chi: " + diaChi);
                    PrintHeader();
                    found = true;
                }
                dsProvider[i].Output();
            }
        }

        if (!found) {
            System.out.println("Khong tim thay nha cung cap co dia chi:" + diaChi);
        } else {
            System.out.format("+----------+------------------------------+-------------------------------------------------------+---------------+%n");
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
            System.out.println("Khong the xoa !! Vi maNCC " + maNCC + " khong ton tai.");
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
            System.out.println("+-------------------------------------------+");
            System.out.println("|           MENU SUA NHA CUNG CAP           |");
            System.out.println("+-------------------------------------------+");
            System.out.printf("| %-41s |%n", "1) Sua ten NCC.");
            System.out.printf("| %-41s |%n", "2) Sua dia chi.");
            System.out.printf("| %-41s |%n", "3) Sua so dien thoai.");
            System.out.printf("| %-41s |%n", "0) Thoat.");
            System.out.println("+-------------------------------------------+");
            System.out.print("Nhap lua chon tu 0 - 3: ");

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
                    dsProvider[vt].setDiaChi(sc.nextLine());
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
        System.out.println("Tong so nha cung cap: " + n);
        Output();
    }

    public void Thongke_diaChi_theo_NCC() {
        System.out.println("THONG KE NHA CUNG CAP THEO DIA CHI");
        if (n == 0) {
            System.out.println("Danh sach nha cung cap rong!!");
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
            System.out.println("Thong ke theo dia chi :" + diaChi);
            PrintHeader();
            for (int j = i; j < n; j++) {
                if (dsProvider[j].getDiaChi().equalsIgnoreCase(diaChi)) {
                    dem++;
                    dsProvider[j].Output();
                }
            }
            System.out.format("+----------+------------------------------+-------------------------------------------------------+---------------+%n");
            System.out.println("--> Tong co " + dem+" NCC\n");
        }
    }
    @Override
    public void ReadFile() {
        Scanner scFile = null;
        FileInputStream inStream = null;
        try {
            inStream = new FileInputStream("data/Provider.txt");
            scFile = new Scanner(inStream);
            this.n = 0;
            while (scFile.hasNextLine()) {
                String line = scFile.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }
                String[] data = line.split(",");
                if (data.length >= 4) {
                    if (n == dsProvider.length) {
                        dsProvider = Arrays.copyOf(dsProvider, dsProvider.length + 1);
                    }
                    dsProvider[n] = new Provider(
                            data[0].trim(),
                            data[1].trim(),
                            data[2].trim(),
                            data[3].trim());
                    n++;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Loi: Khong tim thay file data/Provider.txt!");
        } finally {
            if (scFile != null) {
                scFile.close();
            }
        }
    }

    @Override
    public void WriteFile() {
        FileOutputStream outStream = null;
        PrintWriter writer = null;
        try {
            outStream = new FileOutputStream("data/Provider.txt");
            writer = new PrintWriter(outStream);
            for (int i = 0; i < this.n; i++) {
                if (dsProvider[i] != null) {
                    writer.println(
                            dsProvider[i].getMaNCC() + ","
                            + dsProvider[i].getTenNCC() + ","
                            + dsProvider[i].getDiaChi() + ","
                            + dsProvider[i].getSoDienThoai());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Loi: Khong tim thay duong dan file data/Provider.txt de ghi!");
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}


