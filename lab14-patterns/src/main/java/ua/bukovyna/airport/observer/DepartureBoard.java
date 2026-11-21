package ua.bukovyna.airport.observer;

import ua.bukovyna.airport.common.Logger;

public class DepartureBoard implements FlightObserver {

    @Override
    public String getName() {
        return "Табло";
    }

    @Override
    public void update(Flight flight) {
        Logger.log(getName(), "оновлено рядок: " + flight.getNumber() + " | "
                + flight.getStatus().getTitle() + " | вихід " + flight.getGate());
    }
}
