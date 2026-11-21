package ua.bukovyna.lab15.strategy;

/** ConcreteStrategy: пільговий тариф для нових маршрутів. */
public class PreferentialTariff implements TariffStrategy {

    private static final long RATE_PER_TON = 720;

    @Override
    public String name() {
        return "пільгова";
    }

    @Override
    public long calculate(ServicedFlight flight) {
        return flight.weightTons() * RATE_PER_TON;
    }
}
