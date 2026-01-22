import java.util.Scanner;

public class Book {
    // Khai báo thuộc tính
    private int id;
    private String title;
    private String author;
    private long price; // Tài liệu ghi double text nhưng code dùng long/nextLong, ta dùng long theo code mẫu

    // Constructor không tham số
    public Book() {
    }

    // Constructor đầy đủ tham số
    public Book(int id, String title, String author, long price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
    }

    // Getter và Setter (Bạn có thể tự generate trong VS Code)
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public long getPrice() { return price; }
    public void setPrice(long price) { this.price = price; }

    // Hàm nhập thông tin sách
    public void input() {
        Scanner x = new Scanner(System.in);
        System.out.print("Nhập mã sách: ");
        // Dùng parseInt để tránh lỗi trôi lệnh khi dùng nextLine phía sau
        this.id = Integer.parseInt(x.nextLine()); 
        
        System.out.print("Nhập tên sách: ");
        this.title = x.nextLine();
        
        System.out.print("Nhập tác giả: ");
        this.author = x.nextLine();
        
        System.out.print("Nhập đơn giá: ");
        this.price = x.nextLong();
    }

    // Hàm xuất thông tin sách dùng Text Block và format
    public void output() {
        // Lưu ý: Text Block (""") yêu cầu Java 15 trở lên
        String msg = """
                BOOK: id= %d, title=%s, author=%s, price=%d
                """.formatted(id, title, author, price);
        System.out.print(msg);
    }
}
