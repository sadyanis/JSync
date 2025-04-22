package Profile;

import java.nio.file.Path;

public class ConcreteProfileCreator implements ProfileCreator {

    @Override
    public Profile createProfile(Path dossierA, Path dossierB, String name) {
        return new StdProfile(dossierA, dossierB, name) ;
    }

}
