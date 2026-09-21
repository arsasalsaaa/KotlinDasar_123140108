// Hands-on 1: Higher-Order Function

fun calculate(a: Int, b: Int, operation: (Int, Int) -> Int): Int {
    // TODO 1: Panggil `operation` dengan a dan b, lalu kembalikan hasilnya
    return operation (a,b)
}

fun main() {
    // TODO 2: Panggil calculate(10, 4, ...) dengan lambda penjumlahan -> harus mencetak 144
    val tambah = calculate(10, 4) {x, y -> x + y}
    println("Tambah: $tambah")

    // TODO 3: Panggil calculate(10, 4, ...) dengan lambda pengurangan -> harus mencetak 6
    val kurang = calculate(10, 4) {x, y -> x - y}
    println("Kurang: $kurang")

    // TODO 4: Panggil calculate(10, 4, ...) dengan lambda perkalian -> harus mencetak 40
    val kali = calculate(10, 4) {x, y -> x * y}
    println("Kali: $kali")
}

// === Penjelasan ===
// return operation(a, b): Fungsi calculate disebut sebagai Higher-Order Function karena ia menerima fungsi lain (yaitu operation)
// sebagai parameternya. Dengan mendelegasikan tugas tersebut kepada instruksi apa pun yang diteruskan kepadanya nanti

// Trailing Lambda (Sintaks { x, y -> ... }): Di Kotlin, jika parameter terakhir dari sebuah fungsi adalah fungsi lain (seperti operation),
// maka bisa memisahkan kurung kurawal {} ke luar kurung bulat (). Inilah mengapa penulisannya bisa sangat rapi menjadi
// calculate(10, 4) { x, y -> x + y } tanpa harus memasukkan fungsinya ke dalam tanda kurung bulat

// Fleksibilitas utama dari konsep ini adalah kita bisa mendaur ulang satu fungsi calculate untuk melakukan operasi matematika tak terbatas
// (tambah, kurang, kali, bagi, modulus) hanya dengan mengubah isi lambda-nya di pemanggilan main()