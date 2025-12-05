package SmartLibrary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

// Veritabanı bağlantısı ve tablo oluşturma işlerini topladığım sınıf
public class Database {

    // SQLite veritabanı dosyasının yolu
    private final String url = "jdbc:sqlite:smartlibrary.db";

    // Her seferinde yeni bir bağlantı oluşturmak için kullandığım metot
    public Connection getConnection() {
        try {
            // SQLite JDBC sürücüsünü yükletiyorum
            Class.forName("org.sqlite.JDBC");
            // ve bağlantıyı geri döndürüyorum
            return DriverManager.getConnection(url);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Uygulama ilk açıldığında tabloları oluşturmak için çağırdığım metot
    public void createTables() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

            // Kitaplar tablosu
            String createBooksSql = """
                    CREATE TABLE IF NOT EXISTS books (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        title TEXT,
                        author TEXT,
                        year INTEGER
                    );
                    """;

            // Öğrenciler tablosu
            String createStudentsSql = """
                    CREATE TABLE IF NOT EXISTS students (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        name TEXT,
                        department TEXT
                    );
                    """;

            // Ödünç kayıtları tablosu
            String createLoansSql = """
                    CREATE TABLE IF NOT EXISTS loans (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        bookId INTEGER,
                        studentId INTEGER,
                        dateBorrowed TEXT,
                        dateReturned TEXT
                    );
                    """;

            // Sorguları çalıştırıyorum
            stmt.execute(createBooksSql);
            stmt.execute(createStudentsSql);
            stmt.execute(createLoansSql);

            System.out.println("Tablolar oluşturuldu.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
