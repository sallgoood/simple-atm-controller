import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class SimpleAtmControllerTest {

    val cardNumber = "validCardNumber"
    val accountNumber = "validAccountNumber"
    val pinNumber = "validPinNumber"
    val balance = 100
    val bankCard = BankAtmCard(cardNumber)
    var bankAccount = BankAccount(accountNumber, balance)

    @Test
    fun usecase() {

        val controller = SimpleAtmController(
            findBankCard = { bankCard },
            findCardBankAccounts = { _, _ -> listOf(bankAccount) },
            depositBankAccount = { _, _, amount ->
                bankAccount.copy(
                    balance = bankAccount.balance + amount,
                )
            },
            withdrawBankAccount = { _, _, amount ->
                bankAccount.copy(
                    balance = bankAccount.balance - amount,
                )
            },
        )

        val card = controller.insertCard(
            cardNumber = cardNumber,
        )
        val accounts = controller.pinNumber(
            cardNumber = card.cardNumber,
            pinNumber = pinNumber,
        )
        val account = accounts.first()
        bankAccount = controller.deposit(
            account = account,
            pinNumber = pinNumber,
            amount = 50,
        )
        assertEquals(150, bankAccount.balance)

        bankAccount = controller.withdraw(
            account = bankAccount,
            pinNumber = pinNumber,
            amount = 150,
        )
        assertEquals(0, bankAccount.balance)
    }

    @Test
    fun `withdraw failure - not enough balance`() {
        val controller = SimpleAtmController()

        assertThrows(IllegalArgumentException::class.java) {
            controller.withdraw(
                account = bankAccount,
                pinNumber = pinNumber,
                amount = bankAccount.balance + 1,
            )
        }
    }
}
