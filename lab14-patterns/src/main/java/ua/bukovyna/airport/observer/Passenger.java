package ua.bukovyna.airport.observer;

import ua.bukovyna.airport.common.Logger;

public class Passenger implements FlightObserver {

    private final String name;

    public Passenger(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return "Пасажир " + name;
    }

    @Override
    public void update(Flight flight) {
        Logger.log(getName(), "отримав SMS: рейс " + flight.getNumber() + " "
                + flight.getStatus().getTitle());
    }
}
