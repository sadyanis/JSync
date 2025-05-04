package ProfilePersistence;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import Config.Config;
import File.FileComposant;
import Profile.Profile;
import Profile.StdProfile;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class XmlProfilePersistence implements ProfilePersistence {
    /**
     * Initialise un profil en créant un fichier .sync (XML) contenant ses données.
     *
     * @param profile Le profil à initialiser.
     * @throws IOException Si une erreur d'entrée/sortie se produit.
     */
    @Override
    public void initProfileWithRegistery(Profile profile) throws IOException {
        //Path filePath = Path.of(profile.getName() + ".sync");
    	String ProfileName = profile.getName();
        Path filePath = Path.of(Config.BASE_PATH + "/profiles/" + ProfileName + ".sync");
        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            writer.write("<syncProfile>\n");

            // Données du profil
            writer.write("  <name>" + profile.getName() + "</name>\n");
            writer.write("  <folderA>" + profile.getPathFolderA() + "</folderA>\n");
            writer.write("  <folderB>" + profile.getPathFolderB() + "</folderB>\n");
            writer.write("<isFolderALocal>" + profile.isFolderALocal() + "</isFolderALocal>\n");
            writer.write("<isFolderBLocal>" + profile.isFolderBLocal() + "</isFolderBLocal>\n");

            // Données du registre
            writer.write("<registry>\n");
            writer.write("</registry>\n");
            
            writer.write("</syncProfile>\n");


        }
    }
    /**
     * Met à jour le registre d'un profil .
     *
     * @param files La liste des composants de fichiers à sérialiser.
     * @param path  Le chemin du fichier XML à mettre à jour.
     * @throws IOException                  Si une erreur d'entrée/sortie se produit.
     * @throws TransformerException         Si une erreur de transformation XML se produit.
     * @throws ParserConfigurationException Si une erreur de configuration du parseur se produit.
     * @throws SAXException                 Si une erreur SAX se produit.
     */
    @Override
    public void updateRegistery(List<FileComposant> files) {

    }
    /**
     * Charge un profil à partir d'un fichier .sync.
     *
     * @param path Le chemin du fichier XML contenant le profil.
     * @return Le profil chargé.
     * @throws IOException Si une erreur d'entrée/sortie se produit.
     */
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
    @Override
    public void serializeRegistry(List<FileComposant> files, String path) throws IOException, TransformerException, ParserConfigurationException, SAXException {
        File xmlFile = new File(path);
        if (!xmlFile.exists()) {
            throw new FileNotFoundException("Fichier introuvable: " + path);
        }

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(xmlFile);
        doc.getDocumentElement().normalize();

        NodeList registryNodes = doc.getElementsByTagName("registry");
        Element registry;
        if (registryNodes.getLength() > 0) {
            registry = (Element) registryNodes.item(0);
            while (registry.hasChildNodes()) {
                registry.removeChild(registry.getFirstChild());
            }
        } else {
            registry = doc.createElement("registry");
            doc.getDocumentElement().appendChild(registry);
        }

        for (FileComposant fc : files) {
            appendComposantFormatted(doc, registry, fc);
        }

        // Écriture dans le fichier
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        transformer.transform(new DOMSource(doc), new StreamResult(xmlFile));
    }
    private void appendComposantFormatted(Document doc, Element parent, FileComposant fc) {
        if (fc.isDirectory()) {
            Element dirElem = doc.createElement("directory");

            Element pathElem = doc.createElement("path");
            pathElem.setTextContent(fc.getPath());
            dirElem.appendChild(pathElem);

            Element modElem = doc.createElement("lastModified");
            modElem.setTextContent(fc.getLastModified().toInstant().toString());
            dirElem.appendChild(modElem);

            Element filesElem = doc.createElement("files");
            for (FileComposant child : fc.getChildren()) {
                appendComposantFormatted(doc, filesElem, child);
            }

            dirElem.appendChild(filesElem);
            parent.appendChild(dirElem);
        } else {
            Element fileElem = doc.createElement("file");

            Element pathElem = doc.createElement("path");
            pathElem.setTextContent(fc.getPath());
            fileElem.appendChild(pathElem);

            Element modElem = doc.createElement("lastModified");
            modElem.setTextContent(fc.getLastModified().toInstant().toString());
            fileElem.appendChild(modElem);

            parent.appendChild(fileElem);
        }
    }

}
