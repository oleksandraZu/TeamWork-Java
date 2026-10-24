package ua.lab8.application;

import java.util.ArrayList;
import java.util.List;
import ua.lab8.application.dto.ProductView;
import ua.lab8.application.port.ProductCatalog;
import ua.lab8.domain.Product;

/** Use-case «переглянути каталог». */
public final class CatalogController {
    private final ProductCatalog catalog;

    public CatalogController(ProductCatalog catalog) {
        this.catalog = catalog;
    }

    public List<ProductView> listProducts() {
        List<ProductView> views = new ArrayList<>();
        for (Product product : catalog.findAll()) {
            views.add(new ProductView(product.id(), product.name(), product.price(), product.category()));
        }
        return views;
    }
}
