package ua.lab8.domain.tax;

import java.math.BigDecimal;
import java.math.RoundingMode;

/** ПДВ як відсоток від бази оподаткування. */
public final class VatTaxPolicy implements TaxPolicy {
    private final BigDecimal rate;

    public VatTaxPolicy(BigDecimal rate) {
        if (rate == null || rate.signum() < 0) {
            throw new IllegalArgumentException("Ставка ПДВ не може бути від\u2019ємною");
        }
        this.rate = rate;
    }

    /** Стандартна ставка ПДВ 20% з вихідного коду. */
    public static VatTaxPolicy standard() {
        return new VatTaxPolicy(new BigDecimal("0.20"));
    }

    @Override
    public BigDecimal taxOn(BigDecimal taxableAmount) {
        return taxableAmount.multiply(rate).setScale(2, RoundingMode.HALF_UP);
    }
}
