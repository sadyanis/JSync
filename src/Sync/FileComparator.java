package Sync;

import java.util.List;

import File.FileComposant;
import File.FileData;

/**
 * Interface représentant un comparateur de fichiers.
 * Définit une méthode pour comparer deux dossiers et un registre afin de déterminer les actions de synchronisation nécessaires.
 */
public interface FileComparator {
    /**
     * Compare deux listes de composants de fichiers (dossiers A et B) avec une liste de registre.
     * Retourne une liste d'actions de synchronisation nécessaires pour aligner les dossiers.
     *
     * @param DossierA La liste des composants de fichiers du dossier A.
     * @param DossierB La liste des composants de fichiers du dossier B.
     * @param Register La liste des composants de fichiers du registre.
     * @return Une liste d'actions de synchronisation.
     */
    List<SyncAction> compare (List<FileComposant> DossierA , List<FileComposant> DossierB , List<FileComposant> Register);
}
