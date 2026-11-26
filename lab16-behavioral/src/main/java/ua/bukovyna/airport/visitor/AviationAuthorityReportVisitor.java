package ua.bukovyna.airport.visitor;

import ua.bukovyna.airport.Log;

/** ConcreteVisitor: звіт для державної авіаслужби. Додано окремим класом, класи об'єктів не змінювались. */
public final class AviationAuthorityReportVisitor implements AirportVisitor {
    private static final String SRC = "AviationAuthorityReport";
    private int runways;
    private int terminals;
    private int jetBridges;
    private long fuelLitres;
    private int totalRunwayM;

    @Override
    public void visit(Runway r) {
        runways++;
        totalRunwayM += r.lengthM();
        Log.event(SRC, "ЗПС " + r.id() + ": довжина " + r.lengthM() + " м");
    }

    @Override
    public void visit(Terminal t) {
        terminals++;
        Log.event(SRC, "Термінал " + t.id() + ": площа " + t.areaM2() + " м2");
    }

    @Override
    public void visit(JetBridge j) {
        jetBridges++;
        Log.event(SRC, "Телетрап " + j.id() + ": експлуатується " + j.ageYears() + " р.");
    }

    @Override
    public void visit(FuelStorage f) {
        fuelLitres += f.capacityL();
        Log.event(SRC, "Паливне сховище " + f.id() + ": місткість " + f.capacityL() + " л");
    }

    @Override
    public void summary() {
        Log.event(SRC, "ЗВІТ: ЗПС=" + runways + " (" + totalRunwayM + " м), термінали=" + terminals
                + ", телетрапи=" + jetBridges + ", запас палива=" + fuelLitres + " л");
    }
}
