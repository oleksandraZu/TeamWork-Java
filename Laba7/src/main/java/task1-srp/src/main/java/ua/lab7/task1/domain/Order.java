package ua.lab7.task1.domain;

import java.util.List;

/** Замовлення клієнта. Колекція копіюється, тому об'єкт незмінний. */
public record Order(String customerEmail, CustomerCategory category, List<OrderItem> items, String deliveryAddress) {

    public Order {
        items = items == null ? List.of() : List.copyOf(items);
        category = category == null ? CustomerCategory.STANDARD : category;
    }
}
