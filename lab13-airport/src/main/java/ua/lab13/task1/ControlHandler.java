package ua.lab13.task1;

import ua.lab13.common.EventLogger;

public abstract class ControlHandler {
    private ControlHandler next;

    public ControlHandler setNext(ControlHandler next) {
        this.next = next;
        return next;
    }

    public final ControlResult handle(Passenger passenger) {
        EventLogger.log(title(), "Отримано пасажира " + passenger.name());
        String reason = check(passenger);
        if (reason != null) {
            EventLogger.log(title(), "Пасажира зупинено: " + reason);
            return ControlResult.stopped(title(), reason);
        }
        if (next == null) {
            EventLogger.log(title(), "Перевірку пройдено, це остання ланка");
            return ControlResult.ok();
        }
        EventLogger.log(title(), "Перевірку пройдено, передаю далі: " + next.title());
        return next.handle(passenger);
    }

    protected abstract String check(Passenger passenger);

    public abstract String title();
}
