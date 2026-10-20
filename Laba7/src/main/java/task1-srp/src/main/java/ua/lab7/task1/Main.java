package ua.lab7.task1;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.time.Clock;
import java.util.List;
import ua.lab7.task1.domain.CustomerCategory;
import ua.lab7.task1.domain.Order;
import ua.lab7.task1.domain.OrderItem;
import ua.lab7.task1.infrastructure.ConsoleEmailNotifier;
import ua.lab7.task1.infrastructure.FileOrderRepository;
import ua.lab7.task1.service.HtmlInvoiceRenderer;
import ua.lab7.task1.service.OrderValidator;
import ua.lab7.task1.service.PricingService;

/** Composition Root завдання 1. */
public class Main {

    public static void main(String[] args) {
        OrderProcessor processor = new OrderProcessor(
                new OrderValidator(),
                new PricingService(),
                new HtmlInvoiceRenderer(),
                new FileOrderRepository(Path.of("orders_db.txt"), Clock.systemDefaultZone()),
                new ConsoleEmailNotifier());

        Order order = new Order(
                "client@example.com",
                CustomerCategory.fromString("VIP"),
                List.of(new OrderItem("TV-1", "Телевізор 55\"", new BigDecimal("18999.00"), 1),
                        new OrderItem("HD-2", "HDMI-кабель", new BigDecimal("250.50"), 2)),
                "м. Київ, вул. Хрещатик, 1");

        processor.processOrder(order);
    }
}
