package ua.lab7.task2.method;

import java.math.BigDecimal;
import ua.lab7.task2.PaymentMethod;
import ua.lab7.task2.PaymentRequest;

/** Картка: 2.5% + $0.30. */
public class CreditCardPayment implements PaymentMethod {

    @Override
    public String id() {
        return "CREDIT_CARD";
    }

    @Override
    public BigDecimal calculateFee(BigDecimal amount) {
        return PaymentMethod.round(amount.multiply(new BigDecimal("0.025")).add(new BigDecimal("0.30")));
    }

    @Override
    public boolean pay(PaymentRequest request, BigDecimal fee) {
        BigDecimal total = request.amount().add(fee);
        System.out.println("[CreditCard] Авторизація картки для користувача " + request.payerId() + ". Списано: $" + total);
        return true;
    }
}
