package ua.bukovyna.lab15.strategy;

/** ConcreteStrategy: нічний тариф (додано одним класом). */
public class NightTariff implements TariffStrategy {

    private static final long RATE_PER_TON = 1800;

    @Override
    public String name() {
        return "нічний тариф";
    }

    @Override
    public long calculate(ServicedFlight flight) {
        return flight.weightTons() * RATE_PER_TON;
    }
}
