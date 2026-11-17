package ua.lab13.common;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/** Консольний журнал подій у форматі [час] [джерело] подія. Час умовний (годинник симуляції). */
public final class EventLogger {
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static final int STEP_SECONDS = 5;
    private static LocalTime clock = LocalTime.of(8, 0);

    private EventLogger() {
    }

    public static void log(String source, String event) {
        System.out.println("[" + FORMAT.format(clock) + "] [" + source + "] " + event);
        clock = clock.plusSeconds(STEP_SECONDS);
    }

    public static LocalTime now() {
        return clock;
    }
}
