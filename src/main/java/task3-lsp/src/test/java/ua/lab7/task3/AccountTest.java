package ua.lab7.task3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

class AccountTest {

    @Test
    void depositAndWithdrawChangeBalance() {
        CurrentAccount account = new CurrentAccount("1", Money.of("100"));
        account.deposit(Money.of("50"));
        account.withdraw(Money.of("30"));
        assertEquals(Money.of("120"), account.getBalance());
    }

    @Test
    void withdrawMoreThanBalanceFails() {
        CurrentAccount account = new CurrentAccount("1", Money.of("10"));
        assertThrows(IllegalStateException.class, () -> account.withdraw(Money.of("11")));
        assertEquals(Money.of("10"), account.getBalance());
    }

    @Test
    void nonPositiveAmountsAreRejected() {
        CurrentAccount account = new CurrentAccount("1", Money.of("10"));
        assertThrows(IllegalArgumentException.class, () -> account.deposit(Money.ZERO));
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(Money.ZERO));
    }

    @Test
    void moneyCannotBeNegative() {
        assertThrows(IllegalArgumentException.class, () -> Money.of("-1"));
        assertThrows(NullPointerException.class, () -> new Money(null));
    }

    @Test
    void fixedDepositCannotBeWithdrawnFrom() {
        Account deposit = new FixedDepositAccount("2", Money.of("1000"), 6);
        assertFalse(deposit instanceof Withdrawable);
        assertTrue(deposit instanceof Depositable);
    }

    @Test
    void fixedDepositValidatesTerm() {
        assertThrows(IllegalArgumentException.class, () -> new FixedDepositAccount("2", Money.ZERO, 0));
    }

    /** LSP: будь-який Withdrawable підставляється в клієнтський код без збоїв. */
    @Test
    void everyWithdrawableHonoursTheContractInClientCode() {
        List<Withdrawable> accounts = List.of(
                new CurrentAccount("1", Money.of("100")), new CurrentAccount("2", Money.of("40")));
        BankService service = new BankService();
        for (Withdrawable account : accounts) {
            Money before = account.getBalance();
            service.processMonthlyFee(account, Money.of("10"));
            assertEquals(before.minus(Money.of("10")), account.getBalance());
        }
    }
}
