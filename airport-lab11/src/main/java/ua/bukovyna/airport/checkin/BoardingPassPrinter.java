package ua.bukovyna.airport.checkin;

import ua.bukovyna.airport.common.Log;

/** Підсистема: друк посадкового талона. */
public class BoardingPassPrinter {
    public void print(Passenger passenger, String flightNumber, String seat) {
        Log.log("BoardingPassPrinter",
                "Надруковано талон: " + passenger.name() + ", рейс " + flightNumber + ", місце " + seat);
    }
}
