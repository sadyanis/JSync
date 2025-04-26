package Registre;

import java.util.Map;

public abstract class Registre {
    protected RegistreImplementation implementation;

    public Registre(RegistreImplementation implementation) {
        this.implementation = implementation;
    }

    public abstract void sauvegarder(Map<String, Long> donnees);
    public abstract Map<String, Long> charger();
}
