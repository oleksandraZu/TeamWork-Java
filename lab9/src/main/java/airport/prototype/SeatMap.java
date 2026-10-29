package airport.prototype;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** Схема салону: місця (наприклад, 12A) та їхній стан. */
public class SeatMap {
    private final Map<String, SeatStatus> seats = new LinkedHashMap<>();

    public SeatMap(int rows, String letters) {
        for (int row = 1; row <= rows; row++) {
            for (char letter : letters.toCharArray()) {
                seats.put(row + String.valueOf(letter), SeatStatus.FREE);
            }
        }
    }

    SeatMap(SeatMap other) {
        seats.putAll(other.seats); // ключі (String) і значення (enum) незмінні
    }

    public void block(String seat) {
        if (!seats.containsKey(seat)) {
            throw new IllegalArgumentException("Місця " + seat + " немає в салоні");
        }
        seats.put(seat, SeatStatus.BLOCKED);
    }

    public List<String> blockedSeats() {
        List<String> result = new ArrayList<>();
        seats.forEach((seat, status) -> {
            if (status == SeatStatus.BLOCKED) {
                result.add(seat);
            }
        });
        return result;
    }
}
