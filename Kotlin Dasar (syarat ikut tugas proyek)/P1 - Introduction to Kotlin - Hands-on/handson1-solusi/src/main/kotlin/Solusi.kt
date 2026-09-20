fun describeProfile(nama: String, umur: Int?, kota: String = "Tidak diketahui"): String {
    // TODO 1: Buat val bernama umurText yang berisi:
    //   - "<umur> tahun" jika umur tidak null
    //   - "umur tidak diketahui" jika umur null
    //   Gunakan safe call (?.) dan elvis operator (?:)
    val umurText = umur?.let {"$it tahun"} ?: "umur tidak diketahui"

    // TODO 2: Kembalikan (return) satu String dengan format:
    //   "Nama: <nama>, Umur: <umurText>, Kota: <kota>"
    //   Gunakan string template ($nama, dst), bukan concatenation (+)
    return "Nama: $nama, Umur: $umurText, Kota: $kota"
}
fun main() {
    println(describeProfile("Andi", 20, "Bandar Lampung"))
    println(describeProfile("Budi", null))
    println(describeProfile(nama = "Citra", umur = 19))
}

// === Penjelasan ===
// 1. Parameter Fungsi (Input Data)
// nama: String -> Variabel teks standar. Sifatnya harus diisi saat fungsi dipanggil
// umur: Int? -> Tanda tanya (?) adalah penerapan Null Safety artinya var umur boleh diisi atau null
// kota: String = "Tidak diketahui -> Disebut default parameter. Jika tidak diisi program akan otomatis mengisinya dengan teks "Tidak diketahui"

//2. Penanganan Data Kosong (Baris umurText)
// Safe Calls (?.) pada umur?.let{...} -> Mengecek variabel umur. Jika tidak null, maka perintah .let {...} di sebelahnya akan dijalankan. Jika null, maka perintah dibatalkan
// it -> Kata ganti otomatis dalam blok .let yang mewakili angka asli dari umur yang sudah terjamin tidak null
// Elvis Operator (?:) -> Jika proses di sebelah kirinya menghasilkan null, maka program mengambil teks di sebelah kanan, yaitu "umur tidak diketahui"

// 3. Format Teks
// Simbol dolar ($) -> Pada baris return " ... ", digunakan untuk memanggil isi dari sebuah variabel secara langsung. Simbol ini membuat kode jadi lebih clean

// 4. Main()
// Memasukkan null -> Pada perintah "println(describeProfile("Budi", null))", sengaja mengisi nilai null untuk memicu respons Elvis Operator
// Pada perintah describeProfile(nama = "Citra", umur = 19) -> nama var disebut secara spesifik, untuk memasukkan data secara acak tanpa mengikuti urutan parameter, dan parameter kota pakai nilai default

// Output yang diharapkan:
// Nama: Andi, Umur: 20 tahun, Kota: Bandar Lampung
// Nama: Budi, Umur: umur tidak diketahui, Kota: Tidak diketahui
// Nama: Citra, Umur: 19 tahun, Kota: Tidak diketahui
