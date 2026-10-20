package ua.lab7.task5.infrastructure.fake;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import ua.lab7.task5.domain.AuditLogger;

/** Фейк: збирає повідомлення аудиту в списку. */
public class InMemoryAuditLogger implements AuditLogger {

    private final List<String> messages = new ArrayList<>();

    @Override
    public void logTransaction(String message) {
        messages.add(message);
    }

    public List<String> messages() {
        return Collections.unmodifiableList(messages);
    }
}
