package ua.lab8.ui;

import ua.lab8.application.CatalogController;
import ua.lab8.application.OrderController;
import ua.lab8.application.dto.PlaceOrderCommand;
import ua.lab8.application.dto.ProductView;

import java.io.PrintStream;
import java.io.UncheckedIOException;
import java.util.Scanner;

/**
 * Thin Console UI: читає текст, показує результат. Жодної бізнес-логіки й обчислень.
 * Ввід/вивід інжектується (Scanner/PrintStream), тому клієнт тестується без реальної консолі.
 */
public final class ConsoleApp {
    private final Scanner in;
    private final PrintStream out;
    private final ConsoleCommandParser menuParser;
    private final OrderRequestParser orderParser;
    private final OrderController orderController;
    private final CatalogController catalogController;

    public ConsoleApp(Scanner in,
                      PrintStream out,
                      ConsoleCommandParser menuParser,
                      OrderRequestParser orderParser,
                      OrderController orderController,
                      CatalogController catalogController) {
        this.in = in;
        this.out = out;
        this.menuParser = menuParser;
        this.orderParser = orderParser;
        this.orderController = orderController;
        this.catalogController = catalogController;
    }

    public void run() {
        boolean running = true;
        while (running) {
            printMenu();
            if (!in.hasNextLine()) {
                break;
            }
            switch (menuParser.parse(in.nextLine())) {
                case PLACE_ORDER -> placeOrder();
                case SHOW_CATALOG -> showCatalog();
                case EXIT -> running = false;
                default -> out.println("Невідома команда. Оберіть 1, 2 або 0.");
            }
        }
        out.println("До побачення!");
    }

    private void printMenu() {
        out.println();
        out.println("=== Інтернет-магазин електроніки ===");
        out.println("1. Оформити замовлення");
        out.println("2. Показати каталог");
        out.println("0. Вихід");
        out.print("Ваш вибір: ");
    }

    private void placeOrder() {
        out.println("=== Оформлення замовлення ===");
        String name = ask("Введіть ім\u2019я клієнта: ");
        String type = ask("Введіть тип клієнта (New/Regular/VIP): ");
        String email = ask("Введіть email (Enter — client@example.com): ");
        String cart = ask("Кошик id:кількість через кому (Enter — 1:1,2:2): ");
        try {
            PlaceOrderCommand command = orderParser.parse(name, type, email, cart);
            orderController.placeOrder(command).forEach(out::println);
        } catch (IllegalArgumentException | IllegalStateException | UncheckedIOException e) {
            out.println("Помилка: " + e.getMessage());
        }
    }

    private void showCatalog() {
        out.println("=== Каталог ===");
        for (ProductView product : catalogController.listProducts()) {
            out.println(product.id() + ". " + product.name() + " — "
                    + product.price().toPlainString() + " грн (" + product.category() + ")");
        }
    }

    private String ask(String prompt) {
        out.print(prompt);
        return in.hasNextLine() ? in.nextLine() : "";
    }
}
