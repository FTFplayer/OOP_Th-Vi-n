import Book.DSBook;
public class test2{
    public static void main(String[] args) {
        DSBook ds = new DSBook();
        
        System.out.println("===== 1. NHAP DANH SACH SACH BAN DAU =====");
        ds.Input();
        
        System.out.println("\n===== 2. XUAT DANH SACH SACH =====");
        ds.Output();
        
        System.out.println("\n===== 3. THEM SACH THU CONG (Ham khong tham so) =====");
        ds.them();
        
        // System.out.println("\n===== 4. THEM SACH TRUC TIEP TU CODE (Ham co tham so) =====");
        // EducationBook sachMoi = new EducationBook();
        // sachMoi.setMaSach("B999");
        // sachMoi.setTenSach("Java Co Ban");
        // sachMoi.setTheLoai("CNTT");
        // sachMoi.setMaTG("TG01");
        // sachMoi.setNgayXB("10/10/2026");
        // sachMoi.setDonGia(120000);
        // sachMoi.setSoLuong(20);
        // sachMoi.setSoLuongConLai(20);
        // sachMoi.setTenMon("Lap Trinh OOP");
        // sachMoi.setLop("Nam 2");
        // ds.them(sachMoi);
        // System.out.println("-> Da day sach B999 vao mang bang code!");
        
        System.out.println("\n===== DANH SACH SAU KHI THEM =====");
        ds.Output();
        
        System.out.println("\n===== 5. TIM KIEM (Goi ham UI) =====");
        ds.timkiem();
        ds.timTheoTenSach();
        ds.timTheoTheLoai();
        
        System.out.println("\n===== 6. XOA SACH =====");
        ds.xoa();
        // ds.xoa("B999"); 
        
        System.out.println("\n===== DANH SACH SAU KHI XOA =====");
        ds.Output();
        
        System.out.println("\n===== 7. SUA THONG TIN SACH (Ham khong tham so) =====");
        ds.sua();
        
        System.out.println("\n===== DANH SACH SAU KHI SUA =====");
        ds.Output();
        
        System.out.println("\n===== 8. THONG KE =====");
        ds.thongKeLoaiSach();
        ds.thongKeTinhTrang();
        ds.thongKeGiaTien();
    }
}