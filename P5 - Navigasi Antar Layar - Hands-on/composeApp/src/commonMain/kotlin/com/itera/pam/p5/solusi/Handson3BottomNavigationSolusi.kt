package com.itera.pam.p5.solusi

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.getValue

// Hands-on 3: Bottom Navigation
// Tugas: buat 3 tab (Home/Search/Profil) dengan NavigationBar yang terhubung
// ke NavHost.

private data class Tab(val route: String, val label: String)

private val tabs = listOf(
    Tab("home", "Home"),
    Tab("search", "Search"),
    Tab("profil", "Profil"),
)

@Composable
fun Handson3ScreenSolusi() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar {
                val backStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = backStackEntry?.destination?.route

                tabs.forEach { tab ->
                    NavigationBarItem(
                        selected = currentRoute == tab.route,
                        onClick = {
                            // BUG SENGAJA: navigate tanpa opsi popUpTo/launchSingleTop/
                            // restoreState menyebabkan back stack menumpuk setiap kali
                            // pindah tab.
                            //
                            // TODO:
                            navController.navigate(tab.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { },
                        label = { Text(tab.label) }
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("home") { Text("Halaman Home") }
            composable("search") { Text("Halaman Search") }
            composable("profil") { Text("Halaman Profil") }
        }
    }
}

// === Penjelasan ===
// 1. Penambahan Import yang Kurang
// import androidx.compose.foundation.layout.padding
// Meng-import extension function .padding() untuk Modifier. Line ini wajib ditambahkan agar baris modifier = Modifier.padding(paddingValues) tidak mengalami error unresolved reference.
// import androidx.compose.runtime.getValue
// Meng-import fungsi operator getValue yang dibutuhkan oleh Kotlin Property Delegation (by). Line ini wajib ditambahkan agar sintaks val backStackEntry by navController.currentBackStackEntryAsState() bisa berjalan dengan benar.

// 2. Perbaikan pada Blok Navigasi (onClick)
// - navController.navigate(tab.route) {
// Penjelasan: Memanggil fungsi navigate ke rute tujuan (tab.route) dan membuka blok opsi konfigurasi navigasi (NavOptionsBuilder).
// - popUpTo(navController.graph.findStartDestination().id) {
// Penjelasan: Menentukan bahwa back stack akan dibersihkan/di-pop sampai ke destinasi awal aplikasi (yaitu tab Home). Ini mencegah back stack menumpuk berlebihan saat pengguna sering berpindah tab.
// - saveState = true
// Penjelasan: Mengatur agar kondisi/state dari halaman yang ditinggalkan disimpan sebelum halaman tersebut dilepas dari back stack.
// - launchSingleTop = true
// Penjelasan: Memastikan bahwa jika tab yang diklik sedang aktif berada di paling atas back stack, halaman tersebut tidak akan dibuat ulang (re-launched) berulang kali.
// - restoreState = true
// Penjelasan: Memulihkan kembali kondisi/state halaman yang sebelumnya disimpan oleh saveState = true ketika pengguna kembali memilih tab tersebut.