package ua.lab8.domain.discount;

import java.math.BigDecimal;

/** Polymorphism: знижка для клієнтів типу "Regular" (0.05). */
public final class RegularDiscount extends PercentageDiscount {

    public RegularDiscount() {
        super(new BigDecimal("0.05"));
    }

    @Override
    public String name() {
        return "Regular";
    }
}
