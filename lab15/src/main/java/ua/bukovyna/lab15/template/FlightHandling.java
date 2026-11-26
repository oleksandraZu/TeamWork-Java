package ua.bukovyna.lab15.template;

import ua.bukovyna.lab15.util.Log;

public abstract class FlightHandling {

    private String flightNumber;

    public final void handle(String flightNumber) {
        this.flightNumber = flightNumber;
        log("Початок обслуговування, тип: " + typeName());
        acceptAircraft();
        unload();
        cleanCabin();
        prepareForDeparture();
        load();
        release();
        log("Обслуговування завершено");
    }

    protected abstract String typeName();

    protected abstract void unload();

    protected abstract void load();

    protected void cleanCabin() {
        stage("прибирання салону");
        log("  стандартне прибирання салону");
    }

    protected final void stage(String name) {
        log("Етап «" + name + "»");
    }

    protected final void log(String event) {
        Log.log("Обслуговування " + flightNumber, event);
    }

    private void acceptAircraft() {
        stage("прийняти борт");
        log("  борт прийнято на стоянку, підключено наземне живлення");
    }

    private void prepareForDeparture() {
        stage("підготувати до вильоту");
        log("  перевірено документи та заправлення, борт готовий до завантаження");
    }

    private void release() {
        stage("випустити борт");
        log("  отримано дозвіл диспетчера, борт випущено зі стоянки");
    }
}
