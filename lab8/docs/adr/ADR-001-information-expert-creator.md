# ADR-001: Перенесення обчислень та створення об'єктів у доменні сутності

## Статус
Прийнято (Accepted)

## Контекст
У `LegacyOrderProcessor.processConsoleOrder()` один метод виконує введення з консолі, створення об'єктів, розрахунок суми/знижки/ПДВ, форматування рахунку, «збереження» та «email». Сутності `Customer`, `Product`, `OrderItem`, `Order` — пасивні (public-поля, `type`/`status` як `String`), перевірок даних немає (невідомий тип клієнта мовчки дає знижку 0). Порушення: інкапсуляції, Information Expert, Creator, Demeter (`order.customer.type`, `item.product.price`); Bloated Controller; CBO методу = 5.

## QOC
- **Question:** Хто відповідає за розрахунок підсумків замовлення та створення валідних об'єктів?
- **Options:**
  - A: залишити в `LegacyOrderProcessor` / `Main`;
  - B: окремий `OrderService` з геттерами сутностей (Anemic);
  - C: розрахунок у `Order`/`OrderItem`/`Customer`, створення через статичні фабрики.
- **Criteria:** Information Expert (дані й логіка разом); Low Coupling; інкапсуляція (валідний стан завжди).
- **Conclusion:** Option C. A і B лишають сутності пасивними, а сервіс зчепленим з усіма полями.

## Рішення
- `OrderItem.subtotal()`, `Order.subtotal()/process()`, `Customer.discountFor()` — логіка в носіях даних (**Information Expert**).
- Поля `private final`, сеттерів немає, колекція — `List.copyOf` (**інкапсуляція**).
- `Customer.register`, `Product.of`, `OrderItem.of`, `Order.create` — статичні фабрики з валідацією (**Creator**); `OrderFactory` збирає агрегат із команди.
- `Order.customerName()/customerEmail()` замість ланцюжків `getCustomer().getX()` (**Закон Деметри**).

## Наслідки
* **Позитивні:** неможливо створити невалідний об'єкт; зміна формули — лише в сутностях; сервіс-«god method» зник.
* **Негативні / компроміси:** `Order` тепер залежить від `Customer`, `OrderItem`, `OrderStatus`, `TaxPolicy` (CBO = 4, на межі норми).
