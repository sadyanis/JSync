package Profile;

import java.io.IOException;
import java.util.List;

// class responsable d'enregistrer le profile et le registery dans un fichier
public interface ProfilePersistance {

    void initProfileWithRegistery(Profile profile ) throws IOException;
     void updateRegistery(List<FileComposant> files);
     Profile loadProfile(String path) throws IOException;

}
