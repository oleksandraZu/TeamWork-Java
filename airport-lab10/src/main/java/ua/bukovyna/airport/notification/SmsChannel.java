package ua.bukovyna.airport.notification;

/** ConcreteCreator: канал «SMS». */
public class SmsChannel extends NotificationChannel {

    @Override
    protected Message createMessage(FlightChange change) {
        return new SmsMessage(change);
    }

    @Override
    protected String channelName() {
        return "SMS";
    }
}
