import Profile.*;
import Profile.StdProfile;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
    String path = "C:\\Users\\yanis\\Desktop\\test.txt";
    String path2 = "C:\\Users\\yanis\\Desktop\\test2.txt";
    String Names = "yanis";
        Profile profile = new StdProfile(path,path2,Names,true,true);
        ProfilePersistance persistance = new XmlProfilePersistance();
        persistance.initProfileWithRegistery(profile);
    }
}