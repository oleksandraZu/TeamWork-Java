package ua.bukovyna.lab15.state;

/** ConcreteState: «вилетів». */
class DepartedState implements FlightState {

    @Override
    public String name() {
        return "вилетів";
    }

    @Override
    public void land(Flight flight) {
        flight.setState(new LandedState());
    }
}
