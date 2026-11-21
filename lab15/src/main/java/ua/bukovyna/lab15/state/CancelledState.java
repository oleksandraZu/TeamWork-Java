package ua.bukovyna.lab15.state;

/** ConcreteState: «скасовано» (кінцевий). */
class CancelledState implements FlightState {

    @Override
    public String name() {
        return "скасовано";
    }
}
