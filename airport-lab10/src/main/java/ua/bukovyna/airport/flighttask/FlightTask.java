package ua.bukovyna.airport.flighttask;

import java.util.List;

/** Product: польотне завдання екіпажу. Незмінний об'єкт, створюється лише білдером. */
public final class FlightTask {

    private final String flightNumber;
    private final String route;
    private final List<String> alternateAirfields;
    private final int fuelKg;
    private final String weatherBriefing;
    private final List<String> pilotMessages;
    private final String cargoData;
    private final String specialInstructions;

    FlightTask(ConcreteFlightTaskBuilder builder) {
        this.flightNumber = builder.flightNumber;
        this.route = builder.route;
        this.alternateAirfields = List.copyOf(builder.alternateAirfields);
        this.fuelKg = builder.fuelKg;
        this.weatherBriefing = builder.weatherBriefing;
        this.pilotMessages = List.copyOf(builder.pilotMessages);
        this.cargoData = builder.cargoData;
        this.specialInstructions = builder.specialInstructions;
    }

    @Override
    public String toString() {
        return "  Польотне завдання " + (flightNumber == null ? "—" : flightNumber) + "\n"
                + "    Маршрут:                   " + route + "\n"
                + "    Запасні аеродроми:         " + listOrDash(alternateAirfields) + "\n"
                + "    Розрахунок палива:         " + fuelKg + " кг\n"
                + "    Метеозведення:             " + weatherBriefing + "\n"
                + "    Повідомлення для пілотів:  " + listOrDash(pilotMessages) + "\n"
                + "    Дані про вантаж:           " + (cargoData == null ? "—" : cargoData) + "\n"
                + "    Особливі вказівки:         " + (specialInstructions == null ? "—" : specialInstructions);
    }

    private static String listOrDash(List<String> items) {
        return items.isEmpty() ? "—" : String.join("; ", items);
    }
}
