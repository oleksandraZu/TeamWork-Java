package ua.lab7.task4;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import org.junit.jupiter.api.Test;

class SmartHomeTest {

    private static String capture(Runnable action) {
        PrintStream original = System.out;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer, true));
        try {
            action.run();
        } finally {
            System.setOut(original);
        }
        return buffer.toString();
    }

    @Test
    void devicesImplementOnlyTheirOwnRoles() {
        Object thermostat = new SmartThermostat();
        Object light = new SmartLight();
        Object coffee = new SmartCoffeeMaker();

        assertTrue(thermostat instanceof TemperatureControllable);
        assertFalse(thermostat instanceof Dimmable);
        assertFalse(thermostat instanceof CoffeeBrewer);

        assertTrue(light instanceof Dimmable);
        assertFalse(light instanceof TemperatureControllable);
        assertFalse(light instanceof CoffeeBrewer);

        assertTrue(coffee instanceof CoffeeBrewer);
        assertFalse(coffee instanceof TemperatureControllable);
        assertFalse(coffee instanceof Dimmable);
    }

    @Test
    void controllerDrivesEachRole() {
        SmartHomeController controller = new SmartHomeController();
        String out = capture(() -> {
            controller.setComfortTemperature(new SmartThermostat(), 21.0);
            controller.setEveningLight(new SmartLight());
            controller.prepareMorningCoffee(new SmartCoffeeMaker());
        });
        assertTrue(out.contains("Temperature set to 21.0°C."));
        assertTrue(out.contains("Brightness set to 40%."));
        assertTrue(out.contains("Coffee maker powered on."));
        assertTrue(out.contains("Brewing fresh coffee..."));
    }

    @Test
    void powerOffAllWorksForMixedDevices() {
        String out = capture(() -> new SmartHomeController()
                .powerOffAll(List.of(new SmartThermostat(), new SmartLight(), new SmartCoffeeMaker())));
        assertTrue(out.contains("Thermostat powered off."));
        assertTrue(out.contains("Light turned off."));
        assertTrue(out.contains("Coffee maker powered off."));
    }

    @Test
    void guardClausesRejectInvalidValues() {
        assertThrows(IllegalArgumentException.class, () -> new SmartLight().setBrightness(101));
        assertThrows(IllegalArgumentException.class, () -> new SmartLight().setBrightness(-1));
        assertThrows(IllegalArgumentException.class, () -> new SmartThermostat().setTemperature(Double.NaN));
    }
}
