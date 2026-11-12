package airport.bridge;

import airport.Log;

import java.util.List;

/** ConcreteImplementor: проєктор (додається одним новим класом). */
public class Projector implements DisplayDevice {

    @Override
    public String name() {
        return "Проєктор";
    }

    @Override
    public void render(String title, List<String> lines) {
        Log.log(name(), "проєкція на екран: " + title);
        for (String line : lines) {
            Log.log(name(), "проєкція: " + line);
        }
    }
}
