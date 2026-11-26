package ua.bukovyna.airport.visitor;

/** ConcreteElement: паливне сховище. */
public record FuelStorage(String id, int capacityL, boolean foamSystem) implements AirportObject {
    @Override
    public void accept(AirportVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "FuelStorage{" + id + ", " + capacityL + " л, піногасіння=" + foamSystem + "}";
    }
}
