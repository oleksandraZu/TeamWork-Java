package ua.bukovyna.airport.notification;

/** ConcreteProduct: рядок для табло великими літерами. */
public class DisplayBoardMessage implements Message {

    private final String line;

    public DisplayBoardMessage(FlightChange change) {
        this.line = change.flightNumber() + " " + change.description().toUpperCase();
    }

    @Override
    public String render() {
        return "ТАБЛО | " + line + " |";
    }
}
