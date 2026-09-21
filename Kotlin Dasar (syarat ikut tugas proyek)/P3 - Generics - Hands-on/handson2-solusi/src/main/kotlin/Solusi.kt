// Hands-on 2: Bounded Type Parameter

// TODO 1 & 2:
fun <T : Comparable<T>> findMax(items: List<T>): T {
    // TODO 3:
    if (items.isEmpty()) {
        throw IllegalArgumentException("List tidak boleh kosong")
    }

    // TODO 4
    var max = items[0]
    for (item in items) {
        if (item > max) {
            max = item
        }
    }
    return max
}

fun main() {
    println(findMax(listOf(3, 7, 2, 9, 4)))          // 9
    println(findMax(listOf(1.5, 2.8, 0.3)))        // 2.8
    println(findMax(listOf("apel", "jeruk", "duku"))) // "jeruk" (alfabetis)
}

// === Penjelasan ===
// Todo 1 dan 2:
// fun: Kata kunci untuk mendeklarasikan fungsi di Kotlin
// <T : Comparable<T>> :Bounded Type Parameter. Ini membatasi tipe generik T agar hanya menerima tipe data yang mengimplementasikan
//                      interface Comparable<T> (seperti Int, Double, String, dll.). Batasan ini menjamin setiap elemen T memiliki
//                      metode pembandingan (compareTo atau operator '>')
// findMax: Nama dari fungsi
// (items: List<T>): Parameter input bernama items berupa list dengan elemen bertipe T
// : T -> Tipe pengembalian dari fungsi, yaitu elemen terbesar yang ditemukan dengan tipe T

// Todo 3:
// if (items.isEmpty()) {..} : Memeriksa apakah daftar items yang diterima tidak memiliki elemen sama sekali
// throw IllegalArgumentException("..") : Melempar exception 'IllegalArgumentException' jika daftar kosong, karena nilai maksimum
//                                        tidak dapat dicari dari kumpulan data yang tidak ada isinya

// Todo 4:
// var max = items[0] : Membuat variabel mutable max yang nilainya diinisialisasi dengan elemen pertama (index 0) dari items sebagai pembanding awal
// for (item in items) { : Melakukan loop untuk menelusuri setiap elemen items satu per satu. Elemen yang sedang diperiksa disimpan sementara pada variabel item
// if (item > max) { : Membandingkan apakah item saat ini lebih besar daripada max. Karena adanya batasan <T : Comparable<T>>,
//                     Kotlin mengonversi operator > secara internal menjadi item.compareTo(max) > 0
// max = item : Memperbarui nilai max dengan item jika ditemukan nilai yang lebih besar
