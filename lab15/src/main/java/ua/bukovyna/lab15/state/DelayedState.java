package ua.bukovyna.lab15.state;

/** ConcreteState: «затримано». Пам'ятає стан, з якого рейс затримали, щоб відновити його. */
class DelayedState implements FlightState {

    private final FlightState previous;

    DelayedState(FlightState previous) {
        this.previous = previous;
    }

    @Override
    public String name() {
        return "затримано";
    }

    @Override
    public void changeGate(Flight flight, String gate) {
        previous.changeGate(flight, gate);
    }

    @Override
    public void resume(Flight flight) {
        flight.setState(previous);
    }

    @Override
    public void cancel(Flight flight) {
        flight.setState(new CancelledState());
    }
}
