package SmartLibrary;

// Kitapla ilgili temel bilgileri tuttuğum sınıf
public class Book {

    // Kitabın veritabanındaki benzersiz id değeri
    private int id;
    // Kitabın adı
    private String title;
    // Kitabın yazarı
    private String author;
    // Kitabın basım yılı
    private int year;

    // Boş constructor, bazı yerlerde nesne oluştururken işime yarayabilir
    public Book() {
    }

    // Yeni kitap eklerken (id'yi veritabanı ürettiği için id almayan constructor)
    public Book(String _title, String _author, int _year) {
        title = _title;
        author = _author;
        year = _year;
    }

    // Veritabanından okuduğum kayıtlar için (id dahil olan constructor)
    public Book(int _id, String _title, String _author, int _year) {
        id = _id;
        title = _title;
        author = _author;
        year = _year;
    }

    public int getId() {
        return id;
    }

    public void setId(int _id) {
        id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String _title) {
        title = _title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String _author) {
        author = _author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int _year) {
        year = _year;
    }

    // Konsolda kitapları yazdırırken daha okunaklı görünmesi için
    @Override
    public String toString() {
        return id + " - " + title + " - " + author + " - " + year;
    }
}
