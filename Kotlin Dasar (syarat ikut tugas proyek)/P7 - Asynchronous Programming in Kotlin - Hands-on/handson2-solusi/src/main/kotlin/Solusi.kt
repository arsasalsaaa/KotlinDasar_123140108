import kotlinx.coroutines.*

// Hands-on 2: launch vs async
// Tugas: Ada 2 tugas simulasi — mencatat log (tidak perlu hasil) dan
// menghitung total harga belanja (perlu hasil untuk ditampilkan).
// Gunakan coroutine builder yang TEPAT untuk masing-masing:
// - launch  -> fire-and-forget, cocok untuk tugas yang tidak butuh nilai balik.
// - async   -> mengembalikan Deferred<T>, cocok kalau hasilnya dibutuhkan.

suspend fun writeAccessLog(message: String) {
    delay(500)
    println("[LOG] $message")
}

suspend fun calculateTotalPrice(prices: List<Int>): Int {
    delay(700)
    return prices.sum()
}

fun main() = runBlocking {
    val prices = listOf(15000, 25000, 10000)

    // TODO 1: Jalankan writeAccessLog("Checkout dimulai") dengan launch
    //         (kita tidak butuh return value-nya).
    launch {
        writeAccessLog("Checkout dimulai")
    }

    // TODO 2: Jalankan calculateTotalPrice(prices) dengan async, simpan
    //         Deferred<Int>-nya ke sebuah variabel.
    val deferredTotal = async {
        calculateTotalPrice(prices)
    }

    // TODO 3: Ambil hasil total dari Deferred tersebut dengan await(),
    //         lalu cetak "Total belanja: Rp<total>".
    val total = deferredTotal.await()
    println("Total belanja: Rp$total")
}

// === Penjelasan ===
// TODO 1 (launch { writeAccessLog("Checkout dimulai") })
// Menggunakan coroutine builder launch (fire-and-forget). launch digunakan untuk menjalankan tugas latar belakang yang tidak mengembalikan nilai (return value), seperti mencatat log akses.
// Coroutine ini langsung dijadwalkan untuk dieksekusi secara asinkron tanpa menghentikan langkah perintah berikutnya.

// TODO 2 (val deferredTotal = async { calculateTotalPrice(prices) })
// Menggunakan coroutine builder async. Berbeda dengan launch, async mengembalikan objek Deferred<T> (di sini Deferred<Int>), yang merepresentasikan janji (future/promise) akan adanya nilai hasil perhitungan di masa depan.
// Fungsi calculateTotalPrice dipanggil secara asinkron di dalam blok ini.

// TODO 3 (val total = deferredTotal.await() dan println(...))
// Pemanggilan deferredTotal.await() menangguhkan (suspend) coroutine hingga proses perhitungan pada async selesai dan mengembalikan hasilnya.
// Setelah mendapatkan nilai total (50000), hasilnya dicetak ke konsol dengan format "Total belanja: Rp50000".