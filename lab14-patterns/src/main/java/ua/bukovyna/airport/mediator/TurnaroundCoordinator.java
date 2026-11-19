package ua.bukovyna.airport.mediator;

import java.util.HashMap;
import java.util.Map;

import ua.bukovyna.airport.common.Logger;

/** ConcreteMediator: усі правила узгодження послідовності наземних служб. */
public class TurnaroundCoordinator implements GroundMediator {

    private static final String NAME = "Координатор";

    private final Map<Class<? extends GroundService>, GroundService> services = new HashMap<>();
    private boolean boardingInProgress;
    private boolean fuelingPending;

    public void register(GroundService service) {
        services.put(service.getClass(), service);
        service.setMediator(this);
    }

    private <T extends GroundService> T get(Class<T> type) {
        return type.cast(services.get(type));
    }

    @Override
    public void notify(GroundService sender, GroundEvent event) {
        switch (event) {
            case BOARDING_STARTED -> {
                boardingInProgress = true;
                Logger.log(NAME, "посадка триває: заправка заборонена");
            }
            case BOARDING_FINISHED -> {
                boardingInProgress = false;
                Logger.log(NAME, "посадка завершена: заправка знову дозволена");
                if (fuelingPending) {
                    fuelingPending = false;
                    Logger.log(NAME, "запускаю відкладену заправку");
                    get(FuelingService.class).start(NAME);
                }
            }
            case CLEANING_FINISHED -> {
                Logger.log(NAME, "прибирання завершено: запускаю кейтеринг");
                get(CateringService.class).start(NAME);
            }
            case BAGGAGE_LOADED -> {
                Logger.log(NAME, "багаж завантажено: дозволяю буксирування");
                get(TowingService.class).start(NAME);
            }
            case FUELING_REQUESTED -> {
                if (boardingInProgress) {
                    fuelingPending = true;
                    Logger.log(NAME, "відхиляю запит від служби '" + sender.getName()
                            + "': триває посадка, заправку відкладено");
                } else {
                    Logger.log(NAME, "посадки немає: дозволяю заправку");
                    get(FuelingService.class).start(NAME);
                }
            }
        }
    }
}
