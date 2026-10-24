# ADR-004: Поліморфні стратегії та захист від змін (Polymorphism + Protected Variations)

## Статус
Прийнято (Accepted)

## Контекст
Знижка залежала від рядкового типу клієнта через каскад `if / else if`; ставки `0.15`, `0.05`,
`0.20` були вбудовані в метод; рахунок, збереження та сповіщення були жорстко прописані.
Кожна зміна бізнес-правила вимагала редагування того самого монолітного методу.

## Рішення
Точки потенційної нестабільності виділено за стабільними інтерфейсами:

| Нестабільність | Контракт (Protected Variations) | Реалізації (Polymorphism) |
|----------------|---------------------------------|---------------------------|
| правила знижок за типом клієнта | `DiscountPolicy` | `NewCustomerDiscount` 0%, `RegularDiscount` 5%, `VipDiscount` 15% (спільна база `PercentageDiscount`) |
| податкове законодавство | `TaxPolicy` | `VatTaxPolicy` (20%) |
| сховище замовлень | `OrderRepository` | `FileOrderRepository` |
| канал сповіщення | `OrderNotifier` | `SimulatedEmailNotifier` |
| формат рахунку | `ReceiptFormatter` | `TextReceiptFormatter` |
| склад післяобробки | `OrderStep` | `ReceiptStep`, `PersistOrderStep`, `NotifyCustomerStep` |
| джерело товарів | `ProductCatalog` | `InMemoryProductCatalog` |

Каскад `if-else` замінено **Dictionary Dispatch**: `DiscountPolicyRegistry` (`TreeMap` без
урахування регістру) мапить `"vip"` → `VipDiscount`. Невідомий тип — явний
`IllegalArgumentException` зі списком допустимих значень.

```java
// БУЛО (процедурне розгалуження):
if ("VIP".equalsIgnoreCase(order.customer.type)) discountRate = 0.15;
else if ("Regular".equalsIgnoreCase(order.customer.type)) discountRate = 0.05;
else if ("New".equalsIgnoreCase(order.customer.type)) discountRate = 0.0;

// СТАЛО (поліморфізм):
BigDecimal discount = customer.discountOn(subtotal);   // Customer делегує своїй DiscountPolicy
```

**Open/Closed на практиці** (перевірено тестом
`newCustomerTypeCanBeAddedWithoutChangingExistingCode`): новий тип клієнта «Gold» додається
новим класом + `registry.register(...)`, без змін у `Order`, `Customer`, `OrderController`.

## Наслідки
* **Позитивні:** 0 умовних розгалужень за типом клієнта; змінні ставки інкапсульовані в іменованих
  класах; додавання каналу чи формату рахунку не торкається контролера.
* **Негативні / компроміси:** для трьох відсотків окремі класи — більше файлів, ніж потребує
  мінімальне рішення (enum із ставкою). Обрано класи, бо знижки з часом отримують різну логіку
  (поріг накопичення, акції), а enum це заблокував би.
