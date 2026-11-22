package quanly;

import danhsach.*;
import java.util.Scanner;

public class QLThuVien {
    Scanner sc = new Scanner(System.in);

    DSAuthor dsAuthor = new DSAuthor();
    DSBook dsBook = new DSBook();
    DSEmployee dsEmployee = new DSEmployee();
    DSPublisher dsPublisher = new DSPublisher();
    DSReader dsReader = new DSReader();
    DSTheLoai dstl = new DSTheLoai();

    DSPhieuPhat dsPP = new DSPhieuPhat();
    DSChiTietPhieuPhat dsCTPP = new DSChiTietPhieuPhat();
    DSQuyDinhPhat dsQDP = new DSQuyDinhPhat();
    DSProvider dsProvider = new DSProvider();

    DSPhieuMuon dsPM = new DSPhieuMuon();
    DSChiTietPhieuMuon dsCTPM = new DSChiTietPhieuMuon();
    DSPhieuNhapHang dsPNH = new DSPhieuNhapHang();
    DSChiTietPhieuNhapHang dsCTPNH = new DSChiTietPhieuNhapHang();

    public void menu() {
    }

    public QLThuVien() {
        dsAuthor.ReadFile();
        dsEmployee.ReadFile();
        dsPublisher.ReadFile();
        dsReader.ReadFile();
        dsBook.ReadFile();
        dstl.ReadFile();

        dsPM.ReadFile();
        dsCTPM.ReadFile();
        dsPM.napChiTiet(dsCTPM);

        dsPNH.ReadFile();
        dsCTPNH.ReadFile();
        dsPNH.napChiTiet(dsCTPNH);

        dsPP.ReadFile();
        dsCTPP.ReadFile();
        dsPP.napChiTiet(dsCTPP);

        dsQDP.ReadFile();
        dsProvider.ReadFile();
    }
}
