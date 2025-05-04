package Sync;



import Profile.FileComposant;
import Profile.Profile;
import Profile.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StdSynchronizer implements Synchronizer {
    // pour une premiere version on va synchroniser uniquement les fichiers locals
    private FileHandlerFactory fileHandlerFactory;
    private  ProfilePersistence persistence;
    private FileHandler fileHandler;
    private FileComparator comparator;

    public StdSynchronizer(FileHandlerFactory fileHandlerFactory , ProfilePersistence persistence, FileComparator comparator) {
        this.comparator = comparator;
        this.fileHandlerFactory = fileHandlerFactory;
        this.persistence = persistence;
        this.comparator = comparator;

    }


    @Override
    public void synchronize(String ProfilePath) throws IOException, ParserConfigurationException, TransformerException, SAXException {
        //initialiser le fileHandler
        fileHandler = fileHandlerFactory.createFileHandler();
        // recuperer le profile
        Profile profile = persistence.loadProfile(ProfilePath);
        // recuprer la liste des fichiers a synchroniser
        List<FileComposant> filesA = fileHandler.getFiles(profile.getPathFolderA());
        List<FileComposant> filesB = fileHandler.getFiles(profile.getPathFolderB());
        //simuler un registre vide
        List<FileComposant> registry = new ArrayList<>();
        List<SyncAction> actions =  comparator.compare(filesA, filesB, registry);
        // executer les actions
        SyncActionVisitor visitor = new StdSyncActionVisitor( profile.getPathFolderA(), profile.getPathFolderB(),fileHandler);
        for (SyncAction action : actions) {
            action.accept(visitor);
        }
        // Liste synchronisée
        List<FileComposant> synchronizedFilesA = fileHandler.getFiles(profile.getPathFolderA());
        //
        // sauvegarder le registre
         persistence.serializeRegistry(synchronizedFilesA, ProfilePath);

       //fileHandler = fileHandlerFactory.createFileHandler();

         // recuperer les fichiers de chaque dossier
         //var filesA = fileHandler.getFiles(profile.getPathFolderA());
         //var filesB = fileHandler.getFiles(profile.getPathFolderB());
         // Loader le Registre depuis le fichier





    }
}
