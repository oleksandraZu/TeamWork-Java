package variant1_orderprocessing.domain;

/** Protected Variations: стабільний контракт для змінного алгоритму знижки. */
public interface DiscountPolicy {
    double discountFor(double subtotal);
}
