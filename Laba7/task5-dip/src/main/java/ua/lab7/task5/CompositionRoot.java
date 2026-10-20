package ua.lab7.task5;

import ua.lab7.task5.domain.OrderFulfillmentService;
import ua.lab7.task5.infrastructure.FileAuditLogger;
import ua.lab7.task5.infrastructure.MySqlOrderRepository;
import ua.lab7.task5.infrastructure.SmtpEmailService;
import ua.lab7.task5.infrastructure.fake.InMemoryAuditLogger;
import ua.lab7.task5.infrastructure.fake.InMemoryOrderRepository;
import ua.lab7.task5.infrastructure.fake.RecordingEmailService;

/**
 * Composition Root (ручний DI): єдине місце, де створюються конкретні реалізації
 * і «склеюються» з бізнес-логікою. Service Locator не використовується.
 */
public final class CompositionRoot {

    private CompositionRoot() {
    }

    public static OrderFulfillmentService production() {
        return new OrderFulfillmentService(new MySqlOrderRepository(), new SmtpEmailService(), new FileAuditLogger());
    }

    public static OrderFulfillmentService inMemory() {
        return new OrderFulfillmentService(
                new InMemoryOrderRepository(), new RecordingEmailService(), new InMemoryAuditLogger());
    }
}
