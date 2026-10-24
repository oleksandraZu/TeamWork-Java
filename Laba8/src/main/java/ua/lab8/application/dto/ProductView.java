package ua.lab8.application.dto;

import java.math.BigDecimal;

/** DTO для відображення товару в CLI (без доступу до доменної сутності). */
public record ProductView(int id, String name, BigDecimal price, String category) {
}
