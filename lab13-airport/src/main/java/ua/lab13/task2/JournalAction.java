package ua.lab13.task2;

public enum JournalAction {
    EXECUTED("ВИКОНАНО"),
    UNDONE("СКАСОВАНО");

    private final String title;

    JournalAction(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
