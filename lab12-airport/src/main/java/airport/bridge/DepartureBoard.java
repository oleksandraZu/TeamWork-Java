package airport.bridge;

/** RefinedAbstraction: вилітне табло. */
public class DepartureBoard extends FlightBoard {

    public DepartureBoard(DisplayDevice device) {
        super(device);
    }

    @Override
    protected String title() {
        return "Вильоти";
    }

    @Override
    protected String formatLine(FlightInfo flight) {
        if (!flight.departing()) {
            return null;
        }
        return flight.number() + " → " + flight.city() + " " + flight.time()
                + ", вихід " + flight.gate() + ", " + flight.status();
    }
}
