package ua.bukovyna.airport.flighttask;

/** Director: описує в одному місці послідовності кроків для типових польотних завдань. */
public class FlightTaskDirector {

    public void constructRegularPassengerFlight(FlightTaskBuilder builder, String flightNumber, String route) {
        builder.setFlightNumber(flightNumber);
        builder.setRoute(route);
        builder.addAlternateAirfield("UKKK");
        builder.addAlternateAirfield("UKLL");
        builder.setFuelCalculation(5400);
        builder.setWeatherBriefing("METAR/TAF отримано, умови придатні для польоту");
        builder.addPilotMessage("Стандартний брифінг перед рейсом");
        builder.setCargoData("Пасажирський багаж, 1850 кг");
    }

    public void constructTrainingFlight(FlightTaskBuilder builder, String flightNumber, String route) {
        builder.setFlightNumber(flightNumber);
        builder.setRoute(route);
        builder.setFuelCalculation(320);
        builder.setWeatherBriefing("Умови придатні для навчальних польотів за ПВП");
    }
}
