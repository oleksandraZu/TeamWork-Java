package ua.bukovyna.lab15.template;

/** ConcreteClass: чартерний рейс. */
public class CharterFlightHandling extends FlightHandling {

    @Override
    protected String typeName() {
        return "чартерний";
    }

    @Override
    protected void unload() {
        stage("вивантажити");
        log("  висаджено пасажирів за списком замовника, вивантажено багаж");
    }

    @Override
    protected void load() {
        stage("завантажити");
        log("  завантажено багаж і кейтеринг, пасажирів звірено зі списком замовника");
    }
}
