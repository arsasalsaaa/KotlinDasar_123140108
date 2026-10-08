package com.itera.pam.p3.solusi

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

// Hands-on 1: ProfileCard (Solusi)

@Composable
fun ProfileCard(name: String, bio: String) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            // TODO 1: tambahkan verticalAlignment = Alignment.CenterVertically
            // (butuh import androidx.compose.ui.Alignment)
            verticalAlignment = Alignment.CenterVertically
        ) {
            // TODO 2: Tambahkan avatar bulat
            Box(
                modifier = Modifier.size(48.dp).background(Color.Gray, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Person, contentDescription = null)
            }
            // TODO 3: Tambahkan Column(modifier = Modifier.padding(start = 12.dp)) berisi:
            //         - Text(name) dengan fontWeight = FontWeight.Bold
            //         - Text(bio) dengan color = Color.Gray
            Column(modifier = Modifier.padding(start = 12.dp)) {
                Text(text = name, fontWeight = FontWeight.Bold)
                Text(text = bio, color = Color.Gray)
            }
        }
    }
}

@Composable
fun Handson1ScreenSolusi() {
    ProfileCard(name = "John Doe", bio = "Mobile Developer")
}

// === Penjelasan ===
// 1. TODO 1: Menambahkan Alignment
// Agar avatar di sebelah kiri dan teks di sebelah kanan berada sejajar tepat di tengah secara vertikal.

// 2. TODO 2: Membuat Komponen Avatar Lingkaran
// Modifier.size(48.dp): Menentukan lebar dan tinggi kotak avatar sebesar 48.dp.
// background(Color.Gray, CircleShape): Memberikan warna latar abu-abu berbentuk lingkaran.
// contentAlignment = Alignment.Center: Memposisikan ikon di tengah-tengah Box.
// Icon(Icons.Default.Person, contentDescription = null): Menampilkan ikon orang di dalam avatar.

// 3. TODO 3: Membuat Column Teks Nama dan Bio
// Column(modifier = Modifier.padding(start = 12.dp)): Menyusun elemen secara vertikal dan memberi jarak sebesar 12.dp dari avatar di sebelah kirinya.
// Text(text = name, fontWeight = FontWeight.Bold): Menampilkan nama pengguna dengan gaya cetak tebal (bold).
// Text(text = bio, color = Color.Gray): Menampilkan deskripsi/bio dengan warna abu-abu (gray).
