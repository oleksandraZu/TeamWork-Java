package ua.bukovyna.lab15.strategy;

/** ConcreteStrategy: за кількістю пасажирів. */
public class PassengerTariff implements TariffStrategy {

    private static final long RATE_PER_PASSENGER = 150;

    @Override
    public String name() {
        return "за пасажирами";
    }

    @Override
    public long calculate(ServicedFlight flight) {
        return flight.passengers() * RATE_PER_PASSENGER;
    }
}
