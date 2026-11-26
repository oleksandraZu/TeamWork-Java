package ua.bukovyna.airport;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;

import ua.bukovyna.airport.cabin.Cabin;
import ua.bukovyna.airport.cabin.PassengerIterator;
import ua.bukovyna.airport.schedule.Flight;
import ua.bukovyna.airport.schedule.Schedule;
import ua.bukovyna.airport.schedule.ScheduleHistory;
import ua.bukovyna.airport.visitor.Airport;
import ua.bukovyna.airport.visitor.AirportObject;
import ua.bukovyna.airport.visitor.AviationAuthorityReportVisitor;
import ua.bukovyna.airport.visitor.FireSafetyVisitor;
import ua.bukovyna.airport.visitor.FuelStorage;
import ua.bukovyna.airport.visitor.JetBridge;
import ua.bukovyna.airport.visitor.Runway;
import ua.bukovyna.airport.visitor.ServiceCostVisitor;
import ua.bukovyna.airport.visitor.Terminal;

/** Раннер: окремий метод для кожної ситуації варіанта 1 «Аеропорт «Буковина»». */
public final class Main {

    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        task1Visitor();
        task2Memento();
        task3Iterator();
    }

    // ---------- Завдання 1: Visitor ----------
    static void task1Visitor() {
        Log.section("Завдання 1. Visitor: операції над інфраструктурою");
        Airport airport = new Airport();
        airport.add(new Runway("RW-01", 2800, 10));
        airport.add(new Runway("RW-02", 2200, 6));
        airport.add(new Terminal("T-1", 6500, true));
        airport.add(new Terminal("T-2", 3200, true));
        airport.add(new JetBridge("JB-1", 4, true));
        airport.add(new JetBridge("JB-2", 12, false));
        airport.add(new FuelStorage("FS-1", 500_000, true));

        logState("Стан інфраструктури ДО операцій", airport);

        Log.event("Main", "--- Операція 1: вартість обслуговування ---");
        airport.accept(new ServiceCostVisitor());

        Log.event("Main", "--- Операція 2: пожежна безпека ---");
        airport.accept(new FireSafetyVisitor());

        Log.event("Main", "--- Операція 3 (нова, один клас): звіт для авіаслужби ---");
        airport.accept(new AviationAuthorityReportVisitor());

        logState("Стан інфраструктури ПІСЛЯ операцій (без змін)", airport);
    }

    private static void logState(String title, Airport airport) {
        Log.event("Airport", title + ":");
        for (AirportObject o : airport.objects()) {
            Log.event("Airport", "  " + o);
        }
    }

    // ---------- Завдання 2: Memento ----------
    static void task2Memento() {
        Log.section("Завдання 2. Memento: чернетка розкладу");
        Schedule schedule = new Schedule();
        ScheduleHistory history = new ScheduleHistory();

        schedule.add(new Flight("BK101", "Київ", "A1", "RW-01", LocalTime.of(7, 30)));
        schedule.add(new Flight("BK202", "Варшава", "A2", "RW-01", LocalTime.of(8, 15)));
        schedule.add(new Flight("BK303", "Відень", "B1", "RW-02", LocalTime.of(9, 0)));
        schedule.add(new Flight("BK404", "Львів", "B2", "RW-02", LocalTime.of(10, 45)));
        logSchedule("Складено розклад з 4 рейсів", schedule);
        history.save("базовий", schedule);

        schedule.reschedule("BK101", LocalTime.of(7, 0));
        schedule.changeGate("BK202", "C3");
        schedule.remove("BK404");
        logSchedule("Внесено зміни (перенесено BK101, змінено вихід BK202, скасовано BK404)", schedule);
        history.save("ранковий пік", schedule);

        schedule.changeRunway("BK303", "RW-01");
        schedule.reschedule("BK303", LocalTime.of(8, 0));
        schedule.add(new Flight("BK505", "Бухарест", "C1", "RW-02", LocalTime.of(8, 30)));
        logSchedule("Внесено ще зміни (BK303 на RW-01 о 08:00, додано BK505)", schedule);

        history.restore("базовий", schedule);
        logSchedule("Стан після відновлення «базовий»", schedule);

        history.restore("ранковий пік", schedule);
        logSchedule("Стан після відновлення «ранковий пік»", schedule);
        Log.event("Main", "Доступні варіанти: " + history.names());
    }

    private static void logSchedule(String title, Schedule schedule) {
        Log.event("Schedule", title + ":");
        for (String line : schedule.lines()) {
            Log.event("Schedule", "  " + line);
        }
    }

    // ---------- Завдання 3: Iterator ----------
    static void task3Iterator() {
        Log.section("Завдання 3. Iterator: обхід пасажирів рейсу");
        Cabin cabin = new Cabin(5);
        cabin.addPassenger(1, "A", "Коваль О.", null);
        cabin.addPassenger(1, "B", "Мельник І.", "VIP");
        cabin.addPassenger(2, "A", "Шевчук Н.", null);
        cabin.addPassenger(2, "C", "Бондар Т.", "потребує допомоги");
        cabin.addPassenger(3, "B", "Лисенко В.", null);
        cabin.addPassenger(3, "D", "Ткачук Р.", "під наглядом");
        cabin.addPassenger(4, "A", "Гнатюк М.", null);
        cabin.addPassenger(5, "A", "Остапчук Д.", null);
        cabin.addPassenger(5, "B", "Савчук Л.", null);
        cabin.addInfant(2, "Шевчук Софія (немовля)", "Шевчук Н.");
        cabin.addInfant(5, "Остапчук Іван (немовля)", "Остапчук Д.");

        Log.event("Cabin", "Салон ДО обходів: пасажирів разом " + cabin.size() + " (5 рядів, 2 немовляти без місця)");

        Log.event("Main", "--- Обхід 1: посадка (від задніх рядів) ---");
        int n = 0;
        PassengerIterator boarding = cabin.boardingIterator();
        while (boarding.hasNext()) {
            Log.event("Boarding", ++n + ". " + boarding.next());
        }

        Log.event("Main", "--- Обхід 2: кейтеринг (спереду, з немовлятами) ---");
        n = 0;
        PassengerIterator catering = cabin.cateringIterator();
        while (catering.hasNext()) {
            Log.event("Catering", ++n + ". " + catering.next());
        }

        Log.event("Main", "--- Обхід 3: служба безпеки (лише з позначками) ---");
        n = 0;
        PassengerIterator security = cabin.securityIterator();
        while (security.hasNext()) {
            Log.event("Security", ++n + ". " + security.next());
        }

        Log.event("Main", "--- Два одночасні незалежні обходи (посадка + кейтеринг, по черзі) ---");
        PassengerIterator a = cabin.boardingIterator();
        PassengerIterator b = cabin.cateringIterator();
        int countA = 0;
        int countB = 0;
        while (a.hasNext() || b.hasNext()) {
            if (a.hasNext()) {
                countA++;
                Log.event("Boarding", "крок " + countA + ": " + a.next());
            }
            if (b.hasNext()) {
                countB++;
                Log.event("Catering", "крок " + countB + ": " + b.next());
            }
        }
        Log.event("Main", "Посадка пройшла " + countA + " пасажирів, кейтеринг " + countB
                + " - кожен обхід мав власний курсор і повний результат");
        Log.event("Cabin", "Салон ПІСЛЯ обходів: пасажирів разом " + cabin.size() + " (без змін)");
    }
}
