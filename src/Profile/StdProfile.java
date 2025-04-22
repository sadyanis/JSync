package Profile;

import java.nio.file.Path;

public class StdProfile implements Profile {
    private Path folderA,folderB;
    private String Name;

    public StdProfile(Path folderA, Path folderB, String Name) {
        this.folderA = folderA;
        this.folderB = folderB;
        this.Name = Name;
    }


    @Override
    public String getName() {
        return this.Name;
    }

    @Override
    public Path getPathFolderA() {
        return this.folderA;
    }

    @Override
    public Path getPathFolderB() {
        return this.folderB;
    }
}
