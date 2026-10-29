package variant1_orderprocessing.application;

import variant1_orderprocessing.domain.Order;

/** Pure Fabrication: обробляє замовлення, зберігає, сповіщає, формує DTO результату. */
public final class OrderCompletion {
    private final OrderRepository repository;
    private final OrderNotifier notifier;

    public OrderCompletion(OrderRepository repository, OrderNotifier notifier) {
        this.repository = repository;
        this.notifier = notifier;
    }

    public OrderResult complete(Order order) {
        order.process();
        repository.save(order);
        notifier.notifyProcessed(order);
        return new OrderResult(order.getId(), order.customerName(), order.customerEmail(),
                order.getTotalAmount(), order.getDiscountAmount(), order.getTaxAmount(), order.getFinalAmount());
    }
}
