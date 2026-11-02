package ua.bukovyna.airport.equipment;

/** ConcreteFactory: комплект обладнання для класу «широкофюзеляжний літак». */
public class WidebodyEquipmentFactory implements GroundEquipmentFactory {

    @Override
    public BoardingStairs createStairs() {
        return new WidebodyStairs();
    }

    @Override
    public PushbackTug createTug() {
        return new WidebodyTug();
    }

    @Override
    public CargoLoader createLoader() {
        return new WidebodyLoader();
    }
}
