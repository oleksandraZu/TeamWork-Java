package ua.lab8.infrastructure;

import ua.lab8.application.port.ProductCatalog;
import ua.lab8.domain.Product;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** Каталог у пам\u2019яті з товарами з вихідного коду (Ноутбук, Мишка). */
public final class InMemoryProductCatalog implements ProductCatalog {
    private final Map<Integer, Product> products = new LinkedHashMap<>();

    public InMemoryProductCatalog(List<Product> initial) {
        for (Product product : initial) {
            products.put(product.id(), product);
        }
    }

    public static InMemoryProductCatalog withDemoProducts() {
        return new InMemoryProductCatalog(List.of(
                new Product(1, "Ноутбук", new BigDecimal("30000.00"), "Electronics"),
                new Product(2, "Мишка", new BigDecimal("800.00"), "Accessories")));
    }

    @Override
    public Product getById(int productId) {
        Product product = products.get(productId);
        if (product == null) {
            throw new IllegalArgumentException("Товар з id=" + productId + " не знайдено");
        }
        return product;
    }

    @Override
    public List<Product> findAll() {
        return List.copyOf(products.values());
    }
}
