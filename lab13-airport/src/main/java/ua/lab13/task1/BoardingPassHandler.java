package ua.lab13.task1;

public class BoardingPassHandler extends ControlHandler {

    @Override
    protected String check(Passenger passenger) {
        return passenger.boardingPassValid() ? null : "посадковий талон недійсний";
    }

    @Override
    public String title() {
        return "Перевірка талона";
    }
}
