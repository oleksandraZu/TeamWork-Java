package ua.bukovyna.airport.visitor;

/** ConcreteElement: термінал. */
public record Terminal(String id, int areaM2, boolean sprinklers) implements AirportObject {
    @Override
    public void accept(AirportVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "Terminal{" + id + ", " + areaM2 + " м2, спринклери=" + sprinklers + "}";
    }
}
