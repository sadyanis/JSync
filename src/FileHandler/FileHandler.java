package FileHandler;

import java.io.IOException;
import java.util.List;

import File.FileComposant;
/**
 * Interface pour la gestion des fichiers.
 * Fournit des méthodes pour récupérer, copier et supprimer des fichiers.
 */
public interface FileHandler {
    /**
     * Récupère une liste de composants de fichiers à partir d'un chemin donné.
     *
     * @param path Le chemin du répertoire ou fichier.
     * @return Une liste de composants de fichiers.
     */
    List<FileComposant> getFiles(String path);
    /**
     * Copie un fichier d'une source vers une destination.
     *
     * @param source Le chemin du fichier source.
     * @param destination Le chemin du fichier de destination.
     * @throws IOException Si une erreur d'entrée/sortie se produit.
     */
    void copyFile(String source, String destination) throws IOException;
    /**
     * Supprime un fichier à partir d'un chemin donné.
     *
     * @param path Le chemin du fichier à supprimer.
     */
    void deleteFile(String path);

}
