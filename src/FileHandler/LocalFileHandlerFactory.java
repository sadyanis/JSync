package FileHandler;
/*
* Implementation de la factory pour créer un gestionnaire de fichiers local.
* */
public class LocalFileHandlerFactory implements FileHandlerFactory {
    @Override
    public FileHandler createFileHandler() {
        return new LocalFileHandler();
    }
}
