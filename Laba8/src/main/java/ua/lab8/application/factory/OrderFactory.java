package ua.lab8.application.factory;

import ua.lab8.application.dto.PlaceOrderCommand;
import ua.lab8.application.port.OrderRepository;
import ua.lab8.domain.Order;
import ua.lab8.domain.tax.TaxPolicy;

/**
 * Збирач агрегату: єдине місце, де сходяться клієнт, рядки, податкова політика та генератор id.
 * Зчеплення свідомо сконцентроване тут, а не розмазане по доменних сутностях.
 */
public final class OrderFactory {
    private final CustomerFactory customerFactory;
    private final OrderItemsFactory itemsFactory;
    private final TaxPolicy taxPolicy;
    private final OrderRepository repository;

    public OrderFactory(CustomerFactory customerFactory,
                        OrderItemsFactory itemsFactory,
                        TaxPolicy taxPolicy,
                        OrderRepository repository) {
        this.customerFactory = customerFactory;
        this.itemsFactory = itemsFactory;
        this.taxPolicy = taxPolicy;
        this.repository = repository;
    }

    public Order create(PlaceOrderCommand command) {
        return Order.place(
                repository.nextId(),
                customerFactory.create(command),
                itemsFactory.create(command.lines()),
                taxPolicy);
    }
}
