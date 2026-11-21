package ua.bukovyna.lab15.strategy;

import ua.bukovyna.lab15.util.Log;

/** Context: авіакомпанія зі схемою розрахунку, яку можна змінити під час виконання. */
public class Airline {

    private static final String SOURCE = "Бухгалтерія";

    private final String name;
    private TariffStrategy tariff;

    public Airline(String name, TariffStrategy tariff) {
        this.name = name;
        this.tariff = tariff;
        Log.log(SOURCE, "Авіакомпанії «" + name + "» призначено схему «" + tariff.name() + "»");
    }

    public void setTariff(TariffStrategy newTariff) {
        Log.log(SOURCE, "Авіакомпанії «" + name + "» змінено схему: «" + tariff.name() + "» → «" + newTariff.name() + "»");
        tariff = newTariff;
    }

    public void invoice(ServicedFlight flight) {
        long amount = tariff.calculate(flight);
        Log.log(SOURCE, "Рахунок «" + name + "» за рейс " + flight.number() + " ("
                + flight.weightTons() + " т, " + flight.passengers() + " пас.) за схемою «"
                + tariff.name() + "»: " + amount + " грн");
    }
}
