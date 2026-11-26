# ЛР 16. Memento, Visitor, Iterator - варіант 1 «Аеропорт «Буковина»»

Java 21, Maven.

```
mvn compile exec:java      # запуск демонстрації
mvn package && java -jar target/lab16-behavioral-1.0.jar
```

| Завдання | Шаблон | Пакет | Метод раннера |
|---|---|---|---|
| 1. Операції над об'єктами аеропорту | Visitor | `ua.bukovyna.airport.visitor` | `Main.task1Visitor()` |
| 2. Чернетка розкладу | Memento | `ua.bukovyna.airport.schedule` | `Main.task2Memento()` |
| 3. Обхід пасажирів рейсу | Iterator | `ua.bukovyna.airport.cabin` | `Main.task3Iterator()` |

Діаграми: `docs/diagrams/` (PlantUML `.puml`; експортуйте в PNG/SVG).
