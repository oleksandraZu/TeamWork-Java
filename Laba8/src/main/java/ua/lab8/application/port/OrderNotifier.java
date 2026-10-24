package ua.lab8.application.port;

import ua.lab8.domain.Order;

/** Indirection: канал сповіщення (email/SMS/push) можна замінити без змін у контролері. */
public interface OrderNotifier {

    void notifyProcessed(Order order);
}
