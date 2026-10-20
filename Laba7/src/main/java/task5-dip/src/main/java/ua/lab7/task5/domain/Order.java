package ua.lab7.task5.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/** Замовлення e-commerce (незмінний об'єкт). */
public record Order(String orderId, String customerEmail, BigDecimal totalAmount, List<String> items) {

    public Order {
        Objects.requireNonNull(orderId, "orderId");
        Objects.requireNonNull(customerEmail, "customerEmail");
        Objects.requireNonNull(totalAmount, "totalAmount");
        items = items == null ? List.of() : List.copyOf(items);
    }
}
