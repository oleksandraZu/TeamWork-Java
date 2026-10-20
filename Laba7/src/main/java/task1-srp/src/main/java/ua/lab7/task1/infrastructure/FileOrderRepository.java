package ua.lab7.task1.infrastructure;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import ua.lab7.task1.domain.Order;
import ua.lab7.task1.domain.OrderTotals;

/** Єдина відповідальність: дописування замовлення у текстовий файл («БД»). */
public class FileOrderRepository implements OrderRepository {

    private static final DateTimeFormatter TIMESTAMP = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final Path file;
    private final Clock clock;

    public FileOrderRepository(Path file, Clock clock) {
        this.file = Objects.requireNonNull(file, "file");
        this.clock = Objects.requireNonNull(clock, "clock");
    }

    @Override
    public void save(Order order, OrderTotals totals) {
        String record = String.format("%s|%s|%s|%s%n",
                LocalDateTime.now(clock).format(TIMESTAMP),
                order.customerEmail(), totals.totalAmount(), order.deliveryAddress());
        try {
            Files.writeString(file, record, StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            // Поведінка збережена з вихідного коду: збій запису не перериває обробку замовлення.
            System.err.println("Помилка запису у файл бази даних: " + e.getMessage());
        }
    }
}
