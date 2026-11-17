package airport;

/** Єдиний спосіб виводу подій у форматі [час] [джерело] подія. Час умовний. */
public final class Log {

    private static int seconds = 6 * 3600;

    private Log() {
    }

    public static void log(String source, String event) {
        int s = seconds++;
        System.out.printf("[%02d:%02d:%02d] [%s] %s%n", (s / 3600) % 24, (s / 60) % 60, s % 60, source, event);
    }
}
