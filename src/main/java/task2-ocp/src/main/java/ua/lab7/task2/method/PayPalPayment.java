package ua.lab7.task2.method;

import java.math.BigDecimal;
import ua.lab7.task2.PaymentMethod;
import ua.lab7.task2.PaymentRequest;

/** PayPal: 3.4%. */
public class PayPalPayment implements PaymentMethod {

    @Override
    public String id() {
        return "PAYPAL";
    }

    @Override
    public BigDecimal calculateFee(BigDecimal amount) {
        return PaymentMethod.round(amount.multiply(new BigDecimal("0.034")));
    }

    @Override
    public boolean pay(PaymentRequest request, BigDecimal fee) {
        System.out.println("[PayPal] Перенаправлення на шлюз PayPal для " + request.payerId() + ". Комісія системи: $" + fee);
        return true;
    }
}
