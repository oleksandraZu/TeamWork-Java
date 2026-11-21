package ua.bukovyna.lab15.template;

/** ConcreteClass: пасажирський рейс. */
public class PassengerFlightHandling extends FlightHandling {

    @Override
    protected String typeName() {
        return "пасажирський";
    }

    @Override
    protected void unload() {
        stage("вивантажити");
        log("  висаджено пасажирів через телетрап, вивантажено багаж");
    }

    @Override
    protected void load() {
        stage("завантажити");
        log("  завантажено багаж, проведено посадку пасажирів");
    }
}
