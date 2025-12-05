package SmartLibrary;

import java.util.ArrayList;
import java.util.Scanner;

// Uygulamanın çalıştığı ana sınıf.
// Konsol menüsünü burada yönetiyorum.
public class Main {

    public static void main(String[] args) {

        // Veritabanını hazırlıyorum (dosya + tablolar)
        Database db = new Database();
        db.createTables();

        // Repository nesnelerini oluşturuyorum
        BookRepository bookRepo = new BookRepository(db);
        StudentRepository studentRepo = new StudentRepository(db);
        LoanRepository loanRepo = new LoanRepository(db);

        Scanner scanner = new Scanner(System.in);

        boolean running = true;

        // Kullanıcı çıkış yapana kadar menüyü döndürdüğüm ana döngü
        while (running) {
            printMenu();
            System.out.print("Seçiminiz: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    // Kitap Ekleme
                    System.out.print("Kitap adı (title): ");
                    String title = scanner.nextLine();

                    System.out.print("Yazar (author): ");
                    String author = scanner.nextLine();

                    System.out.print("Yıl (year): ");
                    int year = Integer.parseInt(scanner.nextLine());

                    Book newBook = new Book(title, author, year);
                    bookRepo.add(newBook);
                    break;

                case "2":
                    // Kitapları Listeleme
                    System.out.println("\n--- Kitap Listesi ---");
                    ArrayList<Book> books = bookRepo.getAll();
                    for (Book b : books) {
                        System.out.println(b);
                    }
                    System.out.println();
                    break;

                case "3":
                    // Öğrenci Ekleme
                    System.out.print("Öğrenci adı (name): ");
                    String name = scanner.nextLine();

                    System.out.print("Bölüm (department): ");
                    String department = scanner.nextLine();

                    Student newStudent = new Student(name, department);
                    studentRepo.add(newStudent);
                    break;

                case "4":
                    // Öğrencileri Listeleme
                    System.out.println("\n--- Öğrenci Listesi ---");
                    ArrayList<Student> students = studentRepo.getAll();
                    for (Student s : students) {
                        System.out.println(s);
                    }
                    System.out.println();
                    break;

                case "5":
                    // Kitap Ödünç Verme
                    System.out.print("Öğrenci ID: ");
                    int studentId = Integer.parseInt(scanner.nextLine());

                    System.out.print("Kitap ID: ");
                    int bookId = Integer.parseInt(scanner.nextLine());

                    System.out.print("Ödünç alma tarihi (YYYY-MM-DD): ");
                    String dateBorrowed = scanner.nextLine();

                    // Basit kontrol: kitap şu anda ödünçte mi?
                    Loan activeLoan = loanRepo.getActiveLoanByBookId(bookId);
                    if (activeLoan != null) {
                        System.out.println("Bu kitap şu anda zaten ödünçte. (loanId = " + activeLoan.getId() + ")\n");
                    } else {
                        Loan loan = new Loan(bookId, studentId, dateBorrowed);
                        loanRepo.add(loan);
                    }
                    break;

                case "6":
                    // Ödünç Kayıtlarını Listeleme
                    System.out.println("\n--- Ödünç Kayıtları ---");
                    ArrayList<Loan> loans = loanRepo.getAll();
                    for (Loan l : loans) {
                        System.out.println(l);
                    }
                    System.out.println();
                    break;

                case "7":
                    // Kitap Geri Teslim Alma
                    System.out.print("Geri teslim alınacak kitabın ID'si (bookId): ");
                    int returnBookId = Integer.parseInt(scanner.nextLine());

                    // Bu kitap için aktif ödünç var mı kontrol ediyorum
                    Loan loanToReturn = loanRepo.getActiveLoanByBookId(returnBookId);
                    if (loanToReturn == null) {
                        System.out.println("Bu kitap için aktif (iade edilmemiş) ödünç kaydı bulunamadı.\n");
                    } else {
                        System.out.print("Geri teslim tarihi (YYYY-MM-DD): ");
                        String dateReturned = scanner.nextLine();

                        loanRepo.updateReturnDate(loanToReturn.getId(), dateReturned);
                    }
                    break;

                case "0":
                    // Programdan çıkış
                    running = false;
                    System.out.println("Programdan çıkılıyor...");
                    break;

                default:
                    // Yanlış seçim durumu
                    System.out.println("Geçersiz seçim, tekrar deneyin.\n");
                    break;
            }
        }

        scanner.close();
    }

    // Menüyü ekrana yazdırdığım küçük yardımcı metot
    private static void printMenu() {
        System.out.println("===== SmartLibrary Menü =====");
        System.out.println("1 - Kitap Ekle");
        System.out.println("2 - Kitapları Listele");
        System.out.println("3 - Öğrenci Ekle");
        System.out.println("4 - Öğrencileri Listele");
        System.out.println("5 - Kitap Ödünç Ver");
        System.out.println("6 - Ödünç Listesini Görüntüle");
        System.out.println("7 - Kitap Geri Teslim Al");
        System.out.println("0 - Çıkış");
        System.out.println("=============================");
    }
}
