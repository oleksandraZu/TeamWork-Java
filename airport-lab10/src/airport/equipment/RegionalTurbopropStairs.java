package ua.bukovyna.airport.equipment;

/** ConcreteProduct: BoardingStairs для класу «регіональний турбогвинтовий літак». */
public class RegionalTurbopropStairs implements BoardingStairs {

    @Override
    public String aircraftClass() {
        return "регіональний турбогвинтовий літак";
    }

    @Override
    public String description() {
        return "Трап, висота майданчика 2,6 м";
    }
}
