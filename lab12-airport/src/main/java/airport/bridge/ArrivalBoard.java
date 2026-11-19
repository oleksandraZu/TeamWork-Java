package airport.bridge;

/** RefinedAbstraction: прильотне табло. */
public class ArrivalBoard extends FlightBoard {

    public ArrivalBoard(DisplayDevice device) {
        super(device);
    }

    @Override
    protected String title() {
        return "Прильоти";
    }

    @Override
    protected String formatLine(FlightInfo flight) {
        if (flight.departing()) {
            return null;
        }
        return flight.number() + " ← " + flight.city() + " " + flight.time() + ", " + flight.status();
    }
}
