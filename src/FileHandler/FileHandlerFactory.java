package FileHandler;

/**
 * Interface qui représente une factory  d'objets FileHandler.
 * Fournit une méthode pour créer un objet {@link FileHandler}.
 */
public interface FileHandlerFactory {
    /**
     * Crée une nouvelle instance de gestionnaire de fichiers.
     *
     * @return Une instance de {@link FileHandler}.
     */
    FileHandler createFileHandler();
}
