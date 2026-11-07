package ua.bukovyna.airport.weather;

import ua.bukovyna.airport.common.Log;

/** Adapter: перетворює формат SkyMetService у формат WeatherProvider. */
public class SkyMetAdapter implements WeatherProvider {
    private final SkyMetService service;
    private final double latitude;
    private final double longitude;

    public SkyMetAdapter(SkyMetService service, double latitude, double longitude) {
        this.service = service;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public Weather getWeather() {
        SkyMetConditions conditions = service.readConditions(latitude, longitude);
        Weather weather = new Weather(
                conditions.temperatureKelvin() - 273.15,
                conditions.windSpeedKmh() / 3.6,
                conditions.visibilityKm() * 1000);
        Log.logf("SkyMetAdapter", "Перетворено: %.1f °C, %.1f м/с, %.1f м",
                weather.temperatureCelsius(), weather.windSpeedMs(), weather.visibilityMeters());
        return weather;
    }
}
