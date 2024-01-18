package ra.business;

import java.util.Scanner;

public class Book {
    //    Tạo thuộc tính
    private int bookId;
    private String bookName;
    private String author;
    private String descriptions;
    private double importPrice;
    private double exportPrice;
    private float interest;
    private boolean bookStatus;

    //    Constructor
    public void book() {
    }

    //Tạo setter và getter
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public double getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(double importPrice) {
        this.importPrice = importPrice;
    }

    public double getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(double exportPrice) {
        this.exportPrice = exportPrice;
    }

    public float getInterest() {
        return interest;
    }

    public void setInterest(float interest) {
        this.interest = interest;
    }

    public boolean isBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(boolean bookStatus) {
        this.bookStatus = bookStatus;
    }

    public Float calInterest(double importPrice, double exportPrice) {
        return (float) (exportPrice - importPrice);
    }
//  Phương thức nhập thông tin
    public void inputData() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập thông tin sản phẩm");
//        Nhập id
        do {
            System.out.println("Nhập ID (số nguyên dương):");
            while (!sc.hasNextInt()) {
                System.out.println("Vui lòng nhập số nguyên dương cho ID:");
                sc.next();
            }
            this.bookId = sc.nextInt();
        } while (this.bookId <= 0);
//        Nhập tên
        sc.nextLine(); //
        do {
            System.out.println("Nhập tên sách:");
            this.bookName = sc.nextLine().trim();
        } while (this.bookName.isEmpty());
//        Nhập tác giả
        do {
            System.out.println("Nhập tên tác giả:");
            this.author = sc.nextLine();
            if (this.author.isEmpty()){
                System.out.println("Tên tác giả không được để trống:");
            }
        } while (this.author.isEmpty());
//        Nhập mô tả
        do {System.out.println("Nhập mô tả:");
            this.descriptions = sc.nextLine();
        } while (this.descriptions.isEmpty()||this.descriptions.length()<10);
//      Nhập giá nhập
        do {
            System.out.println("Nhập giá nhập (phải lớn hơn 0):");
            while (!sc.hasNextDouble()) {
                System.out.println("Vui lòng nhập giá nhập là số:");
                sc.next(); // hủy nhập lại
            }
            this.importPrice = sc.nextDouble();
        } while (this.importPrice <= 0);
//        Nhập giá xuất
        do {
            System.out.println("Nhập giá xuất (phải lớn hơn 1.2 lần giá nhập):");
            while (!sc.hasNextDouble()) {
                System.out.println("Vui lòng nhập giá xuất là số:");
                sc.next(); // hủy nhập lại
            }
            this.exportPrice = sc.nextDouble();
        } while (this.exportPrice <= 1.2 * this.importPrice);
//          Nhập trạng thái
        while (true) {
            System.out.println("Nhập trạng thái (true/false):");
            String statusInput = sc.nextLine().toLowerCase();

            if (statusInput.equals("true") || statusInput.equals("false")) {
                this.bookStatus = Boolean.parseBoolean(statusInput);
                break;
            } else {
                System.out.println("Vui lòng nhập giá trị true hoặc false.");
            }
        }
//        Tính lãi
        this.interest = calInterest(this.importPrice, this.exportPrice);
    }

//    Phương thức hiện thông tin
    public void displayData() {
        System.out.println("Mã sách: " + bookId
                + "\nTên sách: " + bookName
                + "\nTên tác giả: " + author
                + "\nMô tả: " + descriptions
                + "\nGiá nhập: " + importPrice
                + "\nGía xuất: " + exportPrice
                + "\nLợi nhuận: " + interest
                + "\nTrạng thái: " + (bookStatus ? "Có sẵn" : "Không có sẵn"));
    }
}

