package Sync;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;

public class deleteFileAction extends SyncAction{

    public deleteFileAction(Path path, Date lastModified) {
        super(path, lastModified);

    }
    @Override
    public void apllyAction() {
        try {
            Files.deleteIfExists(path);
            System.out.println("Deleted file " + path.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
