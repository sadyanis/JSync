package Sync;



import Profile.FileComposant;
import Profile.Profile;
import Registery.ConcreteRegisteryBuilder;
import Registery.MainTest;
import Registery.Registery;
import Registery.RegisteryBuilder;
import Registery.RegisteryLoader;
import Registery.RegisteryParser;
import Registery.XMLRegisteryParser;
import Profile.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
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
        List<FileComposant> registry = getRegistery(profile.getName()).getFiles();
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
    
    public static Registery getRegistery(String name) {
        try {
            String resourcePath = "profiles/" + name + ".sync"; // remplacer Registery par le chemin qui v conteniur les fichies

            InputStream is = MainTest.class.getClassLoader().getResourceAsStream(resourcePath);
            if (is == null) {
                throw new RuntimeException("Fichier introuvable dans le classpath : " + resourcePath);
            }

            String data = new String(is.readAllBytes(), StandardCharsets.UTF_8);

            RegisteryBuilder builder = new ConcreteRegisteryBuilder();
            RegisteryParser parser = new XMLRegisteryParser();
            RegisteryLoader loader = new RegisteryLoader(parser, builder);

            Registery registery = loader.load(data);

            System.out.println("Fichiers trouvés : " + registery.getFiles().size());
            for (FileComposant file : registery.getFiles()) {
                System.out.println("Path: " + file.getPath() + ", Last Modified: " + file.getLastModified());
            }

            return registery;

        } catch (Exception e) {
            System.err.println("Erreur lors de la lecture ou du parsing du fichier : " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
