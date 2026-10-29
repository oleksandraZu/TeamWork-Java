package airport.singleton;

import airport.Log;

import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

/** Singleton: єдиний центр управління польотами, що веде стан смуг. */
public final class ControlCenter {
    private static final String SOURCE = "ЦУП";

    private final String id;
    private final Map<Integer, Runway> runways = new TreeMap<>();

    private ControlCenter() {
        id = "CUP-" + UUID.randomUUID().toString().substring(0, 8);
        runways.put(1, new Runway(1));
        runways.put(2, new Runway(2));
        Log.log(SOURCE, "Центр управління польотами ініціалізовано");
    }

    /** Ледача потокобезпечна ініціалізація (Initialization-on-demand holder). */
    private static final class Holder {
        private static final ControlCenter INSTANCE = new ControlCenter();
    }

    public static ControlCenter getInstance() {
        return Holder.INSTANCE;
    }

    public String getId() {
        return id;
    }

    public synchronized boolean requestRunway(int number, String flightNumber, Operation operation) {
        Runway runway = runways.get(number);
        if (runway == null) {
            throw new IllegalArgumentException("Смуги " + number + " не існує");
        }
        if (!runway.isFree()) {
            Log.log(SOURCE, "Відмова: Смуга " + number + " зайнята рейсом " + runway.getOccupiedBy());
            return false;
        }
        runway.occupy(flightNumber);
        Log.log(SOURCE, "Дозвіл на " + operation.getTitle() + " рейсу " + flightNumber
                + " видано, смуга " + number + " зайнята");
        return true;
    }

    public synchronized void releaseRunway(int number, String flightNumber) {
        Runway runway = runways.get(number);
        if (runway != null && flightNumber.equals(runway.getOccupiedBy())) {
            runway.release();
            Log.log(SOURCE, "Смуга " + number + " звільнена рейсом " + flightNumber);
        }
    }
}
