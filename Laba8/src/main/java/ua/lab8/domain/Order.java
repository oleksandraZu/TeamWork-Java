package ua.lab8.domain;

import ua.lab8.domain.tax.TaxPolicy;

import java.math.BigDecimal;
import java.util.List;

/**
 * Агрегат «Замовлення» (Rich Domain Model).
 * Усі суми обчислюються на льоту з власного стану — дублювання та застарілі значення неможливі.
 */
public final class Order {
    private final int id;
    private final Customer customer;
    private final List<OrderItem> items;
    private final TaxPolicy taxPolicy;
    private OrderStatus status;

    private Order(int id, Customer customer, List<OrderItem> items, TaxPolicy taxPolicy) {
        this.id = id;
        this.customer = customer;
        this.items = items;
        this.taxPolicy = taxPolicy;
        this.status = OrderStatus.NEW;
    }

    /** Creator (static factory): замовлення створюється валідним, колекція захищена List.copyOf. */
    public static Order place(int id, Customer customer, List<OrderItem> items, TaxPolicy taxPolicy) {
        if (id <= 0) {
            throw new IllegalArgumentException("Ідентифікатор замовлення має бути додатним");
        }
        if (customer == null || taxPolicy == null) {
            throw new IllegalArgumentException("Клієнт і податкова політика обов\u2019язкові");
        }
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Замовлення не може бути порожнім");
        }
        return new Order(id, customer, List.copyOf(items), taxPolicy);
    }

    /** Information Expert: замовлення володіє рядками, тому саме воно рахує підсумок. */
    public BigDecimal subtotal() {
        BigDecimal sum = BigDecimal.ZERO;
        for (OrderItem item : items) {
            sum = sum.add(item.subtotal());
        }
        return sum;
    }

    public BigDecimal discountAmount() {
        return customer.discountOn(subtotal());
    }

    public BigDecimal taxAmount() {
        return taxPolicy.taxOn(subtotal().subtract(discountAmount()));
    }

    public BigDecimal finalAmount() {
        return subtotal().subtract(discountAmount()).add(taxAmount());
    }

    /** Бізнес-перехід NEW -> PROCESSED з інваріантами. */
    public void process() {
        if (status != OrderStatus.NEW) {
            throw new IllegalStateException("Замовлення #" + id + " уже оброблено");
        }
        if (finalAmount().signum() <= 0) {
            throw new IllegalStateException("Сума до сплати має бути додатною");
        }
        status = OrderStatus.PROCESSED;
    }

    public int id() {
        return id;
    }

    public OrderStatus status() {
        return status;
    }

    /** Закон Деметри: клієнт запитує в замовлення, а не order.getCustomer().getName(). */
    public String customerName() {
        return customer.name();
    }

    public String customerEmail() {
        return customer.email();
    }

    public String customerType() {
        return customer.typeLabel();
    }

    public List<OrderItem> items() {
        return items;
    }
}
