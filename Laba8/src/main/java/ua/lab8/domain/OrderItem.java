package ua.lab8.domain;

import java.math.BigDecimal;

/** Рядок замовлення: товар + кількість. Створюється лише через фабричний метод. */
public final class OrderItem {
    private final Product product;
    private final int quantity;

    private OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    /** Creator (static factory): гарантує валідний рядок замовлення. */
    public static OrderItem of(Product product, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException("Товар обов\u2019язковий");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Кількість має бути додатною");
        }
        return new OrderItem(product, quantity);
    }

    /** Information Expert: рядок володіє і товаром, і кількістю. */
    public BigDecimal subtotal() {
        return product.priceFor(quantity);
    }

    /** Делегування замість order.getItem().getProduct().getName() (Закон Деметри). */
    public String productName() {
        return product.name();
    }

    public int quantity() {
        return quantity;
    }
}
