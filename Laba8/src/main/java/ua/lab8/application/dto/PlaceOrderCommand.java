package ua.lab8.application.dto;

import java.util.List;

/** Команда «оформити замовлення». Єдине, що CLI передає в застосунок. */
public record PlaceOrderCommand(String customerName,
                                String customerType,
                                String customerEmail,
                                List<OrderLineRequest> lines) {

    public PlaceOrderCommand {
        lines = List.copyOf(lines);
    }
}
