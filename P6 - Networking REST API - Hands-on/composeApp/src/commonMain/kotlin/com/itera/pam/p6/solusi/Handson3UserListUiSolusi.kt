package com.itera.pam.p6.solusi

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

// Hands-on 3: Tampilkan Data di Compose dengan Loading/Error UI
// Tugas: pakai UserListStateHolder (dari Handson2Repository.kt) untuk
// menampilkan:
// - CircularProgressIndicator() selagi UiState.Loading
// - LazyColumn berisi nama & email user selagi UiState.Success
// - Pesan error + tombol "Coba Lagi" (yang memanggil ulang load()) selagi
//   UiState.Error

@Composable
fun Handson3ScreenSolusi() {
    // TODO 1: buat state holder: val holder = remember { UserListStateHolder() }
    val holder = remember { UserListStateHolder() }

    // TODO 2: collect state-nya: val state by holder.state.collectAsState()
    val state by holder.state.collectAsState()

    // TODO 3: panggil holder.load() sekali saat pertama kali screen ini muncul,
    //         pakai: LaunchedEffect(Unit) { holder.load() }
    // TODO 4: buat scope coroutine untuk tombol retry:
    //         val scope = rememberCoroutineScope()
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        holder.load()
    }

    // TODO 5: buat `when (state)` yang menangani 3 kasus:
    //         - UiState.Loading -> CircularProgressIndicator()
    //         - UiState.Success -> LazyColumn { items(state.users) { user -> ... } }
    //         - UiState.Error   -> Text(state.message) + Button("Coba Lagi") yang
    //                              memanggil scope.launch { holder.load() }
    Box(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        when (val currentState = state) {
            is UiState.Loading -> {
                CircularProgressIndicator()
            }
            is UiState.Success -> {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(currentState.users) { user ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(
                                    text = user.name,
                                    style = MaterialTheme.typography.titleMedium
                                )
                                Text(
                                    text = user.email,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }
                }
            }
            is UiState.Error -> {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = currentState.message)
                    Button(
                        onClick = {
                            scope.launch {
                                holder.load()
                            }
                        },
                        modifier = Modifier.padding(top = 8.dp)
                    ) {
                        Text("Coba Lagi")
                    }
                }
            }
        }
    }
}

// === Penjelasan ===
// Penjelasan Per Line Kode (Handson3Screen)
// - @Composable
// Anotasi yang menandai bahwa fungsi Handson3Screen merupakan fungsi komponen tampilan Jetpack Compose.
// - fun Handson3Screen() {
// Deklarasi fungsi Composable bernama Handson3Screen.
// - val holder = remember { UserListStateHolder() }
// Menginisialisasi dan menyimpan instance UserListStateHolder di memori Compose menggunakan remember agar tidak dibuat ulang saat terjadi recomposition.
// - val state by holder.state.collectAsState()
// Mengobservasi holder.state (StateFlow) dan mengonversinya menjadi State Compose agar UI otomatis terbarui saat status berubah.
// - val scope = rememberCoroutineScope()
// Mengambil CoroutineScope yang terikat dengan siklus hidup Composable untuk menjalankan coroutine (misalnya memanggil fungsi suspend load() dari klik tombol).
// - LaunchedEffect(Unit) {
// Membuka efek samping (side-effect) yang dieksekusi sekali saja saat komponen pertama kali ditampilkan di layar.
// - holder.load()
// Memanggil fungsi load() untuk mulai memuat data dari API saat pertama kali Screen dibuka.
// - Box(
// Komponen kontainer Box yang digunakan untuk menumpuk atau menempatkan UI di tengah layar.
// - modifier = Modifier.fillMaxSize().padding(16.dp),
// Mengatur ukuran Box agar memenuhi seluruh layar dengan jarak margin dalam (padding) sebesar 16dp.
// - contentAlignment = Alignment.Center
// Mengatur posisi default seluruh elemen di dalam Box berada di tengah layar.
// - when (val currentState = state) {
// Memeriksa dan mencocokkan status state saat ini (Loading, Success, atau Error).
// - is UiState.Loading -> {
// Cabang kondisi jika status saat ini adalah UiState.Loading.
// - CircularProgressIndicator()
// Menampilkan komponen loading spinner putar.
// - is UiState.Success -> {
// Cabang kondisi jika status saat ini adalah UiState.Success.
// - LazyColumn(modifier = Modifier.fillMaxSize()) {
// Menampilkan komponen daftar gulir vertikal (LazyColumn) yang memenuhi area Box.
// - items(currentState.users) { user ->
// Melakukan iterasi/looping pada setiap objek user dari daftar currentState.users.
// - Card(
// Komponen pembungkus berbentuk kartu (Card) untuk setiap item user.
// - modifier = Modifier
// Membuka rantai Modifier untuk Card.
// - .fillMaxWidth()
// Mengatur lebar Card agar memenuhi lebar layar.
// - .padding(vertical = 4.dp)
// Memberikan jarak vertikal antar kartu sebesar 4dp.
// - Column(modifier = Modifier.padding(16.dp)) {
// Menyusun elemen teks nama dan email secara vertikal di dalam kartu dengan padding 16dp.
// - Text(
// Komponen teks untuk menampilkan nama user.
// - text = user.name,
// Mengisi nilai teks dengan nama user.
// - style = MaterialTheme.typography.titleMedium
// Mengatur gaya teks nama menggunakan tipografi judul sedang (titleMedium).
// - Text(
// Komponen teks untuk menampilkan email user.
// - text = user.email,
// Mengisi nilai teks dengan email user.
// - style = MaterialTheme.typography.bodyMedium
// Mengatur gaya teks email menggunakan tipografi isi sedang (bodyMedium).
// - is UiState.Error -> {
// Cabang kondisi jika status saat ini adalah UiState.Error.
// - Column(horizontalAlignment = Alignment.CenterHorizontally) {
// Menyusun teks error dan tombol retry secara vertikal tepat di tengah.
// - Text(text = currentState.message)
// Menampilkan teks pesan error yang didapat dari status.
// - Button(
// Komponen tombol interaktif.
// - onClick = {
// Callback ketika tombol diklik.
// - scope.launch {
// Menjalankan coroutine baru melalui scope.
// - holder.load()
// Memanggil ulang fungsi holder.load() untuk mencoba memuat ulang data.
// - modifier = Modifier.padding(top = 8.dp)
// Memberi jarak margin atas sebesar 8dp untuk tombol.
// - Text("Coba Lagi")
// Menampilkan label teks "Coba Lagi" pada tombol.