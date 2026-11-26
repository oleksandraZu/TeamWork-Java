package ua.bukovyna.airport.visitor;

/** Element: об'єкт інфраструктури, що приймає відвідувача. */
public interface AirportObject {
    String id();

    void accept(AirportVisitor visitor);
}
