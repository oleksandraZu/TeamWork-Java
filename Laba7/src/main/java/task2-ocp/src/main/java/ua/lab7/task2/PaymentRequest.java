package ua.lab7.task2;

import java.math.BigDecimal;
import java.util.Objects;

/** Запит на платіж. Інваріанти перевіряються при створенні (fail-fast). */
public record PaymentRequest(String transactionId, BigDecimal amount, String currency, String payerId) {

    public PaymentRequest {
        Objects.requireNonNull(transactionId, "transactionId");
        Objects.requireNonNull(amount, "amount");
        Objects.requireNonNull(currency, "currency");
        Objects.requireNonNull(payerId, "payerId");
        if (amount.signum() <= 0) {
            throw new IllegalArgumentException("Сума платежу має бути додатною.");
        }
    }
}
