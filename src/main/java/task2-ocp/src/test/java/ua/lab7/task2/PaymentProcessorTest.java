package ua.lab7.task2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;
import ua.lab7.task2.extension.ApplePayPayment;
import ua.lab7.task2.method.BankTransferPayment;
import ua.lab7.task2.method.CreditCardPayment;
import ua.lab7.task2.method.CryptoPayment;
import ua.lab7.task2.method.PayPalPayment;

class PaymentProcessorTest {

    private final PaymentProcessor processor = new PaymentProcessor(List.of(
            new CreditCardPayment(), new PayPalPayment(), new BankTransferPayment(), new CryptoPayment()));

    private static BigDecimal bd(String v) {
        return new BigDecimal(v);
    }

    @Test
    void creditCardFeeIsPercentPlusFixed() {
        assertEquals(bd("25.30"), processor.calculateProcessingFee(bd("1000"), "CREDIT_CARD"));
    }

    @Test
    void payPalFeeIsPercent() {
        assertEquals(bd("34.00"), processor.calculateProcessingFee(bd("1000"), "PAYPAL"));
    }

    @Test
    void bankTransferFeeIsFixed() {
        assertEquals(bd("5.00"), processor.calculateProcessingFee(bd("1"), "BANK_TRANSFER"));
        assertEquals(bd("5.00"), processor.calculateProcessingFee(bd("1000000"), "BANK_TRANSFER"));
    }

    @Test
    void cryptoFeeIsClampedBetweenOneAndTwenty() {
        assertEquals(bd("1.00"), processor.calculateProcessingFee(bd("10"), "CRYPTO"));
        assertEquals(bd("8.00"), processor.calculateProcessingFee(bd("1000"), "CRYPTO"));
        assertEquals(bd("20.00"), processor.calculateProcessingFee(bd("100000"), "CRYPTO"));
    }

    @Test
    void unsupportedMethodIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> processor.calculateProcessingFee(bd("10"), "CASH"));
        PaymentRequest request = new PaymentRequest("t", bd("10"), "USD", "p");
        assertThrows(IllegalArgumentException.class, () -> processor.processPayment(request, "CASH"));
    }

    @Test
    void processPaymentSucceedsForAllKnownMethods() {
        PaymentRequest request = new PaymentRequest("t", bd("100"), "USD", "p");
        for (String id : List.of("CREDIT_CARD", "PAYPAL", "BANK_TRANSFER", "CRYPTO")) {
            assertTrue(processor.processPayment(request, id), id);
        }
    }

    /** OCP: нова стратегія підключається без змін у PaymentProcessor. */
    @Test
    void newMethodCanBeAddedWithoutChangingProcessor() {
        PaymentProcessor extended = new PaymentProcessor(List.of(new CreditCardPayment(), new ApplePayPayment()));
        assertEquals(bd("15.00"), extended.calculateProcessingFee(bd("1000"), "APPLE_PAY"));
        assertTrue(extended.processPayment(new PaymentRequest("t", bd("100"), "USD", "p"), "APPLE_PAY"));
    }

    @Test
    void duplicateMethodIdsAreRejected() {
        assertThrows(IllegalArgumentException.class,
                () -> new PaymentProcessor(List.of(new PayPalPayment(), new PayPalPayment())));
    }

    @Test
    void requestRejectsNonPositiveAmount() {
        assertThrows(IllegalArgumentException.class, () -> new PaymentRequest("t", bd("0"), "USD", "p"));
    }
}
