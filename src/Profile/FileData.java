package Profile;

import java.nio.file.Path;
import java.time.LocalDateTime;

public class FileData  {
    private String path;
    private LocalDateTime lastModified;

    public FileData(String path, LocalDateTime lastModified) {
        this.path = path;
        this.lastModified = lastModified;
    }
    public String getPath() {
        return path;
    }
    public LocalDateTime getLastModified() {
        return lastModified;
    }

}
