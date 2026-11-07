package ua.bukovyna.airport.weather;

/** Дані другого метеосервісу: кельвіни, км/год, км. */
public record SkyMetConditions(double temperatureKelvin, double windSpeedKmh, double visibilityKm) {
}
