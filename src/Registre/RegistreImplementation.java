package Registre;

import java.util.Map;

public interface RegistreImplementation {
    void ecrire(Map<String, Long> donnees);
    Map<String, Long> lire();
}
