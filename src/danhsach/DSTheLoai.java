package TheLoai;
import java.util.Arrays;
import java.util.Scanner;
public class DSTheLoai {
    Scanner sc = new Scanner(System.in);
    private TheLoai[] ds;
    private int n;
    public DSTheLoai() {ds = new TheLoai[0]; n = 0; khoiTaoDuLieuMau();}
    private void khoiTaoDuLieuMau() {
        ds = new TheLoai[3];
        ds[0] = new TheLoai("TL01", "Cong Nghe Thong Tin");
        ds[1] = new TheLoai("TL02", "Kinh Te - Quan Tri");
        ds[2] = new TheLoai("TL03", "Van Hoc");
        n = 3;
    }

    public String timTenTheLoai(String ma) {
        for (int i = 0; i < n; i++) {
            if (ds[i].getMaTheLoai().equalsIgnoreCase(ma)) {
                return ds[i].getTenTheLoai();
            }
        }
        return "Khong ro"; 
    }

    public boolean kiemTraTonTai(String ma) {
        for (int i = 0; i < n; i++) {
            if (ds[i].getMaTheLoai().equalsIgnoreCase(ma)) {
                return true;
            }
        }
        return false;
    }
    
    // Hàm 3: In danh sách thu gọn (Chỉ Mã và Tên) cho thủ thư nhìn để nhập sách
    public void hienThiDanhSachChon() {
        System.out.println("\n--- DANH SACH THE LOAI ---");
        for (int i = 0; i < n; i++) {
            System.out.println(ds[i].getMaTheLoai() + ": " + ds[i].getTenTheLoai());
        }
    }

    
    public void xuatDS() {
        System.out.println("\n=========================================");
        System.out.println("          DANH SACH THE LOAI             ");
        System.out.println("=========================================");
        System.out.printf("| %-12s | %-25s |\n", "Ma The Loai", "Ten The Loai");
        System.out.println("-----------------------------------------");
        for (int i = 0; i < n; i++) {
            ds[i].Output();
        }
        System.out.println("=========================================");
    }

    public void them() {
        ds = Arrays.copyOf(ds, n + 1);
        ds[n] = new TheLoai();
        System.out.println("\n--- THEM THE LOAI MOI ---");
        ds[n].Input();
        n++;
        System.out.println("=> Them the loai thanh cong!");
    }

    public void xoa(String ma) {
        int viTri = -1;
        for (int i = 0; i < n; i++) {
            if (ds[i].getMaTheLoai().equalsIgnoreCase(ma)) {
                viTri = i;
                break;
            }
        }
        if (viTri != -1) {
            for (int i = viTri; i < n - 1; i++) {
                ds[i] = ds[i + 1];
            }
            n--;
            ds = Arrays.copyOf(ds, n);
            System.out.println("=> Xoa the loai thanh cong!");
        } else {
            System.out.println("=> Khong tim thay the loai co ma: " + ma);
        }   
    }
}