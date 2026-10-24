package ua.lab8.ui;

import ua.lab8.application.dto.OrderLineRequest;
import ua.lab8.application.dto.PlaceOrderCommand;

import java.util.ArrayList;
import java.util.List;

/**
 * Pure Fabrication: перетворює введені рядки на PlaceOrderCommand.
 * Кошик задається як "id:кількість,id:кількість"; порожній ввід = демо-кошик з вихідного коду.
 */
public final class OrderRequestParser {
    private final String defaultEmail;
    private final String defaultCart;

    public OrderRequestParser(String defaultEmail, String defaultCart) {
        this.defaultEmail = defaultEmail;
        this.defaultCart = defaultCart;
    }

    public PlaceOrderCommand parse(String name, String type, String email, String cart) {
        String effectiveEmail = isBlank(email) ? defaultEmail : email.trim();
        String effectiveCart = isBlank(cart) ? defaultCart : cart.trim();
        return new PlaceOrderCommand(name, type, effectiveEmail, parseCart(effectiveCart));
    }

    private List<OrderLineRequest> parseCart(String cart) {
        List<OrderLineRequest> lines = new ArrayList<>();
        for (String part : cart.split(",")) {
            String[] pair = part.trim().split(":");
            if (pair.length != 2) {
                throw new IllegalArgumentException("Невірний формат кошика: \"" + part.trim() + "\". Очікується id:кількість");
            }
            try {
                lines.add(new OrderLineRequest(Integer.parseInt(pair[0].trim()), Integer.parseInt(pair[1].trim())));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Невірний формат кошика: \"" + part.trim() + "\". Id і кількість мають бути числами");
            }
        }
        return lines;
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}
