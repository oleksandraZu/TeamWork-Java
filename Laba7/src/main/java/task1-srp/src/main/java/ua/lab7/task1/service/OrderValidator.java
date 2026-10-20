package ua.lab7.task1.service;

import java.math.BigDecimal;
import ua.lab7.task1.domain.Order;
import ua.lab7.task1.domain.OrderItem;

/** Єдина відповідальність: перевірка коректності замовлення (fail-fast, Guard Clauses). */
public class OrderValidator {

    public void validate(Order order) {
        requireValidEmail(order.customerEmail());
        requireNotEmptyCart(order);
        order.items().forEach(OrderValidator::requireValidItem);
    }

    private static void requireValidEmail(String email) {
        if (email == null || email.isBlank() || !email.contains("@")) {
            throw new IllegalArgumentException("Некоректна електронна адреса покупця.");
        }
    }

    private static void requireNotEmptyCart(Order order) {
        if (order.items().isEmpty()) {
            throw new IllegalStateException("Кошик порожній.");
        }
    }

    private static void requireValidItem(OrderItem item) {
        boolean invalid = item.quantity() <= 0
                || item.price() == null
                || item.price().compareTo(BigDecimal.ZERO) <= 0;
        if (invalid) {
            throw new IllegalArgumentException(
                    "Некоректний товар " + item.name() + ": ціна та кількість мають бути більше 0.");
        }
    }
}
