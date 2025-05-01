package Profile;

import java.io.IOException;
import java.util.List;

// class responsable d'enregistrer le profile et le registery dans un fichier
public interface ProfilePersistance {

    public void initProfileWithRegistery(Profile profile ) throws IOException;
    public void updateRegistery(List<FileComposant> files);

}
