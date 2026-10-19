package ua.lab7.task5.infrastructure;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import ua.lab7.task5.domain.AuditLogger;

/** Імітація запису в audit.log (вивід у консоль). */
public class FileAuditLogger implements AuditLogger {

    private static final DateTimeFormatter TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void logTransaction(String message) {
        System.out.println("[File Logger] Запис до файлу audit.log: " + LocalDateTime.now().format(TIME) + " - " + message);
    }
}
