package danhsach;
import java.io.FileInputStream;
import java.io.PrintWriter;
// Mở luồng đọc file ký tự
import java.util.Arrays; // Đọc từng dòng văn bản tối ưu, nhanh chóng
import java.util.Scanner; // Mở luồng ghi file ký tự
import model.QuyDinhPhat; // Ghi dữ liệu dạng dòng văn bản (println, printf)

public class DSQuyDinhPhat {
    private QuyDinhPhat[] dsQDP;
    private int n;
    Scanner sc= new Scanner(System.in);

    public DSQuyDinhPhat() {
        this.n = 0;
        this.dsQDP = new QuyDinhPhat[100];
    }

    public DSQuyDinhPhat(QuyDinhPhat[] dsQDP, int n) {
        this.dsQDP = dsQDP;
        this.n = n;
    }

    public int getSoLuong() {
        return n;
    }

    public QuyDinhPhat getQDP(int index) {
        if (index < 0 || index >= n) {
            throw new IndexOutOfBoundsException("Vi tri quy dinh phat khong hop le.");
        }
        return dsQDP[index];
    }

    public void Input() {
        System.out.print("Nhap so luong quy dinh phat: ");
        int sl = Integer.parseInt(sc.nextLine());
        this.dsQDP = new QuyDinhPhat[sl];
        n = 0;
        for (int i = 0; i < sl; i++) {
            Them(sc);
        }

    }

    public void Output() {
        System.out.println("DANH SACH QUY DINH PHAT");
        System.out.printf("|%-10s|%-30s|%-14s|%n", "MA QDP", "TEN LY DO", "CACH THUC PHAT");
        for (int i = 0; i < n; i++) {
            dsQDP[i].Output();
        }
        if (n == 0) {
            System.out.println(" Thu vien chua co quy dinh phat!!");
        }
    }

    public int TimViTri(String maLD) {
        for (int i = 0; i < n; i++) {
            if (dsQDP[i].getMaLD().equalsIgnoreCase(maLD)) {
                return i;
            }
        }
        return -1;
    }

    public QuyDinhPhat Tim(String maLD) {
        int viTri = TimViTri(maLD);
        return viTri == -1 ? null : dsQDP[viTri];
    }

    public QuyDinhPhat Tim(Scanner sc) {
        System.out.print("Nhap ma quy dinh can tim: ");
        QuyDinhPhat quyDinh = Tim(sc.nextLine().trim());
        if (quyDinh == null) {
            System.out.println("Khong tim thay quy dinh phat.");
        }
        return quyDinh;
    }


   

    public void TimQuyDinh(Scanner sc) {
        QuyDinhPhat quyDinh = Tim(sc);
        if (quyDinh != null) {
            quyDinh.Output();
        } else
            System.out.println("Khong tim thay quy dinh phat");
    }

    public void Them(Scanner sc) {
        QuyDinhPhat quyDinh = new QuyDinhPhat();
        quyDinh.Input(sc);
        while (quyDinh.getMaLD().isEmpty() || Tim(quyDinh.getMaLD()) != null) {
            System.out.println("Ma ly do rong hoac da ton tai.");
            System.out.print("Nhap lai ma ly do: ");
            quyDinh.setMaLD(sc.nextLine().trim());
        }
        Them(quyDinh);
    }

    public void Them(QuyDinhPhat quyDinh) {
        if (Tim(quyDinh.getMaLD()) != null) {
            throw new IllegalArgumentException("Ma ly do phat da ton tai.");
        }
        if (n == dsQDP.length) {
            dsQDP = Arrays.copyOf(dsQDP, dsQDP.length + 1);
        }
        dsQDP[n++] = quyDinh;

    }

    public boolean Xoa(String maLD) {
        int viTri = TimViTri(maLD);
        if (viTri == -1) {
            return false;
        }
        for (int i = viTri; i < n - 1; i++) {
            dsQDP[i] = dsQDP[i + 1];
        }
        dsQDP[--n] = null;
        return true;
    }

    public void Xoa(Scanner sc) {
        System.out.print("Nhap ma ly do can xoa: ");
        String maLD = sc.nextLine().trim();
        if (Xoa(maLD))
            System.out.println("Xoa quy dinh thanh cong.");
        else {
            System.out.println("Khong tim thay quy dinh phat.");
        }
    }

    public void Sua(Scanner sc) {
        QuyDinhPhat quyDinh = Tim(sc);
        if (quyDinh == null) {
            return;
        }
        System.out.print("Nhap ten ly do moi: ");
        quyDinh.setTenLD(sc.nextLine().trim());
        System.out.print("Nhap so tien quy dinh moi: ");
        quyDinh.setSoTien(Double.parseDouble(sc.nextLine()));
        System.out.println("Sua quy dinh thanh cong.");
    }

    public void ThongKe() {
        System.out.println("Tong so quy dinh: " + n);
    }



    //Hàm tính tiền phạt dựa theo mã lý do phạt
    public double TinhTienPhat(String maLD,double soNgayTre,String maSach, DSBook dsb)
    {
        if(TimViTri(maLD)== -1)
            return -1;
        QuyDinhPhat qd= Tim(maLD);
        if(maLD.equals("QD01"))
            return soNgayTre* 10;
        double giaSach=dsb.timkiemBook(maSach).getDonGia();
        if(maLD.equals("QD02"))
            return giaSach;
        else
            return giaSach*2;

    }




     public void WriteFile() {
     PrintWriter pw = null;

    try {

        pw = new PrintWriter("data/QuyDinhPhat.txt");

        for (int i = 0; i < n; i++) {

            pw.println(
                dsQDP[i].getMaQDP() + "," +
                dsQDP[i].getTenLD() + "," +
                dsQDP[i].getMoTa()
            );
        }

        System.out.println("Ghi file thanh cong!");

    } catch (Exception e) {
        System.out.println("Loi ghi file: " + e.getMessage());
    } finally {
        if (pw != null)
            pw.close();
    }
}


   public void ReadFile() {
    Scanner sc = null;
    FileInputStream inputStream = null;

    try {
        inputStream = new FileInputStream("data/QuyDinhPhat.txt");
        sc = new Scanner(inputStream);

        n = 0;

        while (sc.hasNextLine()) {

            String line = sc.nextLine().trim();

            if (line.isEmpty())
                continue;

            String[] data = line.split(",");

            if (data.length >= 3) {

                dsQDP[n] = new QuyDinhPhat();

                dsQDP[n].setMaQDP(data[0].trim());
                dsQDP[n].setTenLD(data[1].trim());
                dsQDP[n].setMoTa(data[2].trim());

                n++;
            }
        }

        System.out.println("Doc file thanh cong!");

    } catch (Exception e) {
        System.out.println("Loi doc file: " + e.getMessage());
    } finally {
        if (sc != null)
            sc.close();
    }
}

}
