package ua.lab7.task3;

/** Мінімальний спільний контракт: те, що вміють ВСІ рахунки. */
public interface Account {
    String getAccountNumber();

    Money getBalance();
}
