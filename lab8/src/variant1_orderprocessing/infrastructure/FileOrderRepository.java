package variant1_orderprocessing.infrastructure;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import variant1_orderprocessing.application.OrderRepository;
import variant1_orderprocessing.domain.Order;

public final class FileOrderRepository implements OrderRepository {
    private final Path file;
    private int lastId = 100;

    public FileOrderRepository(Path file) {
        this.file = file;
    }

    @Override
    public int nextId() {
        return ++lastId;
    }

    @Override
    public void save(Order order) {
        String line = "Замовлення #" + order.getId() + " | " + order.customerName()
                + " | до сплати: " + order.getFinalAmount() + " грн" + System.lineSeparator();
        try {
            Files.writeString(file, line, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        System.out.println("[БД] Замовлення #" + order.getId() + " збережено у файл " + file.getFileName() + ".");
    }
}
