package ua.lab7.task4;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        SmartThermostat thermostat = new SmartThermostat();
        SmartLight light = new SmartLight();
        SmartCoffeeMaker coffeeMaker = new SmartCoffeeMaker();
        SmartHomeController controller = new SmartHomeController();

        thermostat.powerOn();
        controller.setComfortTemperature(thermostat, 22.5);
        controller.setEveningLight(light);
        controller.prepareMorningCoffee(coffeeMaker);
        controller.powerOffAll(List.of(thermostat, light, coffeeMaker));

        // controller.setEveningLight(thermostat);  // НЕ СКОМПІЛЮЄТЬСЯ: термостат не Dimmable
    }
}
