package Employee;

import java.util.Scanner;

public class QLEmployee {
    private static DSEmployee dsEmployee = new DSEmployee();
    private Scanner sc = new Scanner(System.in);

    // -----------------------------------------------------------------------------------//
    // 1. Menu chính
    public void menu() {
        int choice;
        do {
            System.out.println();
            System.out.println("+-------------------------------------------+");
            System.out.println("|           MENU QUAN LY NHAN VIEN          |");
            System.out.println("+-------------------------------------------+");
            System.out.println("| 1) Xem danh sach nhan vien.               |");
            System.out.println("| 2) Them nhan vien.                        |");
            System.out.println("| 3) Tim kiem thong tin nhan vien.          |");
            System.out.println("| 4) Sua thong tin nhan vien.               |");
            System.out.println("| 5) Xoa thong tin nhan vien.               |");
            System.out.println("| 6) Thong ke thong tin nhan vien.          |");
            System.out.println("| 0) Thoat.                                 |");
            System.out.println("+-------------------------------------------+");

            System.out.print("Nhap lua chon tu 0 - 6: ");
            choice = nhapLuaChon();

            switch (choice) {
                case 1:
                    System.out.println("\n--- [1] XEM DANH SACH NHAN VIEN ---");
                    dsEmployee.ReadFile();
                    dsEmployee.Output();
                    break;
                case 2:
                    System.out.println("\n--- [2] THEM NHAN VIEN MOI ---");
                    dsEmployee.ThemEmployee();
                    dsEmployee.WriteFile();
                    break;
                case 3:
                    menuTimKiem();
                    break;
                case 4:
                    System.out.println("\n--- [4] SUA THONG TIN NHAN VIEN ---");
                    dsEmployee.SuaEmployee();
                    dsEmployee.WriteFile();
                    break;
                case 5:
                    menuXoa();
                    break;
                case 6:
                    menuThongKe();
                    break;
                case 0:
                    System.out.println("Da thoat khoi Quan ly Nhan vien.");
                    break;
                default:
                    System.out.println("Lua chon khong hop le. Vui long chon lai tu 0 den 6!");
            }
        } while (choice != 0);
    }

    // -----------------------------------------------------------------------------------//
    // 2. Menu Tìm kiếm
    private void menuTimKiem() {
        int choice;
        do {
            System.out.println("+-------------------------------------------+");
            System.out.println("|         MENU TIM KIEM NHAN VIEN           |");
            System.out.println("+-------------------------------------------+");
            System.out.println("| 1) Tim kiem theo ma nhan vien.            |");
            System.out.println("| 2) Tim kiem theo ho nhan vien.            |");
            System.out.println("| 3) Tim kiem theo ten nhan vien.           |");
            System.out.println("| 4) Tim kiem theo so dien thoai.           |");
            System.out.println("| 0) Thoat.                                 |");
            System.out.println("+-------------------------------------------+");

            System.out.print("Nhap lua chon tu 0 - 4: ");
            choice = nhapLuaChon();

            switch (choice) {
                case 1:
                    dsEmployee.TimEmployeeTheoMa();
                    break;
                case 2:
                    dsEmployee.TimEmployeeTheoHo();
                    break;
                case 3:
                    dsEmployee.TimEmployeeTheoTen();
                    break;
                case 4:
                    dsEmployee.TimEmployeeTheoSDT();
                    break;
                case 0:
                    System.out.println("Quay lai menu quan ly nhan vien.");
                    break;
                default:
                    System.out.println("Lua chon khong hop le. Vui long chon lai tu 0 den 4!");
            }
        } while (choice != 0);
    }

    // -----------------------------------------------------------------------------------//
    // 3. Menu Xóa
    private void menuXoa() {
        int choice;
        do {
            System.out.println("+-------------------------------------------+");
            System.out.println("|             MENU XOA NHAN VIEN            |");
            System.out.println("+-------------------------------------------+");
            System.out.println("| 1) Xoa theo ma nhan vien.                 |");
            System.out.println("| 2) Xoa theo ho nhan vien.                 |");
            System.out.println("| 3) Xoa theo ten nhan vien.                |");
            System.out.println("| 4) Xoa theo gioi tinh.                    |");
            System.out.println("| 5) Xoa theo ngay sinh.                    |");
            System.out.println("| 6) Xoa theo so dien thoai.                |");
            System.out.println("| 7) Xoa theo dia chi.                      |");
            System.out.println("| 0) Thoat.                                 |");
            System.out.println("+-------------------------------------------+");

            System.out.print("Nhap lua chon tu 0 - 7: ");
            choice = nhapLuaChon();

            switch (choice) {
                case 1:
                    dsEmployee.XoaEmployeeTheoMa();
                    dsEmployee.WriteFile();
                    break;
                case 2:
                    dsEmployee.XoaEmployeeTheoHo();
                    dsEmployee.WriteFile();
                    break;
                case 3:
                    dsEmployee.XoaEmployeeTheoTen();
                    dsEmployee.WriteFile();
                    break;
                case 4:
                    dsEmployee.XoaEmployeeTheoGioiTinh();
                    dsEmployee.WriteFile();
                    break;
                case 5:
                    dsEmployee.XoaEmployeeTheoNgaySinh();
                    dsEmployee.WriteFile();
                    break;
                case 6:
                    dsEmployee.XoaEmployeeTheoSDT();
                    dsEmployee.WriteFile();
                    break;
                case 7:
                    dsEmployee.XoaEmployeeTheoDiaChi();
                    dsEmployee.WriteFile();
                    break;
                case 0:
                    System.out.println("Quay lai menu quan ly nhan vien.");
                    break;
                default:
                    System.out.println("Lua chon khong hop le. Vui long chon lai tu 0 den 7!");
            }

        } while (choice != 0);
    }

    // -----------------------------------------------------------------------------------//
    // 4. Thống kê
    private void menuThongKe() {
        int choice;
        do {
            System.out.println("+-------------------------------------------+");
            System.out.println("|           MENU THONG KE NHAN VIEN         |");
            System.out.println("+-------------------------------------------+");
            System.out.println("| 1) Thong ke theo gioi tinh.               |");
            System.out.println("| 2) Thong ke theo tuoi.                    |");
            System.out.println("| 3) Thong ke theo muc luong toi thieu.     |");
            System.out.println("| 0) Thoat.                                 |");
            System.out.println("+-------------------------------------------+");

            System.out.print("Nhap lua chon tu 0 - 3: ");
            choice = nhapLuaChon();

            switch (choice) {
                case 1:
                    dsEmployee.ThongKeEmployeeTheoGioiTinh();
                    break;
                case 2:
                    int slTuoi = dsEmployee.ThongKeEmployeeTheoTuoi();
                    System.out.println("So luong nhan vien co do tuoi nay la: " + slTuoi);
                    break;
                case 3:
                    int slLuong = dsEmployee.ThongKeEmployeeTheoLuongToiThieu();
                    System.out.println("So luong nhan vien dat muc luong nay tro len la: " + slLuong);
                    break;
                case 0:
                    System.out.println("Quay lai menu quan ly nhan vien.");
                    break;
                default:
                    System.out.println("Lua chon khong hop le. Vui long chon lai tu 0 den 3!");
            }
        } while (choice != 0);
    }

    // -----------------------------------------------------------------------------------//
    // 5. Check lựa chọn
    private int nhapLuaChon() {
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}