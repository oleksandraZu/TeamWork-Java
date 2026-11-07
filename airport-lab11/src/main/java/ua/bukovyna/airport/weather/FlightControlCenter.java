package ua.bukovyna.airport.weather;

import ua.bukovyna.airport.common.Log;

/** Client: центр управління польотами, працює лише з WeatherProvider. */
public class FlightControlCenter {
    private static final double MAX_WIND_MS = 15.0;
    private static final double MIN_VISIBILITY_M = 1000.0;

    private final WeatherProvider weatherProvider;

    public FlightControlCenter(WeatherProvider weatherProvider) {
        this.weatherProvider = weatherProvider;
    }

    public boolean decideTakeoff(String flightNumber) {
        Weather weather = weatherProvider.getWeather();
        Log.logf("FlightControlCenter", "Метеодані: %.1f °C, %.1f м/с, %.1f м",
                weather.temperatureCelsius(), weather.windSpeedMs(), weather.visibilityMeters());
        boolean allowed = weather.windSpeedMs() <= MAX_WIND_MS
                && weather.visibilityMeters() >= MIN_VISIBILITY_M;
        Log.log("FlightControlCenter",
                "Зліт рейсу " + flightNumber + (allowed ? " дозволено" : " заборонено"));
        return allowed;
    }
}
