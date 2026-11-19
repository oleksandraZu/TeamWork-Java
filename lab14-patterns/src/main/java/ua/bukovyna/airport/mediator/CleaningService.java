package ua.bukovyna.airport.mediator;

public class CleaningService extends GroundService {

    public CleaningService() {
        super("Прибирання");
    }

    public void finishCleaning() {
        notifyMediator(GroundEvent.CLEANING_FINISHED);
    }
}
