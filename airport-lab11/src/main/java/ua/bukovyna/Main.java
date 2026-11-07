package ua.bukovyna.airport;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import ua.bukovyna.airport.checkin.BaggageService;
import ua.bukovyna.airport.checkin.BoardingPassPrinter;
import ua.bukovyna.airport.checkin.BorderServiceNotifier;
import ua.bukovyna.airport.checkin.CheckInFacade;
import ua.bukovyna.airport.checkin.CheckInPoint;
import ua.bukovyna.airport.checkin.DocumentCheckService;
import ua.bukovyna.airport.checkin.Passenger;
import ua.bukovyna.airport.checkin.SeatingService;
import ua.bukovyna.airport.common.Log;
import ua.bukovyna.airport.flightlog.FlightLog;
import ua.bukovyna.airport.flightlog.FlightLogProxy;
import ua.bukovyna.airport.flightlog.ReportModule;
import ua.bukovyna.airport.flightlog.Role;
import ua.bukovyna.airport.flightlog.User;
import ua.bukovyna.airport.tickets.BasicTicket;
import ua.bukovyna.airport.tickets.ExtraBaggage;
import ua.bukovyna.airport.tickets.Insurance;
import ua.bukovyna.airport.tickets.Meal;
import ua.bukovyna.airport.tickets.PriorityBoarding;
import ua.bukovyna.airport.tickets.SeatSelection;
import ua.bukovyna.airport.tickets.Ticket;
import ua.bukovyna.airport.weather.ExternalWeatherAdapter;
import ua.bukovyna.airport.weather.ExternalWeatherService;
import ua.bukovyna.airport.weather.FlightControlCenter;
import ua.bukovyna.airport.weather.SkyMetAdapter;
import ua.bukovyna.airport.weather.SkyMetService;

public class Main {
    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        task1Adapter();
        task2Decorator();
        task3Facade();
        task4Proxy();
    }

    private static void title(String text) {
        System.out.println();
        System.out.println("=== " + text + " ===");
    }

    private static void task1Adapter() {
        title("Завдання 1. Adapter: метеосервіс");

        System.out.println("-- Дані стороннього сервісу у його форматі");
        ExternalWeatherService service = new ExternalWeatherService();
        service.getObservation("UKLN");

        System.out.println("-- Центр управління отримує дані у своєму форматі");
        FlightControlCenter center = new FlightControlCenter(new ExternalWeatherAdapter(service, "UKLN"));
        center.decideTakeoff("PS-101");

        System.out.println("-- Інший метеосервіс, код центру управління той самий");
        center = new FlightControlCenter(new SkyMetAdapter(new SkyMetService(), 48.26, 25.98));
        center.decideTakeoff("PS-101");
    }

    private static void task2Decorator() {
        title("Завдання 2. Decorator: послуги до квитка");

        System.out.println("-- Базовий квиток");
        Ticket first = new BasicTicket("PS-101", "Іваненко І.", 2500.0);
        printTicket(first);

        System.out.println("-- Додано багаж, страхування, пріоритетну посадку");
        first = new PriorityBoarding(new Insurance(new ExtraBaggage(first)));
        printTicket(first);

        System.out.println("-- Другий квиток: харчування двічі, вибір місця");
        Ticket second = new SeatSelection(new Meal(new Meal(new BasicTicket("PS-205", "Коваль О.", 3200.0))));
        printTicket(second);
    }

    private static void printTicket(Ticket ticket) {
        double price = ticket.getPrice();
        Log.log("Main", "Опис: " + ticket.getDescription());
        Log.logf("Main", "Підсумкова вартість: %.2f грн", price);
    }

    private static void task3Facade() {
        title("Завдання 3. Facade: реєстрація пасажира");

        BoardingPassPrinter printer = new BoardingPassPrinter();
        CheckInFacade facade = new CheckInFacade(new DocumentCheckService(), new SeatingService(),
                new BaggageService(), printer, new BorderServiceNotifier());
        CheckInPoint counter = new CheckInPoint("Стійка реєстрації", facade);
        CheckInPoint kiosk = new CheckInPoint("Кіоск самореєстрації", facade);

        System.out.println("-- Реєстрація через стійку");
        Passenger first = new Passenger("Шевченко Т.", "AA123456", 20);
        String seat = counter.register(first, "PS-101");

        System.out.println("-- Реєстрація через кіоск");
        kiosk.register(new Passenger("Українка Л.", "BB654321", 0), "PS-101");

        System.out.println("-- Повторний друк талона без повної реєстрації");
        printer.print(first, "PS-101", seat);
    }

    private static void task4Proxy() {
        title("Завдання 4. Proxy: бортовий журнал");

        ReportModule reports = new ReportModule();
        List<FlightLog> logs = List.of(new FlightLogProxy("PS-101"), new FlightLogProxy("PS-102"),
                new FlightLogProxy("PS-103"), new FlightLogProxy("PS-104"), new FlightLogProxy("PS-105"));

        System.out.println("-- Список рейсів (без завантажень)");
        reports.showFlightList(logs);

        System.out.println("-- Диспетчер відкриває телеметрію PS-101 двічі");
        User dispatcher = new User("Мельник В.", Role.DISPATCHER);
        reports.showTelemetry(logs.get(0), dispatcher);
        reports.showTelemetry(logs.get(0), dispatcher);

        System.out.println("-- Касир намагається відкрити телеметрію PS-102");
        reports.showTelemetry(logs.get(1), new User("Бойко Н.", Role.CASHIER));
    }
}
