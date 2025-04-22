package Profile;

import java.io.IOException;
import java.nio.file.Path;

public interface ProfileInitialiser {
    void initialiseProfilewithRegistery(Profile profile ) throws IOException;
    //Profile loadProfile(Path file);
    //Registery loadRegistery(Path file);
}
