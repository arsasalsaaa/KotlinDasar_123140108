package com.itera.pam.p3.solusi

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

// Hands-on 2: Login Form (Solusi)

@Composable
fun LoginForm(onLogin: (String, String) -> Unit) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxWidth().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // TODO 1: Text judul "Login"
        Text(
            text = "Login",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // TODO 2: OutlinedTextField untuk username
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        // TODO 3: OutlinedTextField untuk password
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        // TODO 4: Button "Login"
        Button(
            onClick = { onLogin(username, password) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Login")
        }
    }
}

@Composable
fun Handson2ScreenSolusi() {
    LoginForm(onLogin = { _, _ -> })
}

// === Penjelasan ===
// 1.  Penambahan import baru
// Spacer & height: Untuk memberikan spasi/jarak vertikal di antara elemen input.
// Button: Komponen tombol dari Material 3.
// MaterialTheme: Untuk mengambil konfigurasi tipografi standar Material Design (headlineMedium).
// OutlinedTextField: Komponen input teks dengan garis tepi (border).
// PasswordVisualTransformation: Mengubah karakter input password menjadi titik-titik (bullets) agar tersembunyi demi keamanan.

// 2. TODO 1: Judul "Login"
// text = "Login": Menampilkan teks judul form.
// style = MaterialTheme.typography.headlineMedium: Mengatur ukuran teks judul menjadi besar (headline).
// modifier = Modifier.padding(bottom = 16.dp): Memberikan jarak bawah sebesar 16.dp agar tidak terlalu rapat dengan kolom username.

// 3. TODO 2: OutlinedTextField untuk Username
// value = username: Menghubungkan isi bidang input dengan variabel state username.
// onValueChange = { username = it }: Meng-update nilai state username setiap kali pengguna mengetik karakter baru.
// label = { Text("Username") }: Menampilkan label petunjuk bertuliskan "Username".
// modifier = Modifier.fillMaxWidth(): Membuat bidang input membentang memenuhi lebar wadah.

// 4. TODO 3: OutlinedTextField untuk Password
// visualTransformation = PasswordVisualTransformation(): Properti khusus untuk menyamarkan teks password yang diketik menjadi karakter titik-titik (asterisk/bullet).
// value & onValueChange: Mengelola state password.