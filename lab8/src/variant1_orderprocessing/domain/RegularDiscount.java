package variant1_orderprocessing.domain;

public final class RegularDiscount implements DiscountPolicy {
    private static final double RATE = 0.05;

    @Override
    public double discountFor(double subtotal) {
        return subtotal * RATE;
    }
}
