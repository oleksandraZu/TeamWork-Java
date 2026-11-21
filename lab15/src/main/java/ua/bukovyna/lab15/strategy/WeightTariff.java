package ua.bukovyna.lab15.strategy;

/** ConcreteStrategy: за вагою літака. */
public class WeightTariff implements TariffStrategy {

    private static final long RATE_PER_TON = 1200;

    @Override
    public String name() {
        return "за вагою";
    }

    @Override
    public long calculate(ServicedFlight flight) {
        return flight.weightTons() * RATE_PER_TON;
    }
}
