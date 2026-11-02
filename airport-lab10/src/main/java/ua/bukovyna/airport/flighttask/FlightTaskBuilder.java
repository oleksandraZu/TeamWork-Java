package ua.bukovyna.airport.flighttask;

/** Builder: кроки формування польотного завдання. */
public interface FlightTaskBuilder {

    void setFlightNumber(String flightNumber);

    void setRoute(String route);

    void addAlternateAirfield(String airfield);

    void setFuelCalculation(int fuelKg);

    void setWeatherBriefing(String weatherBriefing);

    void addPilotMessage(String message);

    void setCargoData(String cargoData);

    void setSpecialInstructions(String specialInstructions);

    /** Перевіряє обов'язкові частини й повертає готове завдання. */
    FlightTask build();
}
