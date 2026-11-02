package ua.bukovyna.airport.notification;

/** ConcreteCreator: канал «Мобільний застосунок». */
public class MobileAppChannel extends NotificationChannel {

    @Override
    protected Message createMessage(FlightChange change) {
        return new MobileAppMessage(change);
    }

    @Override
    protected String channelName() {
        return "Мобільний застосунок";
    }
}
