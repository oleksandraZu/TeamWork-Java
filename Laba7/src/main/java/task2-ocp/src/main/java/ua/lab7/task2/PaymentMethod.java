package ua.lab7.task2;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Extension Point системи: щоб підтримати новий спосіб оплати, достатньо
 * додати ще одну реалізацію цього інтерфейсу — {@link PaymentProcessor} змінювати не потрібно.
 */
public interface PaymentMethod {

    /** Стабільний ідентифікатор способу оплати (замінює enum PaymentType). */
    String id();

    /** Комісія за обробку платежу. */
    BigDecimal calculateFee(BigDecimal amount);

    /** Проведення платежу; повертає true у разі успіху. */
    boolean pay(PaymentRequest request, BigDecimal fee);

    /** Єдине місце округлення грошей (DRY). */
    static BigDecimal round(BigDecimal value) {
        return value.setScale(2, RoundingMode.HALF_UP);
    }
}
