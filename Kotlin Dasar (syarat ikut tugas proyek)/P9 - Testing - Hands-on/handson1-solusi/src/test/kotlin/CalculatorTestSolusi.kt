import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

// Hands-on 1: Unit Test Dasar (pola Arrange-Act-Assert)
// Tugas: Lengkapi ketiga test di bawah mengikuti pola AAA:
//   Arrange -> siapkan data/objek yang dibutuhkan
//   Act     -> panggil method yang diuji
//   Assert  -> verifikasi hasilnya dengan assertEquals(expected, actual)

class CalculatorTest {

    private val calculator = Calculator()

    @Test
    @DisplayName("Penjumlahan dua bilangan positif")
    fun testAdd() {
        // TODO 1 (Arrange): siapkan dua bilangan, misalnya a = 2 dan b = 3
        val a = 2
        val b = 3

        // TODO 2 (Act): panggil calculator.add(a, b) dan simpan hasilnya
        val hasil = calculator.add(a, b)

        // TODO 3 (Assert): assertEquals(5, hasil)
        assertEquals(5, hasil)
    }

    @Test
    @DisplayName("Pengurangan yang menghasilkan angka negatif")
    fun testSubtractNegativeResult() {
        // TODO 1 (Arrange): siapkan dua bilangan a = 3 dan b = 5
        val a = 3
        val b = 5

        // TODO 2 (Act): panggil calculator.subtract(a, b)
        val hasil = calculator.subtract(a, b)

        // TODO 3 (Assert): memverifikasi 3 - 5 = -2
        assertEquals(-2, hasil)
    }

    @Test
    @DisplayName("Pembagian dua bilangan bulat")
    fun testDivide() {
        // TODO 1 (Arrange): siapkan dua bilangan a = 10 dan b = 2
        val a = 10
        val b = 2

        // TODO 2 (Act): panggil calculator.divide(a, b)
        val hasil = calculator.divide(a, b)

        // TODO 3 (Assert): memverifikasi 10 / 2 = 5
        assertEquals(5, hasil)
    }
}

// === Penjelasan ===
// testAdd():
// Arrange (TODO 1): Menyiapkan variabel a = 2 dan b = 3.
// Act (TODO 2): Memanggil fungsi calculator.add(a, b) dan menyimpan hasilnya di variabel hasil.
// Assert (TODO 3): Menggunakan assertEquals(5, hasil) untuk memastikan hasil penjumlahan adalah 5.

// testSubtractNegativeResult():
// Arrange (TODO 1): Menyiapkan variabel a = 3 dan b = 5.
// Act (TODO 2): Memanggil fungsi calculator.subtract(a, b).
// Assert (TODO 3): Menggunakan assertEquals(-2, hasil) untuk memverifikasi bahwa 3 - 5 = -2.

// testDivide():
// Arrange (TODO 1): Menyiapkan variabel a = 10 dan b = 2.
// Act (TODO 2): Memanggil fungsi calculator.divide(a, b).
// Assert (TODO 3): Menggunakan assertEquals(5, hasil) untuk memverifikasi bahwa 10 / 2 = 5.