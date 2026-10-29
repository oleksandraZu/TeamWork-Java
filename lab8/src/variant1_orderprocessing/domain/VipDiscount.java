package variant1_orderprocessing.domain;

public final class VipDiscount implements DiscountPolicy {
    private static final double RATE = 0.15;

    @Override
    public double discountFor(double subtotal) {
        return subtotal * RATE;
    }
}
