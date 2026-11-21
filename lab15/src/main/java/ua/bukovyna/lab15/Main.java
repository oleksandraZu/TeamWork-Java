package ua.bukovyna.lab15;

import ua.bukovyna.lab15.state.Flight;
import ua.bukovyna.lab15.strategy.Airline;
import ua.bukovyna.lab15.strategy.FixedContractTariff;
import ua.bukovyna.lab15.strategy.NightTariff;
import ua.bukovyna.lab15.strategy.PassengerTariff;
import ua.bukovyna.lab15.strategy.PreferentialTariff;
import ua.bukovyna.lab15.strategy.ServicedFlight;
import ua.bukovyna.lab15.strategy.WeightTariff;
import ua.bukovyna.lab15.template.CargoFlightHandling;
import ua.bukovyna.lab15.template.FlightHandling;
import ua.bukovyna.lab15.template.PassengerFlightHandling;
import ua.bukovyna.lab15.template.SanitaryFlightHandling;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== Завдання 1. Життєвий цикл рейсу (State) ===");
        flightLifecycle();
        System.out.println();
        System.out.println("=== Завдання 2. Обслуговування рейсів різних типів (Template Method) ===");
        flightHandling();
        System.out.println();
        System.out.println("=== Завдання 3. Вартість наземного обслуговування (Strategy) ===");
        groundServiceTariffs();
    }

    private static void flightLifecycle() {
        Flight flight = new Flight("BK101", "A1");
        flight.openCheckIn();
        flight.registerPassenger("Коваль О.");
        flight.changeGate("A3");
        flight.startBoarding();
        flight.changeGate("A5");
        flight.depart();
        flight.registerPassenger("Мельник І.");
        flight.land();

        Flight delayed = new Flight("BK202", "B2");
        delayed.openCheckIn();
        delayed.delay();
        delayed.registerPassenger("Шевчук Н.");
        delayed.resume();
        delayed.registerPassenger("Шевчук Н.");
    }

    private static void flightHandling() {
        FlightHandling passenger = new PassengerFlightHandling();
        passenger.handle("BK101");
        new CargoFlightHandling().handle("BK501");
        new SanitaryFlightHandling().handle("BK911");
    }

    private static void groundServiceTariffs() {
        ServicedFlight flight = new ServicedFlight("BK101", 60, 180);

        Airline carpathian = new Airline("Карпати-Ейр", new WeightTariff());
        Airline prut = new Airline("Прут-Лайн", new PassengerTariff());
        Airline cheremosh = new Airline("Черемош-Авіа", new FixedContractTariff(45_000));

        carpathian.invoice(flight);
        prut.invoice(flight);
        cheremosh.invoice(flight);

        carpathian.setTariff(new PreferentialTariff());
        carpathian.invoice(flight);

        carpathian.setTariff(new NightTariff());
        carpathian.invoice(flight);
    }
}
