package ua.lab13.task1;

public class CustomsControlHandler extends ControlHandler {

    @Override
    protected String check(Passenger passenger) {
        return passenger.customsDeclarationOk() ? null : "митна декларація не пройшла перевірку";
    }

    @Override
    public String title() {
        return "Митний контроль";
    }
}
