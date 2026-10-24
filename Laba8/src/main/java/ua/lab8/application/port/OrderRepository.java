package ua.lab8.application.port;

import ua.lab8.domain.Order;

/** Indirection + Protected Variations: деталі сховища (файл/БД) приховані за інтерфейсом. */
public interface OrderRepository {

    int nextId();

    void save(Order order);

    /** Людиночитна назва сховища для підтвердження користувачу. */
    String storageName();
}
