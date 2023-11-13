# Spring Boot Boot Projesi

Bu proje, Codecademy üzerinden alınan Spring Learning kursunda gerçekleştirilen örnek bir Spring Boot projesidir. Proje, JDBC ve ORM kavramlarını pekiştirmek amacıyla geliştirilmiştir.

## Proje Yapısı

Proje, temel olarak üç ana bileşen içermektedir:

### 1. Controller

`BootController` sınıfı, Spring Boot uygulamasının API'larını yönetir. Projenin ana iş mantığını içerir ve gelen isteklere yanıt verir.

- `getAllBoots()`: Tüm botları getiren bir GET isteğine yanıt verir.
- `getBootTypes()`: Tüm bot türlerini getiren bir GET isteğine yanıt verir.
- `addBoot(Boot boot)`: Yeni bir bot eklemek için POST isteğine yanıt verir.
- `deleteBoot(Integer id)`: Belirli bir botu silmek için DELETE isteğine yanıt verir.
- `incrementQuantity(Integer id)`: Belirli bir botun miktarını artırmak için PUT isteğine yanıt verir.
- `decrementQuantity(Integer id)`: Belirli bir botun miktarını azaltmak için PUT isteğine yanıt verir.
- `searchBoots(String material, BootType type, Float size, Integer minQuantity)`: Botları aramak için GET isteğine yanıt verir.

### 2. Model (Entity)

`Boot` sınıfı, bot nesnesinin temsilidir. JPA (Java Persistence API) ile birlikte kullanılarak veritabanına kaydedilebilir.

### 3. Repository

`BootRepository` interface'i, CRUD işlemleri için gerekli metodları içerir. Bu metodlar, veritabanından botları çekmek veya belirli kriterlere göre sorgulamak için kullanılır.

## Kullanılan Teknolojiler

- **Spring Boot**: Hızlı ve basit bir şekilde Spring uygulamaları geliştirmek için kullanılmıştır.
- **Spring Data JPA**: Veritabanı işlemlerini kolaylaştırmak ve hızlandırmak için kullanılmıştır.
- **Maven**: Proje bağımlılıklarını yönetmek ve proje yapılandırmasını kolaylaştırmak amacıyla kullanılmıştır.

## Nasıl Çalıştırılır

1. Proje dizinine gidin.
2. `mvn clean install` komutunu kullanarak projeyi derleyin.
3. `java -jar target/boot-example.jar` komutuyla uygulamayı başlatın.

Uygulama, varsayılan olarak `http://localhost:8080` adresinde çalışacaktır.
