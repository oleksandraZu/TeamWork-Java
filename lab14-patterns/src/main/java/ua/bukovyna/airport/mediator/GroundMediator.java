package ua.bukovyna.airport.mediator;

public interface GroundMediator {
    void notify(GroundService sender, GroundEvent event);
}
