package ua.lab8.domain.discount;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Pure Fabrication + Dictionary Dispatch: замінює каскад if-else за типом клієнта.
 * Новий тип клієнта = новий клас + register(...), без змін в Order/Customer/контролері (OCP).
 */
public final class DiscountPolicyRegistry {
    private final Map<String, DiscountPolicy> byName = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    private DiscountPolicyRegistry() {
    }

    public static DiscountPolicyRegistry withDefaults() {
        DiscountPolicyRegistry registry = new DiscountPolicyRegistry();
        registry.register(new NewCustomerDiscount());
        registry.register(new RegularDiscount());
        registry.register(new VipDiscount());
        return registry;
    }

    public void register(DiscountPolicy policy) {
        byName.put(policy.name(), policy);
    }

    public DiscountPolicy forType(String customerType) {
        if (customerType == null || customerType.isBlank()) {
            throw new IllegalArgumentException("Тип клієнта не вказано. Доступні: " + supportedTypes());
        }
        DiscountPolicy policy = byName.get(customerType.trim());
        if (policy == null) {
            throw new IllegalArgumentException(
                    "Невідомий тип клієнта: " + customerType.trim() + ". Доступні: " + supportedTypes());
        }
        return policy;
    }

    public List<String> supportedTypes() {
        return new ArrayList<>(byName.keySet());
    }
}
