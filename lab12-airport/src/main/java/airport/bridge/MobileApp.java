package airport.bridge;

import airport.Log;

import java.util.List;

/** ConcreteImplementor: мобільний застосунок (push-сповіщення). */
public class MobileApp implements DisplayDevice {

    @Override
    public String name() {
        return "Мобільний застосунок";
    }

    @Override
    public void render(String title, List<String> lines) {
        for (String line : lines) {
            Log.log(name(), "push «" + title + "»: " + line);
        }
    }
}
