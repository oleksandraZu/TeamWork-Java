# QOC-004: Де розмістити формування рахунку, збереження та сповіщення?

* **Question:** Хто виконує «післяобробку» оформленого замовлення?
* **Options:**
  * *Option A:* `OrderController` викликає `repository.save`, `notifier.notify`, `formatter.format` напряму.
  * *Option B:* `Order` сам зберігає себе й надсилає email (Active Record).
  * *Option C:* послідовність `OrderStep` (`ReceiptStep`, `PersistOrderStep`, `NotifyCustomerStep`),
    яку контролер лише обходить.
* **Criteria:**
  * *Low Coupling:* `CBO(OrderController) ≤ 4`.
  * *High Cohesion:* домен не містить інфраструктурних обов'язків.
  * *OCP:* новий крок (аудит, SMS) — без редагування контролера.

| Option | CBO(контролер) | Домен чистий | Розширення без правок |
|--------|:--------------:|:------------:|:---------------------:|
| A | 6 ✗ (3 порти) | ✓ | ✗ |
| B | 2 | ✗ (Order знає файл і SMTP) | ✗ |
| C | 4 ✓ | ✓ | ✓ |

* **Conclusion:** обрано **Option C**. Контролер залежить від одного інтерфейсу `OrderStep`, а
  порядок виводу (рахунок → збереження → email) збігається з legacy. Компроміс: кроки
  виконуються без транзакції (див. ADR-003).
