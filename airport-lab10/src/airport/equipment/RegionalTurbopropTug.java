package ua.bukovyna.airport.equipment;

/** ConcreteProduct: PushbackTug для класу «регіональний турбогвинтовий літак». */
public class RegionalTurbopropTug implements PushbackTug {

    @Override
    public String aircraftClass() {
        return "регіональний турбогвинтовий літак";
    }

    @Override
    public String description() {
        return "Тягач, тягове зусилля 8 т";
    }
}
