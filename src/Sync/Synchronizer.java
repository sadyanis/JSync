package Sync;
import Profile.Profile;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

// Orchestre la synchronisation d'un profile
/**
 * Interface représentant un orchestrateur de synchronisation pour un profil.
 * Fournit une méthode pour synchroniser un profil à partir de son chemin.
 */
public interface Synchronizer {
    /**
     * Synchronise un profil en utilisant le chemin spécifié.
     * @param profilePath Le chemin du profil à synchroniser.
     * @throws IOException                  Si une erreur d'entrée/sortie se produit.
     * @throws ParserConfigurationException Si une erreur de configuration du parseur XML se produit.
     * @throws TransformerException         Si une erreur de transformation XML se produit.
     * @throws SAXException                 Si une erreur SAX se produit.
     */
    void synchronize(String profilePath)throws IOException, ParserConfigurationException, TransformerException, SAXException;
}
