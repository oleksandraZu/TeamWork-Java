package ua.lab7.task3;

/** Поточний рахунок: можна і поповнювати, і знімати. */
public class CurrentAccount extends BaseAccount implements Withdrawable {

    public CurrentAccount(String accountNumber, Money initialBalance) {
        super(accountNumber, initialBalance);
    }

    @Override
    public void withdraw(Money amount) {
        debit(amount);
    }
}
