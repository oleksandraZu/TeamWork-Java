# ADR-003: Поліморфні політики знижок і захищена варіативність податку

## Статус
Прийнято (Accepted)

## Контекст
Знижка обчислюється каскадом `if (VIP) … else if (Regular) … else if (New)` за рядковим типом клієнта; ставка ПДВ 0.20 зашита в формулу.

## QOC
- **Question:** Як ізолювати змінні правила знижки та податку?
- **Options:** A: `switch` у `Order`; B: `DiscountPolicy` + реалізації, вибір через `CustomerType`; C: окремий реєстр політик у сервісі.
- **Criteria:** додавання нового типу без зміни існуючої логіки; Low Coupling `Order`; простота.
- **Conclusion:** Option B для знижок (enum-диспетчеризація замість `if-else`), `TaxPolicy` для податку.

## Рішення
- Інтерфейс `DiscountPolicy` + `NoDiscount`, `RegularDiscount`, `VipDiscount` (**Polymorphism**); `CustomerType` (enum) зіставляє тип клієнта з політикою; невідомий тип → `IllegalArgumentException`.
- Інтерфейс `TaxPolicy` + `VatTax` (**Protected Variations**); `Order` залежить лише від інтерфейсу.

## Наслідки
* **Позитивні:** новий тип клієнта = новий клас + константа enum; `Order.process()` не змінюється.
* **Негативні / компроміси:** `CustomerType` знає конкретні політики (CBO = 4); для повної відкритості можна замінити на реєстр.
