package com.itera.pam.p7.solusi

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.itera.pam.p7.db.AppDatabase

// Hands-on 1: "DataStore Preferences Sederhana"
//
// CATATAN PENTING: DataStore Preferences versi Multiplatform butuh setup
// tambahan (okio + path provider per-platform) yang cukup rumit untuk
// hands-on pengantar dan berisiko salah tanpa bisa di-compile-check.
// Sebagai gantinya, kita simulasikan KONSEP yang sama persis — menyimpan
// satu nilai key-value yang tetap ada lintas restart aplikasi — memakai
// tabel `Preferensi` pada database SQLDelight yang sama (lihat Preferensi.sq).
// Pola read-modify-write-nya identik dengan cara kerja DataStore.
//
// Tugas: simpan & tampilkan "jumlah aplikasi dibuka", yang bertambah 1
// setiap kali layar ini dibuka, dan nilainya PERSISTEN (coba tutup lalu
// buka lagi aplikasinya — nilainya harus lanjut, bukan reset ke 0/1).

private const val KEY_JUMLAH_DIBUKA = "jumlah_dibuka"

@Composable
fun Handson1ScreenSolusi(database: AppDatabase) {
    var jumlah by remember { mutableStateOf(0L) }

    LaunchedEffect(Unit) {
        // TODO 1: baca nilai lama dengan
        val nilaiLama = database.preferensiQueries.selectByKey(KEY_JUMLAH_DIBUKA)
            .executeAsOneOrNull()?.toLongOrNull() ?: 0L

        // TODO 2: hitung nilai baru = nilai lama + 1
        val nilaiBaru = nilaiLama + 1

        // TODO 3: simpan nilai baru dengan
        database.preferensiQueries.upsert(KEY_JUMLAH_DIBUKA, nilaiBaru.toString())

        // TODO 4: update state `jumlah` dengan nilai baru tsb
        jumlah = nilaiBaru
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Hands-on 1: Preferensi Sederhana")
        Text("Aplikasi ini sudah dibuka: $jumlah kali")
    }
}

// === Penjelasan ===
// 1. val nilaiLama = database.preferensiQueries.selectByKey(KEY_JUMLAH_DIBUKA)
// Mengambil data dari database SQLDelight berdasarkan key "jumlah_dibuka".
//
// 2. .executeAsOneOrNull()?.toLongOrNull() ?: 0L
// Mengeksekusi query untuk mengembalikan satu baris atau null jika belum pernah disimpan. Jika ada nilainya, diambil prefValue-nya, dikonversi dari String ke Long (toLongOrNull()), dan jika bernilai null (data awal/belum ada), maka default-nya adalah 0L.
//
// 3. /val nilaiBaru = nilaiLama + 1
// Menghitung nilai baru dengan cara menambahkan 1 ke nilaiLama setiap kali layar/aplikasi dibuka.
//
// 4. database.preferensiQueries.upsert(KEY_JUMLAH_DIBUKA, nilaiBaru.toString())
// Menyimpan kembali (melakukan upsert / insert atau update) nilaiBaru yang telah dikonversi ke tipe String ke dalam database SQLDelight agar datanya persisten (tetap tersimpan meskipun aplikasi ditutup).
//
// 5. jumlah = nilaiBaru
// Memperbarui variabel state Compose jumlah dengan nilaiBaru agar UI secara otomatis merefleksikan jumlah terbaru di layar (Text("Aplikasi ini sudah dibuka: $jumlah kali")).