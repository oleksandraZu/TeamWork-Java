package ua.lab7.task5.infrastructure.fake;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import ua.lab7.task5.domain.Order;
import ua.lab7.task5.domain.OrderRepository;

/** MockRepository: зберігає замовлення в пам'яті (для тестів і демонстрації підміни). */
public class InMemoryOrderRepository implements OrderRepository {

    private final List<Order> saved = new ArrayList<>();

    @Override
    public void saveOrder(Order order) {
        saved.add(order);
    }

    public List<Order> saved() {
        return Collections.unmodifiableList(saved);
    }
}
