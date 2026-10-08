package com.itera.pam.p3.solusi

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

// Hands-on 3: Product List (Solusi)

data class Produk(val nama: String, val harga: String, val warna: Color)

val daftarProdukContoh = listOf(
    Produk("Produk 1", "Rp 100.000", Color(0xFF009688)),
    Produk("Produk 2", "Rp 250.000", Color(0xFF9C27B0)),
    Produk("Produk 3", "Rp 75.000", Color(0xFF4CAF50)),
)

@Composable
fun ProdukItem(produk: Produk) {
    // TODO 1: Membungkus dengan Card
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp)
    ) {
        // TODO 2: Membuat Row di Dalam Card
        Row(
            modifier = Modifier.padding(8.dp)
        ) {
            // TODO 3: Kotak Warna Pengganti Gambar Produk (Box)
            Box(
                modifier = Modifier.size(80.dp).background(produk.warna)
            )
            // TODO 4: Column dan Text Informasi Produk
            Column(
                modifier = Modifier.padding(start = 12.dp)
            ) {
                Text(text = produk.nama)
                Text(text = produk.harga, color = Color.Gray)
            }
        }
    }
}

@Composable
fun Handson3ScreenSolusi() {
    Column {
        daftarProdukContoh.forEach { produk ->
            ProdukItem(produk)
        }
    }
}

// === Penjelasan ===
// 1. Import baru
// background: Mengisi warna latar belakang kotak gambar produk.
// Box: Wadah layout untuk kotak warna pengganti gambar produk.
// Row: Menyusun elemen secara horizontal (gambar produk di kiri, detail teks di kanan).
// fillMaxWidth: Membuat kartu membentang penuh searah lebar layar.
// padding: Memberikan jarak/margin di sekitar elemen.
// size: Mengatur ukuran dimensi kotak gambar produk (80.dp).
// Card: Komponen kartu Material 3 sebagai wadah pembungkus tiap item produk.
// Text: Komponen untuk menampilkan teks nama dan harga produk.
// dp: Satuan ukuran jarak/dimensi dalam Jetpack Compose

// 2.  TODO 1: Membungkus dengan Card
// Card: Membungkus setiap produk dalam tampilan berbentuk kartu bergaya Material Design.
// Modifier.fillMaxWidth(): Kartu memenuhi seluruh lebar layar.
// padding(8.dp): Memberi margin jarak sebesar 8.dp di luar kartu agar antar kartu produk tidak saling menempel.

// 3. TODO 2: Membuat Row di Dalam Card
// Row: Tata letak horizontal agar gambar produk (sebelah kiri) dan informasi teks (sebelah kanan) berada dalam satu baris.
// Modifier.padding(8.dp): Memberikan padding di dalam kartu agar isinya tidak menempel pada tepi kartu.

// 4. TODO 3: Kotak Warna Pengganti Gambar Produk (Box)
// Box: Digunakan sebagai wadah berbentuk persegi pengganti gambar produk (placeholder).
// Modifier.size(80.dp): Mengatur ukuran kotak gambar menjadi 80.dp × 80.dp.
// background(produk.warna): Mewarnai kotak sesuai warna yang ditentukan pada data class Produk (misal: Tosca, Ungu, Hijau).

// 5. TODO 4: Column dan Text Informasi Produk
// Column: Menyusun teks nama produk dan harganya secara vertikal dari atas ke bawah.
// modifier = Modifier.padding(start = 12.dp): Memberikan spasi kiri sebesar 12.dp agar teks tidak menempel pada kotak gambar produk.
// Text(text = produk.nama): Menampilkan nama produk (contoh: "Produk 1").
// Text(text = produk.harga, color = Color.Gray): Menampilkan harga produk (contoh: "Rp 100.000") dengan warna teks abu-abu (Color.Gray).