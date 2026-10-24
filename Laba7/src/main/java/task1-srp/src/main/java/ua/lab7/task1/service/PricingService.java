package ua.lab7.task1.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import ua.lab7.task1.domain.Order;
import ua.lab7.task1.domain.OrderItem;
import ua.lab7.task1.domain.OrderTotals;

/** Єдина відповідальність: бізнес-логіка розрахунку знижки, ПДВ та підсумку. */
public class PricingService {

    public static final BigDecimal VAT_RATE = new BigDecimal("0.20");

    public OrderTotals calculate(Order order) {
        BigDecimal subtotal = order.items().stream()
                .map(OrderItem::lineTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal discountAmount = money(subtotal.multiply(order.category().discountRate()));
        BigDecimal taxableSubtotal = subtotal.subtract(discountAmount);
        BigDecimal taxAmount = money(taxableSubtotal.multiply(VAT_RATE));
        BigDecimal totalAmount = taxableSubtotal.add(taxAmount);

        return new OrderTotals(money(subtotal), discountAmount, taxAmount, money(totalAmount));
    }

    private static BigDecimal money(BigDecimal value) {
        return value.setScale(2, RoundingMode.HALF_UP);
    }
}
