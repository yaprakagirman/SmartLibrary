package SmartLibrary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

// Ödünç alma (Loan) ile ilgili tüm veritabanı işlemlerini topladığım sınıf
public class LoanRepository {

    private Database database;

    public LoanRepository(Database _database) {
        database = _database;
    }

    // Yeni ödünç kaydı ekleme
    public void add(Loan loan) {
        String sql = "INSERT INTO loans(bookId, studentId, dateBorrowed, dateReturned) VALUES(?, ?, ?, ?)";

        try (Connection conn = database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, loan.getBookId());
            pstmt.setInt(2, loan.getStudentId());
            pstmt.setString(3, loan.getDateBorrowed());
            pstmt.setString(4, loan.getDateReturned()); // genelde null olacak

            pstmt.executeUpdate();

            System.out.println("Ödünç kaydı eklendi. (bookId = " + loan.getBookId() +
                    ", studentId = " + loan.getStudentId() + ")");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Tüm ödünç kayıtlarını listeleme
    public ArrayList<Loan> getAll() {
        ArrayList<Loan> loans = new ArrayList<>();

        String sql = "SELECT id, bookId, studentId, dateBorrowed, dateReturned FROM loans";

        try (Connection conn = database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                int bookId = rs.getInt("bookId");
                int studentId = rs.getInt("studentId");
                String dateBorrowed = rs.getString("dateBorrowed");
                String dateReturned = rs.getString("dateReturned");

                Loan loan = new Loan(id, bookId, studentId, dateBorrowed, dateReturned);
                loans.add(loan);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return loans;
    }

    // Id'ye göre tek ödünç kaydı bulma
    public Loan getById(int id) {
        String sql = "SELECT id, bookId, studentId, dateBorrowed, dateReturned FROM loans WHERE id = ?";

        try (Connection conn = database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int loanId = rs.getInt("id");
                int bookId = rs.getInt("bookId");
                int studentId = rs.getInt("studentId");
                String dateBorrowed = rs.getString("dateBorrowed");
                String dateReturned = rs.getString("dateReturned");

                return new Loan(loanId, bookId, studentId, dateBorrowed, dateReturned);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // Var olan bir ödünç kaydını genel olarak güncelleme
    public void update(Loan loan) {
        String sql = "UPDATE loans SET bookId = ?, studentId = ?, dateBorrowed = ?, dateReturned = ? WHERE id = ?";

        try (Connection conn = database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, loan.getBookId());
            pstmt.setInt(2, loan.getStudentId());
            pstmt.setString(3, loan.getDateBorrowed());
            pstmt.setString(4, loan.getDateReturned());
            pstmt.setInt(5, loan.getId());

            pstmt.executeUpdate();

            System.out.println("Ödünç kaydı güncellendi. (id = " + loan.getId() + ")");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Ödünç kaydı silme
    public void delete(int id) {
        String sql = "DELETE FROM loans WHERE id = ?";

        try (Connection conn = database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

            System.out.println("Ödünç kaydı silindi. (id = " + id + ")");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Belirli bir kitabın şu anda ödünçte olup olmadığını kontrol ediyorum
    // dateReturned NULL ise kitap hala öğrencide demektir
    public Loan getActiveLoanByBookId(int bookId) {
        String sql = "SELECT id, bookId, studentId, dateBorrowed, dateReturned " +
                "FROM loans WHERE bookId = ? AND dateReturned IS NULL";

        try (Connection conn = database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, bookId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                int studentId = rs.getInt("studentId");
                String dateBorrowed = rs.getString("dateBorrowed");
                String dateReturned = rs.getString("dateReturned"); // büyük ihtimalle null

                return new Loan(id, bookId, studentId, dateBorrowed, dateReturned);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Eğer null dönerse, kitap ödünçte değil demektir
        return null;
    }

    // Sadece iade tarihini güncellediğim özel bir metot
    public void updateReturnDate(int loanId, String dateReturned) {
        String sql = "UPDATE loans SET dateReturned = ? WHERE id = ?";

        try (Connection conn = database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, dateReturned);
            pstmt.setInt(2, loanId);

            pstmt.executeUpdate();

            System.out.println("Kitap geri teslim alındı. (loanId = " + loanId + ")");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
