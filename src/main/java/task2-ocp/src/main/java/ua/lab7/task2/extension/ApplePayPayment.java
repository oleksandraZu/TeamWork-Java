package ua.lab7.task2.extension;

import java.math.BigDecimal;
import ua.lab7.task2.PaymentMethod;
import ua.lab7.task2.PaymentRequest;

/**
 * ДЕМОНСТРАЦІЯ OCP: новий спосіб оплати (1.5%) доданий окремим файлом.
 * У PaymentProcessor та в існуючих стратегіях не змінено жодного рядка.
 */
public class ApplePayPayment implements PaymentMethod {

    @Override
    public String id() {
        return "APPLE_PAY";
    }

    @Override
    public BigDecimal calculateFee(BigDecimal amount) {
        return PaymentMethod.round(amount.multiply(new BigDecimal("0.015")));
    }

    @Override
    public boolean pay(PaymentRequest request, BigDecimal fee) {
        System.out.println("[ApplePay] Токенізована оплата для " + request.payerId() + ". Комісія: $" + fee);
        return true;
    }
}
