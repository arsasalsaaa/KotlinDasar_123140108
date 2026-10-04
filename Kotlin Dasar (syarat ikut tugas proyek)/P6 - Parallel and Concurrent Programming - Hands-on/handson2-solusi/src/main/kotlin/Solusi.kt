import java.util.concurrent.Callable
import java.util.concurrent.Executors

// Hands-on 2: ExecutorService & Future
// Tugas: Jalankan 4 "task" perhitungan yang lambat (simulasi delay dengan
// Thread.sleep) secara PARALEL menggunakan thread pool, lalu kumpulkan semua
// hasilnya. Jika dijalankan sequential, total waktu ~4 detik. Dengan thread
// pool 4 pekerja, seharusnya total waktu ~1 detik.

fun hitungKuadrat(n: Int): Int {
    Thread.sleep(1000) // Simulasi kerja berat
    return n * n
}

fun main() {
    val angka = listOf(1, 2, 3, 4)
    val startTime = System.currentTimeMillis()

    // TODO 1: Buat ExecutorService dengan Executors.newFixedThreadPool(4)
    val executor = Executors.newFixedThreadPool(4)

    // TODO 2: Submit satu Callable per angka ke executor, simpan Future-nya
    val futures = angka.map { n -> executor.submit(Callable { hitungKuadrat(n) }) }

    // TODO 3: Ambil semua hasil dengan future.get(), lalu tampilkan
    val hasil = futures.map { it.get() }
    println("Hasil: $hasil")

    // TODO 4: Jangan lupa shutdown() executor supaya program bisa berhenti (JVM
    // tidak akan exit selama thread pool masih hidup)
    executor.shutdown()

    val endTime = System.currentTimeMillis()
    println("Waktu: ${endTime - startTime}ms")
}

// === Penjelasan ===
// 1. val executor = Executors.newFixedThreadPool(4)
// Membuat sebuah ExecutorService dengan thread pool berukuran tetap 4 thread (newFixedThreadPool(4)).
// Ini berarti kita menyediakan 4 pekerja (worker threads) yang siap mengeksekusi tugas-tugas secara paralel.

// 2. val futures = angka.map { n -> executor.submit(Callable { hitungKuadrat(n) }) }
// Melakukan iterasi pada list angka (1, 2, 3, 4). Untuk setiap angka, tugas dibungkus ke dalam Callable
// (karena menghasilkan nilai kembalian/return value, berbeda dengan Runnable) lalu di-submit ke executor.
// Method submit() mengembalikan objek Future yang merepresentasikan hasil komputasi yang nantinya akan selesai di masa depan (asynchronous).

// 3. val hasil = futures.map { it.get() } dan println("Hasil: $hasil")
// Mengambil hasil dari masing-masing Future menggunakan method .get(). Pemanggilan it.get() akan memblokir (block) thread utama sementara hingga
// masing-masing task selesai dikerjakan oleh thread di dalam pool. Hasil akhir dari perhitungan kuadrat dikumpulkan menjadi list [1, 4, 9, 16] dan dicetak ke konsol.

// 4. executor.shutdown()
// Menutup (shutdown) ExecutorService setelah semua tugas disubmit. Jika tidak dipanggil, JVM akan terus hidup (running) karena thread pool
// tetap mempertahankan thread-nya yang aktif menunggu tugas baru.