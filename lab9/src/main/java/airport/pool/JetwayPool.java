package airport.pool;

import airport.Log;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Optional;
import java.util.Queue;


public class JetwayPool {
    private static final String SOURCE = "Парк телетрапів";

    private final int capacity;
    private final List<Jetway> all = new ArrayList<>();
    private final Deque<Jetway> free = new ArrayDeque<>();
    private final Queue<String> waiting = new ArrayDeque<>();

    public JetwayPool(int capacity) {
        this.capacity = capacity;
    }


    public synchronized Optional<Jetway> acquire(String flightNumber) {
        Jetway jetway = free.pollFirst();
        if (jetway == null && all.size() < capacity) {
            jetway = new Jetway("T-" + (all.size() + 1)); // створюється лише до фізичного ліміту
            all.add(jetway);
        }
        if (jetway == null) {
            waiting.add(flightNumber);
            Log.log(SOURCE, "Вільних телетрапів немає, рейс " + flightNumber
                    + " очікує в черзі (позиція " + waiting.size() + ")");
            return Optional.empty();
        }
        jetway.attach(flightNumber);
        Log.log(SOURCE, "Телетрап " + jetway.getId() + " → " + flightNumber);
        return Optional.of(jetway);
    }


    public synchronized void release(Jetway jetway) {
        jetway.reset();
        Log.log(SOURCE, jetway.getId() + " повернено, стан скинуто");
        String next = waiting.poll();
        if (next != null) {
            jetway.attach(next);
            Log.log(SOURCE, "Телетрап " + jetway.getId() + " → " + next + " (без повторної підготовки)");
        } else {
            free.addLast(jetway);
        }
    }

    public synchronized void printStatistics() {
        for (Jetway jetway : all) {
            Log.log(SOURCE, "Статистика: " + jetway.getId() + " — видач: " + jetway.getUsageCount());
        }
        Log.log(SOURCE, "Статистика: загальна кількість створених телетрапів — " + all.size());
    }
}
