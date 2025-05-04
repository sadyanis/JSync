package File;

import java.util.Date;
import java.util.List;
/**
 * Représente un composant de fichier ou de répertoire.
 * Cette interface définit les méthodes communes pour manipuler des fichiers et des répertoires.
 *
 */
public interface FileComposant {
    String getPath();
    Date getLastModified ();
    boolean isDirectory();
    List<FileComposant> getChildren();

}
