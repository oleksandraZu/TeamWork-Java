package variant1_orderprocessing.domain;

public final class NoDiscount implements DiscountPolicy {
    private static final double RATE = 0.0;

    @Override
    public double discountFor(double subtotal) {
        return subtotal * RATE;
    }
}
