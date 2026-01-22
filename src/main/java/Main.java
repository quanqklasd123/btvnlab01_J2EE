import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Book> listBook = new ArrayList<>();
        Scanner x = new Scanner(System.in);
        
        // Menu hiển thị
        String menu = """
                \n--- CHƯƠNG TRÌNH QUẢN LÝ SÁCH ---
                1. Thêm 1 cuốn sách
                2. Xóa 1 cuốn sách
                3. Thay đổi cuốn sách
                4. Xuất thông tin tất cả các cuốn sách
                5. Tìm cuốn sách có tựa đề chứa chữ "Lập trình"
                6. Lấy tối đa K cuốn sách có giá <= P
                7. Tìm kiếm theo danh sách tác giả
                0. Thoát
                Chọn chức năng: """;
        
        int chon = 0;
        do {
            System.out.print(menu);
            chon = Integer.parseInt(x.nextLine()); // Dùng parseInt để tránh lỗi trôi lệnh

            // Switch expression
            switch (chon) {
                case 1 -> {
                    System.out.println("--- Thêm sách mới ---");
                    Book newBook = new Book();
                    newBook.input();
                    listBook.add(newBook);
                    System.out.println("Đã thêm sách thành công!");
                }
                case 2 -> {
                    System.out.print("Nhập mã sách cần xóa: ");
                    int bookId = Integer.parseInt(x.nextLine());
                    // Xóa dùng removeIf (ngắn gọn hơn cách filter trong tài liệu một chút nhưng cùng logic)
                    boolean removed = listBook.removeIf(b -> b.getId() == bookId);
                    System.out.println(removed ? "Đã xóa thành công!" : "Không tìm thấy mã sách.");
                }
                case 3 -> {
                    System.out.print("Nhập mã sách cần sửa: ");
                    int bookId = Integer.parseInt(x.nextLine());
                    // Tìm sách và sửa
                    listBook.stream()
                            .filter(b -> b.getId() == bookId)
                            .findFirst()
                            .ifPresentOrElse(b -> {
                                System.out.println("Nhập thông tin mới:");
                                b.input(); 
                                System.out.println("Đã cập nhật!");
                            }, () -> System.out.println("Không tìm thấy sách!"));
                }
                case 4 -> {
                    System.out.println("--- Danh sách sách ---");
                    // Sử dụng Method Reference
                    listBook.forEach(Book::output);
                }
                case 5 -> {
                    // Tìm sách có chữ "Lập trình"
                    System.out.println("--- Sách lập trình ---");
                    listBook.stream()
                            .filter(b -> b.getTitle().toLowerCase().contains("lập trình"))
                            .forEach(Book::output);
                }
                case 6 -> {
                    // Yêu cầu 6: Lấy tối đa K cuốn sách có giá <= P
                    System.out.print("Nhập số lượng K: ");
                    int k = Integer.parseInt(x.nextLine());
                    System.out.print("Nhập mức giá P: ");
                    long p = Long.parseLong(x.nextLine());

                    System.out.println("--- Kết quả tìm kiếm ---");
                    listBook.stream()
                            .filter(b -> b.getPrice() <= p) // Lọc giá
                            .limit(k)                       // Giới hạn số lượng
                            .forEach(Book::output);
                }
                case 7 -> {
                    // Yêu cầu 7: Tìm theo danh sách tác giả nhập từ bàn phím
                    System.out.print("Nhập danh sách tác giả (ngăn cách bởi dấu phẩy): ");
                    String inputAuthors = x.nextLine();
                    // Chuyển chuỗi nhập vào thành Set để tìm kiếm nhanh hơn
                    Set<String> authorSet = Arrays.stream(inputAuthors.split(","))
                                                  .map(String::trim) // Xóa khoảng trắng thừa
                                                  .collect(Collectors.toSet());

                    System.out.println("--- Sách của các tác giả: " + authorSet + " ---");
                    listBook.stream()
                            .filter(b -> authorSet.contains(b.getAuthor())) // Kiểm tra tác giả có trong Set không
                            .forEach(Book::output);
                }
                case 0 -> System.out.println("Đã thoát chương trình.");
                default -> System.out.println("Chức năng không tồn tại!");
            }
        } while (chon != 0);
    }
}