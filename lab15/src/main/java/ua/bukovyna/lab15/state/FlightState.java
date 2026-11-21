package ua.bukovyna.lab15.state;

import ua.bukovyna.lab15.util.Log;

/** State: інтерфейс стану рейсу. Усі операції за замовчуванням недопустимі. */
public interface FlightState {

    String name();

    default void openCheckIn(Flight flight) {
        deny(flight, "відкрити реєстрацію");
    }

    default void registerPassenger(Flight flight, String passenger) {
        deny(flight, "зареєструвати пасажира");
    }

    default void changeGate(Flight flight, String gate) {
        deny(flight, "змінити вихід");
    }

    default void startBoarding(Flight flight) {
        deny(flight, "почати посадку");
    }

    default void depart(Flight flight) {
        deny(flight, "відправити рейс");
    }

    default void land(Flight flight) {
        deny(flight, "посадити рейс");
    }

    default void delay(Flight flight) {
        deny(flight, "затримати рейс");
    }

    default void resume(Flight flight) {
        deny(flight, "відновити рейс");
    }

    default void cancel(Flight flight) {
        deny(flight, "скасувати рейс");
    }

    private void deny(Flight flight, String operation) {
        Log.log(flight.source(), "ВІДМОВА: операція «" + operation + "» недопустима у стані «" + name() + "»");
    }
}
