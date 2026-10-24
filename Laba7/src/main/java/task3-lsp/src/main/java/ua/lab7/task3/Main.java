package ua.lab7.task3;

public class Main {

    public static void main(String[] args) {
        BankService service = new BankService();

        CurrentAccount current = new CurrentAccount("UA-001", Money.of("500.00"));
        service.processMonthlyFee(current, Money.of("25.00"));

        FixedDepositAccount deposit = new FixedDepositAccount("UA-002", Money.of("10000.00"), 12);
        deposit.deposit(Money.of("100.00"));
        System.out.println("Deposit " + deposit.getAccountNumber() + " balance: " + deposit.getBalance());

        // service.processMonthlyFee(deposit, Money.of("25.00"));  // НЕ СКОМПІЛЮЄТЬСЯ: депозит не Withdrawable
    }
}
