package ua.lab7.task3;

import java.util.Objects;

/** Спільна реалізація (DRY): номер, баланс та поповнення. Інваріант: баланс ніколи не від'ємний. */
public abstract class BaseAccount implements Depositable {

    private final String accountNumber;
    private Money balance;

    protected BaseAccount(String accountNumber, Money initialBalance) {
        this.accountNumber = Objects.requireNonNull(accountNumber, "accountNumber");
        this.balance = Objects.requireNonNull(initialBalance, "initialBalance");
    }

    @Override
    public String getAccountNumber() {
        return accountNumber;
    }

    @Override
    public Money getBalance() {
        return balance;
    }

    @Override
    public void deposit(Money amount) {
        requirePositive(amount);
        balance = balance.plus(amount);
    }

    protected void debit(Money amount) {
        requirePositive(amount);
        if (amount.compareTo(balance) > 0) {
            throw new IllegalStateException("Insufficient funds");
        }
        balance = balance.minus(amount);
    }

    private static void requirePositive(Money amount) {
        Objects.requireNonNull(amount, "amount");
        if (!amount.isPositive()) {
            throw new IllegalArgumentException("Amount must be positive");
        }
    }
}
