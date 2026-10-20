package ua.lab7.task2;

import java.math.BigDecimal;
import java.util.List;
import ua.lab7.task2.extension.ApplePayPayment;
import ua.lab7.task2.method.BankTransferPayment;
import ua.lab7.task2.method.CreditCardPayment;
import ua.lab7.task2.method.CryptoPayment;
import ua.lab7.task2.method.PayPalPayment;

/** Composition Root завдання 2: тут перелічуються доступні стратегії. */
public class Main {

    public static void main(String[] args) {
        PaymentProcessor processor = new PaymentProcessor(List.of(
                new CreditCardPayment(), new PayPalPayment(), new BankTransferPayment(),
                new CryptoPayment(), new ApplePayPayment()));

        PaymentRequest request = new PaymentRequest("TX-1001", new BigDecimal("250.00"), "USD", "user-42");
        for (String methodId : List.of("CREDIT_CARD", "PAYPAL", "BANK_TRANSFER", "CRYPTO", "APPLE_PAY")) {
            System.out.println("Комісія " + methodId + ": " + processor.calculateProcessingFee(request.amount(), methodId));
            processor.processPayment(request, methodId);
            System.out.println();
        }
    }
}
