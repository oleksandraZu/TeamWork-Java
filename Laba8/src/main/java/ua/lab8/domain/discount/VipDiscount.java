package ua.lab8.domain.discount;

import java.math.BigDecimal;

/** Polymorphism: знижка для клієнтів типу "VIP" (0.15). */
public final class VipDiscount extends PercentageDiscount {

    public VipDiscount() {
        super(new BigDecimal("0.15"));
    }

    @Override
    public String name() {
        return "VIP";
    }
}
