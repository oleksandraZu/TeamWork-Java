package variant1_orderprocessing.domain;

/** Polymorphism: кожен тип клієнта володіє власною політикою знижки (замість if-else). */
public enum CustomerType {
    NEW(new NoDiscount()),
    REGULAR(new RegularDiscount()),
    VIP(new VipDiscount());

    private final DiscountPolicy discountPolicy;

    CustomerType(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }

    public DiscountPolicy discountPolicy() {
        return discountPolicy;
    }

    public static CustomerType parse(String text) {
        if (text != null) {
            for (CustomerType type : values()) {
                if (type.name().equalsIgnoreCase(text.trim())) {
                    return type;
                }
            }
        }
        throw new IllegalArgumentException("Невідомий тип клієнта: " + text + " (очікується New/Regular/VIP)");
    }
}
