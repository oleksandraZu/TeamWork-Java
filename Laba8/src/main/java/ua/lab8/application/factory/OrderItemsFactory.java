package ua.lab8.application.factory;

import java.util.ArrayList;
import java.util.List;
import ua.lab8.application.dto.OrderLineRequest;
import ua.lab8.application.port.ProductCatalog;
import ua.lab8.domain.OrderItem;

/** Pure Fabrication: перетворює запити «id:кількість» на доменні рядки замовлення. */
public final class OrderItemsFactory {
    private final ProductCatalog catalog;

    public OrderItemsFactory(ProductCatalog catalog) {
        this.catalog = catalog;
    }

    public List<OrderItem> create(List<OrderLineRequest> lines) {
        List<OrderItem> items = new ArrayList<>();
        for (OrderLineRequest line : lines) {
            items.add(OrderItem.of(catalog.getById(line.productId()), line.quantity()));
        }
        return items;
    }
}
