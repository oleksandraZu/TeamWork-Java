package ua.bukovyna.airport.flightlog;

import ua.bukovyna.airport.common.Log;

/** Proxy: ледаче завантаження (virtual proxy) і перевірка ролі (protection proxy). */
public class FlightLogProxy implements FlightLog {
    private final String flightNumber;
    private ArchivedFlightLog realLog;

    public FlightLogProxy(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    @Override
    public String getFlightNumber() {
        return flightNumber;
    }

    @Override
    public String getTelemetry(User user) {
        Role role = user.role();
        if (role != Role.DISPATCHER && role != Role.FLIGHT_SAFETY) {
            Log.log("FlightLogProxy", "Доступ заборонено: " + user.name() + " (" + role.title() + ")");
            throw new AccessDeniedException("Недостатньо прав для перегляду бортового журналу");
        }
        if (realLog == null) {
            Log.log("FlightLogProxy", "Перше звернення до телеметрії рейсу " + flightNumber);
            realLog = new ArchivedFlightLog(flightNumber);
        } else {
            Log.log("FlightLogProxy", "Журнал рейсу " + flightNumber + " уже завантажено");
        }
        return realLog.getTelemetry(user);
    }
}
