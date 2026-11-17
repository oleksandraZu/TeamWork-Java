package ua.lab13.task2;

import java.time.LocalTime;

public record JournalEntry(LocalTime time, String dispatcher, JournalAction action, String description) {
}
