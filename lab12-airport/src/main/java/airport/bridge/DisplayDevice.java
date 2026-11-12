package airport.bridge;

import java.util.List;

/** Implementor: обладнання, на якому показується табло. */
public interface DisplayDevice {

    String name();

    void render(String title, List<String> lines);
}
