package Logger;


public interface Logger {
    Logger instanceLogger();
    Logger getInstance();
    void log(String message);
    void logError(String message);
}
