package ua.lab7.task1.infrastructure;

import ua.lab7.task1.domain.Order;
import ua.lab7.task1.domain.OrderTotals;

/** Абстракція сховища замовлень. */
public interface OrderRepository {
    void save(Order order, OrderTotals totals);
}
