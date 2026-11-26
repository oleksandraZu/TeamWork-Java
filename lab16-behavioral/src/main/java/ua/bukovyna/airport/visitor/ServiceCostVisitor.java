package ua.bukovyna.airport.visitor;

import ua.bukovyna.airport.Log;

/** ConcreteVisitor: розрахунок вартості обслуговування (грн). */
public final class ServiceCostVisitor implements AirportVisitor {
    private static final String SRC = "ServiceCostVisitor";
    private double total;

    @Override
    public void visit(Runway r) {
        double cost = r.lengthM() * 15.0;
        total += cost;
        Log.event(SRC, String.format("%s: %d м x 15 грн/м = %.2f грн", r.id(), r.lengthM(), cost));
    }

    @Override
    public void visit(Terminal t) {
        double cost = t.areaM2() * 40.0;
        total += cost;
        Log.event(SRC, String.format("%s: %d м2 x 40 грн/м2 = %.2f грн", t.id(), t.areaM2(), cost));
    }

    @Override
    public void visit(JetBridge j) {
        double cost = 8000 + j.ageYears() * 500.0;
        total += cost;
        Log.event(SRC, String.format("%s: 8000 + %d р. x 500 = %.2f грн", j.id(), j.ageYears(), cost));
    }

    @Override
    public void visit(FuelStorage f) {
        double cost = f.capacityL() * 0.8;
        total += cost;
        Log.event(SRC, String.format("%s: %d л x 0.8 грн/л = %.2f грн", f.id(), f.capacityL(), cost));
    }

    @Override
    public void summary() {
        Log.event(SRC, String.format("РАЗОМ вартість обслуговування: %.2f грн", total));
    }
}
