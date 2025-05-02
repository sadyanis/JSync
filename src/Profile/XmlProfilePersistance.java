package Profile;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

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
        //retourner un objet profile depuis le fichier .sync qui contient le profile en xml

    return null;
    }
}
