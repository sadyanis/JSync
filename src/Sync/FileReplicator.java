package Sync;

import java.io.File;
import java.util.List;

public interface FileReplicator {
    void replicate(List<SyncAction> actions);
}
