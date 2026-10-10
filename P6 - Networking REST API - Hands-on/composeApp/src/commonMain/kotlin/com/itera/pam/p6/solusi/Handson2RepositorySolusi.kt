package com.itera.pam.p6.solusi

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// Hands-on 2: Repository Pattern & Loading State
// Bungkus fetchUsers() (dari Handson1KtorClient.kt) di balik sebuah Repository,
// lalu buat state holder sederhana yang merepresentasikan status pengambilan
// data: Loading / Success / Error.
//
// Catatan desain: state holder ini sengaja berupa class biasa (bukan
// androidx.lifecycle.ViewModel) supaya hands-on ini tetap fokus ke konsep
// networking/repository — pola ViewModel yang lebih lengkap akan dipelajari
// di Pertemuan 4 (State Management MVVM).

class UserRepository {
    suspend fun getUsers(): List<RemoteUser> = fetchUsers()
}

sealed class UiState {
    data object Loading : UiState()
    data class Success(val users: List<RemoteUser>) : UiState()
    data class Error(val message: String) : UiState()
}

class UserListStateHolder(
    private val repository: UserRepository = UserRepository()
) {
    private val _state = MutableStateFlow<UiState>(UiState.Loading)
    val state: StateFlow<UiState> = _state.asStateFlow()

    suspend fun load() {
        // TODO 1: set _state.value ke UiState.Loading sebelum mulai fetch
        _state.value = UiState.Loading
        try {
            // TODO 2: panggil repository.getUsers() di dalam try-catch
            val users = repository.getUsers()

            // TODO 3: jika berhasil, set _state.value ke UiState.Success(users)
            _state.value = UiState.Success(users)
        } catch (e: Exception) {
            // TODO 4: jika gagal (catch Exception e), set _state.value ke
            _state.value = UiState.Error(e.message ?: "Terjadi kesalahan")
        }
    }
}


@Composable
fun Handson2ScreenSolusi() {
    val holder = remember { UserListStateHolder() }
    val state by holder.state.collectAsState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        holder.load()
    }

    Column(modifier = Modifier.padding(16.dp).fillMaxSize()) {
        Text("Hands-on 2: Repository Pattern & Loading State", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Contoh penggunaan UserRepository & UiState (Loading / Success / Error):")
        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier.fillMaxSize(),
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
                        Text(text = currentState.message, color = MaterialTheme.colorScheme.error)
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
}

// === Penjelasan ===
// Penjelasan Per Line Kode Perubahan (load())
// - suspend fun load() {
// Deklarasi fungsi suspend bernama load() yang bertugas memicu proses pengambilan data dan mengelola perubahan status (state) UI.
// - _state.value = UiState.Loading
// Mengubah nilai _state menjadi UiState.Loading untuk memberi tahu UI bahwa proses memuat data sedang berjalan.
// - try {
// Membuka blok try untuk mengeksekusi operasi asynchronous yang berpotensi memicu kegagalan jaringan atau error lainnya.
// - val users = repository.getUsers()
// Memanggil fungsi getUsers() pada repository dan menyimpan hasilnya (daftar user) ke dalam variabel users.
// - _state.value = UiState.Success(users)
// Mengubah nilai _state menjadi UiState.Success(users) dengan menyisipkan data users yang berhasil didapatkan.
// - } catch (e: Exception) {
// Membuka blok catch untuk menangkap segala jenis kesalahan/exception (e) yang terjadi selama proses di dalam blok try.
// - _state.value = UiState.Error(e.message ?: "Terjadi kesalahan")
// Mengubah nilai _state menjadi UiState.Error dengan membawa pesan kesalahan (e.message), atau pesan alternatif "Terjadi kesalahan" jika e.message bernilai null.