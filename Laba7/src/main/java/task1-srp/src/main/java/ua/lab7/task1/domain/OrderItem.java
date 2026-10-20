package ua.lab7.task1.domain;

import java.math.BigDecimal;

/** Позиція замовлення (сутність доменної моделі). Правила коректності перевіряє {@code OrderValidator}. */
public record OrderItem(String itemId, String name, BigDecimal price, int quantity) {

    public BigDecimal lineTotal() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }
}
