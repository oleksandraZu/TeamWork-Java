package ua.bukovyna.airport.notification;

/** ConcreteProduct: короткий текст SMS. */
public class SmsMessage implements Message {

    private final String text;

    public SmsMessage(FlightChange change) {
        this.text = "Рейс " + change.flightNumber() + ": " + change.description() + ".";
    }

    @Override
    public String render() {
        return "SMS «" + text + "»";
    }
}
