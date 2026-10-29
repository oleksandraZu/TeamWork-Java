package variant1_orderprocessing.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import variant1_orderprocessing.domain.Customer;
import variant1_orderprocessing.domain.CustomerType;
import variant1_orderprocessing.domain.Order;
import variant1_orderprocessing.domain.OrderItem;
import variant1_orderprocessing.domain.TaxPolicy;

/** Pure Fabrication: збирає агрегат Order із команди. */
public final class OrderFactory {
    private final ProductCatalog catalog;
    private final OrderRepository repository;
    private final TaxPolicy taxPolicy;

    public OrderFactory(ProductCatalog catalog, OrderRepository repository, TaxPolicy taxPolicy) {
        this.catalog = catalog;
        this.repository = repository;
        this.taxPolicy = taxPolicy;
    }

    public Order create(PlaceOrderCommand command) {
        List<OrderItem> items = new ArrayList<>();
        for (Map.Entry<Integer, Integer> line : command.quantitiesByProductId().entrySet()) {
            items.add(OrderItem.of(catalog.getById(line.getKey()), line.getValue()));
        }
        return Order.create(
                repository.nextId(),
                Customer.register(command.customerId(), command.customerName(), command.customerEmail(),
                        CustomerType.parse(command.customerType())),
                items,
                taxPolicy);
    }
}
