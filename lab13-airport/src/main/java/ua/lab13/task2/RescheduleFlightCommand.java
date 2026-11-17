package ua.lab13.task2;

import ua.lab13.common.EventLogger;

import java.time.LocalTime;

public class RescheduleFlightCommand implements ScheduleCommand {
    private static final String SOURCE = "RescheduleFlightCommand";

    private final Schedule schedule;
    private final String flightNumber;
    private final LocalTime newTime;
    private LocalTime oldTime;

    public RescheduleFlightCommand(Schedule schedule, String flightNumber, LocalTime newTime) {
        this.schedule = schedule;
        this.flightNumber = flightNumber;
        this.newTime = newTime;
    }

    @Override
    public void execute() {
        EventLogger.log(SOURCE, "Виконується: " + description());
        oldTime = schedule.reschedule(flightNumber, newTime);
    }

    @Override
    public void undo() {
        if (oldTime == null) {
            throw new IllegalStateException("Команду ще не виконано");
        }
        EventLogger.log(SOURCE, "Скасовується: " + description());
        schedule.reschedule(flightNumber, oldTime);
    }

    @Override
    public String description() {
        return "Перенести рейс " + flightNumber + " на " + newTime;
    }
}
