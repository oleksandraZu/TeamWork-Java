package ua.bukovyna.lab15.strategy;

/** Strategy: схема розрахунку вартості наземного обслуговування. */
public interface TariffStrategy {

    String name();

    /** Вартість у гривнях. */
    long calculate(ServicedFlight flight);
}
