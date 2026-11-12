package airport.flyweight;

import airport.Log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** FlyweightFactory: створює об'єкт спільних даних один раз і повторно видає той самий. */
public final class ServiceClassFactory {

    public static final String BUSINESS = "Бізнес";
    public static final String PREMIUM = "Преміум";
    public static final String ECONOMY = "Економ";

    private static final Map<String, ServiceClassData> POOL = new HashMap<>();

    private ServiceClassFactory() {
    }

    public static ServiceClassData get(String name) {
        return POOL.computeIfAbsent(name, ServiceClassFactory::create);
    }

    public static int count() {
        return POOL.size();
    }

    private static ServiceClassData create(String name) {
        ServiceClassData data = switch (name) {
            case BUSINESS -> new ServiceClassData(BUSINESS, "крісла з повним розкладанням",
                    56, 120, 45, List.of("Wi-Fi", "гарячі страви", "розетка"), "business.png");
            case PREMIUM -> new ServiceClassData(PREMIUM, "збільшений простір для ніг",
                    48, 96, 25, List.of("Wi-Fi", "закуски"), "premium.png");
            case ECONOMY -> new ServiceClassData(ECONOMY, "стандартне крісло",
                    44, 79, 12, List.of("напої"), "economy.png");
            default -> throw new IllegalArgumentException("Невідомий клас обслуговування: " + name);
        };
        Log.log("ServiceClassFactory", "створено об'єкт спільних даних: " + name);
        return data;
    }
}
