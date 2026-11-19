package airport.bridge;

import airport.Log;

import java.util.List;

/** ConcreteImplementor: LCD-екран (заголовок і таблиця рядків). */
public class LcdScreen implements DisplayDevice {

    @Override
    public String name() {
        return "LCD-екран";
    }

    @Override
    public void render(String title, List<String> lines) {
        Log.log(name(), "заголовок: " + title);
        for (String line : lines) {
            Log.log(name(), "рядок таблиці: " + line);
        }
    }
}
