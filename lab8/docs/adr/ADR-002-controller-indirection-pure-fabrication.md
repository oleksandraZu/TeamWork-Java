# ADR-002: Тонкий CLI, Use-Case Controller, порти та штучні класи

## Статус
Прийнято (Accepted)

## Контекст
Консольне введення/виведення, бізнес-логіка, збереження у файл і email змішані в одному класі (Bloated Controller / Monolithic Main).

## QOC
- **Question:** Як розділити консоль, координацію сценарію та інфраструктуру?
- **Options:** A: усе в `ConsoleApp`; B: контролер, що сам пише у файл і шле email; C: тонкий CLI → контролер → порти (інтерфейси).
- **Criteria:** Low Coupling (CLI не знає про домен); High Cohesion; підмінюваність сховища/каналу сповіщень.
- **Conclusion:** Option C.

## Рішення
- `ConsoleApp` (Thin UI) — лише `Scanner`/`println`; `ConsoleCommandParser` (**Pure Fabrication**) формує DTO `PlaceOrderCommand`.
- `PlaceOrderController` (**Controller**) — приймає DTO, повертає DTO `OrderResult`, жодного I/O чи обчислень.
- `OrderRepository`, `OrderNotifier`, `ProductCatalog` (**Indirection**) + реалізації `FileOrderRepository`, `ConsoleEmailNotifier`, `InMemoryProductCatalog`.
- `OrderFactory`, `OrderCompletion` (**Pure Fabrication**) — збірка агрегату та завершення (process → save → notify → DTO), щоб контролер лишився з CBO ≤ 4.
- `Main` — composition root, єдине місце з конкретними реалізаціями.

## Наслідки
* **Позитивні:** CLI/контролер не залежать від файлів, email, формули; сховище можна замінити без змін ядра.
* **Негативні / компроміси:** більше класів; `OrderFactory` має CBO = 8 (точка збірки, не нормується).
