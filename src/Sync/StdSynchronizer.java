package Sync;

import Profile.Profile;
import Profile.ProfilePersistance;

public class StdSynchronizer implements Synchronizer {
    // pour une premiere version on va synchroniser uniquement les fichiers locals
    private FileHandlerFactory fileHandlerFactory;

    private FileHandler fileHandler;
    
    private ProfilePersistance profilePersistance;

    public StdSynchronizer(FileHandlerFactory fileHandlerFactory ) {
    this.fileHandlerFactory = fileHandlerFactory;

    }

    @Override
    public void synchronize(Profile profile) {
      //
       fileHandler = fileHandlerFactory.createFileHandler();

         // recuperer les fichiers de chaque dossier
         var filesA = fileHandler.getFiles(profile.getPathFolderA());
         var filesB = fileHandler.getFiles(profile.getPathFolderB());
         // Loader le Registre depuis le fichier
    }
    
    
}
