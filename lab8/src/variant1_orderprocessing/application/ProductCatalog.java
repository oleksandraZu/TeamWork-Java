package variant1_orderprocessing.application;

import variant1_orderprocessing.domain.Product;

/** Indirection: джерело товарів приховане за інтерфейсом. */
public interface ProductCatalog {
    Product getById(int productId);
}
