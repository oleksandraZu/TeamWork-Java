package ua.lab7.task5;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;
import ua.lab7.task5.domain.Order;
import ua.lab7.task5.domain.OrderFulfillmentService;
import ua.lab7.task5.infrastructure.fake.InMemoryAuditLogger;
import ua.lab7.task5.infrastructure.fake.InMemoryOrderRepository;
import ua.lab7.task5.infrastructure.fake.RecordingEmailService;

class OrderFulfillmentServiceTest {

    private final InMemoryOrderRepository repository = new InMemoryOrderRepository();
    private final RecordingEmailService email = new RecordingEmailService();
    private final InMemoryAuditLogger audit = new InMemoryAuditLogger();
    private final OrderFulfillmentService service = new OrderFulfillmentService(repository, email, audit);

    private static Order order(List<String> items) {
        return new Order("ORD-1", "a@b.com", new BigDecimal("10.00"), items);
    }

    @Test
    void validOrderIsSavedLoggedAndConfirmedThroughInjectedFakes() {
        Order order = order(List.of("item"));
        service.processOrder(order);

        assertEquals(List.of(order), repository.saved());
        assertEquals(List.of("Успішно оброблено замовлення ORD-1"), audit.messages());
        assertEquals(List.of("a@b.com:ORD-1"), email.sent());
    }

    @Test
    void emptyOrderIsRejectedAndNothingIsTouched() {
        assertThrows(IllegalArgumentException.class, () -> service.processOrder(order(List.of())));
        assertThrows(IllegalArgumentException.class, () -> service.processOrder(null));
        assertTrue(repository.saved().isEmpty());
        assertTrue(audit.messages().isEmpty());
        assertTrue(email.sent().isEmpty());
    }

    @Test
    void constructorRejectsMissingDependencies() {
        assertThrows(NullPointerException.class, () -> new OrderFulfillmentService(null, email, audit));
        assertThrows(NullPointerException.class, () -> new OrderFulfillmentService(repository, null, audit));
        assertThrows(NullPointerException.class, () -> new OrderFulfillmentService(repository, email, null));
    }

    @Test
    void compositionRootBuildsBothConfigurations() {
        Order order = order(List.of("item"));
        CompositionRoot.production().processOrder(order);
        CompositionRoot.inMemory().processOrder(order);
    }
}
