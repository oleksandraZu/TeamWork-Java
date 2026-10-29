package airport.prototype;

public class Aircraft {
    private final String type;
    private int baggageNormKg;

    public Aircraft(String type, int baggageNormKg) {
        this.type = type;
        this.baggageNormKg = baggageNormKg;
    }

    Aircraft(Aircraft other) {
        this.type = other.type;
        this.baggageNormKg = other.baggageNormKg;
    }

    public String getType() {
        return type;
    }

    public int getBaggageNormKg() {
        return baggageNormKg;
    }
}
