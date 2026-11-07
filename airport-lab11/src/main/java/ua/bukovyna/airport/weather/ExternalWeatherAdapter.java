package ua.bukovyna.airport.weather;

import ua.bukovyna.airport.common.Log;

/** Adapter: перетворює формат ExternalWeatherService у формат WeatherProvider. */
public class ExternalWeatherAdapter implements WeatherProvider {
    private final ExternalWeatherService service;
    private final String stationCode;

    public ExternalWeatherAdapter(ExternalWeatherService service, String stationCode) {
        this.service = service;
        this.stationCode = stationCode;
    }

    @Override
    public Weather getWeather() {
        ExternalObservation observation = service.getObservation(stationCode);
        Weather weather = new Weather(
                (observation.temperatureF() - 32) * 5 / 9,
                observation.windSpeedKnots() * 0.514444,
                observation.visibilityMiles() * 1609.344);
        Log.logf("ExternalWeatherAdapter", "Перетворено: %.1f °C, %.1f м/с, %.1f м",
                weather.temperatureCelsius(), weather.windSpeedMs(), weather.visibilityMeters());
        return weather;
    }
}
