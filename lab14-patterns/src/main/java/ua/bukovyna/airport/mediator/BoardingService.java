package ua.bukovyna.airport.mediator;

public class BoardingService extends GroundService {

    public BoardingService() {
        super("Посадка");
    }

    public void startBoarding() {
        notifyMediator(GroundEvent.BOARDING_STARTED);
    }

    public void finishBoarding() {
        notifyMediator(GroundEvent.BOARDING_FINISHED);
    }
}
