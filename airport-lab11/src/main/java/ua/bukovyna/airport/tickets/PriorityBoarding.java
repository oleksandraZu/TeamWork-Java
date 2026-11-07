package ua.bukovyna.airport.tickets;

/** ConcreteDecorator: пріоритетна посадка. */
public class PriorityBoarding extends TicketDecorator {
    public PriorityBoarding(Ticket wrapped) {
        super(wrapped, "пріоритетна посадка", 200.0);
    }
}
