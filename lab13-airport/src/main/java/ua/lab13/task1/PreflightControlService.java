package ua.lab13.task1;

import ua.lab13.common.EventLogger;

import java.util.EnumMap;
import java.util.Map;
import java.util.StringJoiner;

public class PreflightControlService {
    private static final String SOURCE = "Контроль";

    private final Map<FlightType, ControlHandler> chains = new EnumMap<>(FlightType.class);

    public void configureChain(FlightType type, ControlHandler... handlers) {
        if (handlers.length == 0) {
            throw new IllegalArgumentException("Ланцюг не може бути порожнім");
        }
        StringJoiner order = new StringJoiner(" -> ");
        for (int i = 0; i < handlers.length; i++) {
            handlers[i].setNext(i + 1 < handlers.length ? handlers[i + 1] : null);
            order.add(handlers[i].title());
        }
        chains.put(type, handlers[0]);
        EventLogger.log(SOURCE, "Налаштовано ланцюг для рейсів (" + type.getTitle() + "): " + order);
    }

    public ControlResult process(Passenger passenger) {
        ControlHandler first = chains.get(passenger.flightType());
        if (first == null) {
            throw new IllegalStateException("Ланцюг для типу рейсу не налаштовано: " + passenger.flightType());
        }
        EventLogger.log(SOURCE, "Пасажир " + passenger.name() + " (рейс " + passenger.flightNumber()
                + ", " + passenger.flightType().getTitle() + ") направлений на контроль, перша ланка: " + first.title());
        ControlResult result = first.handle(passenger);
        if (result.passed()) {
            EventLogger.log(SOURCE, "Пасажира " + passenger.name() + " допущено до посадки");
        } else {
            EventLogger.log(SOURCE, "Пасажира " + passenger.name() + " не допущено: зупинила ланка «"
                    + result.stoppedBy() + "», причина: " + result.reason());
        }
        return result;
    }
}
