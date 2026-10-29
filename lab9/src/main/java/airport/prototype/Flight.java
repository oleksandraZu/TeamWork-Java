package airport.prototype;

import airport.Log;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/** Prototype: рейс, новий екземпляр якого створюється копіюванням еталона. */
public class Flight implements Prototype<Flight> {
    private String number;
    private final String route;
    private LocalDate date;
    private final Aircraft aircraft;
    private final SeatMap seatMap;
    private final List<String> crew;
    private final List<String> passengers = new ArrayList<>();
    private final List<String> meals;

    /** Формування з нуля — дорога операція. */
    public Flight(String number, String route, LocalDate date, String aircraftType,
                  List<String> crew, List<String> meals) {
        Log.expensive("Формування рейсів", "Завантаження конфігурації літака " + aircraftType
                + " (схема салону, екіпаж, норми багажу, харчування) для еталона " + route, 30);
        this.number = number;
        this.route = route;
        this.date = date;
        this.aircraft = new Aircraft(aircraftType, 23);
        this.seatMap = new SeatMap(20, "ABCDEF");
        this.crew = new ArrayList<>(crew);
        this.meals = new ArrayList<>(meals);
    }

    /** Конструктор копіювання: глибока копія всіх вкладених об'єктів, без дорогого завантаження. */
    private Flight(Flight other) {
        this.number = other.number;
        this.route = other.route;
        this.date = other.date;
        this.aircraft = new Aircraft(other.aircraft);
        this.seatMap = new SeatMap(other.seatMap);
        this.crew = new ArrayList<>(other.crew);
        this.passengers.addAll(other.passengers);
        this.meals = new ArrayList<>(other.meals);
    }

    @Override
    public Flight copy() {
        return new Flight(this);
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getNumber() {
        return number;
    }

    public void replaceMeal(String oldMeal, String newMeal) {
        meals.set(meals.indexOf(oldMeal), newMeal);
    }

    public void addPassenger(String name) {
        passengers.add(name);
    }

    public void blockSeat(String seat) {
        seatMap.block(seat);
    }

    @Override
    public String toString() {
        return number + ", " + route + ", " + date + ", літак " + aircraft.getType()
                + ", меню=" + meals + ", пасажири=" + passengers
                + ", заблоковані місця=" + seatMap.blockedSeats();
    }
}
