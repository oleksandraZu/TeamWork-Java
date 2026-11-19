package ua.bukovyna.airport.mediator;

import ua.bukovyna.airport.common.Logger;

public class TowingService extends GroundService {

    public TowingService() {
        super("Буксирування");
    }

    public void start(String initiator) {
        Logger.log(getName(), "розпочато буксирування борту (за командою: " + initiator + ")");
    }
}
