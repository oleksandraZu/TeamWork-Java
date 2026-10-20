package ua.lab7.task5.domain;

/** Абстракція сховища (належить доменному шару, реалізації — в infrastructure). */
public interface OrderRepository {
    void saveOrder(Order order);
}
