package Profile;

import java.io.IOException;

// class responsable d'enregistrer le profile et le registery dans un fichier
public interface ProfilePersistance {

    public void initProfileWithRegistery(Profile profile ) throws IOException;

    public void updateProfileWithRegistery(Profile profile ) throws IOException;
}
