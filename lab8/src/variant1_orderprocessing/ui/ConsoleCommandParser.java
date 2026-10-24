package variant1_orderprocessing.ui;

import java.util.LinkedHashMap;
import java.util.Map;

import variant1_orderprocessing.application.PlaceOrderCommand;

/** Pure Fabrication: перетворює текстове введення на DTO. Без бізнес-логіки. */
public final class ConsoleCommandParser {
    private static final int DEMO_CUSTOMER_ID = 1;
    private static final String DEMO_EMAIL = "client@example.com";

    public PlaceOrderCommand parse(String name, String customerType) {
        Map<Integer, Integer> basket = new LinkedHashMap<>();
        basket.put(1, 1); // Ноутбук x1
        basket.put(2, 2); // Мишка x2
        return new PlaceOrderCommand(DEMO_CUSTOMER_ID, name, DEMO_EMAIL, customerType, basket);
    }
}
