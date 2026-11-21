package ua.bukovyna.lab15.state;

import java.util.ArrayList;
import java.util.List;
import ua.bukovyna.lab15.util.Log;

/** Context: рейс. Усі операції делегуються поточному стану. */
public class Flight {

    private final String number;
    private final List<String> passengers = new ArrayList<>();
    private String gate;
    private FlightState state = new PlannedState();

    public Flight(String number, String gate) {
        this.number = number;
        this.gate = gate;
        Log.log(source(), "Рейс створено, стан «" + state.name() + "», вихід " + gate);
    }

    public void openCheckIn() {
        state.openCheckIn(this);
    }

    public void registerPassenger(String passenger) {
        state.registerPassenger(this, passenger);
    }

    public void changeGate(String newGate) {
        state.changeGate(this, newGate);
    }

    public void startBoarding() {
        state.startBoarding(this);
    }

    public void depart() {
        state.depart(this);
    }

    public void land() {
        state.land(this);
    }

    public void delay() {
        state.delay(this);
    }

    public void resume() {
        state.resume(this);
    }

    public void cancel() {
        state.cancel(this);
    }

    String source() {
        return "Рейс " + number;
    }

    void setState(FlightState next) {
        Log.log(source(), "Перехід: «" + state.name() + "» → «" + next.name() + "»");
        state = next;
    }

    void addPassenger(String passenger) {
        passengers.add(passenger);
        Log.log(source(), "Пасажира " + passenger + " зареєстровано (всього: " + passengers.size() + ")");
    }

    void assignGate(String newGate) {
        Log.log(source(), "Вихід змінено: " + gate + " → " + newGate);
        gate = newGate;
    }
}
