package ua.lab13;

import ua.lab13.common.EventLogger;
import ua.lab13.task1.BoardingPassHandler;
import ua.lab13.task1.CarryOnWeightHandler;
import ua.lab13.task1.CustomsControlHandler;
import ua.lab13.task1.FlightType;
import ua.lab13.task1.Passenger;
import ua.lab13.task1.PassportControlHandler;
import ua.lab13.task1.PreflightControlService;
import ua.lab13.task1.SecurityScreeningHandler;
import ua.lab13.task2.CancelFlightCommand;
import ua.lab13.task2.ChangeGateCommand;
import ua.lab13.task2.DispatcherConsole;
import ua.lab13.task2.Flight;
import ua.lab13.task2.RescheduleFlightCommand;
import ua.lab13.task2.Schedule;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;

public class Main {
    private static final String SOURCE = "Раннер";

    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        situation1PreflightControl();
        situation2DispatcherScheduleOperations();
    }

    /** Завдання 1: Chain of Responsibility. */
    private static void situation1PreflightControl() {
        EventLogger.log(SOURCE, "===== Ситуація 1: передпольотний контроль пасажира =====");

        PreflightControlService service = new PreflightControlService();
        service.configureChain(FlightType.INTERNATIONAL,
                new BoardingPassHandler(),
                new PassportControlHandler(),
                new SecurityScreeningHandler(),
                new CarryOnWeightHandler(8.0),
                new CustomsControlHandler());
        service.configureChain(FlightType.DOMESTIC,
                new BoardingPassHandler(),
                new SecurityScreeningHandler(),
                new CarryOnWeightHandler(8.0));

        EventLogger.log(SOURCE, "--- Пасажир міжнародного рейсу проходить усі перевірки ---");
        service.process(new Passenger("Іваненко О.", "PS-410", FlightType.INTERNATIONAL,
                true, true, false, 7.5, true));

        EventLogger.log(SOURCE, "--- Пасажир з ручною поклажею 12 кг зупиняється на перевірці ваги ---");
        service.process(new Passenger("Петренко М.", "PS-410", FlightType.INTERNATIONAL,
                true, true, false, 12.0, true));

        EventLogger.log(SOURCE, "--- Пасажир внутрішнього рейсу: коротший ланцюг без паспортного контролю ---");
        service.process(new Passenger("Шевченко А.", "PS-120", FlightType.DOMESTIC,
                true, true, false, 6.0, true));
    }

    /** Завдання 2: Command. */
    private static void situation2DispatcherScheduleOperations() {
        EventLogger.log(SOURCE, "===== Ситуація 2: операції диспетчера з розкладом =====");

        Schedule schedule = new Schedule();
        schedule.addFlight(new Flight("PS-101", LocalTime.of(10, 0), "A1"));
        schedule.addFlight(new Flight("PS-202", LocalTime.of(11, 30), "B2"));
        schedule.addFlight(new Flight("PS-303", LocalTime.of(13, 0), "C3"));
        DispatcherConsole dispatcher = new DispatcherConsole("Диспетчер Коваль");

        schedule.print("Початковий розклад");

        EventLogger.log(SOURCE, "--- Перенести PS-101 на 15:30 і змінити вихід PS-202 ---");
        dispatcher.execute(new RescheduleFlightCommand(schedule, "PS-101", LocalTime.of(15, 30)));
        dispatcher.execute(new ChangeGateCommand(schedule, "PS-202", "B5"));
        schedule.print("Розклад після змін");

        EventLogger.log(SOURCE, "--- Скасувати дві останні дії ---");
        dispatcher.undoLast();
        dispatcher.undoLast();
        schedule.print("Розклад після скасування (як початковий)");

        EventLogger.log(SOURCE, "--- Підготувати 3 зміни в черзі і виконати пакетом ---");
        dispatcher.enqueue(new RescheduleFlightCommand(schedule, "PS-303", LocalTime.of(14, 15)));
        dispatcher.enqueue(new ChangeGateCommand(schedule, "PS-101", "A4"));
        dispatcher.enqueue(new CancelFlightCommand(schedule, "PS-202"));
        dispatcher.executePending();
        schedule.print("Розклад після пакету");

        dispatcher.getJournal().print();
    }
}
