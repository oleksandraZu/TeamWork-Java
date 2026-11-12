package airport.composite;

/** Component: спільний інтерфейс для окремого вантажу і будь-якої групи вантажів. */
public interface CargoComponent {

    String getName();

    double getWeight();

    int getPlaces();

    void print(int depth);
}
