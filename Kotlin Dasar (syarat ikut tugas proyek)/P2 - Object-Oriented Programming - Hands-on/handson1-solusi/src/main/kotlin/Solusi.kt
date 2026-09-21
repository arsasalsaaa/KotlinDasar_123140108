// Hands-on 1: Class & Inheritance

// TODO 1
open class Vehicle(val name: String, val maxSpeed: Int) {

    // TODO 2
    open fun describe(): String {
        return "$name dapat melaju hingga $maxSpeed km/h"
    }
}
    // TODO 3
    class Car(name: String, val numberOfDoors: Int) : Vehicle(name, 180) {
        override fun describe(): String {
            return "${super.describe()} dan punya $numberOfDoors pintu"
        }
    }

    // TODO 4
    class Motorcycle(name: String, val hasSidecar: Boolean) : Vehicle(name, 200) {
        override fun describe(): String {
            val infoSidecar = if (hasSidecar) "dengan sidecar" else "tanpa sidecar"
            return "${super.describe()} ($infoSidecar)"
        }
    }

fun main() {
    // TODO 5
    val vehicles = listOf<Vehicle>(
        Car("Toyota", numberOfDoors = 4), Motorcycle("Ninja", hasSidecar = false)
    )

    // Polymorphism: setiap elemen dipanggil lewat interface Vehicle,
    // tapi describe() yang jalan adalah versi milik subclass masing-masing.
    vehicles.forEach { println(it.describe()) }
}

// === Penjelasan ===
// open class Vehicle(..) {: Secara default di Kotlin, semua class tidak bisa diturunkan. Agar bisa dijadikan parent class, harus menambahkan kata kunci open di depan class
// open fun describe(): String {: Sama seperti class, agar fungsi describe() ini bisa diubah perilakunya (override) di dalam class anak (Car dan Motorcycle), harus menambahkan kata kunci open. fungsi ini mengembalikan nilai teks (String) menggunakan String Template ($name dan $maxSpeed)
// SubClass pertama (Todo 3): Membuat class baru bernama Car. Menggunakan tanda titik dua (:) untuk mewarisi Vehicle. Karena Vehicle butuh name dan maxSpeed, oper name dari input Car, dan hardcode maxSpeed-nya menjadi 180. Di dalamnya, tambahkan kata kunci override untuk fungsi describe()
// SubClass kedua (Todo 4): Class Motorcycle mewarisi Vehicle dengan memberikan nilai default maxSpeed = 220. Pada fungsi describe(), dilakukan pengecekan kondisi if-else pada properti hasSidecar untuk menentukan apakah teksnya "(dengan sidecar)" atau "(tanpa sidecar)"
// Fungsi utama (Todo 5): Membuat sebuah listOf (daftar) bertipe Vehicle. Di dalam daftar ini bisa memasukkan objek Car dan Motorcycle karena keduanya adalah turunan dari Vehicle
// vehicles.forEach { println(it.describe()) } : Menggunakan perulangan forEach untuk mendatangi setiap objek di dalam list vehicles. Properti it merepresentasikan objek saat itu (sebagai Vehicle).
// Polymorphism: Meskipun list tersebut menganggap tipenya adalah Vehicle, berkat prinsip Polymorphism, saat it.describe() dipanggil, Kotlin tahu secara otomatis untuk menjalankan fungsi describe() milik Car (ketika giliran objek mobil) dan milik Motorcycle (ketika giliran objek motor), bukan fungsi milik Vehicle asli.