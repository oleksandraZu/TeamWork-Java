package ua.bukovyna.airport.observer;

public interface FlightObserver {
    String getName();

    void update(Flight flight);
}
