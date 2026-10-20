package ua.lab7.task1.domain;

import java.math.BigDecimal;

/** Результат розрахунку вартості (усі суми — з точністю до копійок). */
public record OrderTotals(BigDecimal subtotal, BigDecimal discountAmount, BigDecimal taxAmount, BigDecimal totalAmount) {
}
