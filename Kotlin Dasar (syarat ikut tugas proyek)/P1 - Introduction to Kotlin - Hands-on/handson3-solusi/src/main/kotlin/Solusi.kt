class ScoreBoard(private val skorMentah: List<Int?>) {
    val skorValid: List<Int> = skorMentah.filterNotNull()

    fun skorKelulusan(batasLulus: Int): List<Int> {
        return skorValid.filter { it >= batasLulus }.sortedDescending()
    }
}

fun cetakRentangGanjil(sampai: Int) {
    for (i in 1..sampai step 2)
        print("$1 ")
}

fun main() {
    val papan = ScoreBoard(listOf(85, null, 72, 90, null, 55, 100))
    println("Skor lulus (>= 70): ${papan.skorKelulusan(70)}")

    cetakRentangGanjil(10)
}

// === Penjelasan ===
// 1. Membuat skorValid
//    skorMentah: adalah daftar skor yang bisa berisi nilai kosong
//    filterNotNull(): Fungsi yang menyisir seluruh isi daftar dan membuang semua elemen yang null
//    Hasilnya: Mendapatkan list baru (List<Int>) yang bersih dan aman dari NullPointerException
// 2. Fungsi skorKelulusan
//    .filter { it >= batasLulus }: Mengurutkan hanya skor yang nilainya mencapai ambang batas lulus
//    .sortedDescending(): Mengurutkan skor dari paling tinggi ke rendah
//    return: Mengembalikan daftarakhir yang sudah difilter dan diurutkan
// 3. Fungsi cetakRentangGanjil
//    1..sampai: Membuat rentang angka dari 1 hingga batas akhir (inklusif)
//    step 2: Program akan melompati 2 angka agar menghasilkan angka ganjil, karena dimulai dari 1 maka 3,5,dst
//    print("$i "): Mencetak angka diikuti spasi di sampingnya

// Output yang diharapkan:
// Skor lulus (>= 70): [100, 90, 85, 72]
// 1 3 5 7 9
