package ua.bukovyna.airport.mediator;

import ua.bukovyna.airport.common.Logger;

public class CateringService extends GroundService {

    public CateringService() {
        super("Кейтеринг");
    }

    public void start(String initiator) {
        Logger.log(getName(), "розпочато завантаження бортового харчування (за командою: " + initiator + ")");
    }
}
