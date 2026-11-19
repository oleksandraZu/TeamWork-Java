package airport.bridge;

import java.util.ArrayList;
import java.util.List;

/** Abstraction: вид табло. Містить міст (посилання) до обладнання. */
public abstract class FlightBoard {

    protected DisplayDevice device;

    protected FlightBoard(DisplayDevice device) {
        this.device = device;
    }

    public void setDevice(DisplayDevice device) {
        this.device = device;
    }

    public void show(List<FlightInfo> flights) {
        List<String> lines = new ArrayList<>();
        for (FlightInfo flight : flights) {
            String line = formatLine(flight);
            if (line != null) {
                lines.add(line);
            }
        }
        device.render(title(), lines);
    }

    protected abstract String title();

    /** Повертає рядок для рейсу або null, якщо рейс на цьому табло не показується. */
    protected abstract String formatLine(FlightInfo flight);
}
