package ua.lab7.task1.infrastructure;

/** Симуляція SMTP-відправки: вивід у консоль замість реального HTTP/SMTP-клієнта. */
public class ConsoleEmailNotifier implements NotificationService {

    @Override
    public void send(String recipient, String subject, String body) {
        System.out.println("Надсилання email на " + recipient + " через smtp.store.local:587");
        System.out.println("Тема: " + subject);
        System.out.println("Тіло: " + body);
    }
}
