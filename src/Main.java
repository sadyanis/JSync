import Profile.*;
import Profile.StdProfile;
import Sync.FileHandler;
import Sync.LocalFileHandler;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
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
    }
}