package ua.bukovyna.airport.notification;

/** ConcreteProduct: push-сповіщення із заголовком, текстом і дією. */
public class MobileAppMessage implements Message {

    private final String title;
    private final String text;

    public MobileAppMessage(FlightChange change) {
        this.title = "Рейс " + change.flightNumber();
        this.text = change.description();
    }

    @Override
    public String render() {
        return "Push [заголовок: " + title + "] [текст: " + text + "] [кнопка: Відкрити рейс]";
    }
}
