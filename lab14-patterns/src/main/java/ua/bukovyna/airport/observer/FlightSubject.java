package ua.bukovyna.airport.observer;

public interface FlightSubject {
    void subscribe(FlightObserver observer);

    void unsubscribe(FlightObserver observer);

    void notifyObservers();
}
