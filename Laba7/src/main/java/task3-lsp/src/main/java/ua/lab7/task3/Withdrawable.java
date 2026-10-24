package ua.lab7.task3;

/**
 * Контракт: з рахунку можна знімати кошти.
 * Передумови: amount > 0 та amount <= balance. Постумова: баланс зменшується рівно на amount.
 */
public interface Withdrawable extends Account {
    void withdraw(Money amount);
}
