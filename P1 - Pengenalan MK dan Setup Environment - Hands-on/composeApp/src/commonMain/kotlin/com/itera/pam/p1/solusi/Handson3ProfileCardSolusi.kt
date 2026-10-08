package com.itera.pam.p1.solusi

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.itera.pam.p1.getPlatformName

// Hands-on 3: Layout Dasar — Profile Card
// Tugas: Susun sebuah "kartu profil" sederhana berisi nama, NIM, dan platform
// yang sedang berjalan, menggunakan Card, Column, Row, dan Modifier.

@Composable
fun Handson3ScreenSolusi() {
    // TODO 1: Bungkus semua konten dengan:
    //         Card(modifier = Modifier.fillMaxWidth().padding(16.dp)) { ... }
    // TODO 2: Di dalam Card, buat Column(modifier = Modifier.padding(16.dp))
    // TODO 3: Di dalam Column, tambahkan Text() untuk nama kamu
    // TODO 4: Tambahkan Text() untuk NIM kamu
    // TODO 5: Tambahkan Row { } berisi Text("Platform: ") dan
    //         Text(getPlatformName()) (import com.itera.pam.p1.getPlatformName)

    Card(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Nama: Arsa Salsabila")
            Text("NIM: 123140108")
            Row(modifier = Modifier.padding(top = 8.dp)) {
                Text("Platform: ")
                Text(getPlatformName())
            }
        }
    }
}

// === Penjelasan ===
// 1. Menambahkan import state
// import androidx.compose.foundation.layout.Column
// import androidx.compose.foundation.layout.Row
// import androidx.compose.foundation.layout.fillMaxWidth
// import androidx.compose.foundation.layout.padding
// import androidx.compose.material3.Card
// import com.itera.pam.p1.getPlatformName
// Mengimpor komponen Material Design (Card), penata letak tata letak (Column untuk vertikal, Row untuk horizontal),
// pengatur ukuran (fillMaxWidth, padding), serta fungsi getPlatformName() untuk mendeteksi platform saat ini (Android/Desktop/iOS).

// 2. Card(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
// Membuat komponen wadah berupa kartu (Card) yang membentang selebar layar (fillMaxWidth) dengan jarak luar (padding) sebesar 16.dp agar tidak menempel ke tepi layar.

// 3. Column(modifier = Modifier.padding(16.dp)) {
// Di dalam kartu, kita membungkus elemen teks dengan Column agar elemen-elemen di dalamnya tersusun rapi dari atas ke bawah secara vertikal.

// 4. Menampilkan Informasi Profil & Platform (TODO 3, 4, 5)
// Text("Nama: Mahasiswa ITERA")
// Text("NIM: 123140108")
// Row(modifier = Modifier.padding(top = 8.dp)) {
//    Text("Platform: ")
//    Text(getPlatformName())
// Menampilkan teks Nama dan NIM secara berurutan ke bawah.
// Menggunakan Row untuk menyusun teks "Platform: " dan hasil fungsi getPlatformName() secara berdampingan (horizontal) agar tampil sejajar di satu baris.