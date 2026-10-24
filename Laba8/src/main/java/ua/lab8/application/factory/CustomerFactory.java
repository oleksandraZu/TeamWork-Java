package ua.lab8.application.factory;

import java.util.concurrent.atomic.AtomicInteger;
import ua.lab8.application.dto.PlaceOrderCommand;
import ua.lab8.domain.Customer;
import ua.lab8.domain.discount.DiscountPolicyRegistry;

/** Pure Fabrication: перетворює дані команди на валідного клієнта. */
public final class CustomerFactory {
    private final DiscountPolicyRegistry discountPolicies;
    private final AtomicInteger customerSequence = new AtomicInteger(1);

    public CustomerFactory(DiscountPolicyRegistry discountPolicies) {
        this.discountPolicies = discountPolicies;
    }

    public Customer create(PlaceOrderCommand command) {
        return Customer.register(
                customerSequence.getAndIncrement(),
                command.customerName(),
                command.customerEmail(),
                discountPolicies.forType(command.customerType()));
    }
}
