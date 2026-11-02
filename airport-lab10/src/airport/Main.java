package ua.bukovyna.airport;

import ua.bukovyna.airport.common.EventLog;
import ua.bukovyna.airport.equipment.GroundHandlingDispatcher;
import ua.bukovyna.airport.equipment.MediumHaulEquipmentFactory;
import ua.bukovyna.airport.equipment.RegionalTurbopropEquipmentFactory;
import ua.bukovyna.airport.equipment.WidebodyEquipmentFactory;
import ua.bukovyna.airport.flighttask.ConcreteFlightTaskBuilder;
import ua.bukovyna.airport.flighttask.FlightTask;
import ua.bukovyna.airport.flighttask.FlightTaskBuilder;
import ua.bukovyna.airport.flighttask.FlightTaskDirector;
import ua.bukovyna.airport.notification.DisplayBoardChannel;
import ua.bukovyna.airport.notification.EmailChannel;
import ua.bukovyna.airport.notification.FlightChange;
import ua.bukovyna.airport.notification.MobileAppChannel;
import ua.bukovyna.airport.notification.NotificationChannel;
import ua.bukovyna.airport.notification.SmsChannel;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/** Раннер: по одному демонстраційному сценарію на кожну проблемну ситуацію. */
public class Main {

    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

        flightTaskScenario();
        groundEquipmentScenario();
        notificationScenario();
    }

    /** Ситуація 1 (Builder): формування польотного завдання. */
    private static void flightTaskScenario() {
        System.out.println("=== Ситуація 1. Формування польотного завдання (Builder) ===");

        EventLog.log("Диспетчерська", "Формування повного польотного завдання для рейсу PS-101");
        FlightTaskBuilder builder = new ConcreteFlightTaskBuilder();
        builder.setFlightNumber("PS-101");
        builder.setRoute("UKLN (Чернівці) → UKBB (Бориспіль)");
        builder.addAlternateAirfield("UKKK");
        builder.addAlternateAirfield("UKLL");
        builder.setFuelCalculation(5400);
        builder.setWeatherBriefing("METAR/TAF отримано, умови придатні для польоту");
        builder.addPilotMessage("Очікується турбулентність на ешелоні FL330");
        builder.setCargoData("Пасажирський багаж, 1850 кг");
        builder.setSpecialInstructions("Виліт із гейта 2, буксирування не потрібне");
        FlightTask fullTask = builder.build();
        System.out.println(fullTask);

        FlightTaskDirector director = new FlightTaskDirector();

        EventLog.log("Диспетчерська", "Формування типового завдання регулярного пасажирського рейсу PS-102");
        FlightTaskBuilder regularBuilder = new ConcreteFlightTaskBuilder();
        director.constructRegularPassengerFlight(regularBuilder, "PS-102", "UKLN (Чернівці) → UKLL (Львів)");
        System.out.println(regularBuilder.build());

        EventLog.log("Диспетчерська", "Формування типового завдання для навчального польоту");
        FlightTaskBuilder trainingBuilder = new ConcreteFlightTaskBuilder();
        director.constructTrainingFlight(trainingBuilder, "TR-01", "UKLN → зона пілотажу → UKLN");
        System.out.println(trainingBuilder.build());

        EventLog.log("Диспетчерська", "Спроба завершити формування завдання без розрахунку палива");
        FlightTaskBuilder incompleteBuilder = new ConcreteFlightTaskBuilder();
        incompleteBuilder.setFlightNumber("PS-103");
        incompleteBuilder.setRoute("UKLN (Чернівці) → UKKK (Київ)");
        incompleteBuilder.setWeatherBriefing("METAR/TAF отримано, умови придатні для польоту");
        try {
            incompleteBuilder.build();
        } catch (IllegalStateException e) {
            EventLog.log("Диспетчерська", e.getMessage());
        }
        System.out.println();
    }

    /** Ситуація 2 (Abstract Factory): комплекти наземного обладнання. */
    private static void groundEquipmentScenario() {
        System.out.println("=== Ситуація 2. Комплекти наземного обладнання (Abstract Factory) ===");

        GroundHandlingDispatcher dispatcher = new GroundHandlingDispatcher();
        dispatcher.serviceFlight("PS-201", "A320", new MediumHaulEquipmentFactory());
        dispatcher.serviceFlight("PS-202", "ATR-72", new RegionalTurbopropEquipmentFactory());

        EventLog.log("Аеропорт", "Додано клас «широкофюзеляжний літак» новими класами; код диспетчерської не змінено");
        dispatcher.serviceFlight("PS-203", "Boeing 777", new WidebodyEquipmentFactory());
        System.out.println();
    }

    /** Ситуація 3 (Factory Method): сповіщення пасажирів про зміну рейсу. */
    private static void notificationScenario() {
        System.out.println("=== Ситуація 3. Сповіщення пасажирів про зміну рейсу (Factory Method) ===");

        List<NotificationChannel> channels = new ArrayList<>(
                List.of(new SmsChannel(), new EmailChannel(), new DisplayBoardChannel()));

        FlightChange delay = new FlightChange("PS-101", "затримується на 40 хвилин");
        EventLog.log("Аеропорт", "Рейс PS-101 затримується на 40 хвилин: сповіщення через " + channels.size() + " канали");
        for (NotificationChannel channel : channels) {
            channel.notifyPassengers(delay);
        }

        EventLog.log("Аеропорт", "Додано канал «мобільний застосунок» (новий клас каналу і новий клас повідомлення)");
        channels.add(new MobileAppChannel());

        FlightChange gateChange = new FlightChange("PS-101", "змінено вихід на посадку з B2 на A4");
        EventLog.log("Аеропорт", "Зміна виходу на посадку для PS-101: сповіщення через " + channels.size() + " канали");
        for (NotificationChannel channel : channels) {
            channel.notifyPassengers(gateChange);
        }
    }
}
