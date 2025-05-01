package Sync;

public class LocalFileHandlerFactory implements FileHandlerFactory {
    @Override
    public FileHandler createFileHandler() {
        return new LocalFileHandler();
    }
}
