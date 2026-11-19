package quanly;

import danhsach.DSChiTietPhieuMuon;
import danhsach.DSPhieuMuon;
import java.util.Scanner;
import model.ChiTietPhieuMuon;
import model.PhieuMuon;

public class QLPhieuMuon extends QLThuVien {
    private static final String FILE_PHIEU_MUON = "data/PhieuMuon.txt";
    private static final String FILE_CHI_TIET = "data/ChiTietPhieuMuon.txt";

    public QLPhieuMuon() {
        super();
    }

    public QLPhieuMuon(DSPhieuMuon dsPhieuMuon, DSChiTietPhieuMuon dsChiTiet) {
        this.dsPM = dsPhieuMuon;
        this.dsCTPM = dsChiTiet;
    }

    public DSPhieuMuon getDsPhieuMuon() {
        return dsPM;
    }

    public DSChiTietPhieuMuon getDsChiTiet() {
        return dsCTPM;
    }

    @Override
    public void menu() {
        Menu(sc);
    }

    public void Menu(Scanner sc) {
        int choice;
        do {
            System.out.println("
+-------------------------------------------+");
            System.out.println("|          MENU QUAN LY PHIEU MUON          |");
            System.out.println("+-------------------------------------------+");
            System.out.println("| 1)  Xuat danh sach phieu muon             |");
            System.out.println("| 2)  Them phieu muon                       |");
            System.out.println("| 3)  Tim phieu muon theo ma                |");
            System.out.println("| 4)  Sua phieu muon                        |");
            System.out.println("| 5)  Xoa phieu muon                        |");
            System.out.println("| 6)  Xuat chi tiet phieu muon              |");
            System.out.println("| 7)  Them chi tiet phieu muon              |");
            System.out.println("| 8)  Tim chi tiet theo ma phieu            |");
            System.out.println("| 9)  Sua chi tiet phieu muon               |");
            System.out.println("| 10) Xoa chi tiet phieu muon               |");
            System.out.println("| 11) Thong ke so sach muon                 |");
            System.out.println("| 12) Ghi file                              |");
            System.out.println("| 13) Doc file                              |");
            System.out.println("| 0)  Thoat                                 |");
            System.out.println("+-------------------------------------------+");
            System.out.print("Chon: ");
            choice = nhapSo(sc);

            switch (choice) {
                case 1 -> {
                    dsPM.ReadFile();
                    dsPM.Output();
                }
                case 2 -> {
                    themPhieuMuon(sc);
                    dsPM.WriteFile();
                }
                case 3 -> timPhieuMuon(sc);
                case 4 -> {
                    suaPhieuMuon(sc);
                    dsPM.WriteFile();
                }
                case 5 -> {
                    xoaPhieuMuon(sc);
                    dsPM.WriteFile();
                }
                case 6 -> {
                    dsCTPM.ReadFile();
                    dsCTPM.Output();
                }
                case 7 -> {
                    dsCTPM.Them(sc);
                    dsCTPM.WriteFile();
                }
                case 8 -> timChiTiet(sc);
                case 9 -> {
                    suaChiTiet(sc);
                    dsCTPM.WriteFile();
                }
                case 10 -> {
                    xoaChiTiet(sc);
                    dsCTPM.WriteFile();
                }
                case 11 -> thongKe(sc);
                case 12 -> ghiFile();
                case 13 -> docFile();
                case 0 -> System.out.println("Thoat menu phieu muon.");
                default -> System.out.println("Lua chon khong hop le.");
            }
        } while (choice != 0);
    }

    private void themPhieuMuon(Scanner sc) {
        PhieuMuon item = new PhieuMuon();
        item.Input(sc);
        System.out.println(dsPM.Them(item)
                ? "Them phieu muon thanh cong!" : "Ma phieu muon da ton tai hoac ngay khong hop le.");
    }

    private void timPhieuMuon(Scanner sc) {
        System.out.print("Nhap ma phieu muon: ");
        PhieuMuon item = dsPM.TimTheoMa(sc.nextLine().trim());
        if (item == null) {
            System.out.println("Khong tim thay phieu muon.");
        } else {
            item.Output();
        }
    }

    private void suaPhieuMuon(Scanner sc) {
        System.out.print("Nhap ma phieu muon can sua: ");
        String maPM = sc.nextLine().trim();
        PhieuMuon item = dsPM.TimTheoMa(maPM);
        if (item == null) {
            System.out.println("Khong tim thay phieu muon.");
            return;
        }
        System.out.print("Nhap ma doc gia moi: ");
        item.setMaDG(sc.nextLine().trim());
        System.out.print("Nhap ma nhan vien moi: ");
        item.setMaNV(sc.nextLine().trim());
        String ngayMuon;
        while (true) {
            System.out.print("Nhap ngay muon moi (dd/MM/yyyy): ");
            ngayMuon = sc.nextLine().trim();
            if (PhieuMuon.isValidDate(ngayMuon)) break;
            System.out.println("Ngay khong hop le.");
        }
        String ngayTra;
        while (true) {
            System.out.print("Nhap ngay tra moi (dd/MM/yyyy): ");
            ngayTra = sc.nextLine().trim();
            if (!PhieuMuon.isValidDate(ngayTra)) {
                System.out.println("Ngay khong hop le.");
            } else if (PhieuMuon.isBefore(ngayTra, ngayMuon)) {
                System.out.println("Ngay tra khong the truoc ngay muon.");
            } else {
                break;
            }
        }
        String ngayThucTra;
        while (true) {
            System.out.print("Nhap ngay thuc tra moi (dd/MM/yyyy, de trong neu chua tra): ");
            ngayThucTra = sc.nextLine().trim();
            if (ngayThucTra.isEmpty() || PhieuMuon.isValidNgayThucTra(ngayThucTra, ngayMuon, ngayTra)) {
                break;
            }
            System.out.println("Ngay thuc tra khong hop le hoac truoc ngay muon.");
        }
        item.setNgayMuon(ngayMuon);
        item.setNgayTra(ngayTra);
        item.setNgayThucTra(ngayThucTra);
        System.out.println("Sua phieu muon thanh cong.");
    }

    private void xoaPhieuMuon(Scanner sc) {
        System.out.print("Nhap ma phieu muon can xoa: ");
        String maPM = sc.nextLine().trim();
        if (dsCTPM.TimTheoMaPM(maPM).length > 0) {
            System.out.println("Khong the xoa phieu muon dang co chi tiet.");
        } else {
            System.out.println(dsPM.Xoa(maPM)
                    ? "Xoa phieu muon thanh cong." : "Khong tim thay phieu muon.");
        }
    }

    private void timChiTiet(Scanner sc) {
        System.out.print("Nhap ma phieu muon: ");
        ChiTietPhieuMuon[] items = dsCTPM.TimTheoMaPM(sc.nextLine().trim());
        if (items.length == 0) {
            System.out.println("Khong co chi tiet cho phieu muon nay.");
            return;
        }
        for (ChiTietPhieuMuon item : items) item.Output();
    }

    private void suaChiTiet(Scanner sc) {
        System.out.print("Nhap ma chi tiet can sua: ");
        ChiTietPhieuMuon item = dsCTPM.TimTheoMaCT(sc.nextLine().trim());
        if (item == null) {
            System.out.println("Khong tim thay chi tiet.");
            return;
        }
        System.out.print("Nhap ma phieu muon moi: ");
        item.setMaPM(sc.nextLine().trim());
        System.out.print("Nhap ma sach moi: ");
        item.setMaSach(sc.nextLine().trim());
        System.out.print("Nhap so luong moi: ");
        item.setSoLuong(Integer.parseInt(sc.nextLine().trim()));
        System.out.println("Sua chi tiet thanh cong.");
    }

    private void xoaChiTiet(Scanner sc) {
        System.out.print("Nhap ma chi tiet can xoa: ");
        System.out.println(dsCTPM.Xoa(sc.nextLine().trim())
                ? "Xoa chi tiet thanh cong." : "Khong tim thay chi tiet.");
    }

    private void thongKe(Scanner sc) {
        System.out.print("Nhap ma phieu muon: ");
        System.out.println("Tong so sach muon: " + dsCTPM.TongSoSachMuon(sc.nextLine().trim()));
    }

    private void ghiFile() {
        dsPM.ghiFile(FILE_PHIEU_MUON);
        dsCTPM.ghiFile(FILE_CHI_TIET);
        System.out.println("Ghi file phieu muon thanh cong.");
    }

    private void docFile() {
        try {
            dsPM.docFile(FILE_PHIEU_MUON);
            dsCTPM.docFile(FILE_CHI_TIET);
            System.out.println("Doc file phieu muon thanh cong.");
        } catch (IllegalStateException e) {
            System.out.println("Loi doc file: " + e.getMessage());
        }
    }

    private int nhapSo(Scanner sc) {
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
