package ua.lab8.application.step;

import ua.lab8.application.port.OrderNotifier;
import ua.lab8.domain.Order;

/** Крок: сповістити клієнта. */
public final class NotifyCustomerStep implements OrderStep {
    private final OrderNotifier notifier;

    public NotifyCustomerStep(OrderNotifier notifier) {
        this.notifier = notifier;
    }

    @Override
    public String execute(Order order) {
        notifier.notifyProcessed(order);
        return "[Email] Повідомлення відправлено на " + order.customerEmail();
    }
}
