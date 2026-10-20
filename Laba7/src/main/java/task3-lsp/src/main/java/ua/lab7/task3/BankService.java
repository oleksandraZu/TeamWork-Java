package ua.lab7.task3;

/** Клієнтський код залежить лише від потрібного йому контракту — Withdrawable. */
public class BankService {

    public void processMonthlyFee(Withdrawable account, Money fee) {
        account.withdraw(fee);
        System.out.println("Fee processed for " + account.getAccountNumber() + ". New balance: " + account.getBalance());
    }
}
