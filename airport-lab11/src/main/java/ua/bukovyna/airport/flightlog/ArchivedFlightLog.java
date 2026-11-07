package ua.bukovyna.airport.flightlog;

import ua.bukovyna.airport.common.Log;

/** RealSubject: журнал, що довго завантажується з архіву при створенні. */
public class ArchivedFlightLog implements FlightLog {
    private final String flightNumber;
    private final String telemetry;

    public ArchivedFlightLog(String flightNumber) {
        this.flightNumber = flightNumber;
        Log.log("ArchivedFlightLog", "Завантаження журналу рейсу " + flightNumber + " з архіву (довга операція)");
        this.telemetry = "телеметрія рейсу " + flightNumber + ": висота 10500 м, швидкість 840 км/год";
    }

    @Override
    public String getFlightNumber() {
        return flightNumber;
    }

    @Override
    public String getTelemetry(User user) {
        Log.log("ArchivedFlightLog", "Видано телеметрію рейсу " + flightNumber + " користувачу " + user.name());
        return telemetry;
    }
}
