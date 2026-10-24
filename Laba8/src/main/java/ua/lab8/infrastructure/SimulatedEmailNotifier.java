package ua.lab8.infrastructure;

import ua.lab8.application.port.OrderNotifier;
import ua.lab8.domain.Order;

import java.util.ArrayList;
import java.util.List;

/** Імітація відправки email: адреси отримувачів накопичуються у «поштовій скриньці». */
public final class SimulatedEmailNotifier implements OrderNotifier {
    private final List<String> sentTo = new ArrayList<>();

    @Override
    public void notifyProcessed(Order order) {
        sentTo.add(order.customerEmail());
    }

    public List<String> sentTo() {
        return List.copyOf(sentTo);
    }
}
