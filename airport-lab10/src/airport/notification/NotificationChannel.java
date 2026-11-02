package ua.bukovyna.airport.notification;

import ua.bukovyna.airport.common.EventLog;

/**
 * Creator: загальний процес сповіщення описано один раз (notifyPassengers).
 * Конкретне повідомлення створює фабричний метод createMessage у підкласі-каналі.
 */
public abstract class NotificationChannel {

    public final void notifyPassengers(FlightChange change) {
        Message message = createMessage(change);
        send(message);
        EventLog.log("Журнал сповіщень", "Записано: рейс " + change.flightNumber() + ", канал «" + channelName() + "»");
    }

    protected abstract Message createMessage(FlightChange change);

    protected abstract String channelName();

    private void send(Message message) {
        EventLog.log(channelName(), "Надіслано: " + message.render());
    }
}
