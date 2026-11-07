package ua.bukovyna.airport.common;

import java.util.Locale;

/** Консольний журнал подій у форматі [час] [джерело] подія. Час умовний. */
public final class Log {
    private static int seconds = 8 * 3600;

    private Log() {
    }

    public static void log(String source, String event) {
        seconds += 5;
        System.out.println(String.format(Locale.ROOT, "[%02d:%02d:%02d] [%s] %s",
                seconds / 3600 % 24, seconds / 60 % 60, seconds % 60, source, event));
    }

    public static void logf(String source, String format, Object... args) {
        log(source, String.format(Locale.ROOT, format, args));
    }
}
