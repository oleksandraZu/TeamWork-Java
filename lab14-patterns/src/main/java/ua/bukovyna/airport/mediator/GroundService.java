package ua.bukovyna.airport.mediator;

import ua.bukovyna.airport.common.Logger;

public abstract class GroundService {

    private final String name;
    protected GroundMediator mediator;

    protected GroundService(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setMediator(GroundMediator mediator) {
        this.mediator = mediator;
    }

    protected void notifyMediator(GroundEvent event) {
        Logger.log(name, "повідомляє координатора: " + event.getDescription());
        mediator.notify(this, event);
    }
}
