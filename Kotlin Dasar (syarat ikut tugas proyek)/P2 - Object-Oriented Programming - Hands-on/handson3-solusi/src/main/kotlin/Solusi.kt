// Hands-on 3: Sealed Class untuk State

// TODO 1
sealed class NetworkResult

// TODO 2
object Loading : NetworkResult()

// TODO 3
data class Success (val data: String) : NetworkResult()

// TODO 4
data class Error (val message: String) : NetworkResult()

fun describe(result: NetworkResult): String {
    // TODO 5
    return when (result) {
        is Loading -> "Sedang memuat..."
        is Success -> "Berhasil: ${result.data}"
        is Error -> "Gagal: ${result.message}"
    }
}

fun main() {
    println(describe(Loading))
    println(describe(Success("Data pengguna berhasil diambil")))
    println(describe(Error("Koneksi terputus")))
}

// === Penjelasan ===
// Todo 1:
// sealed class adalah kelas tersegel. Ia bertindak seperti enum yang diperluas, di mana semua sub-kelas (turunan) dari kelas tersebut harus dideklarasikan di dalam file yang sama
// Keuntungan utamanya adalah type safety, karena kompiler tahu persis apa saja kemungkinan turunannya. Saat digunakan bersama ekspresi when, kompiler bisa memastikan semua kemungkinan telah ditangani tanpa memerlukan cabang cadangan else

// Todo 2:
// State Loading diwakili sebagai object karena status ini tidak membawa data atau status dinamis tambahan apapun. Cukup sebuah Singleton instance tunggal untuk menandakan bahwa aplikasi sedang sibuk mengambil data
// Tanda : NetworkResult() berarti objek ini merupakan turunan dari NetworkResult

// Todo 3:
// State Success membutuhkan penyimpanan data hasil respons. Oleh karena itu, digunakan data class agar secara otomatis mendapatkan fungsi pendukung seperti toString(), equals(), dan hashCode()
// Di sini, kelas menerima parameter data bertipe String (misalnya payload JSON atau teks) dan menjadi turunan : NetworkResult()

// Todo 4:
// State Error memerlukan parameter tambahan untuk menyimpan informasi tentang apa yang salah (misalnya: pesan error/kesalahan)
// Maka dibuatlah data class Error yang membawa properti message bertipe String dan menjadi turunan : NetworkResult()

// Todo 5:
// fun describe(result: NetworkResult): String -> Sebuah fungsi bernama describe yang menerima argumen berupa objek bertipe NetworkResult dan wajib mengembalikan nilai teks (String)
// return when (result): Mengembalikan langsung hasil evaluasi blok percabangan when
// is Loading -> ...: Menggunakan kata kunci is untuk memeriksa tipe objek (Smart Cast). Karena Loading berupa object, di versi Kotlin tertentu atau jika ditulis sebagai nested class, ia diperiksa tipenya. Jika bertipe Loading, mengembalikan teks "Sedang memuat..."
// is Success -> ...: Jika objek terbukti bertipe Success, Kotlin secara otomatis mengubah tipe data result menjadi Success di dalam cabang tersebut (Smart Casting), sehingga bisa langsung memanggil data di dalamnya lewat teks template ${result.data}
// is Error -> ...: Jika bertipe Error, bisa langsung mengakses pesan kegagalan melalui properti ${result.message}
// Catatan Kunci: Karena NetworkResult adalah sealed class, maka tidak perlu menulis else -> .... Jika menghapus salah satu cabang (misalnya menghapus cabang is Error), kode akan langsung error saat dicompile karena penanganan dianggap belum lengkap

// Fungsi Main:
// println(describe(Loading)): Memanggil fungsi describe dengan melemparkan objek Loading, lalu mencetak hasilnya
// println(describe(Success(...))): Membuat instance baru dari data class Success dengan pesan keberhasilan, lalu mencetak teks deskripsinya
// println(describe(Error(...))): Membuat instance baru dari data class Error dengan membawa pesan kesalahan komputer/jaringan, lalu mencetak deskripsinya

