package airport;

/** Консольний журнал подій у форматі [час] [джерело] подія. Час умовний. */
public final class Log {
    private static int seconds = 8 * 3600; // умовний початок: 08:00:00

    private Log() {
    }

    public static synchronized void log(String source, String event) {
        seconds++;
        System.out.printf("[%02d:%02d:%02d] [%s] %s%n",
                seconds / 3600 % 24, seconds / 60 % 60, seconds % 60, source, event);
    }

    /** Емулює «дорогу» операцію: повідомлення, затримка, зсув умовного часу. */
    public static synchronized void expensive(String source, String event, int virtualSeconds) {
        log(source, event + "...");
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        seconds += virtualSeconds;
    }
}
