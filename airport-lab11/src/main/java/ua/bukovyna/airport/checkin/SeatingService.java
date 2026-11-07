package ua.bukovyna.airport.checkin;

import ua.bukovyna.airport.common.Log;

/** Підсистема: розміщення в салоні. */
public class SeatingService {
    private int nextRow = 12;

    public String assignSeat(Passenger passenger, String flightNumber) {
        String seat = (nextRow++) + "A";
        Log.log("SeatingService",
                "Пасажиру " + passenger.name() + " на рейсі " + flightNumber + " призначено місце " + seat);
        return seat;
    }
}
