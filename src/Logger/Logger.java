package Logger;

/*
* interface qui définit les méthodes de journalisation.
* */
public interface Logger {
    Logger instanceLogger();
    Logger getInstance();
    void log(String message);
    void logError(String message);
}
