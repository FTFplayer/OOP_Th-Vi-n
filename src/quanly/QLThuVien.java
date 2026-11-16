package quanly;

import java.util.Scanner;
import danhsach.*;

public class QLThuVien {
    Scanner sc = new Scanner(System.in);

    DSAuthor dsAuthor = new DSAuthor();
    DSBook dsBook = new DSBook();
    DSEmployee dsEmployee = new DSEmployee();
    DSPublisher dsPublisher = new DSPublisher();
    DSReader dsReader = new DSReader();
    DSTheLoai dstl = new DSTheLoai();
    //of phuoq.lyn-- mn nhớ ghi của ai vào nhé[hyhy]
    DSPhieuPhat dsPP= new DSPhieuPhat();
    DSChiTietPhieuPhat dsCTPP= new DSChiTietPhieuPhat();
    DSQuyDinhPhat dsQDP= new DSQuyDinhPhat();
    DSProvider dsProvider= new DSProvider();
    //of Hưng
    ListPhieuMuon dsPM= new ListPhieuMuon(); 

    public void menu() {
    }

    public QLThuVien() {
        dsAuthor.ReadFile();
        dsEmployee.ReadFile();
        dsPublisher.ReadFile();
        dsReader.ReadFile();
    }
}
