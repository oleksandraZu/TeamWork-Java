package ua.bukovyna.airport.tickets;

/** ConcreteDecorator: харчування. */
public class Meal extends TicketDecorator {
    public Meal(Ticket wrapped) {
        super(wrapped, "харчування", 250.0);
    }
}
