// Hands-on 1: Transformasi Collection
// Tugas: Dari daftar produk, tampilkan NAMA produk yang harganya di atas
// sebuah threshold, terurut dari yang termurah ke termahal.
// Konsep: filter, map, sortedBy

data class Product(val nama: String, val harga: Int, val stok: Int)

fun produkDiAtasHarga(produk: List<Product>, minHarga: Int): List<String> {
    return produk
    // TODO 1: Saring (filter) produk yang harga-nya > minHarga
        .filter {it.harga > minHarga}
    // TODO 2: Urutkan (sortedBy) hasil saringan dari harga termurah
        .sortedBy {it.harga}
    // TODO 3: Ubah (map) menjadi List<String> berisi nama produk saja
        .map {it.nama}
}

fun main() {
    val katalog = listOf(
        Product("Mouse Wireless", 75_000, 20),
        Product("Keyboard Mechanical", 450_000, 5),
        Product("Monitor 24 inch", 1_500_000, 3),
        Product("USB Flashdisk 32GB", 60_000, 50),
        Product("Webcam HD", 250_000, 8)
    )

    val hasil = produkDiAtasHarga(katalog, 100_000)
    println("Produk dengan harga di atas Rp100.000 (termurah dulu):")
    println(hasil)
    // Expected: [Webcam HD, Keyboard Mechanical, Monitor 24 inch]
}

// === Penjelasan ===
// filter { it.harga > minHarga }: Ini adalah tahap penyaringan pertama. Fungsi ini mengecek setiap
// item (diwakili oleh it) dalam daftar produk. Hanya produk yang harganya lebih dari Rp100.000 yang akan diteruskan ke tahap berikutnya

// sortedBy { it.harga }: Kumpulan produk yang lolos filter tadi kemudian diurutkan. Secara bawaan, sortedBy
// akan mengurutkan angka dari yang terkecil ke yang terbesar (ascending)

// map { it.nama }: Fungsi ini mengekstrak atau mengubah bentuk data. Dari objek Product utuh yang berisi nama, harga, dan stok,
// menyuruh map untuk hanya mengambil properti nama-nya saja. Hasil akhirnya adalah sebuah daftar baru yang hanya berisi teks (List<String>).
