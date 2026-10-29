package variant1_orderprocessing.domain;

import java.util.List;

public final class Order {
    private final int id;
    private final Customer customer;
    private final List<OrderItem> items;
    private final TaxPolicy taxPolicy;
    private double totalAmount;
    private double discountAmount;
    private double taxAmount;
    private double finalAmount;
    private OrderStatus status = OrderStatus.NEW;

    private Order(int id, Customer customer, List<OrderItem> items, TaxPolicy taxPolicy) {
        this.id = id;
        this.customer = customer;
        this.items = items;
        this.taxPolicy = taxPolicy;
    }

    /** Creator: агрегат створюється одразу з клієнтом і рядками, у валідному стані. */
    public static Order create(int id, Customer customer, List<OrderItem> items, TaxPolicy taxPolicy) {
        if (customer == null) {
            throw new IllegalArgumentException("Клієнт обов'язковий");
        }
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Замовлення має містити хоча б один товар");
        }
        if (taxPolicy == null) {
            throw new IllegalArgumentException("Політика податку обов'язкова");
        }
        return new Order(id, customer, List.copyOf(items), taxPolicy);
    }

    /** Information Expert: замовлення володіє даними для підрахунку підсумків. */
    public void process() {
        if (status != OrderStatus.NEW) {
            throw new IllegalStateException("Замовлення #" + id + " вже оброблено");
        }
        totalAmount = subtotal();
        discountAmount = customer.discountFor(totalAmount);
        taxAmount = taxPolicy.taxOn(totalAmount - discountAmount);
        finalAmount = totalAmount - discountAmount + taxAmount;
        status = OrderStatus.PROCESSED;
    }

    public double subtotal() {
        double sum = 0.0;
        for (OrderItem item : items) {
            sum += item.subtotal();
        }
        return sum;
    }

    // Закон Деметри: зовнішній код не ходить через order.getCustomer().getX()
    public String customerName() { return customer.getName(); }
    public String customerEmail() { return customer.getEmail(); }

    public int getId() { return id; }
    public List<OrderItem> getItems() { return items; }
    public double getTotalAmount() { return totalAmount; }
    public double getDiscountAmount() { return discountAmount; }
    public double getTaxAmount() { return taxAmount; }
    public double getFinalAmount() { return finalAmount; }
    public OrderStatus getStatus() { return status; }
}
