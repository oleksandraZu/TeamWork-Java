package ua.lab7.task5.infrastructure;

import ua.lab7.task5.domain.Order;
import ua.lab7.task5.domain.OrderRepository;

/** «Продакшн» репозиторій (імітація MySQL — вивід у консоль). */
public class MySqlOrderRepository implements OrderRepository {

    @Override
    public void saveOrder(Order order) {
        System.out.println("[MySQL DB] Замовлення " + order.orderId() + " на суму " + order.totalAmount()
                + " грн збережено в таблицю 'orders'.");
    }
}
