package ua.bukovyna.airport.tickets;

/** ConcreteDecorator: страхування. */
public class Insurance extends TicketDecorator {
    public Insurance(Ticket wrapped) {
        super(wrapped, "страхування", 300.0);
    }
}
