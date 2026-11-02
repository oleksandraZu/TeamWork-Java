package ua.bukovyna.airport.notification;

/** ConcreteCreator: канал «Табло». */
public class DisplayBoardChannel extends NotificationChannel {

    @Override
    protected Message createMessage(FlightChange change) {
        return new DisplayBoardMessage(change);
    }

    @Override
    protected String channelName() {
        return "Табло";
    }
}
