package airport.composite;

public interface CargoComponent {

    String getName();

    double getWeight();

    int getPlaces();

    void print(int depth);
}
