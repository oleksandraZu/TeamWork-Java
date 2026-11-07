package ua.bukovyna.airport.tickets;

import ua.bukovyna.airport.common.Log;

/** Decorator: обгортка квитка, що додає послугу до опису й вартості. */
public abstract class TicketDecorator implements Ticket {
    private final Ticket wrapped;
    private final String serviceName;
    private final double surcharge;

    protected TicketDecorator(Ticket wrapped, String serviceName, double surcharge) {
        this.wrapped = wrapped;
        this.serviceName = serviceName;
        this.surcharge = surcharge;
    }

    @Override
    public String getDescription() {
        return wrapped.getDescription() + " + " + serviceName;
    }

    @Override
    public double getPrice() {
        double total = wrapped.getPrice() + surcharge;
        Log.logf(getClass().getSimpleName(), "%s: +%.2f грн, разом %.2f грн", serviceName, surcharge, total);
        return total;
    }
}
