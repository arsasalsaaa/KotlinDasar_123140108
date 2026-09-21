// Hands-on 2: Lambda vs Function Reference

fun isEvenLength(s: String): Boolean = s.length % 2 == 0

fun toUpper(s: String): String = s.uppercase()

fun main() {
    val mahasiswa = listOf("Andi", "Budi", "Citra", "Dewi", "Eka", "Fajar")
    // TODO 1:
    val hasilLambda: List<String> = mahasiswa
        .filter {it.length % 2 == 0}
        .map {it.uppercase()}

    // TODO 2:
    val hasilReference: List<String> = mahasiswa
        .filter(::isEvenLength)
        .map(::toUpper)

    println("Lambda   : $hasilLambda")
    println("Reference: $hasilReference")
    println("Sama? ${hasilLambda == hasilReference}")
}

// === Penjelasan ===
// fun isEvenLength(s: String): Boolean = s.length % 2 == 0
// Ini adalah fungsi mandiri. Ia menerima satu kata (s), menghitung jumlah hurufnya (s.length), lalu mengecek
// apakah jumlah huruf itu habis dibagi 2 (% 2 == 0). Jika iya, ia mengembalikan nilai true (genap)

// fun toUpper(s: String): String = s.uppercase()
// Fungsi mandiri kedua. Ia menerima satu kata, lalu memodifikasinya menjadi huruf kapital semua
// menggunakan fungsi bawaan Kotlin .uppercase()

// val mahasiswa = listOf("Andi", "Budi", "Citra", "Dewi", "Eka", "Fajar")
// Membuat sebuah daftar (list) yang berisi 6 nama mahasiswa sebagai data mentah yang akan diolah

// val hasilLambda: List<String> = mahasiswa
// Membuat variabel baru bernama hasilLambda yang mengambil data awal dari list mahasiswa

// .filter { it.length % 2 == 0 }
// Langkah pertama pengolahan Lambda. Kata it secara otomatis mewakili setiap nama yang sedang dicek.
// Fungsi ini membuang nama yang jumlah hurufnya ganjil (membuang Citra, Eka, Fajar), dan meloloskan
// nama berhuruf genap (Andi, Budi, Dewi)

// .map { it.uppercase() }
// Langkah kedua pengolahan Lambda. Nama-nama yang lolos saringan (Andi, Budi, Dewi) dilempar ke sini,
// di mana it kembali mewakili setiap nama tersebut untuk diubah menjadi huruf besar semua (ANDI, BUDI, DEWI)

// val hasilReference: List<String> = mahasiswa
// Membuat variabel penampung kedua bernama hasilReference yang kembali mengambil data mentah dari list mahasiswa dari awal

// .filter(::isEvenLength)
// Langkah pertama pengolahan Function Reference. Daripada menulis ulang logika { it.length % 2 == 0 },
// gunakan .filter untuk meminjam dari fungsi isEvenLength yang sudah dibuat di paling atas baris kode
// menggunakan operator ::. Hasil saringannya persis sama

// .map(::toUpper)
// Langkah kedua pengolahan Function Reference. Sama seperti sebelumnya, gunakan .map untuk meminjam dari fungsi toUpper
// untuk mengubah teks menjadi huruf kapital