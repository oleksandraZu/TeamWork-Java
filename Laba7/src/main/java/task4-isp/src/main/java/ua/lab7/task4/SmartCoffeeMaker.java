package ua.lab7.task4;

public class SmartCoffeeMaker implements Switchable, CoffeeBrewer {

    @Override
    public void powerOn() {
        System.out.println("Coffee maker powered on.");
    }

    @Override
    public void powerOff() {
        System.out.println("Coffee maker powered off.");
    }

    @Override
    public void brewCoffee() {
        System.out.println("Brewing fresh coffee...");
    }
}
