package ua.lab13.task2;

import ua.lab13.common.EventLogger;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

/** Command: ініціатор (Invoker) — виконує, скасовує, накопичує команди в черзі та веде журнал. */
public class DispatcherConsole {
    private static final String SOURCE = "Диспетчер";

    private final String dispatcherName;
    private final Deque<ScheduleCommand> history = new ArrayDeque<>();
    private final Queue<ScheduleCommand> pending = new ArrayDeque<>();
    private final ActionJournal journal = new ActionJournal();

    public DispatcherConsole(String dispatcherName) {
        this.dispatcherName = dispatcherName;
    }

    public void execute(ScheduleCommand command) {
        EventLogger.log(SOURCE, "Отримано команду: " + command.description());
        run(command);
    }

    public void enqueue(ScheduleCommand command) {
        pending.add(command);
        EventLogger.log(SOURCE, "Команду відкладено в чергу (у черзі: " + pending.size() + "): "
                + command.description());
    }

    public void executePending() {
        EventLogger.log(SOURCE, "Виконання пакету відкладених команд: " + pending.size());
        while (!pending.isEmpty()) {
            run(pending.poll());
        }
    }

    public void undoLast() {
        if (history.isEmpty()) {
            EventLogger.log(SOURCE, "Немає дій для скасування");
            return;
        }
        ScheduleCommand command = history.pop();
        EventLogger.log(SOURCE, "Скасування останньої дії: " + command.description());
        command.undo();
        journal.add(dispatcherName, JournalAction.UNDONE, command.description());
    }

    public ActionJournal getJournal() {
        return journal;
    }

    private void run(ScheduleCommand command) {
        command.execute();
        history.push(command);
        journal.add(dispatcherName, JournalAction.EXECUTED, command.description());
    }
}
