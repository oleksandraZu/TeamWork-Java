package ua.bukovyna.airport.mediator;

import ua.bukovyna.airport.common.Logger;

public class FuelingService extends GroundService {

    public FuelingService() {
        super("Заправка");
    }

    public void requestFueling() {
        notifyMediator(GroundEvent.FUELING_REQUESTED);
    }

    public void start(String initiator) {
        Logger.log(getName(), "розпочато заправку борту (за командою: " + initiator + ")");
    }
}
