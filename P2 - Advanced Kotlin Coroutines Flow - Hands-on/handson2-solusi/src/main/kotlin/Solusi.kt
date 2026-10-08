import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.random.Random

// Hands-on 2: Flow dengan Operators
// Tugas: Buat Flow yang mensimulasikan sensor suhu, filter suhu di atas 30°C,
// dan tampilkan warning dengan format yang bagus.

fun temperatureSensor(): Flow<Int> = flow {
    repeat(10) {
        delay(500)
        val temp = Random.nextInt(20, 40) // Random 20-39°C
        emit(temp)
    }
}

fun main() = runBlocking {
    // TODO: Gunakan operator flow untuk:
    // 1. Filter suhu > 30°C saja
    // 2. Transform (map) menjadi string warning, contoh:
    //    "⚠️ WARNING: Suhu tinggi terdeteksi: 35°C"
    // 3. Tampilkan setiap warning dengan collect

    temperatureSensor()
        .filter { it > 30 }
        .map { "⚠️ WARNING: Suhu tinggi terdeteksi: ${it}°C" }
        .collect { println(it) }
}

// === Penjelasan ===
// 1. temperatureSensor()
// Memanggil pembuat Flow (Flow<Int>) yang mensimulasikan pembacaan sensor suhu secara berkala.
// 2. .filter { it > 30 }
// Hanya nilai suhu (it) yang bernilai di atas 30 (> 30) yang akan diteruskan ke operator berikutnya. Nilai 30 atau di bawahnya akan diabaikan.
// 3. .map { "⚠️ WARNING: Suhu tinggi terdeteksi: ${it}°C" }
// Setiap nilai integer suhu yang lolos dari filter akan diubah menjadi string template sesuai dengan format yang diminta tugas.
//4. .collect { println(it) }
// Setiap string warning yang sudah difilter dan ditransformasi akan dicetak (println) ke konsol saat data tersebut dipancarkan.