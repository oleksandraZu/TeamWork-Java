package ua.bukovyna.airport.tickets;

/** ConcreteDecorator: вибір місця. */
public class SeatSelection extends TicketDecorator {
    public SeatSelection(Ticket wrapped) {
        super(wrapped, "вибір місця", 150.0);
    }
}
