package ua.bukovyna.airport.common;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/** Консольний журнал подій у форматі [час] [джерело] подія. Час умовний: кожна подія зсуває його на 15 с. */
public final class EventLog {

    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static LocalTime currentTime = LocalTime.of(8, 0, 0);

    private EventLog() {
    }

    public static void log(String source, String event) {
        System.out.println("[" + currentTime.format(FORMAT) + "] [" + source + "] " + event);
        currentTime = currentTime.plusSeconds(15);
    }
}
