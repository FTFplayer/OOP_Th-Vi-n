import Book.DSBook;
import java.util.Scanner;
public class test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DSBook ds = new DSBook();
        int chon = 0;
        ds.Input(); 
        do {
            System.out.println("\n====================== MENU QUAN LY THU VIEN ======================");
            System.out.println("1. Xuat danh sach sach (Kiem tra mảng sau khi Them/Xoa/Sua)");
            System.out.println("2. Them 1 cuon sach moi");
            System.out.println("3. Tim kiem sach (Theo ma)");
            System.out.println("4. Tim kiem sach (Theo ten)");
            System.out.println("5. Tim kiem sach (Theo the loai)");
            System.out.println("6. Xoa sach (Theo ma)");
            System.out.println("7. Sua thong tin sach (Theo ma)");
            System.out.println("8. Thong ke theo loai sach (Giao trinh / Tham khao)");
            System.out.println("9. Thong ke tinh trang kho (Con / Het)");
            System.out.println("10. Thong ke theo muc gia");
            System.out.println("0. Thoat chuong trinh");
            System.out.println("===================================================================");
            System.out.print("Chon chuc nang (0-10): ");
            chon = sc.nextInt();
            sc.nextLine();
            switch (chon) {
                case 1: 
                    ds.Output(); 
                    break;
                case 2: 
                    ds.them(); 
                    break;
                case 3: 
                    ds.timkiem(); 
                    break;
                case 4: 
                    ds.timTheoTenSach(); 
                    break;
                case 5: 
                    ds.timTheoTheLoai(); 
                    break;
                case 6: 
                    ds.xoa(); 
                    break;
                case 7: 
                    ds.sua(); 
                    break;
                case 8: 
                    ds.thongKeLoaiSach(); 
                    break;
                case 9: 
                    ds.thongKeTinhTrang(); 
                    break;
                case 10: 
                    ds.thongKeGiaTien(); 
                    break;
                case 0: 
                    System.out.println("Da thoat chuong trinh. Tam biet!"); 
                    break;
                default: 
                    System.out.println("Lua chon khong hop le. Vui long nhap tu 0 den 10!");
            }
        } while (chon != 0);
    }
}
