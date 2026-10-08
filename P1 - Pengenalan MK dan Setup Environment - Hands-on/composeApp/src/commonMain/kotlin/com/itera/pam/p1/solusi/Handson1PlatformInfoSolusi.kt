package com.itera.pam.p1.solusi

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.itera.pam.p1.getPlatformName

// Hands-on 1: Expect/Actual — Info Platform
// getPlatformName() sudah didefinisikan lewat expect/actual (lihat Platform.kt
// dan Platform.android.kt / Platform.ios.kt / Platform.desktop.kt).
//
// TODO 1: Panggil getPlatformName() untuk mendapatkan nama platform saat ini
// TODO 2: Kembalikan pesan sapaan yang menyebutkan nama platform tsb,
//         contoh: "Halo dari Android 34!" / "Halo dari Desktop JVM 21!"

fun getGreetingMessage(): String {
    // Kode ku di sini...
    val platformName = getPlatformName()
    return "Halo dari $platformName"
}

@Composable
fun Handson1ScreenSolusi() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Hands-on 1: Expect/Actual")
        Text(getGreetingMessage())
    }
}

// === Penjelasan ===
// 1. val platformName = getPlatformName()
// Memanggil fungsi getPlatformName yang didefinisikan melalui konsep expect/actual di Kotlin Multiplatform untuk mendeteksi platform yang sedang berjalan (seperti Android 34, Desktop JVM 21, dll). Hasilnya disimpan ke dalam variabel platformName.
// 2. return "Halo dari $platformName!"
// Mengembalikan string hasil sapaan dengan memanfaatkan string interpolation ($platformName) untuk menyisipkan nilai nama platform dinamis ke dalam kalimat "Halo dari ...!" sesuai dengan contoh format yang diminta.