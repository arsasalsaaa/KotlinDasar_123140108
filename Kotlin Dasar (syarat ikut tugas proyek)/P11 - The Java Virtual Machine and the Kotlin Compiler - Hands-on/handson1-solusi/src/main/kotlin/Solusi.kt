// Hands-on 1: Inline Function & Reified Generics
//
// Konteks: JVM menerapkan TYPE ERASURE pada generics — informasi tipe generik
// (mis. List<String> vs List<Int>) DIHAPUS saat dikompilasi menjadi bytecode
// .class, sehingga pada saat runtime JVM hanya melihat "List" biasa. Karena
// itu, fungsi generik biasa TIDAK BISA melakukan pengecekan `value is T`.
//
// Kotlin punya solusi: kombinasi `inline` + `reified`. Fungsi inline "disalin"
// (di-inline) langsung ke tempat pemanggilannya oleh compiler backend Kotlin
// SEBELUM menjadi bytecode, sehingga tipe T yang konkret (mis. String) ikut
// tersalin dan tidak pernah "dihapus" oleh JVM.
//
// Tugas: Lengkapi fungsi isType() di bawah agar bisa mengecek tipe value pada
// runtime.

// TODO 1: Tambahkan modifier `inline` pada fungsi ini
// TODO 2: Ubah <T> menjadi <reified T> agar T bisa dipakai di `is` check
inline fun <reified T> isType(value: Any): Boolean {
    // TODO 3: Ganti baris di bawah dengan: return value is T
    return value is T
}

fun main() {
    println(isType<String>("Hello"))   // harus mencetak: true
    println(isType<Int>("Hello"))      // harus mencetak: false
    println(isType<String>(123))       // harus mencetak: false

    // Pertanyaan diskusi (jawab di komentar):
    // Kenapa `fun <T> isType(value: Any): Boolean = value is T` TIDAK BISA
    // dikompilasi tanpa `inline` + `reified`? Jelaskan hubungannya dengan
    // type erasure pada JVM bytecode.
}

// === Penjelasan ===
// • Perubahan Kode:
// i. TODO 1 & TODO 2:
//    Menambahkan keyword inline dan modifier reified pada parameter tipe generik <reified T> di fungsi isType.
// ii. TODO 3: Mengubah TODO(...)
//    menjadi ekspresi pengecekan tipe return value is T.

// Jawaban Pertanyaan Diskusi (Penjelasan Teknis Type Erasure): Secara bawaan, JVM menerapkan Type Erasure pada fitur generic. Artinya, informasi tipe generik (seperti T pada List<T>) dihapus saat dikompilasi menjadi bytecode .class dan digantikan dengan tipe Object (atau tipe batasan atas/bound). Karena informasi tipe T hilang pada runtime, fungsi generik biasa tidak dapat melakukan pemeriksaan tipe menggunakan operator is (value is T).
// Solusi Kotlin adalah menggunakan inline + reified:
// ◦ Modifier inline menginstruksikan Kotlin compiler untuk menyalin (inlining) kode fungsi secara langsung ke lokasi pemanggilan (call site) sebelum diubah menjadi bytecode.
// ◦ Modifier reified hanya dapat digunakan pada fungsi inline. Karakteristik ini memungkinkan tipe spesifik T (misalnya String atau Int) diketahui di tempat pemanggilan dan ikut di-inline ke bytecode tanpa mengalami type erasure.