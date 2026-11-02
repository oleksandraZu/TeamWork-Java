package ua.bukovyna.airport.notification;

/** ConcreteCreator: канал «E-mail». */
public class EmailChannel extends NotificationChannel {

    @Override
    protected Message createMessage(FlightChange change) {
        return new EmailMessage(change);
    }

    @Override
    protected String channelName() {
        return "E-mail";
    }
}
