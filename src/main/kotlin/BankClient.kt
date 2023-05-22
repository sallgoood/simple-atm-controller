fun interface FindBankCard {
    fun invoke(
        cardNumber: String,
    ): BankAtmCard?
}

fun interface FindCardBankAccounts {
    fun invoke(
        cardNumber: String,
        pinNumber: String,
    ): List<BankAccount>
}

fun interface DepositBankAccount {
    fun invoke(
        accountNumber: String,
        pinNumber: String,
        amount: Int,
    ): BankAccount
}

fun interface WithdrawBankAccount {
    fun invoke(
        accountNumber: String,
        pinNumber: String,
        amount: Int,
    ): BankAccount
}

data class BankAtmCard(
    val cardNumber: String,
)

data class BankAccount(
    val accountNumber: String,
    val balance: Int,
)
