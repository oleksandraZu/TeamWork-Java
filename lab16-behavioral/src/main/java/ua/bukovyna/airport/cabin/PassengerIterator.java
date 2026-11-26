package ua.bukovyna.airport.cabin;

/** Iterator: єдиний інтерфейс обходу, не залежить від структури салону. */
public interface PassengerIterator {
    boolean hasNext();

    Passenger next();
}
