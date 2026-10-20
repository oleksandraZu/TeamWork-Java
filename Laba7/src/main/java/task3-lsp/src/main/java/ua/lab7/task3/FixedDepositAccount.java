package ua.lab7.task3;

/**
 * Строковий депозит. НЕ реалізує {@link Withdrawable}: зняття до завершення терміну
 * неможливе, тому помилка «зняти з депозиту» виявляється на етапі компіляції, а не винятком у runtime.
 */
public class FixedDepositAccount extends BaseAccount {

    private final int termMonths;

    public FixedDepositAccount(String accountNumber, Money initialBalance, int termMonths) {
        super(accountNumber, initialBalance);
        if (termMonths <= 0) {
            throw new IllegalArgumentException("termMonths must be positive");
        }
        this.termMonths = termMonths;
    }

    public int getTermMonths() {
        return termMonths;
    }
}
