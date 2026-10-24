package ua.lab8.domain.discount;

import java.math.BigDecimal;
import java.math.RoundingMode;

/** Спільна частина відсоткових знижок (DRY): різниця між типами — лише ставка та назва. */
public abstract class PercentageDiscount implements DiscountPolicy {
    private final BigDecimal rate;

    protected PercentageDiscount(BigDecimal rate) {
        if (rate == null || rate.signum() < 0 || rate.compareTo(BigDecimal.ONE) > 0) {
            throw new IllegalArgumentException("Ставка знижки має бути в діапазоні [0; 1]");
        }
        this.rate = rate;
    }

    @Override
    public BigDecimal discountOn(BigDecimal amount) {
        return amount.multiply(rate).setScale(2, RoundingMode.HALF_UP);
    }
}
