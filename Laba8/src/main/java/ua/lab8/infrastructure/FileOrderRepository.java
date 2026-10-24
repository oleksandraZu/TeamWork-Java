package ua.lab8.infrastructure;

import ua.lab8.application.port.OrderRepository;
import ua.lab8.domain.Order;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * Зберігає кожне замовлення окремим рядком у текстовому файлі.
 * Наступний id обчислюється з кількості вже збережених рядків, тому переживає перезапуск.
 */
public final class FileOrderRepository implements OrderRepository {
    private static final int FIRST_ORDER_ID = 101;
    private final Path file;

    public FileOrderRepository(Path file) {
        this.file = file;
    }

    @Override
    public int nextId() {
        try {
            if (!Files.exists(file)) {
                return FIRST_ORDER_ID;
            }
            long saved = Files.readAllLines(file, StandardCharsets.UTF_8).stream()
                    .filter(line -> line.startsWith("#"))
                    .count();
            return FIRST_ORDER_ID + (int) saved;
        } catch (IOException e) {
            throw new UncheckedIOException("Не вдалося прочитати " + file, e);
        }
    }

    @Override
    public void save(Order order) {
        String line = "#" + order.id()
                + " | " + order.customerName()
                + " | " + order.customerType()
                + " | sum=" + order.subtotal()
                + " | discount=" + order.discountAmount()
                + " | vat=" + order.taxAmount()
                + " | total=" + order.finalAmount()
                + " | " + order.status()
                + System.lineSeparator();
        try {
            Files.writeString(file, line, StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new UncheckedIOException("Не вдалося записати " + file, e);
        }
    }

    @Override
    public String storageName() {
        return file.getFileName().toString();
    }
}
