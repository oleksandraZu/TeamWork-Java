package ua.bukovyna.airport.cabin;

import java.util.List;

/** Посадка: від задніх рядів до передніх, лише пасажири з місцями. */
final class BoardingIterator extends RowWalkIterator {
    BoardingIterator(Cabin cabin) {
        super(cabin);
    }

    @Override
    protected boolean fromRear() {
        return true;
    }

    @Override
    protected List<Passenger> collect(Row row) {
        return row.seated();
    }
}
