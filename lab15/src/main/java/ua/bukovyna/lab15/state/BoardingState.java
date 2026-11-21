package ua.bukovyna.lab15.state;

/** ConcreteState: «посадка». */
class BoardingState implements FlightState {

    @Override
    public String name() {
        return "посадка";
    }

    @Override
    public void depart(Flight flight) {
        flight.setState(new DepartedState());
    }

    @Override
    public void delay(Flight flight) {
        flight.setState(new DelayedState(this));
    }

    @Override
    public void cancel(Flight flight) {
        flight.setState(new CancelledState());
    }
}
