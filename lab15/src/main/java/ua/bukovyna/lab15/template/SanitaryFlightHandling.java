package ua.bukovyna.lab15.template;

/** ConcreteClass: санітарний рейс (додано одним класом). */
public class SanitaryFlightHandling extends FlightHandling {

    @Override
    protected String typeName() {
        return "санітарний";
    }

    @Override
    protected void unload() {
        stage("вивантажити");
        log("  передано пацієнтів медичній бригаді, вивантажено медичне обладнання");
    }

    @Override
    protected void load() {
        stage("завантажити");
        log("  завантажено медикаменти та обладнання для транспортування пацієнтів");
    }

    @Override
    protected void cleanCabin() {
        stage("прибирання салону");
        log("  проведено дезінфекцію салону");
    }
}
