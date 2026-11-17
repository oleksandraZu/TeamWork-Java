package airport.bridge;

/** Дані про рейс, які показують табло. */
public record FlightInfo(String number, String city, String time, String gate, String status, boolean departing) {
}
