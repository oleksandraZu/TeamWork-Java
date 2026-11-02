package ua.bukovyna.airport.equipment;

/** ConcreteFactory: комплект обладнання для класу «середньомагістральний лайнер». */
public class MediumHaulEquipmentFactory implements GroundEquipmentFactory {

    @Override
    public BoardingStairs createStairs() {
        return new MediumHaulStairs();
    }

    @Override
    public PushbackTug createTug() {
        return new MediumHaulTug();
    }

    @Override
    public CargoLoader createLoader() {
        return new MediumHaulLoader();
    }
}
