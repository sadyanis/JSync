package Sync;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.nio.file.Path;

public class copyFileAction extends SyncAction {
    private Path source;
    public copyFileAction(Path source , Path target , Date lastModified ) {
        super(target, lastModified);
        this.source = source;
    }
    @Override
    public void apllyAction() {
        // à implementer
        try {
            Files.copy(source, path, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Ficher mit à jour");
            //SyncLogger.getInstance().log("Fichier supprimé : " + describe());

        } catch (IOException e) {
            //SyncLogger.getInstance().log("Erreur de suppression : " + e.getMessage());

        }

    }
}
