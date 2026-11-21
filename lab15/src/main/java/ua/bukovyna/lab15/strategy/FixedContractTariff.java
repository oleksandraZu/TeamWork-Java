package ua.bukovyna.lab15.strategy;

/** ConcreteStrategy: фіксований договірний тариф. */
public class FixedContractTariff implements TariffStrategy {

    private final long contractFee;

    public FixedContractTariff(long contractFee) {
        this.contractFee = contractFee;
    }

    @Override
    public String name() {
        return "фіксований договірний";
    }

    @Override
    public long calculate(ServicedFlight flight) {
        return contractFee;
    }
}
