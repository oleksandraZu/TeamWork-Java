package ua.bukovyna.airport.cabin;

import java.util.ArrayList;
import java.util.List;

/** Служба безпеки: лише пасажири з особливими позначками. */
final class SecurityIterator extends RowWalkIterator {
    SecurityIterator(Cabin cabin) {
        super(cabin);
    }

    @Override
    protected boolean fromRear() {
        return false;
    }

    @Override
    protected List<Passenger> collect(Row row) {
        List<Passenger> all = new ArrayList<>(row.seated());
        all.addAll(row.infants());
        return all;
    }

    @Override
    protected boolean accepts(Passenger passenger) {
        return passenger.hasMark();
    }
}
