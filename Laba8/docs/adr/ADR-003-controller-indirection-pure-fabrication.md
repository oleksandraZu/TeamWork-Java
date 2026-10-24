# ADR-003: Тонкий CLI, Use-Case Controller, Indirection та Pure Fabrication

## Статус
Прийнято (Accepted)

## Контекст
`processConsoleOrder()` читав консоль, обчислював суми, формував рахунок і «зберігав у БД» —
класичний Bloated Controller / Monolithic Main.

## Рішення
Ланцюжок відповідальностей (залежності спрямовані лише вниз/досередини):

```
ConsoleApp (ui) ──► OrderController ──► OrderFactory ──► Order (domain)
   │                     │
   │                     └──► List<OrderStep> ──► ports (OrderRepository, OrderNotifier, ReceiptFormatter)
   └─ ConsoleCommandParser, OrderRequestParser                   ▲ реалізації — у infrastructure
```

* **Thin Console UI (`ConsoleApp`)** — лише `Scanner` → текст → команда, і результат → `PrintStream`.
  Жодних обчислень. Ввід/вивід інжектуються, тому клас тестується без реальної консолі.
* **Controller (`OrderController`)** — перший об'єкт за межами UI. Приймає `PlaceOrderCommand`,
  викликає `OrderFactory`, `order.process()` і послідовно виконує кроки. Не містить жодного
  `Scanner`/`println`/арифметики. CBO = 4.
* **Indirection** — три порти (`OrderRepository`, `OrderNotifier`, `ReceiptFormatter`) плюс
  `ProductCatalog`; контролер і домен не знають про файл, email-шлюз чи формат рахунку.
  Кроки `OrderStep` — ще один шар опосередкування між контролером і портами.
* **Pure Fabrication** — штучні класи, яких немає в предметній області, але які зберігають
  згуртованість сутностей: `ConsoleCommandParser`, `OrderRequestParser`, `CustomerFactory`,
  `OrderItemsFactory`, `OrderFactory`, `DiscountPolicyRegistry`, `TextReceiptFormatter`,
  `FileOrderRepository`, `SimulatedEmailNotifier`, `InMemoryProductCatalog`.
* **Composition Root (`Main`)** — єдине місце, де створюються конкретні класи й зшиваються шари.

## Наслідки
* **Позитивні:** CLI можна замінити (веб, тести) без змін у контролері; сховище/канал
  сповіщення замінюються одним рядком у `Main`; контролер тестується з реальними
  адаптерами на тимчасовому файлі.
* **Негативні / компроміси:** 10+ допоміжних класів; контролер повертає `List<String>` (рядки
  підтвердження) замість окремого типу результату — свідомо, щоб не додавати 5-ту залежність
  (CBO=4); транзакційності між кроками немає (якщо збереження впаде після `process()`,
  сповіщення не піде, але in-memory стан замовлення вже `PROCESSED`) — для навчальної лаби
  прийнятно, у production потрібна Unit of Work / outbox.
