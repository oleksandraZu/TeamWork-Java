package ua.lab7.task5.domain;

/** Абстракція аудит-логу. */
public interface AuditLogger {
    void logTransaction(String message);
}
