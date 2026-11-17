package ua.lab13.task1;

import java.util.Locale;

public class CarryOnWeightHandler extends ControlHandler {
    private final double maxWeightKg;

    public CarryOnWeightHandler(double maxWeightKg) {
        this.maxWeightKg = maxWeightKg;
    }

    @Override
    protected String check(Passenger passenger) {
        if (passenger.carryOnWeightKg() > maxWeightKg) {
            return String.format(Locale.ROOT, "вага ручної поклажі %.1f кг перевищує ліміт %.1f кг",
                    passenger.carryOnWeightKg(), maxWeightKg);
        }
        return null;
    }

    @Override
    public String title() {
        return "Перевірка ваги";
    }
}
