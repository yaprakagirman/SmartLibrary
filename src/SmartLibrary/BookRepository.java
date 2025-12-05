package SmartLibrary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

// Kitaplarla ilgili tüm JDBC CRUD işlemlerini topladığım sınıf
public class BookRepository {

    // Veritabanı bağlantısını buradan alıyorum
    private Database database;

    public BookRepository(Database _database) {
        database = _database;
    }

    // Yeni kitap ekleme
    public void add(Book book) {
        String sql = "INSERT INTO books(title, author, year) VALUES(?, ?, ?)";

        try (Connection conn = database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setInt(3, book.getYear());

            pstmt.executeUpdate();

            System.out.println("Kitap eklendi: " + book.getTitle());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Tüm kitapları listeleme
    public ArrayList<Book> getAll() {
        ArrayList<Book> books = new ArrayList<>();

        String sql = "SELECT id, title, author, year FROM books";

        try (Connection conn = database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            // Her satır için bir Book nesnesi oluşturup listeye ekliyorum
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String author = rs.getString("author");
                int year = rs.getInt("year");

                Book book = new Book(id, title, author, year);
                books.add(book);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return books;
    }

    // Id'ye göre tek bir kitabı bulma
    public Book getById(int id) {
        String sql = "SELECT id, title, author, year FROM books WHERE id = ?";

        try (Connection conn = database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int bookId = rs.getInt("id");
                String title = rs.getString("title");
                String author = rs.getString("author");
                int year = rs.getInt("year");

                return new Book(bookId, title, author, year);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Kitap bulunamazsa null dönüyorum
        return null;
    }

    // Var olan bir kitabı güncelleme
    public void update(Book book) {
        String sql = "UPDATE books SET title = ?, author = ?, year = ? WHERE id = ?";

        try (Connection conn = database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setInt(3, book.getYear());
            pstmt.setInt(4, book.getId());

            pstmt.executeUpdate();

            System.out.println("Kitap güncellendi: " + book.getTitle());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Kitap silme
    public void delete(int id) {
        String sql = "DELETE FROM books WHERE id = ?";

        try (Connection conn = database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

            System.out.println("Kitap silindi (id = " + id + ")");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
