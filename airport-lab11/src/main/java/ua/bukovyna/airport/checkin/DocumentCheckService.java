package ua.bukovyna.airport.checkin;

import ua.bukovyna.airport.common.Log;

/** Підсистема: перевірка документів. */
public class DocumentCheckService {
    public void verify(Passenger passenger) {
        Log.log("DocumentCheckService",
                "Документи пасажира " + passenger.name() + " (" + passenger.passportNumber() + ") перевірено");
    }
}
