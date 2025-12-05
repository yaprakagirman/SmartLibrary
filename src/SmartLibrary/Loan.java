package SmartLibrary;

// Ödünç alma işlemlerini temsil eden sınıf
public class Loan {

    private int id;                // loans tablosundaki id
    private int bookId;            // hangi kitabın ödünç alındığı
    private int studentId;         // hangi öğrencinin aldığı
    private String dateBorrowed;   // ödünç alma tarihi
    private String dateReturned;   // geri teslim tarihi (başta null olabilir)

    public Loan() {
    }

    // Yeni ödünç verilirken kullandığım constructor
    // id'yi yine veritabanı oluşturuyor, dateReturned başta null
    public Loan(int _bookId, int _studentId, String _dateBorrowed) {
        bookId = _bookId;
        studentId = _studentId;
        dateBorrowed = _dateBorrowed;
        dateReturned = null;
    }

    // Veritabanından kayıt okurken kullandığım constructor
    public Loan(int _id, int _bookId, int _studentId,
                String _dateBorrowed, String _dateReturned) {
        id = _id;
        bookId = _bookId;
        studentId = _studentId;
        dateBorrowed = _dateBorrowed;
        dateReturned = _dateReturned;
    }

    public int getId() {
        return id;
    }

    public void setId(int _id) {
        id = _id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int _bookId) {
        bookId = _bookId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int _studentId) {
        studentId = _studentId;
    }

    public String getDateBorrowed() {
        return dateBorrowed;
    }

    public void setDateBorrowed(String _dateBorrowed) {
        dateBorrowed = _dateBorrowed;
    }

    public String getDateReturned() {
        return dateReturned;
    }

    public void setDateReturned(String _dateReturned) {
        dateReturned = _dateReturned;
    }

    // Konsolda ödünç listelerini yazdırırken kullanıyorum
    @Override
    public String toString() {
        return id + " - Book:" + bookId +
                " - Student:" + studentId +
                " - Borrowed:" + dateBorrowed +
                " - Returned:" + dateReturned;
    }
}
