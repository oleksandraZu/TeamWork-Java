package ua.lab7.task2.method;

import java.math.BigDecimal;
import ua.lab7.task2.PaymentMethod;
import ua.lab7.task2.PaymentRequest;

/** Банківський переказ: фіксовано $5.00. */
public class BankTransferPayment implements PaymentMethod {

    private static final BigDecimal FIXED_FEE = new BigDecimal("5.00");

    @Override
    public String id() {
        return "BANK_TRANSFER";
    }

    @Override
    public BigDecimal calculateFee(BigDecimal amount) {
        return FIXED_FEE;
    }

    @Override
    public boolean pay(PaymentRequest request, BigDecimal fee) {
        BigDecimal total = request.amount().add(fee);
        System.out.println("[BankTransfer] Формування платіжного доручення SWIFT/IBAN для " + request.payerId() + ". Сума: $" + total);
        return true;
    }
}
