package ua.lab8.application;

import java.util.ArrayList;
import java.util.List;
import ua.lab8.application.dto.PlaceOrderCommand;
import ua.lab8.application.factory.OrderFactory;
import ua.lab8.application.step.OrderStep;
import ua.lab8.domain.Order;

/**
 * GRASP Controller (Use-Case Controller): перший об\u2019єкт за межами UI.
 * Не містить ні консольного вводу/виводу, ні бізнес-обчислень — лише координує.
 */
public final class OrderController {
    private final OrderFactory orderFactory;
    private final List<OrderStep> steps;

    public OrderController(OrderFactory orderFactory, List<OrderStep> steps) {
        this.orderFactory = orderFactory;
        this.steps = List.copyOf(steps);
    }

    /** @return рядки підтвердження у порядку виконання кроків (рахунок, збереження, сповіщення) */
    public List<String> placeOrder(PlaceOrderCommand command) {
        Order order = orderFactory.create(command);
        order.process();
        List<String> output = new ArrayList<>();
        for (OrderStep step : steps) {
            output.add(step.execute(order));
        }
        return List.copyOf(output);
    }
}
