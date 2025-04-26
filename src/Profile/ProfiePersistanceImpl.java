package Profile;

import java.io.File;

public class ProfiePersistanceImpl implements ProfilePersistance {

    private Profile profile;
    private File file;

    public ProfiePersistanceImpl(Profile profile, File file) {
        this.profile = profile;
        this.file = file;
    }

    public Profile getProfile() {
        return profile;
    }

    public File getFile() {
        return file;
    }

    public void initProfileWithRegistery(Profile profile ){

    }
}
