package variant1_orderprocessing.domain;

public final class OrderItem {
    private final Product product;
    private final int quantity;

    private OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public static OrderItem of(Product product, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException("Товар обов'язковий");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Кількість має бути більшою за 0");
        }
        return new OrderItem(product, quantity);
    }

    /** Information Expert: рядок знає ціну товару і кількість. */
    public double subtotal() {
        return product.getPrice() * quantity;
    }

    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }
}
