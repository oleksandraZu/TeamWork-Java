package ua.bukovyna.airport.flightlog;

import java.util.List;

import ua.bukovyna.airport.common.Log;

/** Client: модуль звітів працює лише з інтерфейсом FlightLog. */
public class ReportModule {
    public void showFlightList(List<FlightLog> logs) {
        for (FlightLog log : logs) {
            Log.log("ReportModule", "Рейс " + log.getFlightNumber());
        }
    }

    public void showTelemetry(FlightLog log, User user) {
        Log.log("ReportModule", user.name() + " (" + user.role().title() + ") відкриває телеметрію рейсу "
                + log.getFlightNumber());
        try {
            Log.log("ReportModule", log.getTelemetry(user));
        } catch (AccessDeniedException e) {
            Log.log("ReportModule", "Відмова: " + e.getMessage());
        }
    }
}
