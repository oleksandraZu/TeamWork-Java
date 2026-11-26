package ua.bukovyna.airport.visitor;

/** Visitor: окремий метод для кожного типу об'єкта. */
public interface AirportVisitor {
    void visit(Runway runway);

    void visit(Terminal terminal);

    void visit(JetBridge jetBridge);

    void visit(FuelStorage fuelStorage);

    /** Підсумок операції після проходу по всій інфраструктурі. */
    void summary();
}
