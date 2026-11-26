package ua.bukovyna.airport;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/** Консольний журнал подій у форматі [час] [джерело] подія. Час умовний: кожна подія +1 с. */
public final class Log {
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static LocalTime clock = LocalTime.of(8, 0, 0);

    private Log() {
    }

    public static void event(String source, String message) {
        System.out.printf("[%s] [%s] %s%n", clock.format(FMT), source, message);
        clock = clock.plusSeconds(1);
    }

    public static void section(String title) {
        System.out.println();
        System.out.println("=== " + title + " ===");
    }
}
