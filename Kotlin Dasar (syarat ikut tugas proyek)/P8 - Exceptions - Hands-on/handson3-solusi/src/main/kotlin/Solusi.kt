// Hands-on 3: Kotlin Sugar - try sebagai expression, require(), error(), multi-catch (Solusi)

data class Tiket(val nama: String, val umur: Int)

fun parseTiket(input: String): Tiket {
    val bagian = input.split("/")

    // TODO 1: Gunakan require() untuk memastikan bagian.size == 2,
    //         jika tidak, lempar IllegalArgumentException dengan pesan
    //         "Format harus 'nama/umur', diterima: $input"
    require(bagian.size == 2) { "Format harus 'nama/umur', diterima: $input" }

    val nama = bagian[0]

    // TODO 2: Gunakan `try { ... } catch (e: NumberFormatException) { ... }`
    //         SEBAGAI EXPRESSION (try mengembalikan nilai) untuk mengubah
    //         bagian[1] menjadi Int bernama `umur`. Jika gagal parsing,
    //         panggil error("Umur tidak valid: ${bagian[1]}") di blok catch
    //         (error() melempar IllegalStateException).
    val umur: Int = try {
        bagian[1].toInt()
    } catch (e: NumberFormatException) {
        error("Umur tidak valid: ${bagian[1]}")
    }

    // TODO 3: Gunakan require() lagi untuk memastikan umur >= 0 dan nama
    //         tidak kosong, dengan pesan error yang sesuai.
    require(nama.isNotBlank()) { "Nama tidak boleh kosong" }
    require(umur >= 0) { "Umur tidak boleh negatif, diterima: $umur" }

    return Tiket(nama, umur)
}

fun main() {
    val inputs = listOf("Budi/25", "Siti/17", "format-salah", "Andi/bukan-angka", "/30")

    for (input in inputs) {
        // TODO 4: Panggil parseTiket(input) di dalam try-catch dengan
        //         BEBERAPA blok catch (multi-catch) untuk menangani
        //         IllegalArgumentException dan IllegalStateException secara
        //         terpisah, lalu cetak hasil atau pesan errornya.
        try {
            val tiket = parseTiket(input)
            println("Berhasil parse: $tiket")
        } catch (e: IllegalArgumentException) {
            println("[IllegalArgumentException] Input '$input' -> ${e.message}")
        } catch (e: IllegalStateException) {
            println("[IllegalStateException] Input '$input' -> ${e.message}")
        }
    }
}

// === Penjelasan ===
// TODO 1 (require() untuk prakondisi):
// Menggunakan fungsi bawaan Kotlin require(bagian.size == 2) untuk memvalidasi struktur input. Jika kondisi bernilai false, Kotlin otomatis melempar IllegalArgumentException.

// TODO 2 (try sebagai Expression & error()):
// Menggunakan sintaks Kotlin di mana try-catch dapat mengembalikan nilai langsung ke variabel val umur: Int.
// Jika parsing bagian[1].toInt() gagal melempar NumberFormatException, blok catch memanggil helper function error(...) bawaan Kotlin yang melempar IllegalStateException.

// TODO 3 (Validasi lanjutan):
// Memakai require() untuk memastikan nama.isNotBlank() (nama tidak kosong/spasi) dan umur >= 0 (umur bernilai non-negatif).

// TODO 4 (Multi-catch):
// Di fungsi main(), setiap string diproses dalam loop for. Digunakan dua blok catch berurutan untuk memisahkan penanganan:
// i. catch (e: IllegalArgumentException) — Menangani format string tidak sesuai atau validasi require().
// ii. catch (e: IllegalStateException) — Menangani error konversi angka umur dari fungsi error().