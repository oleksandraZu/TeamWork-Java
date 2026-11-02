package ua.bukovyna.airport.equipment;

/** ConcreteProduct: PushbackTug для класу «широкофюзеляжний літак». */
public class WidebodyTug implements PushbackTug {

    @Override
    public String aircraftClass() {
        return "широкофюзеляжний літак";
    }

    @Override
    public String description() {
        return "Важкий тягач, тягове зусилля 90 т";
    }
}
