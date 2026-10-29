package airport;

import airport.pool.Jetway;
import airport.pool.JetwayPool;
import airport.prototype.Flight;
import airport.singleton.ControlCenter;
import airport.singleton.Operation;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/** Раннер: по одному демонстраційному методу на кожну ситуацію. */
public class Main {
    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        demoSingleton();
        demoPrototype();
        demoObjectPool();
    }

    /** Завдання 1. Singleton — єдиний центр управління польотами. */
    static void demoSingleton() {
        System.out.println("\n=== Завдання 1. Узгодженість дозволів на використання смуг ===");
        ControlCenter arrivalsCenter = ControlCenter.getInstance();
        Log.log("Прильоти", "Запит: посадка PS-101 на смугу 1");
        arrivalsCenter.requestRunway(1, "PS-101", Operation.LANDING);

        ControlCenter departuresCenter = ControlCenter.getInstance();
        Log.log("Вильоти", "Запит: зліт PS-202 зі смуги 1");
        departuresCenter.requestRunway(1, "PS-202", Operation.TAKEOFF);

        Log.log("Прильоти", "Ідентифікатор центру: " + arrivalsCenter.getId());
        Log.log("Вильоти", "Ідентифікатор центру: " + departuresCenter.getId());
        Log.log("Раннер", "Той самий об'єкт: " + (arrivalsCenter == departuresCenter));
    }

    /** Завдання 2. Prototype — рейси на основі еталона. */
    static void demoPrototype() {
        System.out.println("\n=== Завдання 2. Формування регулярних рейсів ===");
        Flight reference = new Flight("ЕТАЛОН", "Чернівці–Варшава", LocalDate.of(2026, 11, 1), "Embraer 190",
                List.of("Капітан Мельник", "Другий пілот Сидоренко", "Бортпровідник Гнатюк"),
                List.of("Курка з рисом", "Чай", "Булочка"));
        Log.log("Формування рейсів", "Еталон створено: " + reference.getNumber());

        Flight[] flights = new Flight[3];
        for (int i = 0; i < flights.length; i++) {
            flights[i] = reference.copy();
            flights[i].setNumber("PS-30" + (i + 1));
            flights[i].setDate(LocalDate.of(2026, 11, 10 + i));
            Log.log("Формування рейсів", "Рейс " + flights[i].getNumber() + " створено з еталона без повторного завантаження");
        }

        Log.log("Диспетчер", "Зміни в рейсі " + flights[0].getNumber() + ": меню, пасажир, місце 12A");
        flights[0].replaceMeal("Курка з рисом", "Риба з овочами");
        flights[0].addPassenger("Коваленко І.");
        flights[0].blockSeat("12A");

        Log.log("Еталон", reference.toString());
        Log.log("Рейс " + flights[0].getNumber(), flights[0].toString());
        Log.log("Рейс " + flights[1].getNumber(), flights[1].toString());
    }

    /** Завдання 3. Object Pool — парк із 3 телетрапів. */
    static void demoObjectPool() {
        System.out.println("\n=== Завдання 3. Розподіл телетрапів ===");
        JetwayPool pool = new JetwayPool(3);
        String[] arrivals = {"PS-101", "PS-205", "PS-307", "PS-412", "PS-518"};

        Optional<Jetway> first = Optional.empty();
        Optional<Jetway> second = Optional.empty();
        for (int i = 0; i < arrivals.length; i++) {
            Log.log("Прильоти", "Рейс " + arrivals[i] + " прибув і запитує телетрап");
            Optional<Jetway> jetway = pool.acquire(arrivals[i]);
            if (i == 0) first = jetway;
            if (i == 1) second = jetway;
        }

        Log.log("Наземні служби", "PS-101 завершив обслуговування");
        pool.release(first.get());
        Log.log("Наземні служби", "PS-205 завершив обслуговування");
        pool.release(second.get());

        pool.printStatistics();
    }
}
