import Profile.*;
import Sync.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, ParserConfigurationException, TransformerException, SAXException {
//    String path = "C:\\Users\\yanis\\Desktop\\test.txt";
//    String path2 = "C:\\Users\\yanis\\Desktop\\test2.txt";
//    String Names = "yanis";
//        Profile profile = new StdProfile(path,path2,Names,true,true);
//        ProfilePersistance persistance = new XmlProfilePersistance();
//        persistance.initProfileWithRegistery(profile);
        // tester la methode getFiles de FileHandler
        String path = "D:\\CoursM1\\Architecture_Logicielle\\projet\\FileSynchronizer\\yanis";
        FileHandler filehandler = new LocalFileHandler();
        List<FileComposant> Composants = filehandler.getFiles(path);
        for (FileComposant fileComposant : Composants) {
            System.out.println(fileComposant.getPath()+ " "+fileComposant.getLastModified()+" "+fileComposant.isDirectory());

                //afficher les fichiers dans le repertoire

        }


        // tester la methode copyFile de FileHandler
        String source = "D:\\CoursM1\\Architecture_Logicielle\\projet\\FileSynchronizer\\yanis\\Archi.txt";
        String destination = "D:\\CoursM1\\Architecture_Logicielle\\projet\\FileSynchronizer\\Bouzid\\test2.txt";
        FileHandler filehandler2 = new LocalFileHandler();
        filehandler2.copyFile(source,destination);
        // tester la serialisation du registre
        List<FileComposant> register = new ArrayList<>();
        register.add( new FileData("test.txt", new Date()));
        register.add(new DirectoryComposite("test", new ArrayList<>()));
        register.add(new FileData("test2.txt", new Date()));
        ProfilePersistence persistance = new XmlProfilePersistence();
        persistance.serializeRegistry(register, "D:\\CoursM1\\Architecture_Logicielle\\projet\\FileSynchronizer\\yanis.sync");
        // tester la synchronization
        StdSynchronizer synchronizer = new StdSynchronizer(new LocalFileHandlerFactory(), new XmlProfilePersistence(), new StdFileComparator());
        synchronizer.synchronize("D:\\CoursM1\\Architecture_Logicielle\\projet\\FileSynchronizer\\yanis.sync");


    }
}