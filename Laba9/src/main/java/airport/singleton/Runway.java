package airport.singleton;

/** Злітно-посадкова смуга: номер і стан (вільна / зайнята певним рейсом). */
class Runway {
    private final int number;
    private String occupiedBy; // null — смуга вільна

    Runway(int number) {
        this.number = number;
    }

    int getNumber() {
        return number;
    }

    boolean isFree() {
        return occupiedBy == null;
    }

    String getOccupiedBy() {
        return occupiedBy;
    }

    void occupy(String flightNumber) {
        occupiedBy = flightNumber;
    }

    void release() {
        occupiedBy = null;
    }
}
