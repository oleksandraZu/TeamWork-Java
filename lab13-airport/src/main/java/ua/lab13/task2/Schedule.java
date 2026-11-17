package ua.lab13.task2;

import ua.lab13.common.EventLogger;

import java.time.LocalTime;
import java.util.LinkedHashMap;
import java.util.Map;

/** Command: отримувач (Receiver) — знає, як змінювати розклад. */
public class Schedule {
    private static final String SOURCE = "Розклад";

    private final Map<String, Flight> flights = new LinkedHashMap<>();

    public void addFlight(Flight flight) {
        flights.put(flight.getNumber(), flight);
    }

    private Flight find(String number) {
        Flight flight = flights.get(number);
        if (flight == null) {
            throw new IllegalArgumentException("Рейс не знайдено: " + number);
        }
        return flight;
    }

    /** @return попередній час вильоту */
    public LocalTime reschedule(String number, LocalTime newTime) {
        Flight flight = find(number);
        LocalTime old = flight.getDeparture();
        flight.setDeparture(newTime);
        EventLogger.log(SOURCE, "Рейс " + number + ": час вильоту " + old + " -> " + newTime);
        return old;
    }

    /** @return попередній вихід на посадку */
    public String changeGate(String number, String newGate) {
        Flight flight = find(number);
        String old = flight.getGate();
        flight.setGate(newGate);
        EventLogger.log(SOURCE, "Рейс " + number + ": вихід " + old + " -> " + newGate);
        return old;
    }

    /** @return попередній стан скасування */
    public boolean setCancelled(String number, boolean cancelled) {
        Flight flight = find(number);
        boolean old = flight.isCancelled();
        flight.setCancelled(cancelled);
        EventLogger.log(SOURCE, "Рейс " + number + ": " + (cancelled ? "скасовано" : "відновлено"));
        return old;
    }

    public void print(String title) {
        EventLogger.log(SOURCE, title + ":");
        for (Flight flight : flights.values()) {
            EventLogger.log(SOURCE, "  " + flight);
        }
    }
}
