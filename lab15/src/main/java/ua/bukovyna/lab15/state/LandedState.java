package ua.bukovyna.lab15.state;

/** ConcreteState: «приземлився» (кінцевий). */
class LandedState implements FlightState {

    @Override
    public String name() {
        return "приземлився";
    }
}
