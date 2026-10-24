# Лабораторна робота №8 — Варіант 1 (Java): Інтернет-магазин, обробка замовлення

## 1. Аудит legacy-коду (Завдання 1)
| Проблема | Де |
|---|---|
| Anemic Domain Model: public-поля, `type`/`status` — `String` | `Customer`, `Product`, `OrderItem`, `Order` |
| Bloated Controller: введення, створення, розрахунки, форматування, збереження, email в одному методі | `LegacyOrderProcessor.processConsoleOrder` |
| Procedural if-else за типом клієнта; ставки 0.15/0.05/0.20 «зашиті» | розрахунок знижки/ПДВ |
| Порушення Деметри | `order.customer.type`, `item.product.price` |
| Немає валідації; невідомий тип клієнта → знижка 0 | весь код |
| Висока залежність від 4 класів + `Scanner` (CBO = 5) | `LegacyOrderProcessor` |

Обґрунтування рішень: `docs/adr/ADR-001…003`.

## 2. Відповідність GRASP (Завдання 2–4)
| Патерн | Реалізація |
|---|---|
| Information Expert | `OrderItem.subtotal`, `Order.process/subtotal`, `Customer.discountFor` |
| Creator | `Customer.register`, `Product.of`, `OrderItem.of`, `Order.create`, `OrderFactory` |
| Controller | `PlaceOrderController` |
| Low Coupling / High Cohesion | CLI → DTO → контролер; сутності керують лише власним станом |
| Pure Fabrication | `OrderFactory`, `OrderCompletion`, `ConsoleCommandParser` |
| Indirection | `OrderRepository`, `OrderNotifier`, `ProductCatalog` |
| Polymorphism | `DiscountPolicy` → `NoDiscount`/`RegularDiscount`/`VipDiscount` (через `CustomerType`) |
| Protected Variations | `DiscountPolicy`, `TaxPolicy`, порти репозиторію/сповіщень/каталогу |

Закон Деметри: зовнішній код використовує `order.customerName()/customerEmail()`, а не ланцюжки.

## 3. Метрики (Завдання 5)
Правила підрахунку: враховано типи проєкту та `java.util.Scanner` (консольна інфраструктура); `java.lang.*` та колекції JDK не враховано. Зв'язок — поле, параметр, повернення, локальна змінна, `new`, виклик методу. Для LCOM4 виключено конструктори, статичні фабрики та тривіальні геттери.

| Клас | CBO до | CBO після | LCOM4 до | LCOM4 після |
|---|---|---|---|---|
| `LegacyOrderProcessor` → `ConsoleApp` | 5 | 4 | 1* | 1 |
| `Order` | 2 | 4 | — (методів немає) | 1 |
| `OrderItem` | 1 | 1 | — | 1 |
| `Customer` | 0 | 2 | — | 1 |
| `Product` | 0 | 0 | — | 1** |
| `CustomerType` | — | 4 | — | 1 |
| `PlaceOrderController` | — | 4 | — | 1 |
| `OrderCompletion` | — | 4 | — | 1 |
| `OrderFactory` (збірка, не нормується) | — | 8 | — | 1 |

\* Формально один метод, але він виконує ≥ 6 різних обов'язків — LCOM4 цього не показує; проблема виміряна через CBO та аналіз у п. 1.
\** Немає нетривіальних методів: незмінний об'єкт-значення.

LCOM4 для `Order`: методи `process`, `subtotal`, `customerName`, `customerEmail` пов'язані викликом (`process → subtotal`) та спільним полем `customer` → один зв'язний компонент.

**Висновки.** Уся логіка перейшла до сутностей-носіїв даних, тому CBO `Order` зріс з 2 до 4 — це очікувана ціна Information Expert, у межах норми ≤ 4. Усі нормовані класи (сутності, контролер) мають CBO ≤ 4 і LCOM4 = 1. CBO точки входу (CLI) знизився з 5 до 4, а домен тепер не залежить від UI та інфраструктури (стабільне ядро, I ≈ 0).

## 4. Перевірка
Результати збігаються з legacy: VIP — сума 31600.0, знижка 4740.0, ПДВ 5372.0, до сплати 32232.0. Додано валідацію (невідомий тип клієнта → «Помилка: …»).

Запуск: `javac -encoding UTF-8 -d out $(find src -name '*.java')` → `java -cp out variant1_orderprocessing.Main`.

## 5. Крос-рев'ю (Завдання 6)
Виконується командою в Git-репозиторії: Pull Request між студентами A та B за чек-листом — інкапсуляція, відсутність I/O в контролері та доменних класах, CBO ≤ 4, LCOM4 = 1, кожне рішення має ADR.
