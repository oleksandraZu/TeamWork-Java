package ua.lab7.task2;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Закритий для модифікації клас: не знає про конкретні способи оплати, лише делегує
 * обраній стратегії {@link PaymentMethod}. Жодного if/switch за типом.
 */
public class PaymentProcessor {

    private final Map<String, PaymentMethod> methods = new HashMap<>();

    public PaymentProcessor(Collection<? extends PaymentMethod> paymentMethods) {
        Objects.requireNonNull(paymentMethods, "paymentMethods");
        for (PaymentMethod method : paymentMethods) {
            if (methods.putIfAbsent(method.id(), method) != null) {
                throw new IllegalArgumentException("Дубльований спосіб оплати: " + method.id());
            }
        }
    }

    public BigDecimal calculateProcessingFee(BigDecimal amount, String methodId) {
        return resolve(methodId).calculateFee(amount);
    }

    public boolean processPayment(PaymentRequest request, String methodId) {
        System.out.println("[LOG] Початок обробки транзакції " + request.transactionId()
                + " на суму " + request.amount() + " " + request.currency());
        PaymentMethod method = resolve(methodId);
        BigDecimal fee = method.calculateFee(request.amount());
        return method.pay(request, fee);
    }

    private PaymentMethod resolve(String methodId) {
        PaymentMethod method = methods.get(methodId);
        if (method == null) {
            throw new IllegalArgumentException("Непідтримуваний тип платежу: " + methodId);
        }
        return method;
    }
}
