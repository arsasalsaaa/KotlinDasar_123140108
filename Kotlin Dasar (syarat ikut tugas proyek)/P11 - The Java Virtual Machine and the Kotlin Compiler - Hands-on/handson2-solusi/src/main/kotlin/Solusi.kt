// Hands-on 2: Tailrec Optimization
//
// Konteks: Setiap pemanggilan fungsi biasa di JVM menambah satu STACK FRAME
// baru ke call stack thread (memori non-heap, ukurannya kecil — sekitar
// 1024 KB per thread). Fungsi rekursif biasa yang dipanggil ribuan/jutaan
// kali akan menumpuk stack frame terus-menerus sampai StackOverflowError.
//
// Kotlin compiler bisa mengoptimasi fungsi rekursif TERTENTU (rekursi di
// posisi terakhir / "tail call") menjadi LOOP biasa di level bytecode —
// tanpa menambah stack frame sama sekali — asalkan diberi keyword `tailrec`.
//
// Tugas: factorialBiasa() di bawah akan StackOverflowError untuk n besar.
// Buat versi yang aman menggunakan `tailrec`.

fun factorialBiasa(n: Long, acc: Long = 1): Long {
    if (n <= 1) return acc
    return factorialBiasa(n - 1, acc * n) // rekursi di posisi terakhir (tail call)
}

// TODO 1: Tambahkan modifier `tailrec` pada fungsi ini
// TODO 2: Pastikan bentuknya sama seperti factorialBiasa() di atas —
//         syarat tailrec: pemanggilan rekursif harus jadi ekspresi TERAKHIR
//         yang dieksekusi (tidak boleh ada operasi lain setelah hasil rekursi)
tailrec fun factorialTailrec(n: Long, acc: Long = 1): Long {
    if (n <= 1) return acc
    return factorialTailrec(n - 1, acc * n)
}

fun main() {
    println("factorialBiasa(20) = ${factorialBiasa(20)}") // aman, n kecil

    println("factorialTailrec(20) = ${factorialTailrec(20)}")

    // Uji dengan n besar: factorialBiasa akan StackOverflowError,
    // factorialTailrec harus tetap aman (walau hasilnya overflow Long, tidak apa).
    try {
        println("factorialBiasa(100000) = ${factorialBiasa(100000)}")
    } catch (e: StackOverflowError) {
        println("factorialBiasa(100000) -> StackOverflowError! (dibatasi ukuran stack JVM)")
    }

    println("factorialTailrec(100000) = ${factorialTailrec(100000)} (tidak overflow stack)")
}

// === Penjelasan ===
// • Perubahan Kode:
// i. TODO 1: Menambahkan modifier tailrec sebelum kata kunci fun.
// ii. TODO 2: Mengimplementasikan logika fungsi factorialTailrec dengan accumulator (acc). Panggilan rekursif factorialTailrec(n - 1, acc * n) dipastikan menjadi ekspresi terakhir yang dieksekusi (tail call).
// • Penjelasan Teknis Optimasi Tailrec:
// Pada eksekusi fungsi rekursif biasa, setiap panggilan fungsi baru akan mengalokasikan Stack Frame baru pada call stack JVM. Jika fungsi dipanggil jutaan kali (seperti factorialBiasa(100000)), alokasi memori stack terbatas akan habis dan memicu crash StackOverflowError.
// Dengan modifier tailrec, compiler Kotlin secara otomatis mengoptimasi fungsi rekursif ekor (tail recursive) menjadi bentuk iterasi perulangan biasa (looping) di level bytecode. Karena dikonversi menjadi loop, fungsi berjalan tanpa menambah stack frame baru sama sekali, sehingga fungsi aman dari risiko StackOverflowError meskipun dijalankan dengan parameter nilai n yang sangat besar.