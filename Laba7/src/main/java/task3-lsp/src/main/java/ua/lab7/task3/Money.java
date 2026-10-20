package ua.lab7.task3;

import java.math.BigDecimal;
import java.util.Objects;

/** Незмінний Value Object: сума грошей не може бути null або від'ємною (Guard Clauses, fail-fast). */
public record Money(BigDecimal amount) implements Comparable<Money> {

    public static final Money ZERO = new Money(BigDecimal.ZERO);

    public Money {
        Objects.requireNonNull(amount, "amount");
        if (amount.signum() < 0) {
            throw new IllegalArgumentException("Money cannot be negative: " + amount);
        }
    }

    public static Money of(String value) {
        return new Money(new BigDecimal(value));
    }

    public boolean isPositive() {
        return amount.signum() > 0;
    }

    public Money plus(Money other) {
        return new Money(amount.add(other.amount));
    }

    /** Попередня умова: other не більше за this (інакше результат порушив би інваріант). */
    public Money minus(Money other) {
        return new Money(amount.subtract(other.amount));
    }

    @Override
    public int compareTo(Money other) {
        return amount.compareTo(other.amount);
    }

    @Override
    public String toString() {
        return amount.toPlainString();
    }
}
