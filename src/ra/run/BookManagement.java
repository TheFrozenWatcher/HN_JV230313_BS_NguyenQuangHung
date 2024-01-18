package ra.run;

import ra.business.Book;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Scanner;


public class BookManagement {
    public static void main(String[] args) throws ParseException {

        Scanner sc = new Scanner(System.in);
        BookCrud bookCrud = new BookCrud();
        while (true) {
            System.out.println("--------------Menu--------------");
            System.out.println("1. Nhập số lượng sách thêm mới và nhập thông tin cho từng cuốn sách");
            System.out.println("2. Hiển thị thông tin tất cả sách trong thư viện ");
            System.out.println("3. Sắp xếp sách theo lợi nhuận tăng dần ");
            System.out.println("4. Xóa sách theo mã sách");
            System.out.println("5. Tìm kiếm tương đối sách theo tên sách hoặc mô tả");
            System.out.println("6. Thay đổi thông tin sách theo mã sách ");
            System.out.println("7. Thoát");
            System.out.println("Nhập lựa chọn");
            byte choice = Byte.parseByte(sc.nextLine());
            switch (choice) {
                case 1:
                    // hiển thi
                    bookCrud.addNewBooks(sc);
                    break;
                case 2:
                    bookCrud.displayAllBooks(bookCrud.getBooks());
                    break;
                case 3:
                    bookCrud.sortByInterest(sc);
                    break;
                case 4:
                    bookCrud.deleteBook(sc);
                    break;
                case 5:
                    bookCrud.findBook(sc);
                    break;
                case 6:
                    bookCrud.updateBook(sc);
                    break;
                case 7:
                    System.out.println("Kết thúc chương trình");
                    System.exit(0);
                    break;
                default:
                    System.err.println("Không đúng lệnh!");
                    break;
            }
        }
    }


}

class BookCrud {
    public static Book[] Books = new Book[0];
    private static final int MAX_BOOKS = 100;
    private static int nextBookId = 1;

    //    Getter
    public Book[] getBooks() {
        return Books;
    }

    //    Thêm mới sách
    public void addNewBooks(Scanner sc) throws ParseException {
        if (Books.length >= MAX_BOOKS) {
            System.err.println("Danh sách đã đầy, không thể thêm mới sách.");
            return;
        }

        // Nhập số lượng cần thêm
        System.out.println("Nhập số lượng cần thêm vào danh sách");
        int n = Integer.parseInt(sc.nextLine());

        // Tính số lượng sách có thể thêm mới mà không vượt quá giới hạn
        int remainingSpace = MAX_BOOKS - Books.length;
        // Lấy giá trị nhỏ hơn của số lượng cần thêm vào số lượng còn lại
        n = Math.min(n, remainingSpace);

        // Tạo mảng mới và copy giá trị cũ sang
        Book[] newBooks = new Book[Books.length + n];
        for (int i = 0; i < Books.length; i++) {
            newBooks[i] = Books[i];
        }

        if (newBooks.length >= 1) {
            for (int i = 0; i < n; i++) {
                Book newBook = new Book();

                do {
                    newBook.inputData();
//                     Id của phân tử sau phải lớn hơn phần tử trước

                } while (i > 0 && newBook.getBookId() <= newBooks[i - 1].getBookId());

                newBooks[Books.length + i] = newBook;
            }
        }

        Books = newBooks;
    }


    //    Hiện tất cả sách
    public void displayAllBooks(Book[] books) {
        if (books.length == 0) {
            System.err.println("Danh sách rỗng");
        } else {
            System.out.println("Danh sách sách hiện có:");
            for (int i = 0; i < books.length; i++) {
                if (books[i] != null) {
                    books[i].displayData();
                }
            }
        }
    }

    //    Tìm sách theo Id
    public int findIndexById(String id) {
        for (int i = 0; i < Books.length; i++) {
            if (String.valueOf(Books[i].getBookId()).equals(id)) {
                return i;
            }
        }
        return -1;
    }

    //    Sửa thông tin sách theo Id
    public void updateBook(Scanner sc) throws ParseException {
        // Nhập id cần sửa
        System.out.println("Nhập id cần sửa :");
        String id = sc.nextLine();
        int indexById = findIndexById(id);
        if (indexById != -1) {

            System.out.println("Thông tin cũ");
            System.out.println(Books[indexById]);
            System.out.println("Nhập thông tin mới");
            Books[indexById].inputData();
            System.out.println("Cập nhật thành công");
        } else {
            System.err.println("Không có id này");
        }
    }

    //    Xóa sách theo Id
    public void deleteBook(Scanner sc) {
        // Nhập id cần xóa
        System.out.println("Nhập id cần xoá :");
        String id = sc.nextLine();
        int indexById = findIndexById(id);
        if (indexById != -1) {
            Book[] newBooks = new Book[Books.length - 1];
            for (int i = 0; i < newBooks.length; i++) {
                if (i < indexById) {
                    newBooks[i] = Books[i];
                } else if (i > indexById) {
                    newBooks[i - 1] = Books[i];
                }
            }
            Books = newBooks;
            System.out.println("Xóa thành công");
        } else {
            System.err.println("Không có id này");
        }
    }

    //    Sắp xếp theo lợi nhuận
    public void sortByInterest(Scanner sc) {
        if (Books.length > 0) {
            int n = Books.length;
            Book[] newBooks = Arrays.copyOf(Books, n);
//            Tạo vòng lặp sắp xếp
            boolean swapped;
            do {
                swapped = false;
                for (int i = 0; i < n - 1; i++) {
                    if (newBooks[i].getInterest() - newBooks[i + 1].getInterest() > 0) {
                        Book X = newBooks[i];
                        newBooks[i] = newBooks[i + 1];
                        newBooks[i + 1] = X;
                        swapped = true;
                    }
                }
            } while (swapped);

            System.out.println("Danh sách đã được sắp xếp theo lợi nhuận tăng dần:");
//            Hiện danh sách đã sắp xếp
            displayAllBooks(newBooks);
        } else {
            System.err.println("Danh sách rỗng. Không có gì để sắp xếp.");
        }
    }

    //    Tìm sách theo tên hoặc mô tả
    public void findBook(Scanner sc) {
        System.out.println("Nhập từ khóa tìm kiếm (tên sách hoặc mô tả):");
        String keyword = sc.nextLine().toLowerCase();
//        Tạo vòng lặp để kiểm tra
        boolean found = false;

        for (Book book : Books) {
            if (book.getBookName().toLowerCase().contains(keyword) ||
                    book.getDescriptions().toLowerCase().contains(keyword)) {
                book.displayData();
                found = true;
            }
        }

        if (!found) {
            System.out.println("Không tìm thấy sách với từ khóa: " + keyword);
        }
    }

}
