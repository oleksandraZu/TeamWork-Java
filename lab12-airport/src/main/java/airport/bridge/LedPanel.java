package airport.bridge;

import airport.Log;

import java.util.List;

/** ConcreteImplementor: LED-панель (біжучий рядок великими літерами). */
public class LedPanel implements DisplayDevice {

    @Override
    public String name() {
        return "LED-панель";
    }

    @Override
    public void render(String title, List<String> lines) {
        Log.log(name(), "біжучий рядок: " + title.toUpperCase() + " | " + String.join(" | ", lines).toUpperCase());
    }
}
