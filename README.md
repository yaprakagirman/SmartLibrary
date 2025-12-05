# SmartLibrary – Java OOP & SQLite Kütüphane Yönetim Sistemi

SmartLibrary, Nesneye Dayalı Programlama dersi kapsamında geliştirdiğim **konsol tabanlı bir kütüphane yönetim uygulamasıdır**.
Katmanlı yapı, OOP ilkeleri, JDBC ve SQLite kullanarak küçük bir sistemi nasıl modüler hâle getirebileceğimi göstermek amacıyla tasarlandı.

Amaç; kitapları, öğrencileri ve ödünç alma işlemlerini düzenli bir mimariyle yönetmek ve temel CRUD işlemlerini uygulamaktır.


## Projenin Hedefleri

Bu proje ile özellikle şu alanlarda çalışmayı hedefledim:

* Nesneye dayalı sınıf tasarımı
* Kompozisyon ilişkisi kurulumu
* Constructor ve kapsülleme kullanımı
* Repository (veri erişim) yapısının tasarlanması
* JDBC ile veritabanı etkileşimi
* SQLite üzerinde kalıcı veri saklama
* Konsol tabanlı menü ile kullanıcı etkileşimi sağlama

Uygulama, küçük bir senaryoyu gerçek bir sistem gibi ele alarak derste öğrendiğim kavramları pekiştirmemi sağladı.


## Sınıf Mimarisi

Uygulama birkaç temel sınıf üzerinden çalışır:

### **Book**

Kitapların `id`, `title`, `author`, `year` bilgilerini tutar.

### **Student**

Öğrencilerin `id`, `name`, `department` bilgilerini içerir.

### **Loan**

Bir kitabın kim tarafından, ne zaman ödünç alındığını ve geri getirildiğini temsil eder.

### **Database**

SQLite bağlantısını yönetir ve tabloları uygulama ilk açıldığında otomatik olarak oluşturur.

### **Repository Sınıfları**

Veri tabanı işlemleri her tablo için ayrı Repository sınıflarında toplanmıştır:

* `BookRepository`
* `StudentRepository`
* `LoanRepository`

Bu sınıflar, ekleme, silme, güncelleme, tek kayıt getirme ve listeleme işlemlerini içerir.
Bu sayede kod yapısı hem temiz hem de genişletilebilir bir hâle gelir.


## Veri Tabanı Yapısı

Uygulama çalıştırıldığında SQLite üzerinde üç tablo otomatik oluşturulur:

* **books** → Kitap kayıtları
* **students** → Öğrenci kayıtları
* **loans** → Ödünç alma işlemleri

Tablolar, OOP sınıfları ile birebir uyumlu alanlar içerir.
Her kayıt AUTOINCREMENT ile oluşturulan bir `id` değerine sahiptir.


## Uygulama Menüsü

Program konsol üzerinden çalışır ve kullanıcıya şu işlemleri sunar:

### 1️⃣ Kitap Ekle

Kitap bilgileri alınır ve veritabanına kaydedilir.

### 2️⃣ Kitapları Listele

Tüm kitaplar okunaklı bir formatta görüntülenir.

### 3️⃣ Öğrenci Ekle

Yeni öğrenci sistemde kayıt altına alınır.

### 4️⃣ Öğrencileri Listele

Sistemdeki tüm öğrenciler görüntülenir.

### 5️⃣ Kitap Ödünç Ver

* Öğrenci ID ve Kitap ID alınır
* Kitap daha önce iade edilmemişse uyarı verir
* Uygunsa ödünç kaydı eklenir

### 6️⃣ Ödünç Listesini Görüntüle

Tüm ödünç alma işlemleri listelenir.

### 7️⃣ Kitap Geri Teslim Al

Kitaba ait aktif ödünç kaydı bulunur ve iade tarihi güncellenir.

### 0️⃣ Çıkış

Program sonlandırılır.

Menü, sistemin tüm iş akışını sade ve anlaşılır şekilde kontrol eder.


## Kullanılan Teknolojiler

* **Java (Nesneye Dayalı Programlama)**
* **IntelliJ IDEA**
* **JDBC**
* **SQLite**
* **ArrayList ile koleksiyon yönetimi**


## Öğrenme Sürecim

Bu projeyi geliştirirken özellikle şu konularda deneyim kazandım:

* Sınıflar arası ilişki kurarak mimariyi bölümlere ayırmak
* Veritabanı bağlantısını yönetmek ve try-with-resources kullanmak
* CRUD işlemlerini tasarlarken repository yapısını mantıklı kurgulamak
* SQLite ile kalıcı veri saklama mantığını kavramak
* Menü tabanlı uygulamalarda akış kontrolü yapmak

Gerçek bir uygulamanın temellerine benzeyen bu yapı sayesinde ders içeriğini çok daha iyi pekiştirdiğimi düşünüyorum.


## Projeyi Çalıştırma

Projeyi çalıştırmak için:

1. Kaynağı bilgisayarınıza indirin veya klonlayın
2. IntelliJ IDEA ile açın
3. `Main.java` dosyasını çalıştırın
4. SQLite veritabanı otomatik olarak oluşturulur
5. Menüyü kullanarak işlemlere başlayabilirsiniz

Her şey tamamen konsol üzerinden yönetilmektedir.


## Sonuç

SmartLibrary, küçük ama öğretici bir kütüphane yönetim uygulamasıdır.
OOP ilkelerini, JDBC ve SQLite kullanımını sade bir yapı içinde birleştirir.
Kod yapısı geliştirilebilir, anlaşılır ve modülerdir.

Gelecekte bu projeye arayüz ekleme, raporlama sistemi veya gelişmiş doğrulamalar gibi özellikler eklenebilir.


## Not

Bu README, projenin yapısını anlaşılır kılmak için yazılmıştır.
Kodlar ve sınıf ilişkileri GitHub deposu içinde detaylı şekilde incelenebilir.

