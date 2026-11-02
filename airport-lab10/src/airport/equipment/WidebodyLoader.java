package ua.bukovyna.airport.equipment;

/** ConcreteProduct: CargoLoader для класу «широкофюзеляжний літак». */
public class WidebodyLoader implements CargoLoader {

    @Override
    public String aircraftClass() {
        return "широкофюзеляжний літак";
    }

    @Override
    public String description() {
        return "Навантажувач контейнерів ULD";
    }
}
