package ua.bukovyna.airport.observer;

import java.util.ArrayList;
import java.util.List;

import ua.bukovyna.airport.common.Logger;

/** ConcreteSubject: рейс не знає конкретних отримувачів. */
public class Flight implements FlightSubject {

    private final String number;
    private final List<FlightObserver> observers = new ArrayList<>();
    private FlightStatus status = FlightStatus.SCHEDULED;
    private String gate;
    private int delayMinutes;

    public Flight(String number, String gate) {
        this.number = number;
        this.gate = gate;
    }

    public String getNumber() {
        return number;
    }

    public FlightStatus getStatus() {
        return status;
    }

    public String getGate() {
        return gate;
    }

    public int getDelayMinutes() {
        return delayMinutes;
    }

    @Override
    public void subscribe(FlightObserver observer) {
        observers.add(observer);
        Logger.log(source(), observer.getName() + " підписався на зміни статусу");
    }

    @Override
    public void unsubscribe(FlightObserver observer) {
        observers.remove(observer);
        Logger.log(source(), observer.getName() + " відписався від змін статусу");
    }

    @Override
    public void notifyObservers() {
        for (FlightObserver observer : new ArrayList<>(observers)) {
            observer.update(this);
        }
    }

    public void delay(int minutes) {
        delayMinutes = minutes;
        changeStatus(FlightStatus.DELAYED, "затримка на " + minutes + " хв");
    }

    public void changeGate(String newGate) {
        gate = newGate;
        changeStatus(FlightStatus.GATE_CHANGED, "новий вихід " + newGate);
    }

    public void startBoarding() {
        changeStatus(FlightStatus.BOARDING, "вихід " + gate);
    }

    private void changeStatus(FlightStatus newStatus, String details) {
        status = newStatus;
        Logger.log(source(), "статус змінено: " + newStatus.getTitle() + " (" + details
                + "); сповіщаю підписників: " + observers.size());
        notifyObservers();
    }

    private String source() {
        return "Рейс " + number;
    }
}
