import kotlinx.coroutines.*

// Hands-on 3: Structured Concurrency & Cancellation
// Tugas: Implementasikan DownloadManager yang men-download beberapa "file"
// (disimulasikan dengan delay) sebagai child coroutine dari satu Job. Jika
// Job tersebut dibatalkan (cancel), SEMUA child coroutine yang belum
// selesai harus ikut berhenti (ini yang disebut structured concurrency).
//
// CATATAN: File ini SENGAJA belum bisa di-compile sampai kamu melengkapi
// semua TODO di bawah — itu normal untuk latihan ini!

class DownloadManager(private val scope: CoroutineScope) {

    fun downloadFile(name: String, durationMs: Long): Job {
        // TODO 1: Gunakan scope.launch untuk membuat child coroutine baru.
        //         Di dalamnya:
        //         - delay(durationMs) untuk simulasi proses download
        //         - println("$name selesai di-download") setelah delay
        //         Kembalikan Job dari launch tersebut.
        val job = scope.launch {
            delay(durationMs)
            println("$name selesai di-download")
        }
        return job
    }
}

fun main() = runBlocking {
    // TODO 2: Buat sebuah Job induk (parent) baru dengan Job()
    val parentJob = Job()

    // TODO 3: Buat CoroutineScope baru dari parentJob tersebut
    //         (gunakan CoroutineScope(parentJob))
    val scope = CoroutineScope(parentJob)

    val manager = DownloadManager(scope)

    manager.downloadFile("foto.jpg", 1000)
    manager.downloadFile("video.mp4", 3000)
    manager.downloadFile("dokumen.pdf", 1500)

    delay(1200) // Beri waktu foto.jpg dan dokumen.pdf selesai

    println("Membatalkan sisa download...")
    // TODO 4: Batalkan parentJob dengan cancel() — video.mp4 (durasi 3000ms)
    //         seharusnya TIDAK sempat mencetak "selesai di-download".
    parentJob.cancel()

    delay(2000) // Tunggu untuk membuktikan video.mp4 memang tidak selesai
    println("Selesai.")
}

// === Penjelasan ===
// TODO 1 (val job = scope.launch { delay(durationMs); println(...) } dan return job)
// Di dalam metode downloadFile, kita meluncurkan coroutine anak (child coroutine) menggunakan scope.launch.
// Di dalamnya disimulasikan durasi unduhan file menggunakan delay(durationMs) dan mencetak status unduhan jika berhasil hingga selesai.
// Objek Job yang dikembalikan dari launch kemudian di-return oleh fungsi.

// TODO 2 (val parentJob = Job())
// Membuat induk objek Job (parentJob). Objek ini berfungsi sebagai pengontrol hirarki/siklus hidup coroutine induk untuk mengelola coroutine-coroutine anak yang akan dibuat di dalamnya.

// TODO 3 (val scope = CoroutineScope(parentJob))
// Membuat CoroutineScope baru yang diikat dengan parentJob. Dengan cara ini, semua coroutine yang diluncurkan menggunakan scope ini (seperti pada downloadFile)
// secara otomatis menjadi child coroutine dari parentJob. Ini adalah penerapan prinsip Structured Concurrency.

// TODO 4 (parentJob.cancel())
// Memanggil parentJob.cancel() untuk membatalkan parentJob. Karena prinsip Structured Concurrency, ketika parentJob dibatalkan, semua child coroutine yang sedang berjalan
// dan belum selesai (seperti video.mp4 dengan durasi 3000ms dan dokumen.pdf dengan durasi 1500ms) akan otomatis dibatalkan dan menghentikan eksekusinya, sehingga kalimat "video.mp4 selesai di-download" tidak akan pernah dicetak.