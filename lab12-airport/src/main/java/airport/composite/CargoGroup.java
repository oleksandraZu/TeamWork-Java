package airport.composite;

import airport.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/** Composite: будь-яка група вантажів (літак, відсік, контейнер, палета). */
public class CargoGroup implements CargoComponent {

    private final String kind;
    private final String name;
    private final List<CargoComponent> children = new ArrayList<>();

    public CargoGroup(String kind, String name) {
        this.kind = kind;
        this.name = name;
    }

    public void add(CargoComponent component) {
        children.add(component);
        Log.log("Вантаж", getName() + ": додано " + component.getName());
    }

    @Override
    public String getName() {
        return kind + " «" + name + "»";
    }

    @Override
    public double getWeight() {
        double total = 0;
        for (CargoComponent child : children) {
            total += child.getWeight();
        }
        return total;
    }

    @Override
    public int getPlaces() {
        int total = 0;
        for (CargoComponent child : children) {
            total += child.getPlaces();
        }
        return total;
    }

    @Override
    public void print(int depth) {
        Log.log("Вантаж", "  ".repeat(depth) + getName()
                + " — " + String.format(Locale.ROOT, "%.1f", getWeight()) + " кг, місць: " + getPlaces());
        for (CargoComponent child : children) {
            child.print(depth + 1);
        }
    }
}
