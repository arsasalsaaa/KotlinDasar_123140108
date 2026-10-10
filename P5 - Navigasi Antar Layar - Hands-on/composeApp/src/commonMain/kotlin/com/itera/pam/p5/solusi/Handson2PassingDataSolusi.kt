package com.itera.pam.p5.solusi

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

// Hands-on 2: Passing Data via Arguments
// Tugas: dari daftar item di Home, kirim `itemId` sebagai argumen route ke
// Detail, lalu tampilkan item yang sesuai.

private data class Item(val id: Int, val nama: String)

private val daftarItem = listOf(
    Item(1, "Buku Kotlin"),
    Item(2, "Laptop"),
    Item(3, "Headphone"),
)

@Composable
fun Handson2ScreenSolusi() {
    val navController = rememberNavController()
    // TODO 1:
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
    // TODO 2:
        composable("home") {
            HomeListScreen(navController)
        }
    // TODO 3:
        composable(
            route = "detail/{itemId}",
            arguments = listOf(
                navArgument("itemId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getInt("itemId") ?: -1
            DetailItemScreen(navController, itemId)
        }
    }
}

@Composable
private fun HomeListScreen(navController: NavHostController) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Daftar Item")
        daftarItem.forEach { item ->
            Button(onClick = {
                // TODO 4:
                navController.navigate("detail/${item.id}")
            }) {
                Text(item.nama)
            }
        }
    }
}

@Composable
private fun DetailItemScreen(navController: NavHostController, itemId: Int) {
    val item = daftarItem.find { it.id == itemId }
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Detail Item")
        Text("ID: $itemId")
        Text("Nama: ${item?.nama ?: "(tidak ditemukan)"}")
        Button(onClick = { navController.popBackStack() }) {
            Text("< Kembali")
        }
    }
}

// === Penjelasan ===
// 1. Perubahan pada fungsi Handson2Screen() (Menggantikan Text("Belum diimplementasikan...") dengan NavHost)
// - NavHost(
// Penjelasan: Memanggil composable NavHost yang berfungsi sebagai wadah utama (container) tempat perpindahan antar layar (destination) dikelola.
// - navController = navController,
// Penjelasan: Menghubungkan variabel navController (yang dibuat dengan rememberNavController()) ke NavHost untuk mengontrol alur navigasi.
// - startDestination = "home"
// Penjelasan: Menentukan rute pertama yang akan ditampilkan secara otomatis saat layar ini terbuka, yaitu rute "home".
// - composable("home") {
// Penjelasan: Mendaftarkan destinasi layar dengan rute bernama "home".
// - HomeListScreen(navController)
// Penjelasan: Memanggil composable HomeListScreen serta memberikan instance navController ke dalamnya agar tombol-tombol di layar Home bisa memicu navigasi.
// - composable(
// Penjelasan: Mendaftarkan destinasi layar kedua yang mendukung penerimaan argumen dinamis.
// - route = "detail/{itemId}",
// Penjelasan: Menentukan rute tujuan detail. "{itemId}" merupakan pola placeholder variabel argumen yang dikirim melalui rute URL/path.
// - arguments = listOf(
// Penjelasan: Membuka daftar (list) deklarasi argumen yang dibutuhkan oleh rute detail ini.
// - navArgument("itemId") {
// Penjelasan: Membuka blok konfigurasi spesifikasi argumen bernamakan "itemId".
// - type = NavType.IntType
// enjelasan: Menegaskan bahwa tipe data argumen "itemId" harus berjenis Integer (Int).
// - { backStackEntry ->
// Penjelasan: Membuka blok lambda tampilan rute detail dengan menyediakan parameter backStackEntry yang membawa data rute dan argumen saat ini.
// - val itemId = backStackEntry.arguments?.getInt("itemId") ?: -1
// Penjelasan: Membaca/mengambil nilai integer argumen "itemId" dari backStackEntry.arguments. Jika bernilai null atau tidak ditemukan, diberikan nilai fallback default -1.
// - etailItemScreen(navController, itemId)
// Penjelasan: Memanggil composable DetailItemScreen dengan meneruskan navController serta nilai itemId yang berhasil dibaca.

// 2. Perubahan pada fungsi HomeListScreen() (Pada aksi onClick di dalam Button)
// navController.navigate("detail/${item.id}")
// Penjelasan: Memanggil fungsi perintah navigasi untuk berpindah ke rute detail dengan menyisipkan ID dari item yang diklik (item.id) menggunakan Kotlin String Interpolation.