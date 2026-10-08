import kotlinx.coroutines.*

// Hands-on 1: Coroutines Dasar
// Tugas: Ambil data dari 2 sumber secara PARALEL menggunakan async/await,
// lalu gabungkan hasilnya. Total waktu eksekusi harus < 2 detik (bukan ~1800ms
// yang akan terjadi jika dijalankan secara sequential).

suspend fun fetchUserProfile(userId: String): String {
    delay(1000) // Simulasi network delay
    return "User: John Doe"
}

suspend fun fetchUserPosts(userId: String): List<String> {
    delay(800) // Simulasi network delay
    return listOf("Post 1", "Post 2", "Post 3")
}

fun main() = runBlocking {
    // TODO 1: Jalankan fetchUserProfile dan fetchUserPosts secara PARALEL dengan async
    // TODO 2: Tunggu kedua hasil dengan await(), lalu tampilkan dengan println
    // TODO 3: Ukur waktu eksekusi (harus mendekati 1000ms, bukan 1800ms)

    val startTime = System.currentTimeMillis()

    // TODO 1: Jalankan fetchUserProfile dan fetchUserPosts secara PARALEL dengan async
    val profileDeferred = async { fetchUserProfile("123") }
    val postsDeferred = async { fetchUserPosts("123") }

    // TODO 2: Tunggu kedua hasil dengan await(), lalu tampilkan dengan println
    val profile = profileDeferred.await()
    val posts = postsDeferred.await()

    println(profile)
    println("Posts: $posts")

    val endTime = System.currentTimeMillis()
    println("Waktu: ${endTime - startTime}ms")
}

// === Penjelasan ===
// 1. val profileDeferred = async { fetchUserProfile("123") }
// Menjalankan pemanggilan fungsi suspend fetchUserProfile secara asinkron (paralel) di dalam coroutine scope.
// 2. val postsDeferred = async { fetchUserPosts("123") }
// Karena menggunakan async, fungsi ini (dengan delay 800ms) langsung dieksekusi secara serentak/bersamaan dengan fetchUserProfile.
// 3. val profile = profileDeferred.await()
// Fungsi suspend await() menangguhkan eksekusi hingga data profil selesai diambil. Total waktu yang dibutuhkan di sini adalah waktu dari proses terlama, yaitu sekitar 1000ms.
// 4. val posts = postsDeferred.await()
// Karena fetchUserPosts (800ms) selesai lebih cepat daripada fetchUserProfile (1000ms), ketika await() dipanggil di sini, data postingan sebenarnya sudah siap di memori sehingga tidak menambah waktu tunggu baru.
// 5. println(profile) & println("Posts: $posts")
// Menampilkan hasil data profile dan posts yang telah berhasil diambil ke konsol (stdout).
// 6. println("Waktu: ${endTime - startTime}ms")
// Menghitung selisih waktu (endTime - startTime). Karena dijalankan secara paralel (async), total waktu eksekusi akan berada di kisaran 1000ms (mengikuti durasi proses terlama), bukan 1800ms (penjumlahan 1000ms + 800ms seperti pada eksekusi sequential).