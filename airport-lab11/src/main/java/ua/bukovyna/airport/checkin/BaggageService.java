package ua.bukovyna.airport.checkin;

import ua.bukovyna.airport.common.Log;

/** Підсистема: прийом багажу. */
public class BaggageService {
    public void acceptBaggage(Passenger passenger) {
        Log.log("BaggageService",
                "Прийнято багаж пасажира " + passenger.name() + ": " + passenger.baggageKg() + " кг");
    }
}
