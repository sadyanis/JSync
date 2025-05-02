package Profile;

import java.nio.file.Path;

public interface Profile {

    public String getName();
    public String getPathFolderA();
    public String getPathFolderB();
    public boolean isFolderALocal();
    public boolean isFolderBLocal();
}
