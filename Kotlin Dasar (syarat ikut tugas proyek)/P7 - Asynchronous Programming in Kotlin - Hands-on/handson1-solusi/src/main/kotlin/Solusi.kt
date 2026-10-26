import kotlinx.coroutines.*

// Hands-on 1: Suspend Function Dasar
// Tugas: Buat suspend function checkOrderStatus() yang mensimulasikan
// panggilan ke server untuk mengecek status pesanan (delay 2 detik),
// lalu panggil dari dalam coroutine sehingga main thread TIDAK ter-blok
// selama menunggu.
//
// Ingat: "One cannot just walk into a suspending function" — suspend fun
// hanya boleh dipanggil dari coroutine (launch/runBlocking/async) atau
// dari suspend fun lain.

suspend fun checkOrderStatus(orderId: String): String {
    // TODO 1: Simulasikan network delay 2000ms dengan delay()
    delay(2000)

    // TODO 2: Kembalikan String "Order $orderId: SHIPPED"
    return "Order $orderId: SHIPPED"
}

fun main() = runBlocking {
    // TODO 4: Sebelum menunggu hasilnya, cetak dulu "Menunggu status pesanan..."
    //         supaya terlihat bahwa program tetap berjalan (tidak blocking thread).
    println("Menunggu status pesanan...")

    // TODO 3: Panggil checkOrderStatus("A100") dari dalam coroutine ini
    val status = checkOrderStatus("A100")
    println(status)
}

// === Penjelasan ===
// TODO 1 (delay(2000))
// Fungsi delay(2000) digunakan untuk menangguhkan (suspend) eksekusi coroutine selama 2000 milidetik (2 detik).
// Berbeda dengan Thread.sleep(), delay() adalah fungsi non-blocking yang menghentikan sementara coroutine tanpa memblokir thread yang sedang berjalan.

// TODO 2 (return "Order $orderId: SHIPPED")
// Setelah penangguhan 2 detik selesai, fungsi mengembalikan nilai String berupa "Order A100: SHIPPED" menggunakan String template Kotlin ($orderId).

// TODO 4 (println("Menunggu status pesanan..."))
// ini dicetak terlebih dahulu sebelum memanggil fungsi checkOrderStatus(). Hal ini untuk menunjukkan alur eksekusi program bahwa pesan persiapan/penantian ditampilkan terlebih dahulu di konsol.

// TODO 3 (val status = checkOrderStatus("A100") dan println(status))
// checkOrderStatus adalah sebuah suspend fun, ia harus dipanggil dari dalam pembatas coroutine (CoroutineScope), dalam hal ini pembangun coroutine runBlocking.
// Hasil kembalian dipenampungkan dalam variabel status kemudian dicetak ke konsol.