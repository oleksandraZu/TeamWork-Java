package ua.bukovyna.airport.equipment;

/** ConcreteProduct: PushbackTug для класу «середньомагістральний лайнер». */
public class MediumHaulTug implements PushbackTug {

    @Override
    public String aircraftClass() {
        return "середньомагістральний лайнер";
    }

    @Override
    public String description() {
        return "Тягач, тягове зусилля 25 т";
    }
}
