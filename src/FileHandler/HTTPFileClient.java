package FileHandler;

import java.io.IOException;

/**
 * Interface représentant les opérations de gestion de fichiers via HTTP.
 * Cette interface est conçue pour être utilisée dans un contexte de synchronisation distante.
 */
public interface HTTPFileClient {

    /**
     * Récupère la date de dernière modification d'un fichier distant.
     *
     * @param filePath Le chemin du fichier sur le serveur.
     * @return La date de dernière modification en millisecondes depuis l'époque Unix.
     * @throws IOException si une erreur réseau ou HTTP survient.
     */
    long getFileLastModified(String filePath) throws IOException;

    /**
     * Envoie un fichier (ou met à jour) sur le serveur distant via HTTP PUT.
     *
     * @param filePath Le chemin où envoyer le fichier.
     * @param content  Le contenu du fichier sous forme de tableau d’octets.
     * @throws IOException si une erreur réseau ou HTTP survient.
     */
    void putFile(String filePath, byte[] content) throws IOException;

    /**
     * Supprime un fichier distant via HTTP DELETE.
     *
     * @param filePath Le chemin du fichier à supprimer.
     * @throws IOException si une erreur réseau ou HTTP survient.
     */
    void deleteFile(String filePath) throws IOException;
}

