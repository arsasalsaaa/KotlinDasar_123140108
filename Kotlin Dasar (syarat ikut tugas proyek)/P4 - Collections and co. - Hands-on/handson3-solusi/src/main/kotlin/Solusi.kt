// Hands-on 3: Sequence vs List (Lazy Evaluation)

fun prosesDenganList(data: List<Int>): List<Int> {
    // TODO 1: filter angka genap, lalu map kuadratkan, lalu take(5)
    return data
        .filter {it % 2 == 0}
        .map {it * it}
        .take(5)
}

fun prosesDenganSequence(data: List<Int>): List<Int> {
    // TODO 2: ubah `data` menjadi Sequence dengan asSequence(),
    // lalu filter angka genap, map kuadratkan, take(5),
    // dan panggil operasi terminal toList() di akhir.
    return data
        .filter {it % 2 == 0}
        .map {it * it}
        .take(5)
        .toList()
}

fun main() {
    val data = (1..1_000_000).toList()

    val startList = System.currentTimeMillis()
    val hasilList = prosesDenganList(data)
    val waktuList = System.currentTimeMillis() - startList
    println("List  : $hasilList (${waktuList}ms)")

    val startSeq = System.currentTimeMillis()
    val hasilSequence = prosesDenganSequence(data)
    val waktuSequence = System.currentTimeMillis() - startSeq
    println("Sequence: $hasilSequence (${waktuSequence}ms)")

    // Expected: kedua hasil = [4, 16, 36, 64, 100], tapi Sequence jauh lebih
    // cepat karena tidak perlu membuat List perantara berukuran 1 juta elemen.
}

// === Penjelasan ===
// List (Eager / Langsung dieksekusi): Saat menjalankan prosesDenganList, fungsi filter akan memproses seluruh 1 juta data terlebih dahulu
// dan membuat List perantara baru berisi 500.000 angka genap. Setelah itu, fungsi map memproses seluruh 500.000 data tersebut dan membuat
// List baru lagi. Terakhir, take(5) baru mengambil 5 data pertama dan membuang 499.995 sisanya. Ini sangat membuang waktu dan memori (RAM).

// Sequence (Lazy / Malas dieksekusi): Saat memakai asSequence(), data dievaluasi satu per satu secara vertikal
// Angka 1 masuk -> gagal di filter -> lewati
// Angka 2 masuk -> lolos filter -> dikuadratkan di map jadi 4 -> ditangkap oleh take(1 dari 5)
// Angka 3 masuk -> gagal di filter -> lewati
// Angka 4 masuk -> lolos filter -> dikuadratkan di map jadi 16 -> ditangkap oleh take(2 dari 5)
// Proses ini otomatis berhenti sepenuhnya pada angka 10 karena take(5) sudah terpenuhi. Sisa 999.990 angka di belakangnya sama sekali tidak disentuh

// Oleh karena itu, ketika run kode ini, waktu pemrosesan Sequence biasanya hanya 0-2 ms, jauh lebih cepat
// dibandingkan List yang bisa memakan puluhan hingga ratusan milisecond.