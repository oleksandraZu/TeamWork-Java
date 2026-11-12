package airport.flyweight;

/** Контекст: зберігає лише власні (зовнішні) дані місця та посилання на спільний об'єкт класу. */
public class Seat {

    private final int row;
    private final char letter;
    private final String passenger;
    private final ServiceClassData serviceClass;

    public Seat(int row, char letter, String passenger, ServiceClassData serviceClass) {
        this.row = row;
        this.letter = letter;
        this.passenger = passenger;
        this.serviceClass = serviceClass;
    }

    public ServiceClassData getServiceClass() {
        return serviceClass;
    }

    public String describe() {
        return "місце " + row + letter + ", пасажир: " + (passenger == null ? "вільне" : passenger)
                + "; клас: " + serviceClass;
    }
}
