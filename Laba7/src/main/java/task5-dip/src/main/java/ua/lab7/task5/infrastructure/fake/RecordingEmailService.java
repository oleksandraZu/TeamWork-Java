package ua.lab7.task5.infrastructure.fake;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import ua.lab7.task5.domain.EmailService;

/** Фейк: запам'ятовує «надіслані» листи замість реальної відправки. */
public class RecordingEmailService implements EmailService {

    private final List<String> sent = new ArrayList<>();

    @Override
    public void sendOrderConfirmation(String email, String orderId) {
        sent.add(email + ":" + orderId);
    }

    public List<String> sent() {
        return Collections.unmodifiableList(sent);
    }
}
