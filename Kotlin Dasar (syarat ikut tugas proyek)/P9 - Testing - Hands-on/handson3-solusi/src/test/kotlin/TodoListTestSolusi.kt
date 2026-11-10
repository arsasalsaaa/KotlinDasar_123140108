import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

// Hands-on 3: Test Lifecycle & Setup dengan @BeforeEach
// Tugas: Lengkapi setUp() supaya setiap test mendapat TodoList yang BARU dan
// KOSONG (tidak saling memengaruhi satu sama lain), lalu lengkapi ketiga test.

class TodoListTest {

    private lateinit var todoList: TodoList

    @BeforeEach
    fun setUp() {
        // TODO 1 (Setup): inisialisasi todoList dengan TodoList() yang baru.
        todoList = TodoList()
    }

    @Test
    @DisplayName("TodoList baru harus kosong")
    fun testNewListIsEmpty() {
        // TODO 1 (Assert): assertTrue(todoList.isEmpty())
        assertTrue(todoList.isEmpty())
    }

    @Test
    @DisplayName("Menambah item menaikkan ukuran list")
    fun testAddIncreasesSize() {
        // TODO 1 (Act): todoList.add("Belajar Kotlin")
        todoList.add("Belajar Kotlin")

        // TODO 2 (Assert): assertEquals(1, todoList.size())
        assertEquals(1, todoList.size())
    }

    @Test
    @DisplayName("Menghapus item mengembalikan true dan mengurangi ukuran")
    fun testRemoveExistingItem() {
        // TODO 1 (Arrange): todoList.add("Belajar Kotlin")
        todoList.add("Belajar Kotlin")

        // TODO 2 (Act): val removed = todoList.remove("Belajar Kotlin")
        val removed = todoList.remove("Belajar Kotlin")

        // TODO 3 (Assert): assertTrue(removed) dan assertEquals(0, todoList.size())
        assertTrue(removed)
        assertEquals(0, todoList.size())
    }
}

// === Penjelasan ===
// setUp():
// Setup (TODO 1): Menginisialisasi todoList = TodoList() yang baru sebelum setiap pengujian dijalankan. Hal ini memastikan setiap unit test berjalan secara independen dan dimulai dari kondisi list kosong.

// testNewListIsEmpty():
// Assert (TODO 1): Menggunakan assertTrue(todoList.isEmpty()) untuk memverifikasi bahwa list yang baru dibuat bernilai kosong.

//testAddIncreasesSize():
// Act (TODO 1): Menambahkan item "Belajar Kotlin" menggunakan todoList.add(...).
// Assert (TODO 2): Memverifikasi ukuran list telah menjadi 1 dengan assertEquals(1, todoList.size()).

// testRemoveExistingItem():
// Arrange (TODO 1): Menambahkan item "Belajar Kotlin" ke dalam list.
// Act (TODO 2): Menghapus item tersebut dan menyimpan return value bernilai boolean ke variabel removed.
// Assert (TODO 3): Menggunakan assertTrue(removed) untuk memverifikasi penghapusan berhasil dan assertEquals(0, todoList.size()) untuk memastikan ukuran list kembali menjadi 0.