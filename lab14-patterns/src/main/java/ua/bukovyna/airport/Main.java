package ua.bukovyna.airport;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import ua.bukovyna.airport.common.Logger;
import ua.bukovyna.airport.mediator.BaggageLoadingService;
import ua.bukovyna.airport.mediator.BoardingService;
import ua.bukovyna.airport.mediator.CateringService;
import ua.bukovyna.airport.mediator.CleaningService;
import ua.bukovyna.airport.mediator.FuelingService;
import ua.bukovyna.airport.mediator.TowingService;
import ua.bukovyna.airport.mediator.TurnaroundCoordinator;
import ua.bukovyna.airport.observer.CateringSubscriber;
import ua.bukovyna.airport.observer.DepartureBoard;
import ua.bukovyna.airport.observer.Flight;
import ua.bukovyna.airport.observer.Passenger;
import ua.bukovyna.airport.observer.TaxiService;

public class Main {

    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        groundServicesScenario();
        flightStatusScenario();
    }

    /** Завдання 1: Mediator. */
    static void groundServicesScenario() {
        Logger.title("Завдання 1. Координація наземних служб (Mediator)");

        TurnaroundCoordinator coordinator = new TurnaroundCoordinator();
        BoardingService boarding = new BoardingService();
        CleaningService cleaning = new CleaningService();
        BaggageLoadingService baggage = new BaggageLoadingService();
        FuelingService fueling = new FuelingService();
        coordinator.register(boarding);
        coordinator.register(cleaning);
        coordinator.register(baggage);
        coordinator.register(fueling);
        coordinator.register(new CateringService());
        coordinator.register(new TowingService());

        cleaning.finishCleaning();
        baggage.finishLoading();

        boarding.startBoarding();
        fueling.requestFueling();
        boarding.finishBoarding();
    }

    /** Завдання 2: Observer. */
    static void flightStatusScenario() {
        Logger.title("Завдання 2. Сповіщення про зміну статусу рейсу (Observer)");

        Flight flight = new Flight("PS-101", "A1");
        DepartureBoard board = new DepartureBoard();
        Passenger passenger = new Passenger("Іван Петренко");
        CateringSubscriber catering = new CateringSubscriber();
        TaxiService taxi = new TaxiService();

        flight.subscribe(board);
        flight.subscribe(passenger);
        flight.subscribe(catering);
        flight.delay(45);

        flight.subscribe(taxi);
        flight.changeGate("B3");

        flight.unsubscribe(catering);
        flight.startBoarding();
    }
}
