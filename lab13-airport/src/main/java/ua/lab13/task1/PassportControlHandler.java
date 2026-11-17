package ua.lab13.task1;

public class PassportControlHandler extends ControlHandler {

    @Override
    protected String check(Passenger passenger) {
        return passenger.passportValid() ? null : "паспорт недійсний або відсутній";
    }

    @Override
    public String title() {
        return "Паспортний контроль";
    }
}
