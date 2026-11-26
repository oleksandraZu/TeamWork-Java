package ua.bukovyna.airport.visitor;

/** ConcreteElement: злітно-посадкова смуга. */
public record Runway(String id, int lengthM, int hydrants) implements AirportObject {
    @Override
    public void accept(AirportVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "Runway{" + id + ", " + lengthM + " м, гідрантів=" + hydrants + "}";
    }
}
