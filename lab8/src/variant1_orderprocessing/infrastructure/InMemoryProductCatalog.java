package variant1_orderprocessing.infrastructure;

import java.util.HashMap;
import java.util.Map;

import variant1_orderprocessing.application.ProductCatalog;
import variant1_orderprocessing.domain.Product;

public final class InMemoryProductCatalog implements ProductCatalog {
    private final Map<Integer, Product> products = new HashMap<>();

    public InMemoryProductCatalog() {
        add(Product.of(1, "Ноутбук", 30000.0, "Electronics"));
        add(Product.of(2, "Мишка", 800.0, "Accessories"));
    }

    private void add(Product product) {
        products.put(product.getId(), product);
    }

    @Override
    public Product getById(int productId) {
        Product product = products.get(productId);
        if (product == null) {
            throw new IllegalArgumentException("Товар не знайдено: " + productId);
        }
        return product;
    }
}
