package ua.bukovyna.airport.equipment;

/** AbstractFactory: видає узгоджений комплект обладнання одного класу літака. */
public interface GroundEquipmentFactory {

    BoardingStairs createStairs();

    PushbackTug createTug();

    CargoLoader createLoader();
}
