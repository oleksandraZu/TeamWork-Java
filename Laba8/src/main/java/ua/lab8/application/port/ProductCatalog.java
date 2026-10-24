package ua.lab8.application.port;

import java.util.List;
import ua.lab8.domain.Product;

/** Indirection: застосунок не знає, де саме зберігаються товари. */
public interface ProductCatalog {

    /** @throws IllegalArgumentException якщо товару з таким id немає */
    Product getById(int productId);

    List<Product> findAll();
}
