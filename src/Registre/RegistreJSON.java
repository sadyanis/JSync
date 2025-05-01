package Registre;

import java.util.HashMap;
import java.util.Map;

public class RegistreJSON implements RegistreImplementation {
    private String cheminFichier;

    public RegistreJSON(String cheminFichier) {
        this.cheminFichier = cheminFichier;
    }

    @Override
    public void ecrire(Map<String, Long> donnees) {
        // Implémentation pour écrire dans un fichier JSON
    }

    @Override
    public Map<String, Long> lire() {
        // Implémentation pour lire depuis un fichier JSON
        return new HashMap<>();
    }
}

