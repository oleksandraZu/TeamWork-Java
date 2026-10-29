package variant1_orderprocessing.ui;

import java.util.Scanner;

import variant1_orderprocessing.application.OrderResult;
import variant1_orderprocessing.application.PlaceOrderController;

/** Thin Console UI: читає введення, викликає контролер, друкує результат. */
public final class ConsoleApp {
    private final Scanner scanner;
    private final ConsoleCommandParser parser;
    private final PlaceOrderController controller;

    public ConsoleApp(Scanner scanner, ConsoleCommandParser parser, PlaceOrderController controller) {
        this.scanner = scanner;
        this.parser = parser;
        this.controller = controller;
    }

    public void run() {
        System.out.println("=== Оформлення замовлення ===");
        System.out.print("Введіть ім'я клієнта: ");
        String name = scanner.nextLine();
        System.out.print("Введіть тип клієнта (New/Regular/VIP): ");
        String type = scanner.nextLine();
        try {
            print(controller.execute(parser.parse(name, type)));
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    private void print(OrderResult r) {
        System.out.println("Замовлення #" + r.orderId() + " для " + r.customerName()
                + "\nСума: " + r.totalAmount() + " грн"
                + "\nЗнижка: " + r.discountAmount() + " грн"
                + "\nПДВ: " + r.taxAmount() + " грн"
                + "\nДо сплати: " + r.finalAmount() + " грн");
    }
}
