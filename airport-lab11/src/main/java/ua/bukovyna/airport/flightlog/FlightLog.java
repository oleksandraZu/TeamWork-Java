package ua.bukovyna.airport.flightlog;

/** Subject: бортовий журнал рейсу. */
public interface FlightLog {
    String getFlightNumber();

    String getTelemetry(User user);
}
