package Profile;

import java.nio.file.Path;

public interface ProfileCreator {

    Profile createProfile(Path dossierA, Path dossierB, String name);
}
