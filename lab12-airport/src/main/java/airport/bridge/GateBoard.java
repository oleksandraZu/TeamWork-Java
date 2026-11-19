package airport.bridge;

/** RefinedAbstraction: табло біля виходу на посадку. */
public class GateBoard extends FlightBoard {

    private final String gate;

    public GateBoard(DisplayDevice device, String gate) {
        super(device);
        this.gate = gate;
    }

    @Override
    protected String title() {
        return "Вихід " + gate;
    }

    @Override
    protected String formatLine(FlightInfo flight) {
        if (!flight.departing() || !gate.equals(flight.gate())) {
            return null;
        }
        return flight.number() + " " + flight.city() + " " + flight.time() + ", " + flight.status();
    }
}
