package ua.bukovyna.airport.cabin;

/** Aggregate: фабрика ітераторів різних способів обходу. */
public interface PassengerCollection {
    PassengerIterator boardingIterator();

    PassengerIterator cateringIterator();

    PassengerIterator securityIterator();

    int size();
}
