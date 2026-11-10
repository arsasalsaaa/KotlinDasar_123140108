import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

// Hands-on 2: Testing Exception
// Tugas: Lengkapi test untuk BankAccount, termasuk kasus withdraw() yang
// melempar InsufficientFundsException saat saldo tidak cukup.
//
// Konsep baru: assertThrows<TipeException> { ... } -- dari org.junit.jupiter.api,
// digunakan untuk memverifikasi bahwa sebuah blok kode MELEMPAR exception tertentu.

class BankAccountTest {

    @Test
    @DisplayName("Deposit menambah saldo dengan benar")
    fun testDeposit() {
        // TODO 1 (Arrange): buat BankAccount dengan saldo awal 100.0
        val account = BankAccount(100.0)

        // TODO 2 (Act): panggil deposit(50.0)
        account.deposit(50.0)

        // TODO 3 (Assert): assertEquals(150.0, account.balance)
        assertEquals(150.0, account.balance)
    }

    @Test
    @DisplayName("Withdraw mengurangi saldo saat saldo cukup")
    fun testWithdrawSufficientBalance() {
        // TODO 1 (Arrange): buat BankAccount dengan saldo awal 100.0
        val account = BankAccount(100.0)

        // TODO 2 (Act): withdraw 40.0
        account.withdraw(40.0)

        // TODO 3 (Assert): hasil akhir harus 60.0
        assertEquals(60.0, account.balance)
    }

    @Test
    @DisplayName("Withdraw melempar InsufficientFundsException saat saldo tidak cukup")
    fun testWithdrawInsufficientBalance() {
        // TODO 1 (Arrange): buat BankAccount dengan saldo awal 50.0
        val account = BankAccount(50.0)

        // TODO 2 (Act + Assert): gunakan assertThrows<InsufficientFundsException> untuk memastikan exception dilempar
        assertThrows<InsufficientFundsException> {
            account.withdraw(100.0)
        }
    }
}

// === Penjelasan ===
// testDeposit():
// Arrange (TODO 1): Membuat instance BankAccount dengan saldo awal 100.0.
// Act (TODO 2): Memanggil method account.deposit(50.0).
// Assert (TODO 3): Menguji assertEquals(150.0, account.balance) untuk memastikan saldo bertambah menjadi 150.0.

// testWithdrawSufficientBalance():
// Arrange (TODO 1): Membuat instance BankAccount dengan saldo awal 100.0.
// Act (TODO 2): Memanggil method account.withdraw(40.0).
// Assert (TODO 3): Menguji assertEquals(60.0, account.balance) untuk memastikan saldo berkurang menjadi 60.0.

// testWithdrawInsufficientBalance():
// Arrange (TODO 1): Membuat instance BankAccount dengan saldo awal 50.0.
// Act + Assert (TODO 2): Menggunakan assertThrows<InsufficientFundsException> { account.withdraw(100.0) } untuk memastikan exception dilempar ketika penarikan melebihi saldo yang tersedia.