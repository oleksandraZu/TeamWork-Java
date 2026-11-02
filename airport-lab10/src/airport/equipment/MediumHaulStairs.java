package ua.bukovyna.airport.equipment;

/** ConcreteProduct: BoardingStairs для класу «середньомагістральний лайнер». */
public class MediumHaulStairs implements BoardingStairs {

    @Override
    public String aircraftClass() {
        return "середньомагістральний лайнер";
    }

    @Override
    public String description() {
        return "Трап, висота майданчика 5,1 м";
    }
}
