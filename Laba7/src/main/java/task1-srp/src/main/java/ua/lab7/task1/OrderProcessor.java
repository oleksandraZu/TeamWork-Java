package ua.lab7.task1;

import java.util.Objects;
import ua.lab7.task1.domain.Order;
import ua.lab7.task1.domain.OrderTotals;
import ua.lab7.task1.infrastructure.NotificationService;
import ua.lab7.task1.infrastructure.OrderRepository;
import ua.lab7.task1.service.HtmlInvoiceRenderer;
import ua.lab7.task1.service.OrderValidator;
import ua.lab7.task1.service.PricingService;

/** Оркестратор: лише координує кроки, сам жодної бізнес-логіки не містить. */
public class OrderProcessor {

    private static final String CONFIRMATION_SUBJECT = "Ваше замовлення успішно оформлено";

    private final OrderValidator validator;
    private final PricingService pricing;
    private final HtmlInvoiceRenderer invoiceRenderer;
    private final OrderRepository repository;
    private final NotificationService notifier;

    public OrderProcessor(OrderValidator validator, PricingService pricing, HtmlInvoiceRenderer invoiceRenderer,
                          OrderRepository repository, NotificationService notifier) {
        this.validator = Objects.requireNonNull(validator, "validator");
        this.pricing = Objects.requireNonNull(pricing, "pricing");
        this.invoiceRenderer = Objects.requireNonNull(invoiceRenderer, "invoiceRenderer");
        this.repository = Objects.requireNonNull(repository, "repository");
        this.notifier = Objects.requireNonNull(notifier, "notifier");
    }

    public void processOrder(Order order) {
        validator.validate(order);
        OrderTotals totals = pricing.calculate(order);
        String invoice = invoiceRenderer.render(order, totals);
        repository.save(order, totals);
        notifier.send(order.customerEmail(), CONFIRMATION_SUBJECT, invoice);
    }
}
