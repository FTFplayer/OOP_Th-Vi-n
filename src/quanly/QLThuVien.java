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

    public void menu() {
    }

    public QLThuVien() {
        dsAuthor.ReadFile();
        dsEmployee.ReadFile();
        dsPublisher.ReadFile();
        dsReader.ReadFile();
    }
}
