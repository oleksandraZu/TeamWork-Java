package ua.bukovyna.airport.observer;

public enum FlightStatus {
    SCHEDULED("за розкладом"),
    DELAYED("затримано"),
    GATE_CHANGED("змінено вихід"),
    BOARDING("розпочато посадку");

    private final String title;

    FlightStatus(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
