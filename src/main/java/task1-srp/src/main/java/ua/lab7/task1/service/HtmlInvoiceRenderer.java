package ua.lab7.task1.service;

import ua.lab7.task1.domain.Order;
import ua.lab7.task1.domain.OrderItem;
import ua.lab7.task1.domain.OrderTotals;

/** Єдина відповідальність: формування HTML-рахунку (презентація). */
public class HtmlInvoiceRenderer {

    public String render(Order order, OrderTotals totals) {
        StringBuilder html = new StringBuilder("<html><body>");
        html.append("<h1>Рахунок на замовлення для ").append(escape(order.customerEmail())).append("</h1>");
        html.append("<p>Адреса доставки: ").append(escape(order.deliveryAddress())).append("</p>");
        html.append("<table border='1'><tr><th>Товар</th><th>Кількість</th><th>Ціна</th></tr>");
        for (OrderItem item : order.items()) {
            html.append("<tr><td>").append(escape(item.name()))
                .append("</td><td>").append(item.quantity())
                .append("</td><td>").append(item.price()).append(" грн</td></tr>");
        }
        html.append("</table>");
        html.append("<p>Проміжна сума: ").append(totals.subtotal()).append(" грн</p>");
        html.append("<p>Знижка (").append(order.category().label()).append("): -")
            .append(totals.discountAmount()).append(" грн</p>");
        html.append("<p>ПДВ (20%): ").append(totals.taxAmount()).append(" грн</p>");
        html.append("<h3>Загалом до сплати: ").append(totals.totalAmount()).append(" грн</h3>");
        html.append("</body></html>");
        return html.toString();
    }

    private static String escape(String text) {
        if (text == null) {
            return "";
        }
        return text.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
    }
}
