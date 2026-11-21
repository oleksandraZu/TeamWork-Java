package ua.bukovyna.airport.observer;

import ua.bukovyna.airport.common.Logger;

public class CateringSubscriber implements FlightObserver {

    @Override
    public String getName() {
        return "Кейтеринг";
    }

    @Override
    public void update(Flight flight) {
        switch (flight.getStatus()) {
            case DELAYED -> Logger.log(getName(), "переносить завантаження харчування на "
                    + flight.getDelayMinutes() + " хв");
            case GATE_CHANGED -> Logger.log(getName(), "перенаправляє машину до виходу " + flight.getGate());
            case BOARDING -> Logger.log(getName(), "завершує завантаження харчування");
            default -> Logger.log(getName(), "змін немає");
        }
    }
}
