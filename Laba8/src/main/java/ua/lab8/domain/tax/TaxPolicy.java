package ua.lab8.domain.tax;

import java.math.BigDecimal;

/** Protected Variations: податкове законодавство змінюється, контракт — ні. */
public interface TaxPolicy {

    BigDecimal taxOn(BigDecimal taxableAmount);
}
