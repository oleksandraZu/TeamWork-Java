# QOC-001: Хто відповідає за розрахунок і застосування знижки клієнта?

* **Question (Питання):** Який об'єкт має обчислювати знижку при оформленні замовлення?
* **Options (Альтернативні варіанти):**
  * *Option A:* залишити розрахунок у `OrderController` / `ConsoleApp` (як у legacy-методі).
  * *Option B:* окремий сервіс `DiscountService.calculate(order)` з `switch` за типом клієнта.
  * *Option C:* `Order` запитує у `Customer` (`customer.discountOn(subtotal)`), а `Customer` делегує
    своїй стратегії `DiscountPolicy`.
* **Criteria (Критерії оцінки за GRASP):**
  * *Information Expert:* хто володіє даними? `Order` знає суму, `Customer` — свій статус.
  * *Low Coupling:* UI/контролер не повинні залежати від алгоритму знижок.
  * *Protected Variations / OCP:* додавання нового типу клієнта не має змушувати змінювати існуючий код.
  * *Cohesion:* жоден клас не стає «сміттєзбірником» чужих полів.

| Option | Info Expert | Low Coupling | Prot. Variations | Cohesion |
|--------|:-----------:|:------------:|:----------------:|:--------:|
| A | ✗ (контролер читає чужі поля) | ✗ | ✗ (if-else) | ✗ |
| B | ✗ (сервіс оперує чужими даними — Anemic Model) | ~ | ✗ (switch у сервісі) | ~ |
| C | ✓ | ✓ | ✓ (нова реалізація інтерфейсу) | ✓ |

* **Conclusion (Рішення):** обрано **Option C**. `Order` як Information Expert підсумку питає в
  `Customer` розмір знижки; `Customer` як Expert свого статусу делегує `DiscountPolicy`. Зміна
  правил знижок не торкається `Order` і контролера. Ціна рішення — три невеликі класи стратегій.
