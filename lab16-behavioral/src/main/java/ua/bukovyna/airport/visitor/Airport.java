package ua.bukovyna.airport.visitor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** ObjectStructure: інфраструктура аеропорту; будь-яка операція виконується одним проходом. */
public final class Airport {
    private final List<AirportObject> objects = new ArrayList<>();

    public void add(AirportObject object) {
        objects.add(object);
    }

    public List<AirportObject> objects() {
        return Collections.unmodifiableList(objects);
    }

    public void accept(AirportVisitor visitor) {
        for (AirportObject object : objects) {
            object.accept(visitor);
        }
        visitor.summary();
    }
}
