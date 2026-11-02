package ua.bukovyna.airport.flighttask;

import ua.bukovyna.airport.common.EventLog;

import java.util.ArrayList;
import java.util.List;

/** ConcreteBuilder: накопичує частини завдання та перевіряє обов'язкові на завершальному кроці. */
public class ConcreteFlightTaskBuilder implements FlightTaskBuilder {

    private static final String SOURCE = "Конструктор польотного завдання";

    String flightNumber;
    String route;
    final List<String> alternateAirfields = new ArrayList<>();
    Integer fuelKg;
    String weatherBriefing;
    final List<String> pilotMessages = new ArrayList<>();
    String cargoData;
    String specialInstructions;

    @Override
    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
        EventLog.log(SOURCE, "Задано номер рейсу: " + flightNumber);
    }

    @Override
    public void setRoute(String route) {
        this.route = route;
        EventLog.log(SOURCE, "Задано маршрут: " + route);
    }

    @Override
    public void addAlternateAirfield(String airfield) {
        alternateAirfields.add(airfield);
        EventLog.log(SOURCE, "Додано запасний аеродром: " + airfield);
    }

    @Override
    public void setFuelCalculation(int fuelKg) {
        this.fuelKg = fuelKg;
        EventLog.log(SOURCE, "Задано розрахунок палива: " + fuelKg + " кг");
    }

    @Override
    public void setWeatherBriefing(String weatherBriefing) {
        this.weatherBriefing = weatherBriefing;
        EventLog.log(SOURCE, "Задано метеозведення: " + weatherBriefing);
    }

    @Override
    public void addPilotMessage(String message) {
        pilotMessages.add(message);
        EventLog.log(SOURCE, "Додано повідомлення для пілотів: " + message);
    }

    @Override
    public void setCargoData(String cargoData) {
        this.cargoData = cargoData;
        EventLog.log(SOURCE, "Задано дані про вантаж: " + cargoData);
    }

    @Override
    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
        EventLog.log(SOURCE, "Задано особливі вказівки: " + specialInstructions);
    }

    @Override
    public FlightTask build() {
        List<String> missing = new ArrayList<>();
        if (route == null) {
            missing.add("не задано маршрут");
        }
        if (fuelKg == null) {
            missing.add("не задано розрахунок палива");
        }
        if (weatherBriefing == null) {
            missing.add("не задано метеозведення");
        }
        if (!missing.isEmpty()) {
            throw new IllegalStateException(
                    "Неможливо сформувати польотне завдання: " + String.join(", ", missing));
        }
        FlightTask task = new FlightTask(this);
        EventLog.log(SOURCE, "Польотне завдання сформовано");
        return task;
    }
}
