package ua.lab7.task5;

import java.math.BigDecimal;
import java.util.List;
import ua.lab7.task5.domain.Order;
import ua.lab7.task5.domain.OrderFulfillmentService;

public class Main {

    public static void main(String[] args) {
        Order order = new Order("ORD-1001", "client@example.com", new BigDecimal("1499.90"),
                List.of("Навушники", "Кабель USB-C"));

        System.out.println("=== Production-збірка ===");
        OrderFulfillmentService production = CompositionRoot.production();
        production.processOrder(order);

        System.out.println("=== In-memory збірка (той самий бізнес-клас, інші залежності) ===");
        CompositionRoot.inMemory().processOrder(order);
        System.out.println("Нічого не виведено в консоль — залежності підмінені.");
    }
}
