import kotlin.concurrent.thread
import java.util.concurrent.atomic.AtomicInteger

// Hands-on 1: Race Condition
// Tugas: Dua thread meng-increment sebuah shared counter (var c) sebanyak
// 100_000x masing-masing secara BERSAMAAN. Karena c++ bukan operasi atomik
// (baca-ubah-tulis, bisa saling menyela/interleave antar thread), hasil akhirnya
// SERING SALAH (bukan 200_000) — itulah race condition.

// TODO: Perbaiki class Counter di bawah supaya increment() aman dari race
// condition, sehingga hasil akhirnya SELALU 200_000 walau dijalankan berkali-kali.
// Gunakan salah satu: `synchronized(this) { }` ATAU `java.util.concurrent.atomic.AtomicInteger`.

class Counter {
    private val c = AtomicInteger(0)

    fun increment() {
        // TODO 1: Bungkus baris di bawah supaya thread-safe
        c.incrementAndGet()
    }

    fun value(): Int {
        // TODO 2: Jika kamu memakai AtomicInteger, sesuaikan cara membaca nilainya di sini
        return c.get()
    }
}

fun main() {
    val counter = Counter()
    val iterasi = 100_000

    val t1 = thread {
        repeat(iterasi) { counter.increment() }
    }
    val t2 = thread {
        repeat(iterasi) { counter.increment() }
    }

    t1.join()
    t2.join()

    println("Hasil akhir: ${counter.value()} (seharusnya ${iterasi * 2})")
}

// === Penjelasan ===
// 1. import java.util.concurrent.atomic.AtomicInteger: Mengimpor kelas AtomicInteger dari pustaka Java concurrency
// agar kita dapat menggunakan variabel integer yang thread-safe secara lock-free (menggunakan instruksi atomik tingkat rendah CPU seperti CAS / Compare-And-Swap)
// 2. private val c = AtomicInteger(0): Mengganti variabel primitif private var c = 0 dengan instansiasi AtomicInteger(0)
// Dengan ini, penyimpanan nilai counter dibungkus di dalam objek atomik
// 3. c.incrementAndGet(): Menyelesaikan TODO 1 dengan mengganti operasi c++ (yang tidak atomik karena melibatkan 3 langkah: baca, ubah, tulis
// yang rentan terkena interleave antar thread) dengan method bawaan incrementAndGet(). Method ini menjamin proses penambahan nilai 1 dilakukan secara atomik
// 4. return c.get(): Menyelesaikan TODO 2 dengan memanggil method .get() untuk membaca nilai aktual yang tersimpan di dalam AtomicInteger secara aman ketika fungsi value() dipanggil.
