package ua.lab7.task4;

import java.util.List;

/**
 * Клієнт. Кожен метод залежить лише від тієї ролі, яку реально викликає,
 * тому йому не можна передати пристрій без відповідної можливості (перевірка компілятором).
 */
public class SmartHomeController {

    public void powerOffAll(List<? extends Switchable> devices) {
        devices.forEach(Switchable::powerOff);
    }

    public void setComfortTemperature(TemperatureControllable device, double temp) {
        device.setTemperature(temp);
    }

    public void setEveningLight(Dimmable light) {
        light.setBrightness(40);
    }

    public void prepareMorningCoffee(Switchable machine, CoffeeBrewer brewer) {
        machine.powerOn();
        brewer.brewCoffee();
    }

    /** Зручна перегрузка для пристроїв, що є одночасно Switchable і CoffeeBrewer. */
    public <T extends Switchable & CoffeeBrewer> void prepareMorningCoffee(T device) {
        prepareMorningCoffee(device, device);
    }
}
