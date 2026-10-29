package variant1_orderprocessing.domain;

public final class Customer {
    private final int id;
    private final String name;
    private final String email;
    private final CustomerType type;

    private Customer(int id, String name, String email, CustomerType type) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.type = type;
    }

    /** Creator: об'єкт завжди створюється у валідному стані. */
    public static Customer register(int id, String name, String email, CustomerType type) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Ім'я клієнта не може бути порожнім");
        }
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Некоректний email клієнта");
        }
        if (type == null) {
            throw new IllegalArgumentException("Тип клієнта обов'язковий");
        }
        return new Customer(id, name.trim(), email.trim(), type);
    }

    /** Information Expert: клієнт знає свій тип, а отже і свою знижку. */
    public double discountFor(double subtotal) {
        return type.discountPolicy().discountFor(subtotal);
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public CustomerType getType() { return type; }
}
