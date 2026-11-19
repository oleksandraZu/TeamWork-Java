package ua.bukovyna.airport.mediator;

public class BaggageLoadingService extends GroundService {

    public BaggageLoadingService() {
        super("Багаж");
    }

    public void finishLoading() {
        notifyMediator(GroundEvent.BAGGAGE_LOADED);
    }
}
