package com.itera.pam.p7.solusi

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.itera.pam.p7.db.AppDatabase
import com.itera.pam.p7.latihan.CatatanRepository

// Hands-on 3: Offline-First — Tampilkan & Hapus Catatan
// Gunakan CatatanRepository dari Handson2Repository.kt (yang sudah kamu
// lengkapi) untuk menampilkan daftar catatan dalam LazyColumn, form untuk
// menambah catatan baru, dan tombol hapus per item.
//
// CATATAN: layar ini SENGAJA belum menampilkan apa pun (hanya placeholder
// di bawah) sampai semua TODO dilengkapi.

@Composable
fun Handson3ScreenSolusi(database: AppDatabase) {
    // TODO 1: buat val repository = remember { CatatanRepository(database) }
    val repository = remember { CatatanRepository(database) }

    // TODO 2: collect repository.getAll() sebagai Compose state, misalnya:
    val catatanList by repository.getAll().collectAsState(initial = emptyList())

    // State lokal untuk menampung input form (judul dan isi)
    var judul by remember { mutableStateOf("") }
    var isi by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Hands-on 3: Offline-First Catatan", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = judul,
            onValueChange = { judul = it },
            label = { Text("Judul Catatan") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = isi,
            onValueChange = { isi = it },
            label = { Text("Isi Catatan") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                if (judul.isNotBlank() && isi.isNotBlank()) {
                    repository.tambah(judul, isi)
                    judul = ""
                    isi = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Tambah")
        }

        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(16.dp))

        // TODO 3: tampilkan `catatanList` dengan LazyColumn { items(catatanList) { catatan -> ... } }
        //         setiap item: Text(catatan.judul), Text(catatan.isi),
        //         Button("Hapus") { repository.hapus(catatan.id) }
        LazyColumn(modifier = Modifier.fillMaxWidth().weight(1f)) {
            items(catatanList) { catatan ->
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(text = catatan.judul, style = MaterialTheme.typography.titleMedium)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = catatan.isi, style = MaterialTheme.typography.bodyMedium)
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(
                            onClick = { repository.hapus(catatan.id) }
                        ) {
                            Text("Hapus")
                        }
                    }
                }
            }
        }
    }
}

// TODO 5: build & jalankan, tutup aplikasi, lalu buka lagi — catatan
    //         yang kamu tambahkan harus tetap ada (itulah "offline-first")

// === Penjelasan ===
// 1. val repository = remember { CatatanRepository(database) } (TODO 1)
// Membuat dan mengingat instance CatatanRepository menggunakan remember agar tidak dibuat ulang setiap kali terjadi re-composition.
//
// 2. val catatanList by repository.getAll().collectAsState(initial = emptyList()) (TODO 2)
// Mengumpulkan data dari Flow (repository.getAll()) dan mengubahnya menjadi Compose State (catatanList) dengan nilai awal berupa list kosong (emptyList()). Setiap kali data di database berubah, UI akan otomatis memperbarui dirinya.
//
// 3. OutlinedTextField untuk judul & isi, serta Button("Tambah") (TODO 4)
// Menyediakan input teks untuk judul dan isi catatan. Tombol "Tambah" memanggil repository.tambah(judul, isi) untuk memasukkan data ke database, kemudian mengosongkan kembali kolom input (judul = "", isi = "").
//
// 4. LazyColumn dengan items(catatanList) (TODO 3)
// Menampilkan daftar catatan secara efisien menggunakan LazyColumn. Di dalam setiap item, ditampilkan Text(catatan.judul), Text(catatan.isi), serta Button("Hapus") yang memanggil repository.hapus(catatan.id) saat diklik.
//
// 5. Verifikasi Offline-First (TODO 5)
// Dengan arsitektur ini, saat aplikasi ditutup dan dibuka kembali, data catatan akan tetap tersimpan di database lokal (SQLite/SQLDelight), mencerminkan prinsip offline-first.
//
// 6. Menambahkan import seperti remember, mutableStateOf, collectAsState, LazyColumn, OutlinedTextField, Button, Card, dll. agar fungsi-fungsi Compose dapat dikenali.