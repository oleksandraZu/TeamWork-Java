package ua.lab7.task3;

/** Контракт: рахунок можна поповнювати. Передумова: amount > 0. */
public interface Depositable extends Account {
    void deposit(Money amount);
}
