# Лабораторна робота №10 — варіант 1, аеропорт «Буковина»

Maven-проєкт (Java 17). Три ситуації, по одному шаблону на кожну:

| Ситуація | Шаблон | Пакет |
|---|---|---|
| 1. Формування польотного завдання | Builder | `ua.bukovyna.airport.flighttask` |
| 2. Комплекти наземного обладнання | Abstract Factory | `ua.bukovyna.airport.equipment` |
| 3. Сповіщення пасажирів про зміну рейсу | Factory Method | `ua.bukovyna.airport.notification` |

Запуск (раннер `Main`, окремий метод на кожну ситуацію):

```
mvn compile exec:java
```

Діаграми класів: `docs/diagrams/` (`.puml` + `.svg` + `.png`).
