package SmartLibrary;

// Öğrencilerle ilgili temel bilgileri tuttuğum sınıf
public class Student {

    private int id;              // Veritabanı id
    private String name;         // Öğrencinin adı
    private String department;   // Öğrencinin bölümü

    public Student() {
    }

    // Yeni öğrenci eklerken kullandığım constructor (id yok)
    public Student(String _name, String _department) {
        name = _name;
        department = _department;
    }

    // Veritabanından okunan öğrenciler için (id dahil)
    public Student(int _id, String _name, String _department) {
        id = _id;
        name = _name;
        department = _department;
    }

    public int getId() {
        return id;
    }

    public void setId(int _id) {
        id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String _name) {
        name = _name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String _department) {
        department = _department;
    }

    // Listelerken daha düzgün görünmesi için
    @Override
    public String toString() {
        return id + " - " + name + " - " + department;
    }
}
