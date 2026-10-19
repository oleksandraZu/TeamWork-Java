package ua.lab7.task5.infrastructure;

import ua.lab7.task5.domain.EmailService;

/** Імітація SMTP-відправки. */
public class SmtpEmailService implements EmailService {

    @Override
    public void sendOrderConfirmation(String email, String orderId) {
        System.out.println("[SMTP Email] Лист з підтвердженням замовлення " + orderId + " надіслано на " + email + ".");
    }
}
