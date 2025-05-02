package Sync;

import Profile.Profile;

public class StdSynchronizer implements Synchronizer {
    private FileHandlerFactory LocalfileHandlerFactory;
    private FileHandlerFactory RemoteFileHandlerFactory;
    private FileHandler fileHandlerA;
    private FileHandler fileHandlerB;
    public StdSynchronizer(FileHandlerFactory fileHandlerFactoryA, FileHandlerFactory fileHandlerFactoryB ) {
    this.LocalfileHandlerFactory = fileHandlerFactoryA;
    this.RemoteFileHandlerFactory = fileHandlerFactoryB;
    }

    @Override
    public void synchronize(Profile profile) {
      // verifier si le foldeA du profile est local ou distant
       fileHandlerA = profile.isFolderALocal() ? LocalfileHandlerFactory.createFileHandler() : RemoteFileHandlerFactory.createFileHandler();
       fileHandlerB = profile.isFolderALocal() ? RemoteFileHandlerFactory.createFileHandler() : LocalfileHandlerFactory.createFileHandler();
         // recuperer les fichiers de chaque dossier
         var filesA = fileHandlerA.getFiles(profile.getPathFolderA());
         var filesB = fileHandlerB.getFiles(profile.getPathFolderB());
         // Loader le Registre depuis le fichier





    }
}
