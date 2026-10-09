package com.itera.pam.p4.solusi

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

// Solusi 3: Todo App dengan ViewModel — implementasi MVVM pattern (slide P4 hal. 31)
// Checklist:
// [ ] Data class Todo
// [ ] TodoUiState dengan list
// [ ] TodoViewModel dengan StateFlow
// [ ] Add todo function
// [ ] Toggle done function
// [ ] TextField untuk input
// [ ] LazyColumn untuk list
// [ ] Checkbox untuk done

// 1. Data class
data class Todo(val id: Int, val text: String, val done: Boolean)

data class TodoUiState(
    val todos: List<Todo> = emptyList(),
    val input: String = ""
)

// 2. ViewModel
class TodoViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(TodoUiState())
    val uiState: StateFlow<TodoUiState> = _uiState.asStateFlow()

    // TODO: update _uiState.input dengan `text`, gunakan _uiState.update { it.copy(input = text) }
    fun onInputChange(text: String) {
        _uiState.update { it.copy(input = text) }
    }

    // TODO: tambahkan Todo baru ke uiState.todos dari uiState.input (id boleh pakai todos.size),
    //       lalu kosongkan input. Jangan tambahkan jika input blank.
    fun addTodo() {
        _uiState.update { currentState ->
            if (currentState.input.isBlank()) {
                currentState
            } else {
                val newTodo = Todo(
                    id = (currentState.todos.maxOfOrNull { it.id } ?: 0) + 1,
                    text = currentState.input.trim(),
                    done = false
                )
                currentState.copy(
                    todos = currentState.todos + newTodo,
                    input = ""
                )
            }
        }
    }

    // TODO: toggle `done` untuk Todo dengan id yang cocok
    //       (map list, ganti item yang id-nya sama dengan copy(done = !done))
    fun toggleTodo(id: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                todos = currentState.todos.map { todo ->
                    if (todo.id == id) todo.copy(done = !todo.done) else todo
                }
            )
        }
    }
}

// 3. Compose UI
@Composable
fun Handson3ScreenSolusi(viewModel: TodoViewModel = viewModel { TodoViewModel() }) {
    val uiState by viewModel.uiState.collectAsState()

    // TODO: Implement UI:
    //  - OutlinedTextField untuk input (value = uiState.input, onValueChange = viewModel::onInputChange)
    //  - Button "Tambah" -> viewModel.addTodo()
    //  - LazyColumn menampilkan uiState.todos, tiap item pakai Row + Checkbox(checked = todo.done,
    //    onCheckedChange = { viewModel.toggleTodo(todo.id) }) + Text(todo.text)
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Solusi 3: Todo App dengan ViewModel",
            style = MaterialTheme.typography.titleMedium
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = uiState.input,
                onValueChange = viewModel::onInputChange,
                label = { Text("Masukkan todo") },
                modifier = Modifier.weight(1f)
            )
            Button(onClick = viewModel::addTodo) {
                Text("Tambah")
            }
        }

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(uiState.todos, key = { it.id }) { todo ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Checkbox(
                        checked = todo.done,
                        onCheckedChange = { viewModel.toggleTodo(todo.id) }
                    )
                    Text(
                        text = todo.text,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }
    }
}

// === Penjelasan ===
// TODO: Update _uiState.input pada onInputChange
// Memperbarui atribut input pada StateFlow setiap kali pengguna mengetik di TextField.

// 2. TODO: Menambahkan Todo Baru pada addTodo
// Jika input tidak kosong, buat object Todo baru dengan ID ter-increment, tambahkan ke list todos, lalu bersihkan field input.

// 3. TODO: Toggle Status Done pada toggleTodo
// Mencari item berdasarkan id dan membalik nilai boolean done.

// 4. Observasi ViewModel & Form Input di Compose UI
// Menghubungkan StateFlow di ViewModel ke UI Compose menggunakan collectAsState(), lalu menyambungkan callback input dan tombol ke fungsi ViewModel.

// 5. TODO: LazyColumn & Checkbox untuk Menampilkan List Todo
// Menampilkan daftar todo secara efisien. Saat checkbox dicentang, ia memanggil viewModel.toggleTodo(todo.id).