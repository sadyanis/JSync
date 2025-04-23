package Sync;

import java.nio.file.Path;
import java.util.Date;

public abstract class SyncAction {
    protected Path path;
    protected Date lastModified;

    public SyncAction(Path path, Date lastModified) {
        this.path = path;
        this.lastModified = lastModified;
    }
    public Path getPath() {
        return path;
    }
    public Date getLastModified() {
        return lastModified;
    }
    public abstract void apllyAction();
}
