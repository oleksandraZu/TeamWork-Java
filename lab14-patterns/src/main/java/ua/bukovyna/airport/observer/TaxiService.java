package ua.bukovyna.airport.observer;

import ua.bukovyna.airport.common.Logger;

public class TaxiService implements FlightObserver {

    @Override
    public String getName() {
        return "Таксі-сервіс";
    }

    @Override
    public void update(Flight flight) {
        switch (flight.getStatus()) {
            case DELAYED -> Logger.log(getName(), "відкладає подачу авто на " + flight.getDelayMinutes() + " хв");
            case GATE_CHANGED -> Logger.log(getName(), "оновлює точку зустрічі: вихід " + flight.getGate());
            case BOARDING -> Logger.log(getName(), "готує авто до прибуття пасажирів");
            default -> Logger.log(getName(), "змін немає");
        }
    }
}
