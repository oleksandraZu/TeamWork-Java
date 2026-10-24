package variant1_orderprocessing.application;

/** DTO результату для CLI. */
public record OrderResult(int orderId, String customerName, String customerEmail,
                          double totalAmount, double discountAmount, double taxAmount, double finalAmount) {
}
