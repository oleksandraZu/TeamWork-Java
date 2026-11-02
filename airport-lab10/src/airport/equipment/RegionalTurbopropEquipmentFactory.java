package ua.bukovyna.airport.equipment;

/** ConcreteFactory: комплект обладнання для класу «регіональний турбогвинтовий літак». */
public class RegionalTurbopropEquipmentFactory implements GroundEquipmentFactory {

    @Override
    public BoardingStairs createStairs() {
        return new RegionalTurbopropStairs();
    }

    @Override
    public PushbackTug createTug() {
        return new RegionalTurbopropTug();
    }

    @Override
    public CargoLoader createLoader() {
        return new RegionalTurbopropLoader();
    }
}
