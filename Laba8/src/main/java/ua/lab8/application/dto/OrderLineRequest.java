package ua.lab8.application.dto;

/** DTO: один рядок запиту на замовлення (id товару + кількість). */
public record OrderLineRequest(int productId, int quantity) {
}
