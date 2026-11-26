package ua.bukovyna.airport.cabin;

/** Пасажир; seat для немовляти — "без місця". mark — особлива позначка (може бути null). */
public record Passenger(String name, String seat, String mark, boolean infant) {
    public boolean hasMark() {
        return mark != null && !mark.isBlank();
    }

    @Override
    public String toString() {
        return name + " [" + seat + "]" + (hasMark() ? " позначка: " + mark : "");
    }
}
