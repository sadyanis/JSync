package Profile;

import java.nio.file.Path;
/**
 * Interface représentant un profil de synchronisation.
 * Définit les méthodes pour accéder aux informations des dossiers et leur localisation.
 */
public interface Profile {

    public String getName();
    public String getPathFolderA();
    public String getPathFolderB();
    public boolean isFolderALocal();
    public boolean isFolderBLocal();
}
