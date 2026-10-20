package ua.lab7.task5.domain;

/** Абстракція сповіщень покупця. */
public interface EmailService {
    void sendOrderConfirmation(String email, String orderId);
}
