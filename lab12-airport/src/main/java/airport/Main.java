package airport;

import airport.bridge.ArrivalBoard;
import airport.bridge.DepartureBoard;
import airport.bridge.DisplayDevice;
import airport.bridge.FlightBoard;
import airport.bridge.FlightInfo;
import airport.bridge.GateBoard;
import airport.bridge.LcdScreen;
import airport.bridge.LedPanel;
import airport.bridge.MobileApp;
import airport.bridge.Projector;
import airport.composite.CargoComponent;
import airport.composite.CargoGroup;
import airport.composite.CargoItem;
import airport.flyweight.CabinLayout;
import airport.flyweight.Seat;
import airport.flyweight.ServiceClassFactory;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/** Раннер: окремий метод для кожної проблемної ситуації варіанта 1 («Аеропорт «Буковина»). */
public class Main {

    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        compositeSituation();
        flyweightSituation();
        bridgeSituation();
    }

    /** Завдання 1. Розрахунок завантаження літака (Composite). */
    private static void compositeSituation() {
        Log.log("Composite", "=== Завдання 1: розрахунок завантаження літака ===");

        CargoGroup plane = new CargoGroup("Літак", "UR-BUK");
        CargoGroup bay1 = new CargoGroup("Відсік", "1");
        CargoGroup bay2 = new CargoGroup("Відсік", "2");

        CargoGroup container1 = new CargoGroup("Контейнер", "AKE-001");
        container1.add(new CargoItem("Валіза", "Іваненко", 23.5));
        container1.add(new CargoItem("Валіза", "Петренко", 18.0));
        container1.add(new CargoItem("Посилка", "P-101", 5.2));

        CargoGroup pallet = new CargoGroup("Палета", "PMC-01");
        pallet.add(new CargoItem("Посилка", "P-201", 120.0));
        pallet.add(new CargoItem("Посилка", "P-202", 85.5));
        CargoGroup container2 = new CargoGroup("Контейнер", "AKE-002");
        container2.add(new CargoItem("Валіза", "Коваленко", 20.0));
        container2.add(pallet);

        CargoGroup container3 = new CargoGroup("Контейнер", "AKE-003");
        container3.add(new CargoItem("Валіза", "Шевченко", 22.0));
        container3.add(new CargoItem("Валіза", "Мельник", 19.5));
        container3.add(new CargoItem("Посилка", "P-301", 7.8));

        CargoGroup container4 = new CargoGroup("Контейнер", "AKE-004");
        container4.add(new CargoItem("Посилка", "P-401", 14.0));

        bay1.add(container1);
        bay1.add(container2);
        bay2.add(container3);
        bay2.add(container4);
        plane.add(bay1);
        plane.add(bay2);

        Log.log("Composite", "структура вантажу (дерево):");
        plane.print(0);

        report(plane);
        report(bay1);
    }

    /** Один і той самий виклик для будь-якого вузла структури. */
    private static void report(CargoComponent node) {
        Log.log("Розрахунок", node.getName() + ": вага " + String.format(Locale.ROOT, "%.1f", node.getWeight())
                + " кг, місць: " + node.getPlaces());
    }

    /** Завдання 2. Місця в схемі салону (Flyweight). */
    private static void flyweightSituation() {
        Log.log("Flyweight", "=== Завдання 2: місця в схемі салону ===");

        List<CabinLayout> cabins = new ArrayList<>();
        int seatCount = 0;
        for (int i = 1; i <= 20; i++) {
            CabinLayout cabin = new CabinLayout("BK" + (100 + i), i);
            cabins.add(cabin);
            seatCount += cabin.getSeats().size();
        }
        Log.log("Flyweight", "сформовано схем салонів: " + cabins.size());
        Log.log("Flyweight", "кількість об'єктів місць: " + seatCount);
        Log.log("Flyweight", "кількість об'єктів спільних даних класів обслуговування: " + ServiceClassFactory.count());

        CabinLayout firstCabin = cabins.get(0);
        CabinLayout otherCabin = cabins.get(14);
        Seat first = firstCabin.seatAt(1, 'A');
        Seat second = otherCabin.seatAt(3, 'C');
        Log.log("Flyweight", "рейс " + firstCabin.getFlightNumber() + ": " + first.describe());
        Log.log("Flyweight", "рейс " + otherCabin.getFlightNumber() + ": " + second.describe());
        Log.log("Flyweight", "посилаються на той самий об'єкт спільних даних: "
                + (first.getServiceClass() == second.getServiceClass())
                + " (id " + System.identityHashCode(first.getServiceClass())
                + " і " + System.identityHashCode(second.getServiceClass()) + ")");
    }

    /** Завдання 3. Табло інформації про рейси (Bridge). */
    private static void bridgeSituation() {
        Log.log("Bridge", "=== Завдання 3: табло інформації про рейси ===");

        List<FlightInfo> flights = List.of(
                new FlightInfo("BK101", "Київ", "10:30", "A1", "за розкладом", true),
                new FlightInfo("BK205", "Варшава", "11:15", "B3", "затримка 20 хв", true),
                new FlightInfo("BK310", "Львів", "12:40", "A1", "посадка", true),
                new FlightInfo("BK402", "Відень", "12:10", "-", "приземлився", false),
                new FlightInfo("BK518", "Стамбул", "13:05", "-", "очікується", false));

        DisplayDevice led = new LedPanel();
        DisplayDevice lcd = new LcdScreen();
        DisplayDevice app = new MobileApp();

        FlightBoard departures = new DepartureBoard(led);
        departures.show(flights);
        departures.setDevice(lcd);
        departures.show(flights);

        new GateBoard(app, "A1").show(flights);

        Log.log("Bridge", "підключено нове обладнання «Проєктор» (один новий клас)");
        new ArrivalBoard(new Projector()).show(flights);
    }
}
