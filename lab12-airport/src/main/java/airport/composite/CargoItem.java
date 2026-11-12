package airport.composite;

import airport.Log;

import java.util.Locale;

/** Leaf: окрема валіза або посилка. */
public class CargoItem implements CargoComponent {

    private final String kind;
    private final String name;
    private final double weight;

    public CargoItem(String kind, String name, double weight) {
        this.kind = kind;
        this.name = name;
        this.weight = weight;
    }

    @Override
    public String getName() {
        return kind + " «" + name + "»";
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public int getPlaces() {
        return 1;
    }

    @Override
    public void print(int depth) {
        Log.log("Вантаж", "  ".repeat(depth) + getName()
                + " — " + String.format(Locale.ROOT, "%.1f", getWeight()) + " кг, місць: " + getPlaces());
    }
}
