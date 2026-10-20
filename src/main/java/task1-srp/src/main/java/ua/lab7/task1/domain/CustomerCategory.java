package ua.lab7.task1.domain;

import java.math.BigDecimal;

/**
 * Категорія покупця. Єдине джерело правди (SSOT) для ставок накопичувальної знижки:
 * раніше ці числа були «зашиті» в if/else всередині God Object.
 */
public enum CustomerCategory {
    VIP("VIP", new BigDecimal("0.15")),
    REGULAR("Regular", new BigDecimal("0.05")),
    STANDARD("Standard", BigDecimal.ZERO);

    private final String label;
    private final BigDecimal discountRate;

    CustomerCategory(String label, BigDecimal discountRate) {
        this.label = label;
        this.discountRate = discountRate;
    }

    public String label() {
        return label;
    }

    public BigDecimal discountRate() {
        return discountRate;
    }

    /** Невідома або порожня категорія трактується як STANDARD (без знижки) — як і у вихідному коді. */
    public static CustomerCategory fromString(String raw) {
        if (raw == null) {
            return STANDARD;
        }
        for (CustomerCategory category : values()) {
            if (category.label.equalsIgnoreCase(raw.trim())) {
                return category;
            }
        }
        return STANDARD;
    }
}
