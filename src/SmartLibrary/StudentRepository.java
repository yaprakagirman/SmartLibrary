package SmartLibrary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

// Öğrencilerle ilgili tüm veritabanı işlemlerini topladığım sınıf
public class StudentRepository {

    private Database database;

    public StudentRepository(Database _database) {
        database = _database;
    }

    // Yeni öğrenci ekleme
    public void add(Student student) {
        String sql = "INSERT INTO students(name, department) VALUES(?, ?)";

        try (Connection conn = database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getDepartment());

            pstmt.executeUpdate();

            System.out.println("Öğrenci eklendi: " + student.getName());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Tüm öğrencileri listeleme
    public ArrayList<Student> getAll() {
        ArrayList<Student> students = new ArrayList<>();

        String sql = "SELECT id, name, department FROM students";

        try (Connection conn = database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String department = rs.getString("department");

                Student student = new Student(id, name, department);
                students.add(student);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return students;
    }

    // Id'ye göre tek öğrenci bulma
    public Student getById(int id) {
        String sql = "SELECT id, name, department FROM students WHERE id = ?";

        try (Connection conn = database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int studentId = rs.getInt("id");
                String name = rs.getString("name");
                String department = rs.getString("department");

                return new Student(studentId, name, department);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // Öğrenci güncelleme
    public void update(Student student) {
        String sql = "UPDATE students SET name = ?, department = ? WHERE id = ?";

        try (Connection conn = database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getDepartment());
            pstmt.setInt(3, student.getId());

            pstmt.executeUpdate();

            System.out.println("Öğrenci güncellendi: " + student.getName());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Öğrenci silme
    public void delete(int id) {
        String sql = "DELETE FROM students WHERE id = ?";

        try (Connection conn = database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

            System.out.println("Öğrenci silindi (id = " + id + ")");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
