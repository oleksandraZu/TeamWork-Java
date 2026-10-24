package variant1_orderprocessing.application;

import variant1_orderprocessing.domain.Order;

/** Indirection: деталі сховища приховані за інтерфейсом. */
public interface OrderRepository {
    int nextId();

    void save(Order order);
}
