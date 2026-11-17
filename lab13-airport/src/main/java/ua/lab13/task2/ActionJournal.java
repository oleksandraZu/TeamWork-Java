package ua.lab13.task2;

import ua.lab13.common.EventLogger;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/** Журнал усіх дій диспетчера. */
public class ActionJournal {
    private static final String SOURCE = "Журнал дій";
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");

    private final List<JournalEntry> entries = new ArrayList<>();

    public void add(String dispatcher, JournalAction action, String description) {
        entries.add(new JournalEntry(EventLogger.now(), dispatcher, action, description));
    }

    public void print() {
        EventLogger.log(SOURCE, "Журнал дій диспетчера (записів: " + entries.size() + "):");
        int number = 1;
        for (JournalEntry entry : entries) {
            EventLogger.log(SOURCE, "  " + number++ + ". " + FORMAT.format(entry.time()) + " | " + entry.dispatcher()
                    + " | " + entry.action().getTitle() + " | " + entry.description());
        }
    }
}
