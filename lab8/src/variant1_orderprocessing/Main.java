package variant1_orderprocessing;

import java.nio.file.Path;
import java.util.Scanner;

import variant1_orderprocessing.application.OrderCompletion;
import variant1_orderprocessing.application.OrderFactory;
import variant1_orderprocessing.application.PlaceOrderController;
import variant1_orderprocessing.domain.VatTax;
import variant1_orderprocessing.infrastructure.ConsoleEmailNotifier;
import variant1_orderprocessing.infrastructure.FileOrderRepository;
import variant1_orderprocessing.infrastructure.InMemoryProductCatalog;
import variant1_orderprocessing.ui.ConsoleApp;
import variant1_orderprocessing.ui.ConsoleCommandParser;

/** Composition root: єдине місце, що знає про конкретні реалізації. */
public final class Main {
    public static void main(String[] args) {
        FileOrderRepository repository = new FileOrderRepository(Path.of("orders.txt"));
        PlaceOrderController controller = new PlaceOrderController(
                new OrderFactory(new InMemoryProductCatalog(), repository, new VatTax()),
                new OrderCompletion(repository, new ConsoleEmailNotifier()));
        new ConsoleApp(new Scanner(System.in), new ConsoleCommandParser(), controller).run();
    }
}
