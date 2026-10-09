package com.itera.pam.p4.solusi

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Solusi 2: Form dengan State Hoisting (slide P4 hal. 30)
// Reusable TextField component dengan state hoisting —
// komponen LabeledTextField sendiri STATELESS (tidak punya state internal),
// state-nya dipegang oleh parent (Handson2ScreenSolusi) dan dikirim turun lewat parameter.

// Checklist:
// [ ] Stateless LabeledTextField
// [ ] Parameter: value, onValueChange
// [ ] State hoisted ke parent
// [ ] Name dan Email field
// [ ] Preview yang menampilkan "Hello, [name]!"
// [ ] Gunakan 1 komponen untuk kedua field

// Stateless TextField component
@Composable
fun LabeledTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    // TODO: Implement dengan OutlinedTextField(value = value, onValueChange = onValueChange, label = { Text(label) })
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) }
    )
}

// Parent yang menyimpan state
@Composable
fun Handson2ScreenSolusi() {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Solusi 2: Form dengan State Hoisting",
            style = MaterialTheme.typography.titleMedium
        )

        LabeledTextField(
            label = "Name",
            value = name,
            onValueChange = { name = it }
        )

        // TODO: Tambahkan email field pakai LabeledTextField yang sama, hoisted ke `email`
        LabeledTextField(
            label = "Email",
            value = email,
            onValueChange = { email = it }
        )

        // TODO: Tampilkan preview data, misal: Text("Hello, $name! Email: $email")
        Text(
            text = "Hello, $name! Email: $email",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

// === Penjelasan ===
// 1. TODO: Stateless LabeledTextField Component
// Membuat komponen terpisah yang stateless (tidak menyimpan state internal). Komponen menerima nilai (value) dari luar dan mengirim event perubahan (onValueChange) ke parent.

// 2. State Hoisting di Parent (Handson2ScreenSolusi)
// var name by remember { mutableStateOf("") }
// var email by remember { mutableStateOf("") }
// State untuk name dan email di-hoist (diangkat) ke layar utama parent.

// 3. TODO: Menggunakan 1 Komponen Reusable untuk Field Name & Email
// Memanggil komponen LabeledTextField yang sama untuk dua field berbeda dengan membinding state masing-masing.

// 4. TODO: Tampilkan Preview Data
// Menampilkan hasil ketikan secara real-time.