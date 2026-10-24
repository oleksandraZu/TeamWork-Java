package ua.lab8.domain;

import ua.lab8.domain.discount.DiscountPolicy;

import java.math.BigDecimal;

/** Клієнт. Тип клієнта виражений стратегією знижки, а не рядком "VIP"/"Regular". */
public final class Customer {
    private final int id;
    private final String name;
    private final String email;
    private final DiscountPolicy discountPolicy;

    private Customer(int id, String name, String email, DiscountPolicy discountPolicy) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.discountPolicy = discountPolicy;
    }

    /** Creator (static factory): клієнт існує лише у валідному стані. */
    public static Customer register(int id, String name, String email, DiscountPolicy discountPolicy) {
        if (id <= 0) {
            throw new IllegalArgumentException("Ідентифікатор клієнта має бути додатним");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Ім\u2019я клієнта не може бути порожнім");
        }
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Некоректний email клієнта");
        }
        if (discountPolicy == null) {
            throw new IllegalArgumentException("Політика знижки обов\u2019язкова");
        }
        return new Customer(id, name.trim(), email.trim(), discountPolicy);
    }

    /** Information Expert: клієнт знає свій статус, а отже й свою знижку. */
    public BigDecimal discountOn(BigDecimal amount) {
        return discountPolicy.discountOn(amount);
    }

    public String typeLabel() {
        return discountPolicy.name();
    }

    public int id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String email() {
        return email;
    }
}
