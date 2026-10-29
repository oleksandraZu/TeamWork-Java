package variant1_orderprocessing.application;

import java.util.Map;

/** DTO запиту від CLI. quantitiesByProductId: id товару -> кількість. */
public record PlaceOrderCommand(int customerId, String customerName, String customerEmail,
                                String customerType, Map<Integer, Integer> quantitiesByProductId) {
}
