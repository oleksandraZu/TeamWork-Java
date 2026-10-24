package ua.lab8.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

/** Товар каталогу. Створюється одразу у валідному стані, поля незмінні. */
public final class Product {
    private final int id;
    private final String name;
    private final BigDecimal price;
    private final String category;

    public Product(int id, String name, BigDecimal price, String category) {
        if (id <= 0) {
            throw new IllegalArgumentException("Ідентифікатор товару має бути додатним");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Назва товару не може бути порожньою");
        }
        if (price == null || price.signum() < 0) {
            throw new IllegalArgumentException("Ціна товару не може бути від\u2019ємною");
        }
        if (category == null || category.isBlank()) {
            throw new IllegalArgumentException("Категорія товару не може бути порожньою");
        }
        this.id = id;
        this.name = name.trim();
        this.price = price.setScale(2, RoundingMode.HALF_UP);
        this.category = category.trim();
    }

    /** Information Expert: лише товар знає свою ціну, тому саме він рахує вартість N одиниць. */
    public BigDecimal priceFor(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Кількість має бути додатною");
        }
        return price.multiply(BigDecimal.valueOf(quantity));
    }

    public int id() {
        return id;
    }

    public String name() {
        return name;
    }

    public BigDecimal price() {
        return price;
    }

    public String category() {
        return category;
    }
}
