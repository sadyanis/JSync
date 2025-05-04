package Profile;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import Profile.XmlProfile;

public class XmlProfilePersistance implements ProfilePersistance{
    @Override
    public void initProfileWithRegistery(Profile profile) throws IOException {
        Path filePath = Path.of(profile.getName() + ".sync");
        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            writer.write("<syncProfile>\n");

            // Données du profil
            writer.write("  <name>" + profile.getName() + "</name>\n");
            writer.write("  <folderA>" + profile.getPathFolderA() + "</folderA>\n");
            writer.write("  <folderB>" + profile.getPathFolderB() + "</folderB>\n");
            writer.write("<isFolderALocal>" + profile.isFolderALocal() + "</isFolderALocal>\n");
            writer.write("<isFolderBLocal>" + profile.isFolderBLocal() + "</isFolderBLocal>\n");

            writer.write("</syncProfile>\n");
            // Données du registre
            writer.write("<registry>\n");
            writer.write("</registry>\n");


        }
    }

    @Override
    public void updateRegistery(List<FileComposant> files) {

    }

    @Override
    public Profile loadProfile(String path) throws IOException {
    	try {
            JAXBContext context = JAXBContext.newInstance(XmlProfile.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            XmlProfile syncProfile = (XmlProfile) unmarshaller.unmarshal(new File(path));

            // Retourner un objet anonyme implémentant Profile
            return new Profile() {
                public String getName() { return syncProfile.getName(); }
                public String getPathFolderA() { return syncProfile.getFolderA(); }
                public String getPathFolderB() { return syncProfile.getFolderB(); }
                public boolean isFolderALocal() { return false; } 
                public boolean isFolderBLocal() { return false; }
            };

        } catch (Exception e) {
            throw new IOException("Erreur lors du chargement du profil depuis " + path, e);
        }
    }
}
