// Hands-on 1: Generic Class — Box<T>

class Box<T>(val value: T) {
    // TODO 1:
    fun <R> map(transform: (T) -> R): Box<R> {
        return Box(transform(value))
    }
}

fun main() {
    val intBox = Box(23)
    println("intBox.value = ${intBox.value}")

    val cupBox = Box("cup") // Tipe parameter bisa di-infer
    println("cupBox.value = ${cupBox.value}")

    // TODO 2:
    val stringBox = intBox.map {"Angka: $it"}
    println("stringBox.value = ${stringBox.value}")

    // TODO 3:
    val lengthBox = cupBox.map {it.length}
    println("lengthBox.value = ${lengthBox.value}")
}

// === Penjelasan ===
// class Box<T>: Deklarasi class generik bernama Box. Simbol <T> adalah type parameter (parameter tipe generik)
//               Artinya, Box bisa menampung tipe data apa saja secara dinamis
// (val value: T): Primary constructor yang mendefinisikan properti read-only (val) bernama value dengan tipe data T

// Todo 1:
// Fungsi map menggunakan parameter generik <R> (dideklarasikan sebelum nama fungsi). Fungsi ini menerima lambda transform
// yang mengubah tipe T menjadi R, lalu membungkus hasilnya ke dalam instance Box baru menggunakan Box(transform(value))

// Fungsi main:
// val intBox = Box(23) : Membuat variabel intBox yang berisi instance Box dengan nilai integer 23. Kotlin secara otomatis
//                        mendeteksi (type inference) bahwa tipe T adalah Int, sehingga tipe dari intBox adalah Box<Int>
// println("intBox.value = ${intBox.value}") : Mencetak teks ke konsol yang menampilkan nilai dari properti value pada
//                                             intBox menggunakan string template ${intBox.value}. Output: intBox.value = 23
// val cupBox = Box("cup") : Membuat instance Box kedua bernama cupBox dengan nilai string "cup". Tipe parameter T terinferensi
//                           secara otomatis sebagai String, sehingga tipe cupBox adalah Box<String>

// Todo 2:
// Pemanggilan intBox.map { "Angka: $it" } mengubah isi Box<Int> (bernilai 23) menjadi Box<String> dengan memanfaatkan variabel implisit it,
// it adalah nama variabel bawaan (implisit) yang otomatis tersedia di dalam sebuah lambda expression jika fungsi tersebut hanya memiliki satu parameter.
// Sebagai perbandingan:
// -> Penulisan Manual: intBox.map { angka -> "Angka: $angka" }
// -> Menggunakan it: intBox.map { "Angka: $it" }

// Todo 3:
// Pemanggilan cupBox.map { it.length } menghitung jumlah karakter dari string "cup" di dalam Box<String>, sehingga menghasilkan Box<Int> bernilai 3.