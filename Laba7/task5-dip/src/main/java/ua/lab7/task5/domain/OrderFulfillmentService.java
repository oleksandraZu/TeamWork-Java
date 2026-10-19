package ua.lab7.task5.domain;

import java.util.Objects;

/**
 * Високорівневий клас бізнес-логіки. Залежить ВИКЛЮЧНО від абстракцій,
 * які отримує через конструктор (Constructor Injection); жодного {@code new} інфраструктури.
 */
public class OrderFulfillmentService {

    private final OrderRepository repository;
    private final EmailService emailService;
    private final AuditLogger auditLogger;

    public OrderFulfillmentService(OrderRepository repository, EmailService emailService, AuditLogger auditLogger) {
        this.repository = Objects.requireNonNull(repository, "repository");
        this.emailService = Objects.requireNonNull(emailService, "emailService");
        this.auditLogger = Objects.requireNonNull(auditLogger, "auditLogger");
    }

    public void processOrder(Order order) {
        if (order == null || order.items().isEmpty()) {
            throw new IllegalArgumentException("Замовлення не може бути порожнім.");
        }
        repository.saveOrder(order);
        auditLogger.logTransaction("Успішно оброблено замовлення " + order.orderId());
        emailService.sendOrderConfirmation(order.customerEmail(), order.orderId());
    }
}
