// Hands-on 3: Variance — declaration-site "out"

open class Animal(val name: String)
class Cat(name: String) : Animal(name)

// TODO 1: Tambahkan modifier variance yang tepat pada T di sini
interface Container<out T> {
    fun get(): T
}

class CatContainer(private val cat: Cat) : Container<Cat> {
    override fun get(): Cat = cat
}

fun printAnimalName(container: Container<Animal>) {
    println("Nama hewan: ${container.get().name}")
}

fun main() {
    val catContainer: Container<Cat> = CatContainer(Cat("Whiskers"))

    // TODO 2: Setelah TODO 1 benar, baris berikut akan bisa di-compile
    printAnimalName(catContainer)
}

// === Penjelasan ===

// Sebelum ada out (Invariant): Kotlin sangat kaku. Sistem menganggap kotak berisi kucing (Container<Cat>) bukanlah kotak berisi hewan (Container<Animal>),
// sehingga kodenya langsung error atau merah saat di-compile.

// Setelah ada out (Covariant): Memberi tahu compiler bahwa kotak ini hanya berfungsi untuk mengeluarkan/memproduksi isinya (lewat fun get()),
// dan tidak akan pernah diubah isinya. Karena kucing adalah hewan, maka mengambil kucing dari kotak tersebut sangat aman untuk diperlakukan sebagai hewan secara umum.

// TODO 1 (out T): Secara default, parameter generik di Kotlin bersifat invariant, artinya Container<Cat> dianggap tidak ada hubungannya sama sekali
//  dengan Container<Animal>. Karena antarmuka Container hanya mengembalikan nilai T lewat fungsi get() dan tidak pernah mengonsumsinya sebagai argumen,
//  bisa menggunakan modifier out. Ini membuat parameter T menjadi covariant.

// Todo 2: Dengan menambahkan modifier out, Kotlin sekarang memahami bahwa sangat aman untuk membaca seekor Cat dari dalam kotak (CatContainer)
//  dan memperlakukannya secara umum sebagai sebuah Animal. Fungsi printAnimalName yang meminta Container<Animal> sekarang dapat menerima Container<Cat>.
//  Oleh karena itu, error tipe (TYPE_MISMATCH) langsung menghilang.

// Output: Whiskers