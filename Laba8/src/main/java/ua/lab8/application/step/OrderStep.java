package ua.lab8.application.step;

import ua.lab8.domain.Order;

/** Polymorphism: кожен крок післяобробки — окрема реалізація, контролер лише ітерує список. */
public interface OrderStep {

    /** Виконує крок і повертає рядок підтвердження для користувача. */
    String execute(Order order);
}
