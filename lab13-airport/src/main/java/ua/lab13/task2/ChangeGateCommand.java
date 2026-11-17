package ua.lab13.task2;

import ua.lab13.common.EventLogger;

public class ChangeGateCommand implements ScheduleCommand {
    private static final String SOURCE = "ChangeGateCommand";

    private final Schedule schedule;
    private final String flightNumber;
    private final String newGate;
    private String oldGate;

    public ChangeGateCommand(Schedule schedule, String flightNumber, String newGate) {
        this.schedule = schedule;
        this.flightNumber = flightNumber;
        this.newGate = newGate;
    }

    @Override
    public void execute() {
        EventLogger.log(SOURCE, "Виконується: " + description());
        oldGate = schedule.changeGate(flightNumber, newGate);
    }

    @Override
    public void undo() {
        if (oldGate == null) {
            throw new IllegalStateException("Команду ще не виконано");
        }
        EventLogger.log(SOURCE, "Скасовується: " + description());
        schedule.changeGate(flightNumber, oldGate);
    }

    @Override
    public String description() {
        return "Змінити вихід рейсу " + flightNumber + " на " + newGate;
    }
}
