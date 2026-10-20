package ua.lab7.task4;

public class SmartThermostat implements Switchable, TemperatureControllable {

    @Override
    public void powerOn() {
        System.out.println("Thermostat powered on.");
    }

    @Override
    public void powerOff() {
        System.out.println("Thermostat powered off.");
    }

    @Override
    public void setTemperature(double temp) {
        if (!Double.isFinite(temp)) {
            throw new IllegalArgumentException("Temperature must be a finite number");
        }
        System.out.println("Temperature set to " + temp + "°C.");
    }
}
