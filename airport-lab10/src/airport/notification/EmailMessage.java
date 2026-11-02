package ua.bukovyna.airport.notification;

/** ConcreteProduct: електронний лист з темою та розгорнутим текстом. */
public class EmailMessage implements Message {

    private final String subject;
    private final String body;

    public EmailMessage(FlightChange change) {
        this.subject = "Зміна за рейсом " + change.flightNumber();
        this.body = "Шановний пасажире! За рейсом " + change.flightNumber() + ": " + change.description()
                + ". З повагою, аеропорт «Буковина».";
    }

    @Override
    public String render() {
        return "Лист [тема: " + subject + "] [текст: " + body + "]";
    }
}
