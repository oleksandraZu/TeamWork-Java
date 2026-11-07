package ua.bukovyna.airport.tickets;

/** ConcreteDecorator: додатковий багаж. */
public class ExtraBaggage extends TicketDecorator {
    public ExtraBaggage(Ticket wrapped) {
        super(wrapped, "додатковий багаж", 600.0);
    }
}
