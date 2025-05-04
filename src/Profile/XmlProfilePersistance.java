package Profile;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

        File xmlFile = Paths.get(path).toFile();
        if (!xmlFile.exists() || !xmlFile.getName().endsWith(".sync")){
            throw new IOException("Le fichier n'existe pas ou n'est pas un fichier de profil valide.");
        }
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            Element root = doc.getDocumentElement();
            String name = root.getElementsByTagName("name").item(0).getTextContent();
            String folderA = root.getElementsByTagName("folderA").item(0).getTextContent();
            String folderB = root.getElementsByTagName("folderB").item(0).getTextContent();

            Profile profile = new StdProfile(folderA, folderB,name , true, true);
            return profile;
        }catch (ParserConfigurationException | SAXException e){
            throw new IOException("Erreur lors de la lecture du fichier XML: " + e.getMessage());
        }

    }
}
