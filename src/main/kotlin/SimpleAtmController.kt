class SimpleAtmController(
    private val findBankCard: FindBankCard? = null,
    private val findCardBankAccounts: FindCardBankAccounts? = null,
    private val depositBankAccount: DepositBankAccount? = null,
    private val withdrawBankAccount: WithdrawBankAccount? = null,
) {

    fun insertCard(cardNumber: String): BankAtmCard {
        return findBankCard!!.invoke(cardNumber)
            ?: throw IllegalArgumentException("cardNumber($cardNumber) has not found.")
    }

    fun pinNumber(
        cardNumber: String,
        pinNumber: String,
    ): List<BankAccount> {
        return findCardBankAccounts!!.invoke(
            cardNumber = cardNumber,
            pinNumber = pinNumber,
        )
    }

    fun deposit(
        account: BankAccount,
        pinNumber: String,
        amount: Int,
    ): BankAccount {
        assert(amount > 0)

        return depositBankAccount!!.invoke(
            accountNumber = account.accountNumber,
            pinNumber = pinNumber,
            amount = amount,
        )
    }

    fun withdraw(
        account: BankAccount,
        pinNumber: String,
        amount: Int,
    ): BankAccount {
        assert(amount > 0)

        if (amount > account.balance)
            throw IllegalArgumentException("amount($amount) cannot exceed balance(${account.balance})")

        return withdrawBankAccount!!.invoke(
            accountNumber = account.accountNumber,
            pinNumber = pinNumber,
            amount = amount,
        )
    }
}
