package ua.lab8.infrastructure;

import ua.lab8.application.port.ReceiptFormatter;
import ua.lab8.domain.Order;

/** Текстовий рахунок у форматі вихідного коду. */
public final class TextReceiptFormatter implements ReceiptFormatter {

    @Override
    public String format(Order order) {
        return "Замовлення #" + order.id() + " для " + order.customerName()
                + "\nСума: " + order.subtotal().toPlainString() + " грн"
                + "\nЗнижка: " + order.discountAmount().toPlainString() + " грн"
                + "\nПДВ: " + order.taxAmount().toPlainString() + " грн"
                + "\nДо сплати: " + order.finalAmount().toPlainString() + " грн";
    }
}
