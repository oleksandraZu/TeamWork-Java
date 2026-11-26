package ua.bukovyna.airport.cabin;

import java.util.List;
import java.util.NoSuchElementException;

/** Базовий ітератор по рядах. Власний курсор у кожного екземпляра — обходи незалежні. */
abstract class RowWalkIterator implements PassengerIterator {
    private final Cabin cabin;
    private int rowPos;
    private int index;
    private List<Passenger> current;
    private Passenger buffered;

    RowWalkIterator(Cabin cabin) {
        this.cabin = cabin;
    }

    /** true — від заднього ряду до переднього. */
    protected abstract boolean fromRear();

    /** Пасажири ряду в порядку обходу. */
    protected abstract List<Passenger> collect(Row row);

    protected boolean accepts(Passenger passenger) {
        return true;
    }

    private void advance() {
        while (buffered == null) {
            if (current == null) {
                if (rowPos >= cabin.rowCount()) {
                    return;
                }
                int rowIndex = fromRear() ? cabin.rowCount() - 1 - rowPos : rowPos;
                current = collect(cabin.row(rowIndex));
                index = 0;
                rowPos++;
            }
            if (index >= current.size()) {
                current = null;
                continue;
            }
            Passenger candidate = current.get(index++);
            if (accepts(candidate)) {
                buffered = candidate;
            }
        }
    }

    @Override
    public boolean hasNext() {
        advance();
        return buffered != null;
    }

    @Override
    public Passenger next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Passenger result = buffered;
        buffered = null;
        return result;
    }
}
