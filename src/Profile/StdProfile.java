package Profile;

import java.nio.file.Path;

public class StdProfile implements Profile {
    private String folderA,folderB;
    private String Name;

    public StdProfile(String folderA, String folderB, String Name) {
        this.folderA = folderA;
        this.folderB = folderB;
        this.Name = Name;
    }


    @Override
    public String getName() {
        return this.Name;
    }

    @Override
    public String getPathFolderA() {
        return this.folderA;
    }

    @Override
    public String getPathFolderB() {
        return this.folderB;
    }
}
