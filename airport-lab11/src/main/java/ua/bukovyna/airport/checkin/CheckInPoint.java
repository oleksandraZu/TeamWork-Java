package ua.bukovyna.airport.checkin;

import ua.bukovyna.airport.common.Log;

/** Client: пункт реєстрації (стійка, кіоск, застосунок) працює лише з фасадом. */
public class CheckInPoint {
    private final String name;
    private final CheckInFacade facade;

    public CheckInPoint(String name, CheckInFacade facade) {
        this.name = name;
        this.facade = facade;
    }

    public String register(Passenger passenger, String flightNumber) {
        Log.log(name, "Запит на реєстрацію пасажира " + passenger.name());
        return facade.checkIn(passenger, flightNumber);
    }
}
