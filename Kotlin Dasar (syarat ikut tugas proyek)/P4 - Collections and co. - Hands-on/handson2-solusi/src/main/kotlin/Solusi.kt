// Hands-on 2: Grouping & Aggregation

data class Transaksi(val id: String, val kategori: String, val nominal: Int)

fun totalPerKategori(transaksi: List<Transaksi>): Map<String, Int> {
    // TODO 1: Kelompokkan (groupBy) transaksi berdasarkan kategori
    // TODO 2: Untuk setiap grup, jumlahkan (sumOf) nominal-nya
    return transaksi
        .groupBy {it.kategori}
        .mapValues {entry ->
            entry.value.sumOf {it.nominal}
        }
}

fun transaksiById(transaksi: List<Transaksi>): Map<String, Transaksi> {
    // TODO 3:
    return transaksi.associateBy {it.id}
}

fun main() {
    val transaksi = listOf(
        Transaksi("TRX01", "Makanan", 50_000),
        Transaksi("TRX02", "Transportasi", 20_000),
        Transaksi("TRX03", "Makanan", 35_000),
        Transaksi("TRX04", "Hiburan", 100_000),
        Transaksi("TRX05", "Transportasi", 15_000)
    )

    println("Total per kategori: ${totalPerKategori(transaksi)}")
    // Expected: {Makanan=85000, Transportasi=35000, Hiburan=100000}

    val byId = transaksiById(transaksi)
    println("Cari TRX03: ${byId["TRX03"]}")
    // Expected: Transaksi(id=TRX03, kategori=Makanan, nominal=35000)
}

// === Penjelasan ===
// groupBy { it.kategori }: Memecah list tunggal menjadi beberapa kelompok berdasarkan kategorinya. Hasil dari fungsi ini adalah
// sebuah Map di mana key-nya adalah nama kategori ("Makanan", "Transportasi"), dan value-nya adalah daftar (List) semua transaksi di kategori tersebut

// mapValues { ... }: Berfungsi untuk mengubah value dari setiap kelompok yang dihasilkan oleh groupBy. Dapat mengambil list transaksi dari
// setiap grup (entry.value), lalu menghitung totalnya menggunakan fungsi bawaan sumOf { it.nominal }

// associateBy { it.id }: Fungsi untuk mengubah List menjadi Map. Fungsi ini menggunakan nilai id dari setiap transaksi sebagai key,
// dan objek Transaksi itu sendiri sebagai value. Ini sangat berguna kalau mau mencari data (lookup) pakai ID tanpa harus melakukan perulangan dari awal