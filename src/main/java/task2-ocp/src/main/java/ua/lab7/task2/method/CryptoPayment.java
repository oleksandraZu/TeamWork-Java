package ua.lab7.task2.method;

import java.math.BigDecimal;
import ua.lab7.task2.PaymentMethod;
import ua.lab7.task2.PaymentRequest;

/** Крипто: 0.8%, але не менше $1.00 і не більше $20.00. */
public class CryptoPayment implements PaymentMethod {

    private static final BigDecimal RATE = new BigDecimal("0.008");
    private static final BigDecimal MIN_FEE = new BigDecimal("1.00");
    private static final BigDecimal MAX_FEE = new BigDecimal("20.00");

    @Override
    public String id() {
        return "CRYPTO";
    }

    @Override
    public BigDecimal calculateFee(BigDecimal amount) {
        BigDecimal fee = PaymentMethod.round(amount.multiply(RATE));
        return fee.max(MIN_FEE).min(MAX_FEE);
    }

    @Override
    public boolean pay(PaymentRequest request, BigDecimal fee) {
        System.out.println("[Crypto] Очікування підтвердження транзакції в мережі Blockchain для " + request.payerId() + ".");
        return true;
    }
}
