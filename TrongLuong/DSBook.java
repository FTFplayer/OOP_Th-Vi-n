import java.util.Arrays;
import java.util.Scanner;
public class DSBook{
    Scanner sc = new Scanner(System.in);
    private Book[] ds = new Book[1];
    private int n;
    public DSBook(){this.ds=new Book[1];this.n=0;}
    public DSBook(Book[] ds, int n){this.ds=ds;this.n=n;}
    public void Input() {
        System.out.print("Nhap so luong sach khac nhau: ");
        n = sc.nextInt();
        sc.nextLine();
        ds = new Book[n];
        for (int i = 0; i < n; i++) {
            System.out.println("\n---Nhap thong tin sach thu " + (i + 1) + "---");
            System.out.print("Chon loai sach (1-Sach giao khoa | 2-Sach thuong): ");
            int loai=sc.nextInt();sc.nextLine();
            if(loai==1) ds[i]=new EducationBook();
            else ds[i]=new ReferenceBook();
            ds[i].Input();
        }
    }
    public void Output() {
        System.out.println("\n============================================================================================================");
        System.out.println("                                         DANH SACH QUAN LY SACH                                         ");
        System.out.println("============================================================================================================");
        System.out.printf("| %-4s | %-12s | %-8s | %-4s | %-8s | %-7s | %-3s | %-3s | %-10s | %-18s |\n",
                "Ma", "Ten Sach", "The Loai", "MaTG", "Ngay XB", "Don Gia", "SL", "Con", "Trang thai", "Thong tin rieng");       
        System.out.println("------------------------------------------------------------------------------------------------------------");
        
        for (int i = 0; i < n; i++) {
            ds[i].Output();
        }
        System.out.println("============================================================================================================");
    }
    public void them() {
        ds = Arrays.copyOf(ds, n + 1);
        System.out.print("Chon loai sach (1 - Sach giao khoa | 2 - Sach thuong): ");
        int loai = sc.nextInt(); sc.nextLine();
        if (loai == 1) ds[n] = new EducationBook();
        else ds[n] = new ReferenceBook();
        ds[n].Input();
        n++;
        System.out.println("Them sach thanh cong!");
    }
    public void them(Book x) {
        ds = Arrays.copyOf(ds, n + 1);
        ds[n] = x;
        n++;
        System.out.println("Them sach thanh cong!");
    }
    public int timkiem(String ma) {
        for (int i = 0; i < n; i++) {
            if (ds[i].getMaSach().equalsIgnoreCase(ma)) {
                return i;
            }
        }
        return -1;
    }
    public void timkiem() {
        System.out.print("Nhap ma sach can tim: ");
        String ma = sc.nextLine();
        int vt = timkiem(ma);
        if (vt != -1) {
            System.out.println("\n===== KET QUA TIM KIEM =====");
            System.out.printf("| %-4s | %-12s | %-8s | %-4s | %-8s | %-7s | %-3s | %-3s | %-10s | %-18s |\n",
                    "Ma", "Ten Sach", "The Loai", "MaTG", "Ngay XB", "Don Gia", "SL", "Con", "Trang thai", "Thong tin rieng");
            System.out.println("------------------------------------------------------------------------------------------------------------");
            ds[vt].Output();
            System.out.println("============================================================================================================");
        } else {
            System.out.println("Khong tim thay sach co ma: " + ma);
        }
    }
    public Book timkiemBook(String ma) {
        int vt = timkiem(ma);
        if (vt != -1) {
            return ds[vt];
        }
        return null;
    }
    public void timTheoTenSach() {
        System.out.print("Nhap ten sach can tim: ");
        String ten = sc.nextLine();
        boolean found = false;
        for (int i = 0; i < n; i++) {
            if (ds[i].getTenSach().toLowerCase().contains(ten.toLowerCase())) {
                if (!found) {
                    System.out.println("\n===== DANH SACH TIM THAY =====");
                    System.out.printf("| %-4s | %-12s | %-8s | %-4s | %-8s | %-7s | %-3s | %-3s | %-10s | %-18s |\n",
                            "Ma", "Ten Sach", "The Loai", "MaTG", "Ngay XB", "Don Gia", "SL", "Con", "Trang thai", "Thong tin rieng");
                    System.out.println("------------------------------------------------------------------------------------------------------------");
                    found = true;
                }
                ds[i].Output();
            }
        }
        if (found) {
            System.out.println("============================================================================================================");
        } else {
            System.out.println("Khong tim thay sach nao co ten chua: " + ten);
        }
    }
    public void timTheoTheLoai() {
        System.out.print("Nhap the loai can tim: ");
        String tl = sc.nextLine();
        boolean found = false;
        for (int i = 0; i < n; i++) {
            if (ds[i].getTheLoai().equalsIgnoreCase(tl)) {
                if (!found) {
                    System.out.println("\n===== DANH SACH TIM THAY =====");
                    System.out.printf("| %-4s | %-12s | %-8s | %-4s | %-8s | %-7s | %-3s | %-3s | %-10s | %-18s |\n",
                            "Ma", "Ten Sach", "The Loai", "MaTG", "Ngay XB", "Don Gia", "SL", "Con", "Trang thai", "Thong tin rieng");
                    System.out.println("------------------------------------------------------------------------------------------------------------");
                    found = true;
                }
                ds[i].Output();
            }
        }
        if (found) {
            System.out.println("============================================================================================================");
        } else {
            System.out.println("Khong tim thay sach thuoc the loai: " + tl);
        }
    }
    public void xoa(String ma) {
        int vt = timkiem(ma);
        if (vt == -1) {
            System.out.println("Khong tim thay sach de xoa!");
            return;
        }
        for (int i = vt; i < n - 1; i++) {
            ds[i] = ds[i + 1];
        }
        n--;
        ds = Arrays.copyOf(ds, n);
        System.out.println("Xoa sach thanh cong!");
    }
    public void xoa() {
        System.out.print("Nhap ma sach can xoa: ");
        String ma = sc.nextLine();
        xoa(ma);
    }
    public void sua() {
        System.out.print("Nhap ma sach can sua: ");
        String ma = sc.nextLine();
        sua(ma);
    }
    public void sua(String ma) {
        int vt = timkiem(ma);
        if (vt == -1) {
            System.out.println("Khong tim thay sach de sua!");
            return;
        }
        System.out.println("\nNhap lai thong tin moi cho sach (" + ds[vt].getTenSach() + "):");
        System.out.print("Nhap Ten sach moi: ");
        ds[vt].setTenSach(sc.nextLine());
        System.out.print("Nhap Don gia moi: ");
        ds[vt].setDonGia(sc.nextFloat());
        System.out.print("Nhap So luong tong moi: ");
        int slMoi = sc.nextInt();
        ds[vt].setSoLuong(slMoi);
        ds[vt].setSoLuongConLai(slMoi);
        sc.nextLine();
        if (ds[vt] instanceof EducationBook) {
            System.out.print("Nhap Ten Mon moi: ");
            ((EducationBook) ds[vt]).setTenMon(sc.nextLine());
            System.out.print("Nhap Lop moi: ");
            ((EducationBook) ds[vt]).setLop(sc.nextLine());
        } else if (ds[vt] instanceof ReferenceBook) {
            System.out.print("Nhap Linh vuc moi: ");
            ((ReferenceBook) ds[vt]).setLinhvuc(sc.nextLine());
        }
        System.out.println("Sua thong tin thanh cong!");
    }
    public void thongKeLoaiSach() {
        int sgk = 0, st = 0;
        for (int i = 0; i < n; i++) {
            if (ds[i] instanceof EducationBook) {
                sgk++;
            } else if (ds[i] instanceof ReferenceBook) {
                st++;
            }
        }
        System.out.println("\n===== THONG KE THEO LOAI SACH =====");
        System.out.println("Sach giao khoa (EducationBook): " + sgk + " cuon");
        System.out.println("Sach tham khao (ReferenceBook): " + st + " cuon");
    }
    public void thongKeTinhTrang() {
        int con = 0, het = 0;
        for (int i = 0; i < n; i++) {
            if (ds[i].getSoLuongConLai() > 0) {
                con++;
            } else {
                het++;
            }
        }
        System.out.println("\n===== THONG KE TINH TRANG KHO =====");
        System.out.println("Sach con trong kho : " + con + " ma sach");
        System.out.println("Sach da het hang   : " + het + " ma sach (Can nhap them!)");
    }
    public void thongKeGiaTien() {
        int re = 0, trungBinh = 0, cao = 0;
        for (int i = 0; i < n; i++) {
            double gia = ds[i].getDonGia();
            if (gia < 50000) re++;
            else if (gia <= 150000) trungBinh++;
            else cao++;
        }
        System.out.println("\n===== THONG KE THEO MUC GIA =====");
        System.out.println("Sách dưới 50.000 VNĐ       : " + re + " cuon");
        System.out.println("Sách từ 50.000 - 150.000 VNĐ: " + trungBinh + " cuon");
        System.out.println("Sách trên 150.000 VNĐ      : " + cao + " cuon");
    }
}