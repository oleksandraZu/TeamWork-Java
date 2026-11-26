package ua.bukovyna.lab15.state;

class PlannedState implements FlightState {

    @Override
    public String name() {
        return "заплановано";
    }

    @Override
    public void openCheckIn(Flight flight) {
        flight.setState(new CheckInState());
    }

    @Override
    public void changeGate(Flight flight, String gate) {
        flight.assignGate(gate);
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
