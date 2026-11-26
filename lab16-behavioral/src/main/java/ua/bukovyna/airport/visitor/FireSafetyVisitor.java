package ua.bukovyna.airport.visitor;

import ua.bukovyna.airport.Log;

/** ConcreteVisitor: перевірка пожежної безпеки. */
public final class FireSafetyVisitor implements AirportVisitor {
    private static final String SRC = "FireSafetyVisitor";
    private int checked;
    private int violations;

    @Override
    public void visit(Runway r) {
        int required = (int) Math.ceil(r.lengthM() / 300.0);
        report(r.id(), r.hydrants() >= required,
                "гідрантів " + r.hydrants() + " з " + required + " потрібних (1 на 300 м)");
    }

    @Override
    public void visit(Terminal t) {
        report(t.id(), t.sprinklers(), "система спринклерів " + (t.sprinklers() ? "є" : "відсутня"));
    }

    @Override
    public void visit(JetBridge j) {
        report(j.id(), j.extinguisher(), "вогнегасник " + (j.extinguisher() ? "є" : "відсутній"));
    }

    @Override
    public void visit(FuelStorage f) {
        report(f.id(), f.foamSystem(), "система піногасіння " + (f.foamSystem() ? "є" : "відсутня"));
    }

    private void report(String id, boolean ok, String detail) {
        checked++;
        if (!ok) {
            violations++;
        }
        Log.event(SRC, id + ": " + (ok ? "OK" : "ПОРУШЕННЯ") + " - " + detail);
    }

    @Override
    public void summary() {
        Log.event(SRC, "Перевірено об'єктів: " + checked + ", порушень: " + violations);
    }
}
