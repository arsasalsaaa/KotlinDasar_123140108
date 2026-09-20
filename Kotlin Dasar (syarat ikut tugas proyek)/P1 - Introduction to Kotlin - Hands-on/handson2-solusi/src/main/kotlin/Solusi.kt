fun gradeOf(nilai: Int): String = when (nilai) {
    in 90..100 -> "A"
    in 80..89 -> "B"
    in 70..79-> "C"
    in 60..69 -> "D"
    else -> "E"
}

fun main() {
    val nilaiMahasiswa = listOf(95, 82, 71, 60, 45)
    for (nilai in nilaiMahasiswa) {
        println("Nilai $nilai -> Grade ${gradeOf(nilai)}")
    }
}

// === Penjelasan ===
// 1. Single-Expression Function (=): Ditandai dengan penggunaan "=" setelah penulisan fungsi,
//    tanpa petlu menggunakan kurung kurawal ataupun return. Kotlin mengembalikan nilai dari hasil expression di sebelah kanannya
// 2. Expression when: Bertindak sebagai nilai yang langsung dikembalikan
// 3. Rentang in (in): Operator in digunakan untuk mencocokkan angka di dalam suatu rentang,
//    misal in 90..100 berarti dari angka 90 sampai 100, 90 dan 100 inklusif
// 4. else: Berfungsi sebagai nilai diluar range 60-100, maka langsung menghasilkan "E"