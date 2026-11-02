package ua.bukovyna.airport.equipment;

/** ConcreteProduct: CargoLoader для класу «регіональний турбогвинтовий літак». */
public class RegionalTurbopropLoader implements CargoLoader {

    @Override
    public String aircraftClass() {
        return "регіональний турбогвинтовий літак";
    }

    @Override
    public String description() {
        return "Легкий навантажувач багажу";
    }
}
