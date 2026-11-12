package airport.flyweight;

import java.util.List;

/** Flyweight: спільні (внутрішні) дані класу обслуговування. Незмінний об'єкт. */
public final class ServiceClassData {

    private final String name;
    private final String description;
    private final int seatWidthCm;
    private final int pitchCm;
    private final int reclineDegrees;
    private final List<String> amenities;
    private final String image;

    public ServiceClassData(String name, String description, int seatWidthCm, int pitchCm,
                            int reclineDegrees, List<String> amenities, String image) {
        this.name = name;
        this.description = description;
        this.seatWidthCm = seatWidthCm;
        this.pitchCm = pitchCm;
        this.reclineDegrees = reclineDegrees;
        this.amenities = amenities;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " — " + description + "; ширина крісла " + seatWidthCm + " см, крок " + pitchCm
                + " см, нахил спинки " + reclineDegrees + "°, зручності: " + String.join(", ", amenities)
                + ", зображення: " + image;
    }
}
