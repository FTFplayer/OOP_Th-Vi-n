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
    // of phuoq.lyn--MÁ SAO CTRL / CỦA TUI (:>>)
    DSPhieuPhat dsPP = new DSPhieuPhat();
    DSChiTietPhieuPhat dsCTPP = new DSChiTietPhieuPhat();
    DSQuyDinhPhat dsQDP = new DSQuyDinhPhat();
    DSProvider dsProvider = new DSProvider();
    // // of Hưng
    DSPhieuMuon dsPM = new DSPhieuMuon();

    public void menu() {
    }

    public QLThuVien() {
        dsAuthor.ReadFile();
        dsEmployee.ReadFile();
        dsPublisher.ReadFile();
        dsReader.ReadFile();
        dsBook.ReadFile();
        dstl.ReadFile();
    }
}
