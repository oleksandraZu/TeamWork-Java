package ua.bukovyna.airport.weather;

/** Метеодані у форматі центру управління польотами. */
public record Weather(double temperatureCelsius, double windSpeedMs, double visibilityMeters) {
}
