package Registre;

import java.util.HashMap;
import java.util.Map;

public class RegistreXML implements RegistreImplementation {
    private String cheminFichier;

    public RegistreXML(String cheminFichier) {
        this.cheminFichier = cheminFichier;
    }

    @Override
    public void ecrire(Map<String, Long> donnees) {
        
    }

    @Override
    public Map<String, Long> lire() {
        
        return new HashMap<>();
    }
}

