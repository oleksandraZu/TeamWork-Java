package ua.lab13.task2;

import ua.lab13.common.EventLogger;

public class CancelFlightCommand implements ScheduleCommand {
    private static final String SOURCE = "CancelFlightCommand";

    private final Schedule schedule;
    private final String flightNumber;
    private boolean wasCancelled;
    private boolean executed;

    public CancelFlightCommand(Schedule schedule, String flightNumber) {
        this.schedule = schedule;
        this.flightNumber = flightNumber;
    }

    @Override
    public void execute() {
        EventLogger.log(SOURCE, "Виконується: " + description());
        wasCancelled = schedule.setCancelled(flightNumber, true);
        executed = true;
    }

    @Override
    public void undo() {
        if (!executed) {
            throw new IllegalStateException("Команду ще не виконано");
        }
        EventLogger.log(SOURCE, "Скасовується: " + description());
        schedule.setCancelled(flightNumber, wasCancelled);
    }

    @Override
    public String description() {
        return "Скасувати рейс " + flightNumber;
    }
}
