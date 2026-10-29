package variant1_orderprocessing.application;

import variant1_orderprocessing.domain.Order;

/** Indirection: канал сповіщення (email, SMS...) прихований за інтерфейсом. */
public interface OrderNotifier {
    void notifyProcessed(Order order);
}
