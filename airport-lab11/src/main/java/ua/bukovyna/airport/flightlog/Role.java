package ua.bukovyna.airport.flightlog;

public enum Role {
    FLIGHT_SAFETY("служба безпеки польотів"),
    DISPATCHER("диспетчер"),
    CASHIER("касир");

    private final String title;

    Role(String title) {
        this.title = title;
    }

    public String title() {
        return title;
    }
}
