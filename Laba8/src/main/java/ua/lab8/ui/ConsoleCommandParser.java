package ua.lab8.ui;

import java.util.Map;

/** Pure Fabrication: розбір тексту меню в команду. Словник замість switch за рядками. */
public final class ConsoleCommandParser {
    private final Map<String, MenuChoice> keys = Map.of(
            "1", MenuChoice.PLACE_ORDER,
            "2", MenuChoice.SHOW_CATALOG,
            "0", MenuChoice.EXIT,
            "q", MenuChoice.EXIT);

    public MenuChoice parse(String line) {
        if (line == null) {
            return MenuChoice.EXIT;
        }
        return keys.getOrDefault(line.trim().toLowerCase(), MenuChoice.UNKNOWN);
    }
}
