// Hands-on 1: Try-Catch-Finally Dasar (Solusi)

fun safeDivide(a: Int, b: Int): Int {
    return try {
        // TODO 1: Bungkus pembagian a / b di dalam blok try
        a / b
    } catch (e: ArithmeticException) {
        // TODO 2: Tangkap ArithmeticException, cetak pesan error yang jelas,
        //         lalu kembalikan 0 sebagai nilai default
        println("Error: Pembagian dengan nol ($a / $b)")
        0
    } finally {
        // TODO 3: Di blok finally, cetak "safeDivide($a, $b) selesai diproses"
        //         (finally HARUS tetap jalan baik sukses maupun error)
        println("safeDivide($a, $b) selesai diproses")
    }
}

fun main() {
    println("Hasil 1: ${safeDivide(10, 2)}")  // sukses -> 5
    println("Hasil 2: ${safeDivide(10, 0)}")  // b == 0 -> harus ditangani, hasil 0
    println("Hasil 3: ${safeDivide(9, 3)}")   // sukses -> 3
}

// === Penjelasan ====
// TODO 1 (Blok try):
// Pembagian a / b ditempatkan di dalam blok try untuk memantau kemungkinan runtime exception saat b bernilai 0.
// TODO 2 (Blok catch):
// Menangkap ArithmeticException khusus untuk pembagian dengan nol. Di dalamnya, pesan error dicetak ke konsol dan mengembalikan nilai 0 sebagai fallback/default return agar program tidak crash.
// TODO 3 (Blok finally):
// Blok finally dijamin selalu dieksekusi baik operasi pembagian di try berhasil maupun gagal ditangkap di catch. Di sini kita mencetak teks "safeDivide($a, $b) selesai diproses".