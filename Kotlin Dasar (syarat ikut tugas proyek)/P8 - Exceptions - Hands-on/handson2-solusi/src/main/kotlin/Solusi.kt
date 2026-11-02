// Hands-on 2: Custom Exception & Exception Hierarchy (Solusi)

// TODO 1: Buat class InsufficientBalanceException yang meng-extend Exception
//         dan menerima parameter message: String
class InsufficientBalanceException(message: String) : Exception(message)

class BankAccount(private val owner: String, startBalance: Double) {
    var balance: Double = startBalance
        private set

    fun deposit(amount: Double) {
        require(amount > 0) { "Jumlah deposit harus lebih besar dari 0" }
        balance += amount
    }

    fun withdraw(amount: Double) {
        require(amount > 0) { "Jumlah penarikan melebih batas / harus lebih besar dari 0" }

        // TODO 2: Jika amount > balance, lempar InsufficientBalanceException
        //         dengan pesan yang menyebutkan nama pemilik, saldo, dan
        //         jumlah yang diminta.
        if (amount > balance) {
            throw InsufficientBalanceException("Penarikan gagal: Saldo $owner tidak cukup (Saldo: $balance, Diminta: $amount)")
        }

        // TODO 3: Jika cukup, kurangi balance dengan amount.
        balance -= amount
    }
}

fun main() {
    val account = BankAccount("Andi", 100_000.0)

    account.deposit(50_000.0)
    println("Saldo setelah deposit: ${account.balance}")

    // TODO 4: Panggil account.withdraw(500_000.0) di dalam try-catch,
    //         tangkap InsufficientBalanceException secara spesifik dan
    //         cetak pesan errornya (JANGAN biarkan program crash).
    try {
        account.withdraw(500_000.0)
    } catch (e: InsufficientBalanceException) {
        println("Gagal tarik dana: ${e.message}")
    }

    account.withdraw(30_000.0)
    println("Saldo akhir: ${account.balance}")
}

// === Penjelasan ===
// TODO 1 (Custom Exception):
// Deklarasi class InsufficientBalanceException(message: String) : Exception(message) membuat kelas pengecualian kustom berjenis checked/unchecked exception bawaan Kotlin yang dapat menyimpan pesan error spesifik domain perbankan.
// TODO 2 (Melempar Exception Kustom):
// Di metode withdraw(), dilakukan pemeriksaan if (amount > balance). Jika saldo kurang, perintah throw InsufficientBalanceException(...) dijalankan dengan pesan informative yang mencantumkan nama pemilik, saldo saat ini, dan nominal yang diminta.
// TODO 3 (Pengurangan Saldo):
// Jika pemeriksaan saldo cukup (kondisi if tidak terpenuhi), kode mengeksekusi balance -= amount untuk mengurangi saldo bank.
// TODO 4 (Penanganan Exception Spesifik):
// Penarikan sebesar 500.000 (melebihi saldo 150.000) dibungkus try-catch. Blok catch (e: InsufficientBalanceException) menangkap tipe exception ini secara presisi dan mencetak e.message tanpa menghentikan alur program (sehingga penarikan berikutnya 30.000 tetap dapat diproses).