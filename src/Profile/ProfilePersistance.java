package Profile;

import java.util.List;

// class responsable d'enregistrer le profile et le registery dans un fichier
public interface ProfilePersistance {

    public void initProfileWithRegistery(Profile profile );
    public void updateRegistery(List<FileData> files);

}
