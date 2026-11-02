package ua.bukovyna.airport.equipment;

/** ConcreteProduct: BoardingStairs для класу «широкофюзеляжний літак». */
public class WidebodyStairs implements BoardingStairs {

    @Override
    public String aircraftClass() {
        return "широкофюзеляжний літак";
    }

    @Override
    public String description() {
        return "Подвійний трап, висота майданчика 5,9 м";
    }
}
