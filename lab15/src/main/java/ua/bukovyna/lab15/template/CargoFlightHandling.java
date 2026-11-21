package ua.bukovyna.lab15.template;

/** ConcreteClass: вантажний рейс. */
public class CargoFlightHandling extends FlightHandling {

    @Override
    protected String typeName() {
        return "вантажний";
    }

    @Override
    protected void unload() {
        stage("вивантажити");
        log("  вивантажено контейнери та палети навантажувачем");
    }

    @Override
    protected void load() {
        stage("завантажити");
        log("  завантажено контейнери, вантаж закріплено й зважено");
    }

    @Override
    protected void cleanCabin() {
        stage("прибирання салону");
        log("  очищено вантажний відсік замість салону");
    }
}
