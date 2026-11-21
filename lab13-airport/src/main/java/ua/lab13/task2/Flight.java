package ua.lab13.task2;

import java.time.LocalTime;

public class
Flight {
    private final String number;
    private LocalTime departure;
    private String gate;
    private boolean cancelled;

    public Flight(String number, LocalTime departure, String gate) {
        this.number = number;
        this.departure = departure;
        this.gate = gate;
    }

    public String getNumber() {
        return number;
    }

    public LocalTime getDeparture() {
        return departure;
    }

    public void setDeparture(LocalTime departure) {
        this.departure = departure;
    }

    public String getGate() {
        return gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @Override
    public String toString() {
        return number + " | вильот " + departure + " | вихід " + gate + " | "
                + (cancelled ? "СКАСОВАНО" : "за розкладом");
    }
}
