package ua.lab7.task1.infrastructure;

/** Абстракція каналу сповіщень покупця. */
public interface NotificationService {
    void send(String recipient, String subject, String body);
}
