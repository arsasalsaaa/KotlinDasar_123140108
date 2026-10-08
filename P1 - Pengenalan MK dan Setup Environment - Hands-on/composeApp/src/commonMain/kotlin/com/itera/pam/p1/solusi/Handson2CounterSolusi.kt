package com.itera.pam.p1.solusi

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

// Hands-on 2: State & Recomposition — Counter
// Tugas: Buat counter sederhana dengan tombol tambah (+) dan kurang (-),
// menggunakan remember { mutableStateOf(...) } agar UI otomatis recompose
// setiap kali nilainya berubah.

@Composable
fun Handson2ScreenSolusi() {
    // TODO 1: Buat state `count` dengan nilai awal 0:
    //         var count by remember { mutableStateOf(0) }
    // (butuh import androidx.compose.runtime.getValue/setValue/mutableStateOf/remember)
    var count by remember { mutableStateOf(0) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Hands-on 2: Counter")
        Text("Nilai: $count") // TODO 2: ganti "???" dengan nilai count

        Row {
            // TODO 3: tambah count
            Button(onClick = {count++}) {
                Text("+")
            }
            // TODO 4: kurangi count
            Button(onClick = {count--}) {
                Text("-")
            }
        }
    }
}

// === Penjelasan ===
// 1. Menambahkan import state
// import androidx.compose.runtime.getValue
// import androidx.compose.runtime.mutableStateOf
// import androidx.compose.runtime.remember
// import androidx.compose.runtime.setValue
// Diperlukan agar kita bisa menggunakan delegasi properti reaktif (by) serta fungsi remember dan
// mutableStateOf. Tanpa import ini, Kotlin tidak akan mengenali fungsi state management di Jetpack Compose.

// 2. var count by remember { mutableStateOf(0) }
// mutableStateOf(0) membuat variabel penampung nilai dengan angka awal 0.
// remember { ... } memastikan nilai variabel ini tetap disimpan (mengingat nilainya) meskipun layar mengalami recomposition (penyegaran UI).
// Kata kunci by (delegation) membuat kita bisa langsung membaca atau mengubah variabel count seperti variabel biasa tanpa perlu .value.

// 3. Text("Nilai: $count")
// Menggunakan string interpolation ($count) untuk mengambil dan menampilkan nilai terbaru dari variabel count secara real-time ke layar.

// 4. Aksi Tombol Tambah & Kurang (TODO 3 & TODO 4)
// Button(onClick = { count++ }) { Text("+") }
// Button(onClick = { count-- }) { Text("-") }
// Setiap kali tombol + diklik, nilai count bertambah 1 (count++). Setiap kali tombol - diklik, nilai count berkurang 1 (count--).
// Perubahan nilai ini otomatis memerintahkan Compose untuk melakukan recomposition sehingga teks nilai di atasnya ikut berubah seketika.