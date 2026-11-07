package ua.bukovyna.airport.weather;

import ua.bukovyna.airport.common.Log;

/** Adaptee: сторонній метеосервіс, код якого змінити неможливо. */
public class ExternalWeatherService {
    public ExternalObservation getObservation(String stationCode) {
        ExternalObservation observation = new ExternalObservation(59.0, 10.0, 5.0);
        Log.logf("ExternalWeatherService", "Станція %s: %.1f °F, %.1f вузлів, %.1f миль",
                stationCode, observation.temperatureF(), observation.windSpeedKnots(),
                observation.visibilityMiles());
        return observation;
    }
}
