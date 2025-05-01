package Registre;

import java.util.Map;

//Bridge pour separer ce qu'on fait avec un registre (l'abstraction) de comment on le stocke (l'implémentation : JSON, XML, etc)

public abstract class Registre {
    protected RegistreImplementation implementation;

    public Registre(RegistreImplementation implementation) {
        this.implementation = implementation;
    }

    public abstract void sauvegarder(Map<String, Long> donnees);
    public abstract Map<String, Long> charger();
}
