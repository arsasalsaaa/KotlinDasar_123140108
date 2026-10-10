package com.itera.pam.p5.solusi

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

// Hands-on 1: NavHost & NavController Dasar
// Tugas: buat 2 layar ("home" dan "detail") yang dihubungkan dengan
// NavHost + NavController — dari Home klik tombol untuk pindah ke Detail,
// dari Detail klik tombol untuk kembali (back stack).

@Composable
fun Handson1ScreenSolusi() {
    val navController = rememberNavController()
    // TODO 1: Panggil NavHost(navController = navController, startDestination = "home") { ... }
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {

    // TODO 2: Di dalamnya, daftarkan composable("home") { HomeScreen(navController) }
        composable("home") {
            HomeScreenSolusi(navController)
        }

    // TODO 3: Daftarkan juga composable("detail") { DetailScreen(navController) }
        composable("detail") {
            DetailScreen(navController)
        }
    }
}

@Composable
private fun HomeScreenSolusi(navController: NavHostController) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Home Screen")
        Button(onClick = {
            // TODO 4: Navigasi ke "detail" dengan navController.navigate("detail")
            navController.navigate("detail")
        }) {
            Text("Buka Detail")
        }
    }
}

@Composable
private fun DetailScreen(navController: NavHostController) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Detail Screen")
        Button(onClick = {
            // TODO 5: Kembali ke layar sebelumnya dengan navController.popBackStack()
            navController.popBackStack()
        }) {
            Text("< Kembali")
        }
    }
}

// === Penjelasan ===
// 1. Perubahan pada fungsi Handson1Screen() (Menggantikan Text("Belum diimplementasikan...") dengan NavHost)
// - NavHost(
// Penjelasan: Memanggil composable NavHost yang berfungsi sebagai wadah (container) utama untuk menampilkan dan mengelola perpindahan antar layar.
// - navController = navController,
// Penjelasan: Menghubungkan instansi navController (hasil dari rememberNavController()) ke dalam NavHost untuk mengendalikan alur navigasi.
// - startDestination = "home"
// Penjelasan: Menentukan rute pertama yang akan langsung ditampilkan saat Handson1Screen dibuka, yaitu rute "home".
// - composable("home") {
// Penjelasan: Mendaftarkan destinasi layar pertama dengan nama rute "home".
// - HomeScreen(navController)
// Penjelasan: Memanggil composable HomeScreen dan mengoper navController ke dalamnya agar tombol di halaman Home dapat memicu navigasi.
// - composable("detail") {
// Penjelasan: Mendaftarkan destinasi layar kedua dengan nama rute "detail".
// - DetailScreen(navController)
// Penjelasan: Memanggil composable DetailScreen dan mengoper navController ke dalamnya agar tombol di halaman Detail dapat memicu navigasi kembali (back stack).

// 2.  Perubahan pada fungsi HomeScreen() (Di dalam parameter onClick pada Button)
// navController.navigate("detail")
// Penjelasan: Memanggil fungsi navigate pada navController untuk melakukan perpindahan dari layar saat ini ke layar dengan rute "detail".

// 3. Perubahan pada fungsi DetailScreen() (Di dalam parameter onClick pada Button)
// navController.popBackStack()
// Penjelasan: Memanggil fungsi popBackStack pada navController untuk melepas (pop) layar saat ini dari tumpukan riwayat navigasi (back stack) dan kembali ke layar sebelumnya (yaitu Home).