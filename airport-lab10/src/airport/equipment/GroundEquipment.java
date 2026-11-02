package ua.bukovyna.airport.equipment;

/** Спільна абстракція будь-якої одиниці наземного обладнання. */
public interface GroundEquipment {

    /** Клас літака, для якого призначена ця одиниця обладнання. */
    String aircraftClass();

    String description();
}
