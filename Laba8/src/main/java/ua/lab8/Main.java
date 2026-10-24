package ua.lab8;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;
import ua.lab8.application.CatalogController;
import ua.lab8.application.OrderController;
import ua.lab8.application.factory.CustomerFactory;
import ua.lab8.application.factory.OrderFactory;
import ua.lab8.application.factory.OrderItemsFactory;
import ua.lab8.application.step.NotifyCustomerStep;
import ua.lab8.application.step.PersistOrderStep;
import ua.lab8.application.step.ReceiptStep;
import ua.lab8.domain.discount.DiscountPolicyRegistry;
import ua.lab8.domain.tax.VatTaxPolicy;
import ua.lab8.infrastructure.FileOrderRepository;
import ua.lab8.infrastructure.InMemoryProductCatalog;
import ua.lab8.infrastructure.SimulatedEmailNotifier;
import ua.lab8.infrastructure.TextReceiptFormatter;
import ua.lab8.ui.ConsoleApp;
import ua.lab8.ui.ConsoleCommandParser;
import ua.lab8.ui.OrderRequestParser;

/**
 * Composition Root: єдине місце, яке знає про всі конкретні класи й зшиває шари.
 * Це найнестабільніший елемент системи (I = 1) — від нього ніхто не залежить.
 */
public final class Main {

    private Main() {
    }

    public static void main(String[] args) {
        InMemoryProductCatalog catalog = InMemoryProductCatalog.withDemoProducts();
        FileOrderRepository repository = new FileOrderRepository(Path.of("orders.txt"));

        OrderFactory orderFactory = new OrderFactory(
                new CustomerFactory(DiscountPolicyRegistry.withDefaults()),
                new OrderItemsFactory(catalog),
                VatTaxPolicy.standard(),
                repository);

        OrderController orderController = new OrderController(orderFactory, List.of(
                new ReceiptStep(new TextReceiptFormatter()),
                new PersistOrderStep(repository),
                new NotifyCustomerStep(new SimulatedEmailNotifier())));

        PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8);
        Scanner in = new Scanner(System.in, StandardCharsets.UTF_8);

        new ConsoleApp(in, out,
                new ConsoleCommandParser(),
                new OrderRequestParser("client@example.com", "1:1,2:2"),
                orderController,
                new CatalogController(catalog)).run();
    }
}
