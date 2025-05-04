package Profile;

import java.nio.file.Path;
/**
 * Classe représentant un profil de synchronisation standard.
 * Implémente l'interface {@link Profile} pour fournir des informations sur les dossiers et leur localisation.
 */
public class StdProfile implements Profile {
    private String folderA,folderB;
    private String Name;
    private Boolean isLocalA;
    private Boolean isLocalB;

    /**
     * Constructeur pour initialiser un profil standard.
     *
     * @param folderA  Le chemin du dossier A.
     * @param folderB  Le chemin du dossier B.
     * @param Name     Le nom du profil.
     * @param isLocalA Indique si le dossier A est local.
     * @param isLocalB Indique si le dossier B est local.
     */
    public StdProfile(String folderA, String folderB, String Name ,Boolean isLocalA, Boolean isLocalB) {
        this.folderA = folderA;
        this.folderB = folderB;
        this.Name = Name;
    }


    @Override
    public String getName() {
        return this.Name;
    }

    @Override
    public String getPathFolderA() {
        return this.folderA;
    }

    @Override
    public String getPathFolderB() {
        return this.folderB;
    }

    @Override
    public boolean isFolderALocal() {
        return false;
    }

    @Override
    public boolean isFolderBLocal() {
        return false;
    }
}
