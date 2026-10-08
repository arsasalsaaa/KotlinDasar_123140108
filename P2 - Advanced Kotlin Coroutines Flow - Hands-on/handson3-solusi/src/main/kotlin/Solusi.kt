import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

// Hands-on 3: StateFlow untuk Counter
// Tugas: Implementasikan counter sederhana menggunakan StateFlow.
// Counter harus bisa increment, decrement, dan reset.
//
// CATATAN: File ini belum bisa dijalankan sampai kamu melengkapi
// semua TODO di bawah — itu normal untuk latihan ini!

class CounterManager {
    // TODO: Buat MutableStateFlow dengan nilai awal 0
    private val _count = MutableStateFlow(0)

    // TODO: Expose sebagai StateFlow (read-only)
    val count: StateFlow<Int> = _count.asStateFlow()

    fun increment() {
        // TODO: Tambah nilai count
        _count.value++
    }

    fun decrement() {
        // TODO: Kurangi nilai count (minimum 0)
        _count.update { maxOf(0, it - 1) }
    }

    fun reset() {
        // TODO: Reset ke 0
        _count.value = 0
    }
}

fun main() = runBlocking {
    val counter = CounterManager()

    // Collect di background
    val job = launch {
        counter.count.collect { println("Count: $it") }
    }

    delay(100)
    counter.increment() // Count: 1
    delay(100)
    counter.increment() // Count: 2
    delay(100)
    counter.decrement() // Count: 1
    delay(100)
    counter.reset()     // Count: 0
    delay(100)

    job.cancel()
}

// === Penjelasan ===
// 1. private val _count = MutableStateFlow(0)
// Menggunakan konvensi backing property (_count sebagai private mutable dan count sebagai public read-only) agar enkapsulasi data terjaga dan state internal hanya bisa dimodifikasi dari dalam class CounterManager.
// 2. val count: StateFlow<Int> = _count.asStateFlow()
// .asStateFlow() mengubah MutableStateFlow menjadi read-only StateFlow sehingga kode di luar class tidak dapat mengubah nilai count secara sembarangan selain melalui method yang disediakan (increment, decrement, reset).
// 3. _count.value++ (di dalam increment())
// Mengakses dan memperbarui properti .value dari _count secara langsung. Perubahan nilai ini akan otomatis dipancarkan ke semua collector yang sedang mengamati count.
// 4. _count.update { maxOf(0, it - 1) } (di dalam decrement())
// Menggunakan operator .update { } untuk pembaruan state yang aman (thread-safe). Fungsi maxOf(0, it - 1) memastikan bahwa jika nilai saat ini 0, maka hasilnya tetap 0 dan tidak turun menjadi negatif.
// 5. _count.value = 0 (di dalam reset())
// Mengatur ulang properti .value ke angka 0, yang otomatis memancarkan nilai 0 ke seluruh collector.