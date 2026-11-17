package ua.lab13.task1;

public class SecurityScreeningHandler extends ControlHandler {

    @Override
    protected String check(Passenger passenger) {
        return passenger.hasProhibitedItems() ? "виявлено заборонені предмети" : null;
    }

    @Override
    public String title() {
        return "Огляд безпеки";
    }
}
