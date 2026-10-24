package ua.lab8.application.step;

import ua.lab8.application.port.OrderRepository;
import ua.lab8.domain.Order;

/** Крок: зберегти замовлення. */
public final class PersistOrderStep implements OrderStep {
    private final OrderRepository repository;

    public PersistOrderStep(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public String execute(Order order) {
        repository.save(order);
        return "[БД] Замовлення #" + order.id() + " збережено у файл " + repository.storageName() + ".";
    }
}
