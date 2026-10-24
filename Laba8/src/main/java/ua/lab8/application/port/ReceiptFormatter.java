package ua.lab8.application.port;

import ua.lab8.domain.Order;

/** Protected Variations: формат рахунку (текст/HTML/PDF) змінюється незалежно від домену. */
public interface ReceiptFormatter {

    String format(Order order);
}
