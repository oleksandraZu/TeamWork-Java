package ua.bukovyna.airport.cabin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/** Ряд салону: місця за літерами + окремий список немовлят без місця. Невидимий поза пакетом. */
final class Row {
    private final int number;
    private final Map<String, Passenger> seats = new TreeMap<>();
    private final List<Passenger> infants = new ArrayList<>();

    Row(int number) {
        this.number = number;
    }

    int number() {
        return number;
    }

    void seat(Passenger passenger) {
        seats.put(passenger.seat(), passenger);
    }

    void addInfant(Passenger infant) {
        infants.add(infant);
    }

    List<Passenger> seated() {
        return new ArrayList<>(seats.values());
    }

    List<Passenger> infants() {
        return new ArrayList<>(infants);
    }
}
