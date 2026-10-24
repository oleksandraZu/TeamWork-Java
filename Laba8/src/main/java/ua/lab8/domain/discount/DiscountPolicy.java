package ua.lab8.domain.discount;

import java.math.BigDecimal;

/** Protected Variations: стабільний контракт навколо змінної частини — правил знижок. */
public interface DiscountPolicy {

    /** Ім\u2019я типу клієнта, за яким політику можна знайти ("VIP", "Regular", "New"). */
    String name();

    BigDecimal discountOn(BigDecimal amount);
}
