# QOC-002: Хто створює `Order`, `OrderItem` і `Customer`?

* **Question:** Де і як конструювати агрегат замовлення, щоб він завжди був валідним?
* **Options:**
  * *Option A:* публічні конструктори без аргументів + заповнення полів ззовні (legacy).
  * *Option B:* `Order` сам створює `OrderItem` з `Product` (`order.addItem(product, qty)`).
  * *Option C:* статичні фабричні методи `Customer.register`, `OrderItem.of`, `Order.place`;
    збирання з вхідної команди виконує `OrderFactory`.
* **Criteria:**
  * *Creator:* створює той, хто містить/агрегує об'єкт або має дані для його ініціалізації.
  * *Validity:* об'єкт не існує в невалідному стані.
  * *Low Coupling:* `CBO(Order) ≤ 4`.
  * *Encapsulation:* внутрішні колекції недоступні для зовнішньої зміни.

| Option | Creator | Validity | CBO(Order) | Encapsulation |
|--------|:-------:|:--------:|:----------:|:-------------:|
| A | ✗ | ✗ | 2 | ✗ |
| B | ✓ (контейнер створює елемент) | ✓ | 5 (+`Product`) ✗ | ✓ |
| C | ✓ (static factory + assembler) | ✓ | 4 ✓ | ✓ (`List.copyOf`) |

* **Conclusion:** обрано **Option C**. Option B ідеальний за класичним Creator, але піднімає
  `CBO(Order)` до 5 через залежність від `Product`; статичні фабричні методи прямо дозволені
  методичкою й дають той самий ефект гарантованої валідності. Зв'язування «команда → агрегат»
  виконує `OrderFactory` (Pure Fabrication) з двома допоміжними фабриками.
