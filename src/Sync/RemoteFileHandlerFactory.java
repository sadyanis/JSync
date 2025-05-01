package Sync;

public class RemoteFileHandlerFactory implements FileHandlerFactory {
    @Override
    public FileHandler createFileHandler() {
        return new RemoteFileHandler();
    }
}
