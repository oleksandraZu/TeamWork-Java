package ua.lab13.task2;

/** Command: інтерфейс команди. */
public interface ScheduleCommand {

    void execute();

    void undo();

    String description();
}
