package ua.lab8.application.step;

import ua.lab8.application.port.ReceiptFormatter;
import ua.lab8.domain.Order;

/** Крок: сформувати текстовий рахунок. */
public final class ReceiptStep implements OrderStep {
    private final ReceiptFormatter formatter;

    public ReceiptStep(ReceiptFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public String execute(Order order) {
        return formatter.format(order);
    }
}
