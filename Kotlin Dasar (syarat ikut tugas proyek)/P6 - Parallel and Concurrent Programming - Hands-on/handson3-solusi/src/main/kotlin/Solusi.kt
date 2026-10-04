import java.util.concurrent.LinkedBlockingQueue
import kotlin.concurrent.thread

// Hands-on 3: Producer-Consumer dengan BlockingQueue
// Tugas: Implementasikan pola producer-consumer menggunakan LinkedBlockingQueue.
// Satu thread producer menaruh 5 pesan ke queue (dengan delay 200ms per pesan),
// satu thread consumer mengambil dan mencetak pesan itu satu per satu.
// Consumer harus berhenti setelah menerima tanda "selesai" dari producer.
//
// CATATAN: File ini belum bisa dijalankan (compile error) sampai kamu
// melengkapi semua TODO di bawah — itu normal untuk latihan ini!

const val PESAN_SELESAI = "SELESAI"

fun main() {
    // TODO 1: Buat LinkedBlockingQueue<String> untuk komunikasi antar thread
    val queue = LinkedBlockingQueue<String>()

    val producer = thread {
        for (i in 1..5) {
            val pesan = "Pesan #$i"
            // TODO 2: Masukkan pesan ke queue dengan queue.put(pesan)
            queue.put(pesan)
            println("[Producer] mengirim: $pesan")
            Thread.sleep(200)
        }
        // TODO 3: Kirim PESAN_SELESAI ke queue sebagai tanda producer sudah selesai
        queue.put(PESAN_SELESAI)
    }

    val consumer = thread {
        while (true) {
            // TODO 4: Ambil pesan dari queue dengan queue.take() (blocking, menunggu jika queue kosong)
            val pesan = queue.take()

            // TODO 5: Jika pesan == PESAN_SELESAI, hentikan loop (break)
            if (pesan == PESAN_SELESAI) {
                break
            }
            // TODO 6: Jika bukan, cetak: println("[Consumer] menerima: $pesan")
            println("[Consumer] menerima: $pesan")
        }
    }

    producer.join()
    consumer.join()
    println("Selesai!")
}

// === Penjelasan ===
// 1. val queue = LinkedBlockingQueue<String>()
// Membuat instance antrean thread-safe bertipe LinkedBlockingQueue<String> yang bertindak sebagai buffer
// perantara komunikasi data antar thread Producer dan Consumer secara aman (thread-safe queue).

// 2. queue.put(pesan)
// Di dalam thread Producer, method queue.put(pesan) digunakan untuk memasukkan setiap pesan (Pesan #1 hingga Pesan #5)
// ke dalam antrean. Jika antrean penuh, method ini akan secara otomatis melakukan blocking (menunggu) hingga ada ruang kosong.

// 3. queue.put(PESAN_SELESAI)
// Setelah perulangan pengiriman 5 pesan selesai, Producer mengirimkan sinyal khusus berupa konstanta
// PESAN_SELESAI ("SELESAI") ke dalam queue untuk memberitahu Consumer bahwa proses produksi telah usai.

// 4. val pesan = queue.take()
// Di dalam thread Consumer, method queue.take() digunakan untuk mengambil elemen dari antrean. Method ini bersifat blocking, jika
// antrean kosong, thread Consumer akan beristirahat secara efisien tanpa memakan CPU (busy waiting) sampai ada item baru yang dimasukkan oleh Producer.

// 5. if (pesan == PESAN_SELESAI) { break }
// Memeriksa apakah pesan yang diambil bernilai "SELESAI". Jika ya, loop while(true) dihentikan (break) agar thread
// Consumer dapat keluar dari perulangan dan mengakhiri tugasnya.

// 6. println("[Consumer] menerima: $pesan")
// Jika pesan yang diterima bukan tanda selesai, Consumer langsung memproses dan mencetak pesan tersebut ke konsol.