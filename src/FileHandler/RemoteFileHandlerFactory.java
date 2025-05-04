package FileHandler;
/*
* Factory pour créer un gestionnaire de fichiers distants.
* */
public class RemoteFileHandlerFactory implements FileHandlerFactory {
    @Override
    public FileHandler createFileHandler() {
        return new RemoteFileHandler();
    }
}
