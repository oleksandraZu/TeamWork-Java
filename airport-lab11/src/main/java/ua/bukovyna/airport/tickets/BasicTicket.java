package ua.bukovyna.airport.tickets;

import ua.bukovyna.airport.common.Log;

/** ConcreteComponent: базовий квиток. */
public class BasicTicket implements Ticket {
    private final String flightNumber;
    private final String passengerName;
    private final double basePrice;

    public BasicTicket(String flightNumber, String passengerName, double basePrice) {
        this.flightNumber = flightNumber;
        this.passengerName = passengerName;
        this.basePrice = basePrice;
    }

    @Override
    public String getDescription() {
        return "Квиток " + flightNumber + " (" + passengerName + ")";
    }

    @Override
    public double getPrice() {
        Log.logf("BasicTicket", "Базова вартість %.2f грн", basePrice);
        return basePrice;
    }
}
