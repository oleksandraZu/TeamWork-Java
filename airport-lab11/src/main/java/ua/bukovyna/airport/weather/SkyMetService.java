package ua.bukovyna.airport.weather;

import ua.bukovyna.airport.common.Log;

/** Adaptee: другий умовний метеосервіс з іншим форматом і іншими методами. */
public class SkyMetService {
    public SkyMetConditions readConditions(double latitude, double longitude) {
        SkyMetConditions conditions = new SkyMetConditions(278.15, 72.0, 0.8);
        Log.logf("SkyMetService", "Координати %.2f, %.2f: %.2f K, %.1f км/год, %.1f км",
                latitude, longitude, conditions.temperatureKelvin(), conditions.windSpeedKmh(),
                conditions.visibilityKm());
        return conditions;
    }
}
