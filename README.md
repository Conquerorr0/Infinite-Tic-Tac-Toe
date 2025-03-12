# Sonsuz Tic-Tac-Toe

Bu proje, klasik Tic-Tac-Toe (XOX) oyununun yenilikçi bir varyasyonudur. Android platformu için Java programlama dili kullanılarak geliştirilmiştir.

## Oyun Özellikleri

- Dinamik Hamle Sistemi: Her yeni hamle yapıldığında, en eski hamle silinir (n. hamlede n-3. hamle silinir)
- 3x3'lük Klasik Oyun Tahtası
- Sıra Tabanlı Oynanış: X ve O sırayla hamle yapar
- Kazanan Belirleme: Yatay, dikey veya çapraz olarak aynı sembolleri sıralayan ilk oyuncu kazanır
- Hamle Geçmişi: Önceki hamlelerin takibi
- Modern ve Kullanıcı Dostu Arayüz

## Teknolojiler

- Java
- Android SDK
- XML tabanlı kullanıcı arayüzü tasarımı
- Android View sistemi
- Gradle build sistemi

## Kurulum

1. Projeyi klonlayın:
```bash
git clone https://github.com/yourusername/Infinite-Tic-Tac-Toe.git
```

2. Android Studio'da projeyi açın

3. Gradle sync işlemini tamamlayın

4. Uygulamayı bir Android cihazda veya emülatörde çalıştırın

## Geliştirme

Bu proje aşağıdaki minimum gereksinimlere sahiptir:

- Android Studio 
- Android SDK 21 veya üzeri
- JDK 8 veya üzeri

## Nasıl Oynanır?

1. Oyuncular sırayla X ve O işaretlerini yerleştirir
2. Her yeni hamle yapıldığında, en eski hamle tahtadan silinir
   - Örneğin: 4. hamle yapıldığında 1. hamle silinir
   - 5. hamle yapıldığında 2. hamle silinir
3. Oyun, bir oyuncu yatay, dikey veya çapraz olarak üç sembolü sıralayıncaya kadar devam eder
4. Kazanan oyuncu ilan edilir

## Lisans

Bu proje MIT lisansı altında lisanslanmıştır. Daha fazla bilgi için `LICENSE` dosyasına bakınız.

## Sorumluluk Reddi

Bu proje, orijinal oyundan esinlenilerek yapılmış bir klondur. Bu deponun sahibi, orijinal ürünün sahibi olan şirket ile herhangi bir bağlantısı veya ilişkisi yoktur.