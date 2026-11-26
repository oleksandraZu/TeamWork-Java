package ua.bukovyna.airport.visitor;

/** ConcreteElement: телетрап. */
public record JetBridge(String id, int ageYears, boolean extinguisher) implements AirportObject {
    @Override
    public void accept(AirportVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "JetBridge{" + id + ", вік=" + ageYears + " р., вогнегасник=" + extinguisher + "}";
    }
}
