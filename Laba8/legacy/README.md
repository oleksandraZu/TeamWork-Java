# Вихідний (legacy) код — варіант 1

Файли з `variants-java.xml` (варіант 1), **розкладені по класах без зміни логіки**
(в оригіналі всі `public class` лежали в одному файлі, що не компілюється в Java).
Використовується лише як «до» для аудиту та метрик (`python3 tools/metrics.py legacy`).
Каталог `legacy/` не входить у Maven-збірку.

Запуск для порівняння поведінки:

```bash
javac -d /tmp/legacy legacy/variant1_orderprocessing/*.java
printf "Іван\nVIP\n" | java -cp /tmp/legacy variant1_orderprocessing.LegacyMain
```
