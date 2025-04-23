package Sync;

import java.nio.file.Path;
import java.util.List;
import Profile.FileData;
import Profile.Profile;
public interface FileManager {
    List<FileData> readFiles(Path directoy);
}
