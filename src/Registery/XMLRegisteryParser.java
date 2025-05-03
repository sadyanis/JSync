package Registery;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilderFactory;

public class XMLRegisteryParser implements RegisteryParser {
    @Override
    public void parseRegistery(String xmlData, RegisteryBuilder builder) throws Exception {
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = factory.newDocumentBuilder();
        Document doc = docBuilder.parse(new java.io.ByteArrayInputStream(xmlData.getBytes()));
        doc.getDocumentElement().normalize();

        // Aller jusqu'au noeud <registry>
        NodeList registryNodes = doc.getElementsByTagName("registry");
        if (registryNodes.getLength() == 0) {
            throw new Exception("No <registry> element found in the XML");
        }

        Element registryElement = (Element) registryNodes.item(0);
        NodeList fileNodes = registryElement.getElementsByTagName("file");

        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

        
        for (int i = 0; i < fileNodes.getLength(); i++) {
            Element fileElement = (Element) fileNodes.item(i);

            String path = fileElement.getElementsByTagName("path").item(0).getTextContent();
            String lastModifiedStr = fileElement.getElementsByTagName("lastModified").item(0).getTextContent();
            Date lastModified = dateFormat.parse(lastModifiedStr);

            
            builder.addFile(path, lastModified);
        }
    }

}
