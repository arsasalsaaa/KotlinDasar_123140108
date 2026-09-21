interface Payable {
    fun calculateSalary(): Double
}

data class Employee(val name: String, val baseSalary: Double, val bonus: Double) : Payable {
    override fun calculateSalary(): Double {
        return baseSalary + bonus
    }
}

fun main() {
    val alice = Employee("Alice", baseSalary = 5_000_000.0, bonus = 500_000.0)

    val bob = alice.copy(name = "Bob")

    println("Gaji ${alice.name}: ${alice.calculateSalary()}")
    println("Gaji ${bob.name}: ${bob.calculateSalary()}")

    val aliceDuplicate = Employee("Alice", baseSalary = 5_000_000.0, bonus = 500_000.0)
    println("alice == aliceDuplicate? ${alice == aliceDuplicate}")

    // toString() bawaan data class akan mencetak semua property secara otomatis.
    println(alice)
}

// === Penjelasan ===
// interface Payable: Interface mendefinisikan perilaku (fungsi) tanpa menyimpan data (state)
// fun calculateSalary(): Double -> Sebuah fungsi abstrak. Setiap kelas yang menggunakan interface ini wajib mengimplementasikan logika perhitungan gaji yang mengembalikan tipe Double
// data class: Kata kunci khusus Kotlin yang otomatis membuat fungsi toString(), equals(), hashCode(), dan copy(). Sangat cocok untuk kelas yang tujuan utamanya menyimpan data
// val name, val baseSalary, val bonus: Properti yang didefinisikan langsung di konstruktor
// : Payable -> Menandakan bahwa Employee mengimplementasikan interface Payable
// override fun calculateSalary(): Memberikan implementasi untuk fungsi dari interface. Di sini, gaji dihitung dari gaji pokok ditambah bonus
// val alice: Membuat objek (instance) pertama dari kelas Employee
// alice.copy(name = "Bob"): Fitur bawaan data class. Membuat objek baru (bob) dengan menyalin semua data dari alice, tetapi mengubah properti name menjadi "Bob"
// aliceDuplicate: Objek baru dengan isi yang sama persis dengan alice
// alice == aliceDuplicate: Membandingkan dua objek. Pada data class, operator == membandingkan isi data, bukan alamat memori. Karena isinya sama, hasilnya adalah true