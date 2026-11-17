package ua.lab13.task1;

public enum FlightType {
    DOMESTIC("внутрішній"),
    INTERNATIONAL("міжнародний");

    private final String title;

    FlightType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
