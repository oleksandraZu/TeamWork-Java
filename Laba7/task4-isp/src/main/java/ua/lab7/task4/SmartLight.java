package ua.lab7.task4;

public class SmartLight implements Switchable, Dimmable {

    @Override
    public void powerOn() {
        System.out.println("Light turned on.");
    }

    @Override
    public void powerOff() {
        System.out.println("Light turned off.");
    }

    @Override
    public void setBrightness(int level) {
        if (level < 0 || level > 100) {
            throw new IllegalArgumentException("Brightness must be within 0..100");
        }
        System.out.println("Brightness set to " + level + "%.");
    }
}
