package com.itera.pam.p4.solusi

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Solusi 1: Counter App dengan State (slide P4 hal. 29)
// Tugas: Buat counter dengan increment, decrement, dan reset.

// Checklist:
// [ ] State dengan remember
// [ ] mutableStateOf(0)
// [ ] Text untuk display
// [ ] Button increment (+1)
// [ ] Button decrement (-1)
// [ ] Button reset (ke 0)
// [ ] Disable decrement jika 0

@Composable
fun Handson1ScreenSolusi() {
    // Todo: Deklarasikan state untuk count dengan remember & mutableStateOf
    var count by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Solusi 1: Counter App dengan State",
            style = MaterialTheme.typography.titleMedium
        )

        // Todo: Tampilkan nilai count
        Text(
            text = "Count: $count",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(vertical = 24.dp)
        )

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            // Todo: Decrement button (-1), disabled jika count <= 0
            Button(
                onClick = { if (count > 0) count-- },
                enabled = count > 0
            ) {
                Text("-")
            }
            // Todo: Increment button (+1)
            Button(onClick = { count++ }) {
                Text("+")
            }
            // Todo: Reset button (ke 0)
            Button(onClick = { count = 0 }) {
                Text("Reset")
            }
        }
    }
}

// === Penjelasan ===
// 1. TODO: Deklarasikan State dengan remember & mutableStateOf(0)
// Menggunakan mutableStateOf(0) agar Compose tahu bahwa nilai angka ini berubah dan memicu recomposition. Dibungkus remember supaya nilainya tidak ter-reset saat UI di-render ulang.

// 2. TODO: Tampilkan nilai count
// Text(text = "Count: $count", style = MaterialTheme.typography.headlineLarge)
// Menampilkan angka count terbaru ke layar.

// 3. TODO: Implementasi Tombol Decrement (-1) & Disable jika 0
// Button(
//    onClick = { if (count > 0) count-- },
//    enabled = count > 0
//) { Text("-") }
// Callback onClick mengurangi count dengan batasan tidak boleh < 0. Parameter enabled = count > 0 otomatis menonaktifkan tombol ketika counter mencapai 0.

// 4. TODO: Implementasi Tombol Increment (+1)
// Button(onClick = { count++ }) { Text("+") }
// Menambahkan nilai count sebesar 1 setiap kali diklik.

// 5. TODO: Implementasi Tombol Reset ke 0
// Button(onClick = { count = 0 }) { Text("Reset") }
// Mengembalikan nilai count langsung ke 0.