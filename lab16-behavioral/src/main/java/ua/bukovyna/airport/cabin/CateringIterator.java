package ua.bukovyna.airport.cabin;

import java.util.ArrayList;
import java.util.List;

/** Кейтеринг: по рядах спереду; у ряду — місця, потім немовлята. */
final class CateringIterator extends RowWalkIterator {
    CateringIterator(Cabin cabin) {
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
}
