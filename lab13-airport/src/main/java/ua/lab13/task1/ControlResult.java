package ua.lab13.task1;

public record ControlResult(boolean passed, String stoppedBy, String reason) {

    public static ControlResult ok() {
        return new ControlResult(true, null, null);
    }

    public static ControlResult stopped(String stoppedBy, String reason) {
        return new ControlResult(false, stoppedBy, reason);
    }
}
