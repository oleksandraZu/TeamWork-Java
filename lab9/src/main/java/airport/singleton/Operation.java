package airport.singleton;

public enum Operation {
    LANDING("посадку"),
    TAKEOFF("зліт");

    private final String title;

    Operation(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
