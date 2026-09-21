// Hands-on 3: Closure — Counter Factory

fun makeCounter(): () -> Int {
    // TODO 1: Deklarasikan `var count` bertipe Int, mulai dari 0.
    var count = 0

    // TODO 2: Kembalikan sebuah lambda `() -> Int` yang menambah `count`
    return {
        count += 1
        count
    }
}

fun main() {
    val counterA = makeCounter()
    val counterB = makeCounter()

    println(counterA()) // 1
    println(counterA()) // 2
    println(counterA()) // 3

    println(counterB()) // 1 (counterB independen dari counterA)
    println(counterB()) // 2
}

// === Penjelasan ===
// fun makeCounter(): () -> Int
// Ini adalah fungsi yang tugasnya "mencetak" fungsi lain. Perhatikan nilai baliknya: () -> Int. Ini
// berarti ketika memanggil makeCounter(), ia tidak mengembalikan angka, melainkan mengembalikan sebuah
// fungsi yang jika nanti dijalankan akan menghasilkan angka (Int)

// var count = 0
// Membuat variabel memori biasa di dalam makeCounter. Secara teori normal, variabel lokal seperti ini akan
// hilang dari memori begitu fungsi makeCounter selesai dijalankan

// return { count += 1; count }
// Mengembalikan sebuah fungsi anonim (Lambda). Karena Lambda ini menggunakan variabel count yang ada
// di luar tubuhnya, ia "menangkap" dan mengunci variabel tersebut. Meskipun makeCounter sudah selesai
// dieksekusi, memori count ini tidak dihapus oleh sistem, melainkan terus dihidupkan untuk melayani Lambda tersebut.

// val counterA = makeCounter()
// Memanggil pembuat counter. counterA kini berisi sebuah fungsi Lambda yang membawa memori count-nya sendiri
// yang bernilai 0. Saat dipanggil berulang kali (counterA()), fungsi tersebut menambah ingatannya (menjadi 1, 2, 3).

// val counterB = makeCounter()
// Sistem akan menciptakan ruang memori count = 0 yang benar-benar baru dan terpisah dari counterA.
// Itulah mengapa hitungan counterB dimulai dari 1 lagi, tidak terpengaruh oleh hitungan counterA