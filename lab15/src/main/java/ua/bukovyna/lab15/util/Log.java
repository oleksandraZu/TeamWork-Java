package ua.bukovyna.lab15.util;

/** Консольний журнал подій у форматі [час] [джерело] подія. Час умовний: кожна подія +1 хвилина. */
public final class Log {

    private static int minutes = 8 * 60;

    private Log() {
    }

    public static void log(String source, String event) {
        System.out.printf("[%02d:%02d] [%s] %s%n", minutes / 60, minutes % 60, source, event);
        minutes++;
    }
}
