package Profile;

import java.nio.file.Path;

public class ConcreteProfileCreator implements ProfileCreator {
    String dossierA;
    String dossierB;
    String name;
    public ConcreteProfileCreator(String dossierA, String dossierB, String name) {
        this.dossierA = dossierA;
        this.dossierB = dossierB;
        this.name = name;
    }
    @Override
    public Profile createProfile() {
        return new StdProfile(dossierA, dossierB, name) ;
    }

}
