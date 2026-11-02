package ua.bukovyna.airport.equipment;

/** ConcreteProduct: CargoLoader для класу «середньомагістральний лайнер». */
public class MediumHaulLoader implements CargoLoader {

    @Override
    public String aircraftClass() {
        return "середньомагістральний лайнер";
    }

    @Override
    public String description() {
        return "Стрічковий навантажувач багажу";
    }
}
