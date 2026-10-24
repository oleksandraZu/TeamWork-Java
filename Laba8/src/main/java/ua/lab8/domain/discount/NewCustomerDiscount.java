package ua.lab8.domain.discount;

import java.math.BigDecimal;

/** Polymorphism: знижка для клієнтів типу "New" (0.00). */
public final class NewCustomerDiscount extends PercentageDiscount {

    public NewCustomerDiscount() {
        super(new BigDecimal("0.00"));
    }

    @Override
    public String name() {
        return "New";
    }
}
