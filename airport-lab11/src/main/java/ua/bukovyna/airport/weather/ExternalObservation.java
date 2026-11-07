package ua.bukovyna.airport.weather;

/** Дані міжнародного метеосервісу: °F, вузли, милі. */
public record ExternalObservation(double temperatureF, double windSpeedKnots, double visibilityMiles) {
}
