package ua.bukovyna.airport.cabin;

import java.util.ArrayList;
import java.util.List;

/** ConcreteAggregate: схема салону (ряди -> місця + немовлята). Структура прихована від служб. */
public final class Cabin implements PassengerCollection {
    private final List<Row> rows = new ArrayList<>();

    public Cabin(int rowCount) {
        for (int i = 1; i <= rowCount; i++) {
            rows.add(new Row(i));
        }
    }

    public void addPassenger(int row, String seat, String name, String mark) {
        rows.get(row - 1).seat(new Passenger(name, row + seat, mark, false));
    }

    public void addInfant(int row, String name, String guardian) {
        rows.get(row - 1).addInfant(new Passenger(name, "без місця, ряд " + row + ", з " + guardian, null, true));
    }

    @Override
    public int size() {
        int count = 0;
        for (Row r : rows) {
            count += r.seated().size() + r.infants().size();
        }
        return count;
    }

    @Override
    public PassengerIterator boardingIterator() {
        return new BoardingIterator(this);
    }

    @Override
    public PassengerIterator cateringIterator() {
        return new CateringIterator(this);
    }

    @Override
    public PassengerIterator securityIterator() {
        return new SecurityIterator(this);
    }

    int rowCount() {
        return rows.size();
    }

    Row row(int index) {
        return rows.get(index);
    }
}
