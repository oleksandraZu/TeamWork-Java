package ua.bukovyna.airport.mediator;

public enum GroundEvent {
    BOARDING_STARTED("посадка пасажирів почалася"),
    BOARDING_FINISHED("посадка пасажирів завершена"),
    CLEANING_FINISHED("прибирання салону завершено"),
    BAGGAGE_LOADED("завантаження багажу завершено"),
    FUELING_REQUESTED("запит на початок заправки");

    private final String description;

    GroundEvent(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
