package airport.bridge;

import java.util.List;

public interface DisplayDevice {

    String name();

    void render(String title, List<String> lines);
}
