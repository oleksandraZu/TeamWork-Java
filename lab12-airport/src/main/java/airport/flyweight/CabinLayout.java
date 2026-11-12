package airport.flyweight;

import java.util.ArrayList;
import java.util.List;

/** Схема салону одного рейсу: 30 рядів по 6 місць (180 місць). */
public class CabinLayout {

    private static final int ROWS = 30;
    private static final char[] LETTERS = {'A', 'B', 'C', 'D', 'E', 'F'};

    private final String flightNumber;
    private final List<Seat> seats = new ArrayList<>();

    public CabinLayout(String flightNumber, int flightIndex) {
        this.flightNumber = flightNumber;
        for (int row = 1; row <= ROWS; row++) {
            String serviceClass = row <= 5 ? ServiceClassFactory.BUSINESS
                    : row <= 10 ? ServiceClassFactory.PREMIUM : ServiceClassFactory.ECONOMY;
            for (char letter : LETTERS) {
                boolean occupied = (row * 7 + (letter - 'A') * 3 + flightIndex) % 4 == 0;
                String passenger = occupied ? "Пасажир-" + flightIndex + "-" + row + letter : null;
                seats.add(new Seat(row, letter, passenger, ServiceClassFactory.get(serviceClass)));
            }
        }
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public Seat seatAt(int row, char letter) {
        return seats.get((row - 1) * LETTERS.length + (letter - 'A'));
    }
}
