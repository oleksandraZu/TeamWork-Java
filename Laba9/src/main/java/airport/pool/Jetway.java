package airport.pool;

import airport.Log;

/** Телетрап: ідентифікатор і рейс, до якого під'єднаний. */
public class Jetway {
    private final String id;
    private String attachedFlight; // null — від'єднаний
    private int usageCount;

    /** Створення включає тривалу підготовку (підведення, калібрування, перевірка). */
    Jetway(String id) {
        Log.expensive("Телетрап " + id, "Підготовка: підведення, калібрування, перевірка", 20);
        this.id = id;
    }

    void attach(String flightNumber) {
        attachedFlight = flightNumber;
        usageCount++;
    }

    /** Скидання стану перед повторною видачею. */
    void reset() {
        attachedFlight = null;
    }

    public String getId() {
        return id;
    }

    public String getAttachedFlight() {
        return attachedFlight;
    }

    public int getUsageCount() {
        return usageCount;
    }
}
