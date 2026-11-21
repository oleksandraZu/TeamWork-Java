package ua.bukovyna.airport.common;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/** Консольний журнал подій у форматі [час] [джерело] подія. Час умовний: кожен запис +1 хвилина. */
public final class Logger {

    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("HH:mm");
    private static LocalTime clock = LocalTime.of(8, 0);

    private Logger() {
    }

    public static void log(String source, String event) {
        System.out.println("[" + clock.format(FORMAT) + "] [" + source + "] " + event);
        clock = clock.plusMinutes(1);
    }

    public static void title(String title) {
        System.out.println();
        System.out.println("=== " + title + " ===");
    }
}
