package ua.bukovyna.airport.checkin;

import ua.bukovyna.airport.common.Log;

/** Підсистема: повідомлення прикордонної служби. */
public class BorderServiceNotifier {
    public void notifyBorderService(Passenger passenger, String flightNumber) {
        Log.log("BorderServiceNotifier",
                "Прикордонну службу повідомлено про пасажира " + passenger.name() + " (рейс " + flightNumber + ")");
    }
}
