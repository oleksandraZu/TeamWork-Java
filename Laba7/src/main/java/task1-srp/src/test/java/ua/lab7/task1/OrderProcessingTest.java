package ua.lab7.task1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import ua.lab7.task1.domain.CustomerCategory;
import ua.lab7.task1.domain.Order;
import ua.lab7.task1.domain.OrderItem;
import ua.lab7.task1.domain.OrderTotals;
import ua.lab7.task1.infrastructure.FileOrderRepository;
import ua.lab7.task1.infrastructure.NotificationService;
import ua.lab7.task1.infrastructure.OrderRepository;
import ua.lab7.task1.service.HtmlInvoiceRenderer;
import ua.lab7.task1.service.OrderValidator;
import ua.lab7.task1.service.PricingService;

class OrderProcessingTest {

    private static OrderItem item(String price, int qty) {
        return new OrderItem("id", "Товар", new BigDecimal(price), qty);
    }

    private static Order order(CustomerCategory category, OrderItem... items) {
        return new Order("a@b.com", category, List.of(items), "Київ");
    }

    // ---------- PricingService ----------

    @Test
    void vipGetsFifteenPercentDiscountAndVat() {
        OrderTotals t = new PricingService().calculate(order(CustomerCategory.VIP, item("1000.00", 2)));
        assertEquals(new BigDecimal("2000.00"), t.subtotal());
        assertEquals(new BigDecimal("300.00"), t.discountAmount());
        assertEquals(new BigDecimal("340.00"), t.taxAmount());
        assertEquals(new BigDecimal("2040.00"), t.totalAmount());
    }

    @Test
    void regularGetsFivePercentAndStandardNone() {
        PricingService pricing = new PricingService();
        assertEquals(new BigDecimal("5.00"),
                pricing.calculate(order(CustomerCategory.REGULAR, item("100.00", 1))).discountAmount());
        assertEquals(new BigDecimal("0.00"),
                pricing.calculate(order(CustomerCategory.STANDARD, item("100.00", 1))).discountAmount());
    }

    @Test
    void unknownCategoryFallsBackToStandard() {
        assertEquals(CustomerCategory.STANDARD, CustomerCategory.fromString("gold"));
        assertEquals(CustomerCategory.STANDARD, CustomerCategory.fromString(null));
        assertEquals(CustomerCategory.VIP, CustomerCategory.fromString(" vip "));
    }

    // ---------- OrderValidator ----------

    @Test
    void rejectsInvalidEmail() {
        Order bad = new Order("no-at-sign", CustomerCategory.VIP, List.of(item("1", 1)), "x");
        assertThrows(IllegalArgumentException.class, () -> new OrderValidator().validate(bad));
    }

    @Test
    void rejectsEmptyCart() {
        Order empty = new Order("a@b.com", CustomerCategory.VIP, null, "x");
        assertThrows(IllegalStateException.class, () -> new OrderValidator().validate(empty));
    }

    @Test
    void rejectsNonPositiveQuantityOrPrice() {
        OrderValidator validator = new OrderValidator();
        assertThrows(IllegalArgumentException.class,
                () -> validator.validate(order(CustomerCategory.VIP, item("10", 0))));
        assertThrows(IllegalArgumentException.class,
                () -> validator.validate(order(CustomerCategory.VIP, item("0", 1))));
    }

    // ---------- HtmlInvoiceRenderer ----------

    @Test
    void invoiceContainsTotalsAndEscapesHtml() {
        Order o = new Order("a@b.com", CustomerCategory.VIP,
                List.of(new OrderItem("1", "<b>TV</b>", new BigDecimal("100.00"), 1)), "Київ");
        String html = new HtmlInvoiceRenderer().render(o, new PricingService().calculate(o));
        assertTrue(html.contains("&lt;b&gt;TV&lt;/b&gt;"));
        assertTrue(html.contains("Загалом до сплати: 102.00 грн"));
        assertTrue(html.contains("Знижка (VIP): -15.00 грн"));
    }

    // ---------- FileOrderRepository ----------

    @Test
    void repositoryAppendsRecord(@TempDir Path dir) throws IOException {
        Path file = dir.resolve("db.txt");
        Clock clock = Clock.fixed(Instant.parse("2026-01-02T03:04:05Z"), ZoneOffset.UTC);
        Order o = order(CustomerCategory.STANDARD, item("100.00", 1));
        new FileOrderRepository(file, clock).save(o, new PricingService().calculate(o));
        assertEquals("2026-01-02 03:04:05|a@b.com|120.00|Київ" + System.lineSeparator(), Files.readString(file));
    }

    // ---------- Orchestrator ----------

    @Test
    void orchestratorRunsStepsInOrderAndSkipsThemOnInvalidOrder() {
        List<String> calls = new ArrayList<>();
        OrderRepository repo = (o, t) -> calls.add("save");
        NotificationService notifier = (r, s, b) -> calls.add("send:" + r);
        OrderProcessor processor = new OrderProcessor(new OrderValidator(), new PricingService(),
                new HtmlInvoiceRenderer(), repo, notifier);

        processor.processOrder(order(CustomerCategory.VIP, item("10.00", 1)));
        assertEquals(List.of("save", "send:a@b.com"), calls);

        calls.clear();
        Order bad = new Order("bad", CustomerCategory.VIP, List.of(item("10.00", 1)), "x");
        assertThrows(IllegalArgumentException.class, () -> processor.processOrder(bad));
        assertTrue(calls.isEmpty());
    }
}
